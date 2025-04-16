
package schoolcourseenrolmentsystem;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Instructor extends User<Instructor> {
    // Constructor
    public Instructor(String name, String id, String password, String email, String phoneNumber,
            User.Role role, String address, List<Student> enrolledStudents) {
        super(name, id, password, email, phoneNumber, role, address);
        // If you store enrolled students, save them here
    }

    // Methods

    @Override
    public Instructor login(List<Instructor> instructors, String id, String password) {
        for (Instructor i : instructors) {
            if (i.getId().equals(id) && i.getPassword().equals(password)) {
                System.out.println("Instructor " + i.getName() + " logged in.");
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

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null || getClass() != obj.getClass())
        return false;
    Instructor other = (Instructor) obj;
    return this.getId() != null && this.getId().equals(other.getId());
}

@Override
public int hashCode() {
    return Objects.hash(getId());
}


    public void viewEnrolledStudents(Instructor instructor, Set<Course> courses) {
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
        // The ! means false which will activate if the upper block returns false
        // instead of true.
        if (!enrolledCourse) {
            System.out.println("Instructor " + instructor.getName() + " is not assigned to any courses.");
        }
    }

    public void gradeAssignments() {

    }

    public void updateCourseInfo(String courseCode, Set<Course> courses, String newSchedule, String newDescription) {
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
            // We must validate if the instructor is even assigned to the course they want
            // to update
            Instructor assignedInstructor = courseToUpdate.getInstructor();

            if (assignedInstructor != null && assignedInstructor.getId().equals(this.getId())) {
                courseToUpdate.setSchedule(newSchedule);
                courseToUpdate.setDescription(newDescription);

                System.out.println("Instructor " + assignedInstructor.getName() + "'s"
                        + " course information updated for " + courseToUpdate.getCourseName());
            } // This is just in case if the course is assigned to a different instructor.
              // Its not empty but it also doesnt match the targetId.
            else if (assignedInstructor != null) {
                System.out
                        .println(assignedInstructor.getName() + " with the ID number: " + assignedInstructor.getId()
                                + " is not assigned to " + courseToUpdate.getCourseName() + ".");
            } // You don't see it but its basically (assignedInstructor == null);
            else {
                System.out.println("No instructor assigned to " + courseToUpdate.getCourseName() + ".");
            }
        }
    }

    public void updateInstructorPersonalInfo(String newPassword, String newEmail,
            String newPhoneNumber, String newAddress) {
        // No need to check if they match because instructors are already logged in.
        setPassword(newPassword);
        setEmail(newEmail);
        setPhoneNumber(newPhoneNumber);
        setAddress(newAddress);
        System.out.println("Instructor " + getName() + "'s information updated.");
    }
}