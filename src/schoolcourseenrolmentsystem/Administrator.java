
package schoolcourseenrolmentsystem;

import java.util.*;
import schoolcourseenrolmentsystem.Course.EnrollmentStatusEnum;

public class Administrator extends User<Administrator> {
    // Constructors
    // We must define the default constructor in case we define any other custom
    // constructors
    public Administrator() {
    }

    public Administrator(String name, String id, String password, String email, String phoneNumber, String address) {
        super(name, id, password, email, phoneNumber, User.Role.ADMIN, address);
    }

    // Methods

    // Login/logout & role
    @Override
    public Administrator login(List<Administrator> administrators, String id, String password) {
        Administrator returnAdmin = null;
        for (Administrator administrator : administrators) {
            if (administrator.getId().equals(id) && administrator.getPassword().equals(password)) {
                System.out.println("Administrator " + administrator.getName() + " logged in.");
                returnAdmin = administrator;
                break;
            }
            System.out.println("No admin record was found with the ID and password provided.");
        }

        return returnAdmin;
    }

    @Override
    public String logout(Administrator administrators) {
        return ("Administrator " + administrators.getName() + " logged out.");
    }

    // 1. Manage student & instructor accounts
    // Add and remove users

    public boolean removeStudent(List<Student> students, String targetId) {
        // Loop used to iterate over all the students.
        // An Iterator keeps track of the current position safely, and when you call
        // remove(), it tells the collection to safely remove the current element
        // without invalidating the loop.
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId().equals(targetId)) {
                iterator.remove();
                System.out.println(s.getName() + " with the ID: " + s.getId() + " has been successfully removed");
                return true;
            }
        }
        System.out.println(targetId + " is not found.");
        return false;
    }

    public boolean removeInstructor(List<Instructor> instructors, String targetId) {
        // An Iterator keeps track of the current position safely, and when you call
        // remove(), it tells the collection to safely remove the current element
        // without invalidating the loop.

        // Loop used to iterate over all the instructors
        Iterator<Instructor> iterator = instructors.iterator();
        while (iterator.hasNext()) {
            Instructor i = iterator.next();
            if (i.getId().equals(targetId)) {
                iterator.remove();
                System.out.println(i.getName() + " with the ID: " + i.getId() + " removed successfully.");
                return true;
            }
        }
        System.out.println("Instructor with the ID: " + targetId + " not found.");
        return false;
    }

    public void addInstructor(Instructor i, List<Instructor> instructors) {
        instructors.add(i);
        System.out.println("Instructor added: " + i.getName() + " with the ID: " + i.getId());
    }

    public void addStudent(Student s, List<Student> students) {
        students.add(s);
        System.out.println("Student added: " + s.getName() + " with the ID: " + s.getId());

    }

    // Update user info.

    public void updateStudent(String targetId, List<Student> students, String newName, String newId, String newPassword,
            String newEmail,
            String newPhoneNumber, User.Role newRole, String newAddress) {

        Student updateStudentInfo = null;
        for (Student s : students) {
            if (s.getId().equals(targetId)) {
                updateStudentInfo = s;
                break;

            }
        }
        if (updateStudentInfo != null) {
            updateStudentInfo.setName(newName);
            updateStudentInfo.setPassword(newPassword);
            // updateStudentInfo.setId(newId);
            updateStudentInfo.setEmail(newEmail);
            updateStudentInfo.setPhoneNumber(newPhoneNumber);
            updateStudentInfo.setRole(newRole);
            updateStudentInfo.setAddress(newAddress);
        } else {
            System.out.println("Student with the ID " + targetId + " not found.");
        }
    }

    public void updateInstructor(String targetId, List<Instructor> instructors, String newName,
            String newPassword,
            String newEmail,
            String newPhoneNumber, User.Role newRole, String newAddress) {

        Instructor updateInstructorInfo = null;
        for (Instructor i : instructors) {
            if (i.getId().equals(targetId)) {
                updateInstructorInfo = i;
                break;
            }
        }
        if (updateInstructorInfo != null) {
            updateInstructorInfo.setName(newName);
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
        if (courses.add(c)) {
            System.out.println("Course added: (" + c.getCourseName() + ") (" + c.getCourseCode() + ") succesfully.");
        } else {
            System.out.println("Course already exists: " + c.getCourseName());
        }
    }

    public void assignInstructor(Course c, Instructor i) {
        if (c.getInstructor() == null) {
            c.setInstructor(i);
            System.out.println("Instructor " + i.getName() + " is assigned to " + c.getCourseName());
        } else if (c.getInstructor().equals(i)) {
            System.out.println("Instructor " + i.getName() + " is already assigned to " + c.getCourseName());
        } else {
            System.out.println("Course already has a different instructor assigned.");
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
            System.out.println(c.getCourseName() + " still has available capacity.");
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