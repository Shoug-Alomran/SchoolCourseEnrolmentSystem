
package schoolcourseenrolmentsystem;

import java.util.List;

public class Instructor extends User<Instructor> {
    // Attributes
    List<Student> enrolledStudents;

    // Constructor
    public Instructor(String name, String id, String password, String email, String phoneNumber, String role,
            String address) {
        super(name, id, password, email, phoneNumber, role, address);

    }

    // Methods
    @Override
    public String getRole() {
        return "Instructor"; // Return the role for Instructor
    }

    @Override
    public Instructor login(List<Instructor> instructors, String id, String password) {
        Instructor instructor = null;
        for (Instructor i : instructors) {
            if (i.getId().equals(id) && i.getPassword().equals(password)) {
                instructor = i;
                System.out.println("Instructor " + getName() + " logged in.");

            }
        }
        if (instructor == null) {
            System.out.println("No instructor record was found with the ID and password provided.");
            return null;
        }
        return instructor;
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
                if (enrolledStudents.isEmpty()) {
                    // Will return true if its indeed empty whuch will trigger this message.
                    System.out.println("No students enrolled in this course.");
                } else {
                    // This part will be triggered if the code above it is false.
                    for (Student s : enrolledStudents) {
                        System.out.println("Student: " + s.getName() + ", ID: " + s.getId()
                                + ", Email: " + s.getEmail() + ", Phone number: " + s.getPhoneNumber() + ".");
                    }
                }
            }
        }
        // The ! means false which will activate if the upper block returns false
        // instead of true.
        if (!enrolledCourse) {
            System.out.println("Instructor " + instructor.getName() + " is not assigned to any courses.");
        }
    }

    public void gradeAssignments() {

    }

    public void updateCourseInfo(String courseCode, List<Course> courses, String newSchedule, String newDescription) {
        // We must make sure that the course actually even exists.
        Course courseFound = null;
        // This following block is created for when the course actually exists.
        for (Course c : courses) {
            if (c.getCourseCode().equals(courseCode)) {
                courseFound = c;
                break;
            }
        }
        if (courseFound != null) {
            // We must validate if the instructor is even assigned to the course they want
            // to update
            Instructor assignedInstructor = courseFound.getInstructor();

            if (assignedInstructor != null && assignedInstructor.getId().equals(this.getId())) {
                courseFound.setSchedule(newSchedule);
                courseFound.setDescription(newDescription);

                System.out.println("Instructor " + assignedInstructor.getName() + "'s"
                        + " course information updated for " + courseFound.getCourseName());
            } // This is just in case if the course is assigned to a different instructor.
              // Its not empty but it also doesnt match the targetId.
            else if (assignedInstructor != null) {
                System.out
                        .println(assignedInstructor.getName() + " with the ID number: " + assignedInstructor.getId()
                                + " is not assigned to " + courseFound.getCourseName() + ".");
            } // You don't see it but its basically (assignedInstructor == null);
            else {
                System.out.println("No instructor assigned to " + courseFound.getCourseName() + ".");
            }
        } else {
            System.out.println("Course with code " + courseCode + " not found.");
        }
    }

    public void updateInstructorPersonalInfo(List<Instructor> instructors, String targetId, String newPassword, String newEmail,
            String newPhoneNumber,
            String newAddress) {
        Instructor instructorFound = null;
        for (Instructor i : instructors) {
            if (i.getId().equals(targetId)) {
                instructorFound = i;
                break;
            }
        }
        if (instructorFound == null) {
            System.out.println("Instructor with ID " + targetId + " not found.");
            return;
        }
        // If the instructor is found, update their information
        if (instructorFound != null) {
            instructorFound.setPassword(newPassword);
            instructorFound.setEmail(newEmail);
            instructorFound.setPhoneNumber(newPhoneNumber);
            instructorFound.setAddress(newAddress);
        }
    }
}
