
package schoolcourseenrolmentsystem;

import java.util.*;

public class Student extends User<Student> {
    // Attributes
    private int creditLimit;
    private List<Course> enrolledCourses;

    // Constructor
    public Student(String name, String id, String password, String email, String phoneNumber, String role,
            String address, int creditLimit, List<Course> enrolledCourses) {
        super(name, id, password, email, phoneNumber, Role.STUDENT, address);
        setCreditLimit(creditLimit);
        // If the list is null, create a new one. Fixes NullPointerException.
        if (enrolledCourses != null) {
            this.enrolledCourses = enrolledCourses;
        } else {
            this.enrolledCourses = new ArrayList<>();
        }
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
    public Student login(List<Student> students, String id, String password) {
        for (Student s : students) {
            if (s.getId().equals(id) && s.getPassword().equals(password)) {
                System.out.println("Student " + s.getName() + " logged in.");
                return s;
            }
        }
        System.out.println("No student record was found with the ID and password provided.");
        return null;
    }

    @Override
    public String logout(Student student) {
        return ("Student " + student.getName() + " logged out.");
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
            // Needed inorder for the course to know who enrolled.
            c.getEnrolledStudents().add(this);
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

    public void viewCreditLimit(List<Student> students) {
        for (Student s : students) {
            System.out.println(s.getName() + "'s credit limit is: " + s.getCreditLimit());

            // (condition) ? (value if true) : (value if false);
            int enrolledHours = (s.getEnrolledCourses() != null) ? s.enrolledInHours() : 0;
            System.out.println("Remaining credit limit is: " + (s.getCreditLimit() - enrolledHours) + " hours.");
        }
    }

    public int enrolledInHours() {
        int total = 0;
        if (enrolledCourses != null) {
            for (Course c : enrolledCourses) {
                total += c.getCreditHours();
            }
        }
        return total;
    }

    public void viewEnrolledCourses() {
        // In the main I can write Student1.viewEnrolledCourses()
        if (enrolledCourses.isEmpty()) {
            System.out.println(getName() + " is not enrolled in any courses.");
        } else {
            System.out.println(getName() + " is enrolled in: ");
            int index = 1;
            for (Course c : enrolledCourses) {
                System.out.println(
                        index + "- (" + c.getCourseName() + ") (" + c.getCourseCode() + ") ( " + c.getCreditHours()
                                + "). The schedule is: "
                                + c.getSchedule() + ".");
                index++;
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
                System.out.println("- (Course name: " + c.getCourseName() + ") (Course code: " + c.getCourseCode()
                        + ") (Course schedule: " + c.getSchedule() + ") (Course instructor: " + c.getInstructor()
                        + ") (Course capacity: " + c.getCapacity() + ").");
            }
        }
        if (!avaliableCourses) {
            System.out.println("No courses are avaliable.");
        }
    }

    public void updatePersonalInfo(List<Student> students, String targetId, String newPassword, String newEmail,
            String newPhoneNumber,
            String newAddress) {
        // Initialize a variable to store the matched student
        Student studentFound = null;
        /// Search for the student with the given ID in the list
        for (Student s : students) {
            if (s.getId().equals(targetId)) {
                studentFound = s;
                break;
            }
        }
        // If the student is found, update their information
        if (studentFound != null) {
            studentFound.setPassword(newPassword);
            studentFound.setEmail(newEmail);
            studentFound.setPhoneNumber(newPhoneNumber);
            studentFound.setAddress(newAddress);
            System.out.println("Student found succesfully.");
        } else {
            System.out.println("Student with ID " + targetId + " not found.");
        }
    }
}