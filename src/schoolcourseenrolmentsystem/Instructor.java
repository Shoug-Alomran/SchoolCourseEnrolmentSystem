
package schoolcourseenrolmentsystem;

import java.util.*;

public class Instructor extends User<Instructor> {
    // Constructor
    public Instructor() {
    }

    public Instructor(String name, String id, String password, String email, String phoneNumber,
            String address, List<Student> enrolledStudents) {
        super(name, id, password, email, phoneNumber, Role.INSTRUCTOR, address);
    }

    // Methods
    @Override
    public Instructor login(List<Instructor> instructors, String id, String password) {
        for (Instructor i : instructors) {
            if (i.getId().equals(id) && i.getPassword().equals(password)) {
                System.out.println("Instructor " + i.getName() + " logged in.\n");
                return i;
            }
        }
        System.out.println("No instructor record was found with the ID and password provided.");
        return null;
    }

    @Override
    public String logout(Instructor instructors) {
        return ("Instructor " + instructors.getName() + " logged out.");
    }

    public void viewEnrolledStudents(Instructor instructor, List<Course> courses) {
        boolean enrolledCourse = false;
        // Look through all the courses
        for (Course c : courses) {
            // Make sure that the instructor is actually assigned to the course
            if (c.getInstructor() != null && c.getInstructor().getId().equals(instructor.getId())) {
                enrolledCourse = true;
                // The instructor might teach multiple courses
                System.out.println("\n(Course: " + c.getCourseName() + ") (Code: " + c.getCourseCode() + ").");
                c.getEnrolledStudents();

                // Make sure there are actually students enrolled in the course.
                // Update the list.
                List<Student> courseStudents = c.getEnrolledStudents();
                if (courseStudents == null || courseStudents.isEmpty()) {
                    System.out.println("No students enrolled in this course.");
                } else {
                    for (Student s : courseStudents) {
                        System.out.println("Student: " + s.getName() + ", ID: " + s.getId()
                                + ", Email: " + s.getEmail() + ", Phone number: " + s.getPhoneNumber() + ".");
                    }
                }
            }
        }
        if (!enrolledCourse) {
            System.out.println("Instructor " + instructor.getName() + " is not assigned to any courses.");
        }
    }

    public void gradeStudent(List<Assessment> grades, List<Course> courses) {
        Scanner input = new Scanner(System.in);

        try {
            // Step 1: Show instructor's courses
            List<Course> instructorCourses = new ArrayList<>();
            System.out.println("\nCourses you're assigned to:");
            for (Course c : courses) {
                if (c.getInstructor() != null && c.getInstructor().getId().equals(this.getId())) {
                    instructorCourses.add(c);
                    System.out.println(
                            (instructorCourses.size()) + ". " + c.getCourseName() + " (" + c.getCourseCode() + ")");
                }
            }
            if (instructorCourses.isEmpty()) {
                System.out.println("You're not assigned to any courses.");
                return;
            }

            int courseChoice = Helpers.getSafeIntInput("Select a course to grade: ");
            if (courseChoice < 1 || courseChoice > instructorCourses.size()) {
                System.out.println("Invalid selection.");
                return;
            }
            Course selectedCourse = instructorCourses.get(courseChoice - 1);

            // Step 2: Select student
            List<Student> enrolled = selectedCourse.getEnrolledStudents();
            if (enrolled.isEmpty()) {
                System.out.println("No students enrolled in this course.");
                return;
            }

            System.out.println("\nStudents in this course:");
            for (int i = 0; i < enrolled.size(); i++) {
                System.out.println((i + 1) + ". " + enrolled.get(i).getName() + " (ID: " + enrolled.get(i).getId() + ")");
            }

            int studentChoice = Helpers.getSafeIntInput("Select a student to grade: ");
            if (studentChoice < 1 || studentChoice > enrolled.size()) {
                System.out.println("Invalid student selection.");
                return;
            }
            Student selectedStudent = enrolled.get(studentChoice - 1);

            // Step 3: Choose exam type
            Assessment.ExamType[] examTypes = Assessment.ExamType.values();
            System.out.println("\nSelect the assessment type to grade:");
            for (int i = 0; i < examTypes.length; i++) {
                System.out.println((i + 1) + ". " + examTypes[i]);
            }

            int examChoice = Helpers.getSafeIntInput("Choice: ");
            if (examChoice < 1 || examChoice > examTypes.length) {
                System.out.println("Invalid selection.");
                return;
            }
            Assessment.ExamType selectedExamType = examTypes[examChoice - 1];

            // Step 4: Enter score
            double score;
            while (true) {
                System.out.print("Enter score (0–100): ");
                try {
                    score = input.nextDouble();
                    input.nextLine(); // Buffer
                    if (score < 0 || score > 100) {
                        System.out.println("Score must be between 0 and 100.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    input.nextLine(); // Clear input
                }
            }

            // Step 5: Assign or update grade
            boolean updated = false;
            for (Assessment g : grades) {
                if (g.getStudentId().equals(selectedStudent.getId())
                        && g.getCourseCode().equals(selectedCourse.getCourseCode())
                        && g.getExamType().equals(selectedExamType)) {
                    g.setScore(score);
                    System.out.println("Grade updated successfully.");
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                Assessment newAssessment = new Assessment(selectedStudent.getId(), selectedCourse.getCourseCode(),
                        selectedExamType, score, selectedExamType.toString());
                grades.add(newAssessment);
                System.out.println("Grade recorded successfully.");
            }
          
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
        }
    }

    public void updateCourseInfo(String courseCode, List<Course> courses, String newSchedule, String newDescription) {
        // We must make sure that the course actually even exists.
        Course courseNotFound = new Course(courseCode, courseCode, newSchedule, newDescription, null, null, 0, null, 0);
        // This following block is created for when the course actually exists.
        if (!courses.contains(courseNotFound)) {
            System.out.println("Course with code " + courseCode + " not found.");
            return;
        }
        // Now find the actual Course object
        Course courseToUpdate = null;
        for (Course c : courses) {
            if (c.getCourseCode().equals(courseCode)) {
                courseToUpdate = c;
                break;
            }
        }
        if (courseToUpdate == null) {
            System.out.println("Course with code " + courseCode + " not found.");
            return;
        }
        if (courseToUpdate != null) {
            Instructor assignedInstructor = courseToUpdate.getInstructor();

            if (assignedInstructor != null && assignedInstructor.getId().equals(this.getId())) {
                courseToUpdate.setSchedule(newSchedule);
                courseToUpdate.setDescription(newDescription);

                System.out.println("Instructor " + assignedInstructor.getName() + "'s"
                        + " course information updated for " + courseToUpdate.getCourseName());
            } else if (assignedInstructor != null) {
                System.out
                        .println(assignedInstructor.getName() + " with the ID number: " + assignedInstructor.getId()
                                + " is not assigned to " + courseToUpdate.getCourseName() + ".");
            }
            // You don't see it but its basically (assignedInstructor == null);
            else {
                System.out.println("No instructor assigned to " + courseToUpdate.getCourseName() + ".");
            }
        }
    }

    public void updateInstructorPersonalInfo(String newPassword, String newEmail, String newPhoneNumber,
            String newAddress) {
        // No need to check if they match because instructors are already logged in.
        setPassword(newPassword);
        setEmail(newEmail);
        setPhoneNumber(newPhoneNumber);
        setAddress(newAddress);
        System.out.println("Instructor " + getName() + "'s information updated.");
    }

    @Override
    public String toString() {
        return String.format("ID: %s Name: %s", this.getId(), this.getName());
    }

}