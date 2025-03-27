
package schoolcourseenrolmentsystem;

import java.util.*;

import schoolcourseenrolmentsystem.Course.EnrollmentStatusEnum;

public class Administrator extends User {
    // Constructor
    public Administrator(String name, String id, String password, String email, String phoneNumber, String role,
            String address) {
        super(name, id, password, email, phoneNumber, role, address);
    }

    public List<Instructor> getInstructors() {
        return getInstructors();
    }

    // Methods

    // Login/logout & role
    @Override
    public void login() {
        System.out.println("Administrator " + getName() + " logged in.");
    }

    @Override
    public void logout() {
        System.out.println("Administrator " + getName() + " logged out.");
    }

    @Override
    public String getRole() {
        return "Administrator"; // Return the role for Administrator
    }

    // 1. Manage student & instructor accounts
    // Add and remove users

    public boolean removeStudent(List<Student> students, String targetId) {
        // Loop used to iterate over all the students
        for (Student s : students) {
            if (s.getId().equals(targetId)) {
                students.remove(s);
                System.out.println(s.getName() + " with the ID: " + s.getId() + " has been successfully removed");
                return true;
            }
        }
        System.out.println(targetId + " is not found.");
        return false;

    }

    public boolean removeInstructor(List<Instructor> instructors, String targetId) {
        // Loop used to iterate over all the instructors
        for (Instructor i : instructors) {
            if (i.getId().equals(targetId)) {
                instructors.remove(i);
                System.out.println(i.getName() + " with the ID: " + i.getId() + " removed successfully.");
                return true;
            }
        }

        System.out.println("Instructor witht the ID: " + targetId + " not found.");
        return false;

    }

    public void addInstructor(Instructor i, List<Instructor> instructors) {
        if (!instructors.contains(i)) {
            instructors.add(i);
            System.out.println("User added: " + i.getName());
        } else {
            System.out.println("User already exists: " + i.getName());
        }
    }

    public void addStudent(Student s, List<Student> students) {
        if (!students.contains(s)) {
            students.add(s);
            System.out.println("User added: " + ((User) s).getName());
        } else {
            System.out.println("User already exists: " + ((User) s).getName());
        }
    }

    // Update user info.

    public void updateStudent(String targetId, List<Student> students, String newName, String newId, String newPassword,
            String newEmail,
            String newPhoneNumber, String newRole, String newAddress) {

        Student updateStudentInfo = null;
        for (Student s : students) {
            if (s.getId().equals(targetId)) {
                updateStudentInfo = s;
                break;

            }
        }
        if (updateStudentInfo != null) {

            updateStudentInfo.setName(newName);
            updateStudentInfo.setId(newId);
            updateStudentInfo.setPassword(newPassword);
            updateStudentInfo.setEmail(newEmail);
            updateStudentInfo.setPhoneNumber(newPhoneNumber);
            updateStudentInfo.setRole(newRole);
            updateStudentInfo.setAddress(newAddress);
        } else {
            System.out.println("Student with the ID " + targetId + " not found.");
        }
    }

    public void updateInstructor(String targetId, List<Instructor> instructors, String newName, String newId,
            String newPassword,
            String newEmail,
            String newPhoneNumber, String newRole, String newAddress) {

        Instructor updateInstructorInfo = null;
        for (Instructor i : instructors) {
            if (i.getId().equals(targetId)) {
                updateInstructorInfo = i;
                break;
            }
        }
        if (updateInstructorInfo != null) {
            updateInstructorInfo.setName(newName);
            updateInstructorInfo.setId(newId);
            updateInstructorInfo.setPassword(newPassword);
            updateInstructorInfo.setEmail(newEmail);
            updateInstructorInfo.setPhoneNumber(newPhoneNumber);
            updateInstructorInfo.setRole(newRole);
            updateInstructorInfo.setAddress(newAddress);
        } else {
            System.out.println("Instructor with the ID " + targetId + " not found.");
        }
    }

    // 2. Create & manage courses.
    // Create a new course
    public void addCourse(List<Course> courses, Course c) {
        if (!courses.contains(c)) {
            courses.add(c);
            System.out.println("Course added: (" + c.getCourseName() + ") (" + c.getCourseCode() + ")");
        } else {
            System.out.println("Course already exists: " + c.getCourseName());
        }
    }

    public void assignInstructor(Course c, Instructor i) {
        if (c.getInstructor().equals(i) && c.getInstructor() != null) {
            System.out.println("Instructor " + i.getName() + " is already assigned to " + c.getCourseName());
        } else if (!c.getInstructor().equals(i) && c.getInstructor() == null) {
            c.setInstructor(i);
            System.out.println("Instructor:" + i.getName() + " is assigned to " + c.getCourseName());

        }
    }

    // Closes course when capacity is full or closed
    public void closeCourse(Course c, List<Course> courses) {
        if (courses.contains(c)) {
            if (c.getEnrolledStudents().size() >= c.getCapacity()) {
                System.out.println(c.getCourseName() + " is at full capacity.");
                c.setEnrollmentStatus(EnrollmentStatusEnum.Closed);
            } else if (c.getEnrollmentStatus() == Course.EnrollmentStatusEnum.Closed) {
                System.out.println(c.getCourseName() + " enrollment status is closed.");
            }
        } else if (!courses.contains(c)) {
            System.out.println(c.getCourseName() + " not found.");
        } else {
            System.out.println(c.getCourseName() + " still has avaliable capacity.");
        }
    }

    // 3. View enrollment statistics
    public void enrollmentStatistics(List<Student> enrolledStudents, List<Course> enrolledCourses) {

    }

    // 4. Generate reports.
    // Administrators can generate reports on student enrollment patterns,
    // course popularity, and other statistics for analysis
    public void generateReports() {

    }
}
