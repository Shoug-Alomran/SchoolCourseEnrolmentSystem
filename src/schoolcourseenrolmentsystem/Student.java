
package schoolcourseenrolmentsystem;

import java.util.*;

public class Student extends User {
    // Attributes
    private int creditLimit;
    private List<Course> enrolledCourses;

    // Constructor
    public Student(String name, String id, String password, String email, String phoneNumber, String role,
            String address, int creditLimit, Course enrolledCourses) {
        super(name, id, password, email, phoneNumber, role, address);
        setCreditLimit(creditLimit);
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    // Setter & Getter
    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    // Methods
    @Override
    public String getRole() {
        return "Student"; // Return the role for Student
    }

    @Override
    public void login() {
        System.out.println("Student " + getName() + " logged in.");
    }

    @Override
    public void logout() {
        System.out.println("Student " + getName() + " logged out.");
    }

    // Count the hours of the courses already enrolled
    public int totalCreditLimit() {
        int total = 0;
        // This loop is to count how many courses and their toal hours
        for (Course c : enrolledCourses) {
            total += c.getCreditHours();
        }
        return total;
    }

    public void enroll_In_Course(Course c) {
        // 1. make sure credit limit is not excited and that the capacity is avaliable
        if (enrolledCourses.contains(c)) {
            System.out.println("Already enrolled in (" + c.getCourseName() + ") (" + c.getCourseCode() + ").");
        } else if (c.isFull()) {
            System.out.println("Cannot enroll in " + c.getCourseName() + " — course is full.");
        } // Check if the enrollment status is open or not
        else if (c.getEnrollmentStatus() == Course.EnrollmentStatusEnum.Closed) {
            System.out.println("Enrollment status is closed.");
        } // Previously enrolled hours + new hours
        else if (totalCreditLimit() + c.getCreditHours() > creditLimit) {
            System.out.println("Cannot enroll in " + c.getCourseName() + " — credit limit exceeded.");
        } else {
            this.enrolledCourses.add(c);
            System.out.println(getName() + " enrolled successfully");
        }
    }

    public void dropCourse(Course c) {
        if (!enrolledCourses.contains(c)) {
            System.out.println(
                    getName() + " is not enrolled in (" + c.getCourseName() + ") (" + c.getCourseCode() + ").");
        } else if (enrolledCourses.contains(c) && c.getEnrollmentStatus() == Course.EnrollmentStatusEnum.Closed) {
            System.out.println("Drop period is closed.");
        } else if (enrolledCourses.contains(c) && c.getEnrollmentStatus() == Course.EnrollmentStatusEnum.Open) {
            this.enrolledCourses.remove(c);
            System.out.println(
                    getName() + " has successfully dropped (" + c.getCourseName() + ") (" + c.getCourseCode() + ").");
        }
    }

    // public void enroll_In_Courses(List<Course> enrolledCourses, Student s, String
    // targetId, String CourseCode) {
    // if (s.getId().equals(targetId)) {
    // }
    // }

    // public void dropCourse(List<Course> enrolledCourses, List<Student>
    // enrolledStudents) {
    // }

    public void viewCreditLimit(List<Student> students) {
        for (Student s : students) {
            System.out.println(s.getName() + "'s credit limit is: " + s.getCreditLimit());
        }
    }

    public void viewEnrolledCourses() {
        // In the main I can write Student1.viewEnrolledCourses()
        if (enrolledCourses.isEmpty()) {
            System.out.println(getName() + " is not enrolled in any courses.");
        } else {
            System.out.println(getName() + " is enrolled in: ");
            for (Course c : enrolledCourses) {
                System.out.println("- (" + c.getCourseName() + ") (" + c.getCourseCode() + ") ( " + c.getCreditHours()
                        + "). The schedule is: "
                        + c.getSchedule() + ".");
            }
        }
    }

    public void viewGrades() {

    }

    public void viewAvailableCourses(List<Course> courses) {
        boolean avaliableCourses = false;
        System.out.println("Available courses are: ");
        for (Course c : courses) {
            if (c.isFull() == false && c.getEnrollmentStatus() == Course.EnrollmentStatusEnum.Open) {
                avaliableCourses = true;
                System.out.println("- (" + c.getCourseName() + ") (" + c.getCourseCode()
                        + ") (" + c.getSchedule() + ") (" + c.getInstructor() + ") (" + c.getCapacity() + ").");
            }
        }
        if (!avaliableCourses) {
            System.out.println("No courses are avaliable.");
        }
    }

    public void updatePersonalInfo(List<Student> students, String targetId, String newPassword, String newEmail,
            String newPhoneNumber,
            String newAddress) {
        // We must make sure that the students actually even exists.
        boolean studentFound = false;
        // This following block is created for when the student actually exists.
        for (Student s : students) {
            if (s.getId().equals(targetId) || s != null) {
                studentFound = true;
                s.setPassword(newPassword);
                s.setEmail(newEmail);
                s.setPhoneNumber(newPhoneNumber);
                s.setAddress(newAddress);

                if (!studentFound) {
                    System.out.println("Student with ID " + targetId + " not found.");
                }
            }
        }
    }
}
