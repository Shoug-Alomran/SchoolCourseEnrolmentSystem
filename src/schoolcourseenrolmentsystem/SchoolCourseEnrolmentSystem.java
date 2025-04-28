
package schoolcourseenrolmentsystem;

import java.util.*;

public class SchoolCourseEnrolmentSystem {

    // We placed the lists here in order to pass them only once instead of having
    // many lists.
    // Buffer used after each input.nextInt();

    // MAIN LISTS
    private static List<Instructor> instructors = new ArrayList<>();
    private static List<Administrator> administrators = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();// List to store enrolled students
    private static List<Course> courses = new ArrayList<>(); // List to store courses

    public static void main(String[] args) {
        // I'm putting them outside the switch because multiple cases use them so this
        // helps my code be less dense.
        Course newCourse = null;
        boolean exit = false;
        Scanner input = new Scanner(System.in);

        Administrator defaultAdmin = new Administrator("Shoug", "1234567890", "Defualt123", "S.Alomran@gmail.com",
                "0531110904", "Administrator", "Qirawan district");
        administrators.add(defaultAdmin);

        while (!exit) {
            System.out.println("\nWelcome to School Course Enrolment System!");
            System.out.println("Enter your role from the current options: (Student/Instructor/Admin)");
            String role = input.next();

            // Menu
            switch (role.toLowerCase()) {
                case "student":
                    // Login my happen first
                    System.out.println("\nPlease enter your ID and Password");
                    String studentID = input.next();
                    String studentPassword = input.nextLine();
                    Student student = null;
                    boolean studentExit = false;

                    for (Student s : students) {
                        if (s.getId().equals(studentID) && s.getPassword().equals(studentPassword)) {
                            student = s;
                            student.login();
                            break;
                        }
                    }
                    if (student == null) {
                        System.out.println("No student record was found with the ID and password provided.");
                        break;
                    }

                    while (!studentExit) {
                        System.out.println("\nEnter the number for the choice you want.");
                        System.out.println("1. View avaliable courses.");
                        System.out.println("2. Enroll in course.");
                        System.out.println("3. View enrolled courses.");
                        System.out.println("4. Drop course.");
                        System.out.println("5. View grades.");
                        System.out.println("6. Update personal information.");
                        System.out.println("7. Logout.");
                        int studentChoice = input.nextInt();
                        input.nextLine(); // Buffer

                        switch (studentChoice) {
                            case 1:
                                student.viewAvailableCourses(courses);
                                break;
                            case 2:
                                System.out.println("Courses avaliable to enroll in: ");
                                // Sub-List to the main
                                List<Course> availableCourses = new ArrayList<>();
                                int index = 1;

                                for (Course c : courses) {
                                    // Validate that the course is not full, not closed, and doesnt already contain
                                    // the same course
                                    if (!c.isFull() && c.getEnrollmentStatus() == Course.EnrollmentStatusEnum.Open
                                            && !student.getEnrolledCourses().contains(c)) {
                                        // List the avaliable courses
                                        System.out.println(
                                                index + ". (" + c.getCourseName() + ") (" + c.getCourseCode() + ")");
                                        availableCourses.add(c);
                                        index++;
                                    }
                                }
                                if (availableCourses.isEmpty()) {
                                    System.out.println("No available courses to enroll in.");
                                } else {
                                    System.out.print("Enter the number of the course to enroll in: ");
                                    int selectedCourse = input.nextInt();
                                    if (selectedCourse >= 1 && selectedCourse <= availableCourses.size()) {
                                        Course updateIndex = availableCourses.get(selectedCourse - 1);
                                        student.enroll_In_Course(updateIndex);
                                    } else {
                                        System.out.println("Unable to enroll in" + selectedCourse + ".");
                                    }
                                }
                                break;
                            case 3:
                                student.viewEnrolledCourses();
                                break;
                            case 4:
                                System.out.print(
                                        "\nEnter course name and course code of the course you would like to drop.");
                                String dropCourseName = input.next();
                                String dropCourseCode = input.next();
                                Course courseCodeToDrop = null;
                                for (Course c : courses) {
                                    if (c.getCourseName().equalsIgnoreCase(dropCourseName)
                                            && c.getCourseCode().equalsIgnoreCase(dropCourseCode)) {
                                        courseCodeToDrop = c;
                                        break;
                                    }
                                }
                                if (courseCodeToDrop != null) {
                                    student.dropCourse(courseCodeToDrop);
                                } else {
                                    System.out.println("Course not found with that name and code.");
                                }
                                break;
                            case 5:

                                break;
                            case 6:
                                System.out.println("\nPlease enter the needed information to update your profile.");
                                // Start with default (existing) values
                                String newPassword = student.getPassword();
                                String newEmail = student.getEmail();
                                String newPhone = student.getPhoneNumber();
                                String newAddress = student.getAddress();

                                // Option to update password
                                System.out.println("\nDo you want to change your password?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                int passwordChoice = input.nextInt();
                                input.nextLine(); // Buffer
                                if (passwordChoice == 1) {
                                    System.out.print("\nEnter new password: ");
                                    newPassword = input.nextLine();
                                    student.setPassword(newPassword);
                                } else if (passwordChoice == 2) {
                                    System.out.println("Previous password kept.");
                                }
                                // Option to update email
                                System.out.println("\nDo you want to change your email?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                int emailChoice = input.nextInt();
                                if (emailChoice == 1) {
                                    System.out.print("\nEnter new email: ");
                                    newEmail = input.nextLine();
                                    student.setEmail(newEmail);
                                } else if (emailChoice == 2) {
                                    System.out.println("Email remains unchanged.");
                                }
                                // Option to update phone number
                                System.out.println("\nDo you want to change your phone number?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                int phoneNumberChoice = input.nextInt();
                                input.nextLine(); // Buffer
                                if (phoneNumberChoice == 1) {
                                    System.out.print("\nEnter new phone number: ");
                                    newPhone = input.nextLine();
                                    student.setPhoneNumber(newPhone);
                                } else if (phoneNumberChoice == 2) {
                                    System.out.println("Phone number remains unchanged.");
                                }
                                // Option to update address
                                System.out.println("\nDo you want to change your address?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                int addressChoice = input.nextInt();
                                input.nextLine(); // Buffer
                                if (addressChoice == 1) {
                                    System.out.print("\nEnter new address: ");
                                    newAddress = input.nextLine();
                                    student.setAddress(newAddress);
                                } else if (addressChoice == 2) {
                                    System.out.println("Address number remains unchanged.");
                                }
                                student.updatePersonalInfo(students, studentPassword, newPassword, newEmail, newPhone,
                                        newAddress);
                                break;

                            case 7:
                                student.logout();
                                studentExit = true;
                                break;
                            default:
                                System.out.println("No student record was found with the ID and password provided.");
                        }
                    }
                    break;
                case "instructor":
                    // Login first
                    System.out.println("Please enter your ID and Password");

                    String instructorID = input.next();
                    String instructorPassword = input.next();
                    Instructor instructor = null;
                    boolean exitInstructor = false;

                    for (Instructor i : instructors) {
                        if (i.getId().equals(instructorID) && i.getPassword().equals(instructorPassword)) {
                            instructor = i;
                            instructor.login();
                            break;
                        }
                    }
                    if (instructor == null) {
                        System.out.println("No instructor record was found with the ID and password provided.");
                        break;
                    }

                    while (!exitInstructor) {
                        System.out.println("\nEnter the number for the option you desire.");
                        System.out.println("1. View enrolled students.");
                        System.out.println("2. Grade assignments.");
                        System.out.println("3. Update course information.");
                        System.out.println("4. Logout.");
                        int instructorChoice = input.nextInt();
                        input.nextLine(); // Buffer
                        switch (instructorChoice) {
                            case 1:
                                // The instructor comes from the Instructor class while the courses is a list
                                instructor.viewEnrolledStudents(instructor, courses);
                                break;
                            case 2:
                            System.out.println("\nEnter the students ID you would like to enter their grade for.");
                            String inputStudentGrade = input.next();
                            System.out.println(
                                    "\n Please choose from the menu of what assignment type you would like to grade.");
                            System.out.println("1. Quizes.");
                            System.out.println("2. Midterms.");
                            System.out.println("3. Homework.");
                            System.out.println("4. Final exam.");
                            int examChoice = input.nextInt();
                            input.nextLine(); // Buffer
                                if (examChoice == 1) {

                                }
                                if (examChoice == 2) {

                                }
                                if (examChoice == 3) {

                                }
                                if (examChoice == 4) {

                                }

                                break;
                            case 3:
                                System.out.print("\nEnter the course code you want to update: ");
                                String courseCode = input.next();

                                System.out.print("\nEnter new schedule: ");
                                String newSchedule = input.nextLine();

                                System.out.print("\nEnter new description: ");
                                String newDescription = input.nextLine();

                                // Start with default (existing) values
                                String newPassword = instructor.getPassword();
                                String newEmail = instructor.getEmail();
                                String newPhone = instructor.getPhoneNumber();
                                String newAddress = instructor.getAddress();

                                // Option to update password
                                System.out.println("\nDo you want to change your password?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                int passwordChoice = input.nextInt();
                                input.nextLine(); // Buffer

                                if (passwordChoice == 1) {
                                    System.out.print("\nEnter new password: ");
                                    newPassword = input.nextLine();
                                    instructor.setPassword(newPassword);
                                } else if (passwordChoice == 2) {
                                    System.out.println("Previous password kept.");
                                }

                                // Option to update email
                                System.out.println("\nDo you want to change your email?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                int emailChoice = input.nextInt();
                                input.nextLine(); // Buffer

                                if (emailChoice == 1) {
                                    System.out.print("\nEnter new email: ");
                                    newEmail = input.next();
                                    instructor.setEmail(newEmail);
                                } else if (emailChoice == 2) {
                                    System.out.println("Email remains unchanged.");
                                }

                                // Option to update phone number
                                System.out.println("\nDo you want to change your phone number?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                int phoneNumberChoice = input.nextInt();
                                input.nextLine(); // Buffer

                                if (phoneNumberChoice == 1) {
                                    System.out.print("\nEnter new phone number: ");
                                    newPhone = input.nextLine();
                                    instructor.setPhoneNumber(newPhone);
                                } else if (phoneNumberChoice == 2) {
                                    System.out.println("Phone number remains unchanged.");
                                }

                                // Option to update address
                                System.out.println("\nDo you want to change your address?");
                                System.out.println("1. Yes");
                                System.out.println("2. No");
                                int addressChoice = input.nextInt();
                                input.nextLine(); // Buffer
                                if (addressChoice == 1) {
                                    System.out.print("\nEnter new address: ");
                                    newAddress = input.nextLine();
                                    instructor.setAddress(newAddress);
                                } else if (addressChoice == 2) {
                                    System.out.println("Address number remains unchanged.");
                                }
                                instructor.updateCourseInfo(courseCode, courses, newSchedule, newDescription,
                                        instructors,
                                        newPassword, newEmail, newPhone, newAddress);
                                break;
                            case 4:
                                instructor.logout();
                                exitInstructor = true;
                                break;
                            default:
                                System.out.println("No instructor record was found with the ID and password provided.");
                                break;
                        }
                    }
                    break;
                case "admin":
                    // Login first
                    System.out.println("\nPlease enter your ID and Password");
                    String adminID = input.next();
                    String adminPassword = input.next();
                    Administrator administrator = null;

                    boolean exitAdmin = false;

                    for (Administrator a : administrators) {
                        if (a.getId().equals(adminID) && a.getPassword().equals(adminPassword)) {
                            administrator = a;
                            System.out.println();
                            administrator.login();
                            break;
                        }
                    }
                    if (administrator == null) {
                        System.out.println("No administrator record was found with the ID and password provided.");
                        break;
                    }

                    while (!exitAdmin) {
                        System.out.println("\n1. Add/Remove Students.");
                        System.out.println("2. Add/Remove Instructors.");
                        System.out.println("3. Add Course.");
                        System.out.println("4. Update Student Info.");
                        System.out.println("5. Update Instructor Info.");
                        System.out.println("6. Assign Instructor to Course.");
                        System.out.println("7. Close Course.");
                        System.out.println("8. Logout.");

                        int adminChoice = input.nextInt();
                        input.nextLine();

                        switch (adminChoice) {
                            case 1:
                                System.out.println("\nEnter the number for the option you desire.");
                                System.out.println("1. Add student.");
                                System.out.println("2. Remove student.");
                                int remove_add = input.nextInt();
                                input.nextLine(); // buffer

                                if (remove_add == 1) {

                                    System.out.print("\nEnter student name: ");
                                    String name = input.nextLine();

                                    System.out.print("\nEnter student ID: ");
                                    String ID = input.next();

                                    System.out.print("\nEnter password: ");
                                    String password = input.nextLine();

                                    System.out.print("\nEnter email: ");
                                    String email = input.next();

                                    System.out.print("\nEnter phone number: ");
                                    String phoneNumber = input.nextLine();

                                    System.out.print("\nEnter address: ");
                                    String address = input.nextLine();

                                    System.out.print("\nEnter credit limit: ");
                                    int creditLimit = input.nextInt();
                                    input.nextLine(); // Buffer

                                    Student newStudent = new Student(name, ID, password, email, phoneNumber, role,
                                            address, creditLimit, newCourse);
                                    administrator.addStudent(newStudent, students);

                                } else if (remove_add == 2) {
                                    System.out.print("\nEnter the ID of the student to remove: ");
                                    String targetId = input.next();
                                    administrator.removeStudent(students, targetId);
                                } else {
                                    System.out.println("Invalid option.");
                                }

                                break;
                            case 2:
                                System.out.println("\nEnter the number for the option you desire.");
                                System.out.println("1. Add instructor.");
                                System.out.println("2. Remove instructor.");
                                int add_remove = input.nextInt();
                                input.nextLine(); // buffer
                                if (add_remove == 1) {
                                    System.out.print("\nEnter instructor name: ");
                                    String name = input.nextLine();

                                    System.out.print("\nEnter instructor ID: ");
                                    String ID = input.next();

                                    System.out.print("\nEnter password: ");
                                    String password = input.nextLine();

                                    System.out.print("\nEnter email: ");
                                    String email = input.next();

                                    System.out.print("\nEnter phone number: ");
                                    String phoneNumber = input.nextLine();

                                    System.out.print("\nEnter role: ");
                                    String role1 = input.next();

                                    System.out.print("\nEnter address: ");
                                    String address = input.next();

                                    Instructor newInstructor2 = new Instructor(name, ID, password, email, phoneNumber,
                                            role1,
                                            address);
                                    administrator.addInstructor(newInstructor2, instructors);

                                } else if (add_remove == 2) {
                                    System.out.print("Enter the ID of the instructor you want to remove: ");
                                    String targetId = input.next();
                                    administrator.removeInstructor(instructors, targetId);
                                } else {
                                    System.out.println("Invalid option.");
                                }
                                break;
                            case 3:
                                System.out.println("\nEnter the course information you would like to add.");
                                System.out.print("\nEnter course name: ");
                                String courseName = input.nextLine();

                                System.out.print("\nEnter course code: ");
                                String courseCode = input.nextLine();

                                System.out.print("\nEnter course capacity: ");
                                int courseCapacity = input.nextInt();
                                input.nextLine(); // Buffer

                                System.out.print("\nEnter course credit hours: ");
                                int courseCreditHours = input.nextInt();
                                input.nextLine(); // Buffer

                                // We need to create the course object here in order to look through the
                                // instructor list in order to assign them. Same thing for the enrollment
                                // status.
                                newCourse = new Course(courseName, courseCode, null, null, null, null,
                                        courseCapacity, null, courseCreditHours);

                                // Assign instructor
                                System.out.print("\nAssign instructor by ID: ");
                                String assignInstructorID = input.nextLine();

                                Instructor selectedInstructor = null;

                                for (Instructor i : instructors) {
                                    if (i.getId().equals(assignInstructorID)) {
                                        selectedInstructor = i;
                                        break;
                                    }
                                }
                                if (selectedInstructor != null) {
                                    administrator.assignInstructor(newCourse, selectedInstructor);
                                } else {
                                    System.out.println("Instructor with ID " + assignInstructorID + " not found.");
                                }
                                // Assign course status
                                System.out.println("Enter course status: ");
                                System.out.println("1. Open");
                                System.out.println("2. Closed");
                                int courseStatus = input.nextInt();
                                input.nextLine(); // Buffer

                                if (courseStatus == 1) {
                                    newCourse.setEnrollmentStatus(Course.EnrollmentStatusEnum.Open);
                                } else if (courseStatus == 2) {
                                    newCourse.setEnrollmentStatus(Course.EnrollmentStatusEnum.Closed);
                                }
                                administrator.addCourse(courses, newCourse);

                                break;
                            case 4:

                                System.out.println(
                                        "\nPlease enter the requiered information to update students profiles.");
                                System.out.print("\nEnter student name: ");
                                String name = input.nextLine();

                                System.out.print("\nEnter student ID: ");
                                String ID = input.next();

                                Student studentToUpdate = null;

                                for (Student s : students) {
                                    if (s.getName().equals(name) && s.getId().equals(ID)) {
                                        studentToUpdate = s;
                                        break;
                                    }
                                }
                                if (studentToUpdate != null) {
                                    System.out.println("Update " + studentToUpdate.getName() + " details below: ");

                                    // Start with default (existing) values
                                    String newName = studentToUpdate.getName();
                                    String newID = studentToUpdate.getId();
                                    String newPassword = studentToUpdate.getPassword();
                                    String newEmail = studentToUpdate.getEmail();
                                    String newPhone = studentToUpdate.getPhoneNumber();
                                    String newRole = studentToUpdate.getRole();
                                    String newAddress = studentToUpdate.getAddress();

                                    // Option to update name
                                    System.out.println(
                                            "Would you like to update " + studentToUpdate.getName() + " name?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int nameChoice = input.nextInt();
                                    if (nameChoice == 1) {
                                        System.out.print("Enter new name: ");
                                        newName = input.nextLine();
                                        studentToUpdate.setName(newName);
                                    } else if (nameChoice == 2) {
                                        System.out.println("Name remains unchanged.");
                                    }
                                    // Option to update ID
                                    System.out.println("Do you want to change " + studentToUpdate.getName() + " ID?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int IDChoice = input.nextInt();
                                    if (IDChoice == 1) {
                                        System.out.print("Enter new ID: ");
                                        newID = input.nextLine();
                                        studentToUpdate.setId(newID);
                                    } else if (IDChoice == 2) {
                                        System.out.println("ID remains unchanged.");
                                    }

                                    // Option to update email
                                    System.out.println(
                                            "Do you want to change " + studentToUpdate.getEmail() + "'s email?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int emailChoice = input.nextInt();
                                    if (emailChoice == 1) {
                                        System.out.print("Enter new email: ");
                                        newEmail = input.nextLine();
                                        studentToUpdate.setEmail(newEmail);
                                    } else if (emailChoice == 2) {
                                        System.out.println("Email remains unchanged.");
                                    }
                                    // Option to update phone number
                                    System.out.println(
                                            "Do you want to change " + studentToUpdate.getName() + "'s phone number?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int phoneNumberChoice = input.nextInt();
                                    if (phoneNumberChoice == 1) {
                                        System.out.print("Enter new phone number: ");
                                        newPhone = input.nextLine();
                                        studentToUpdate.setPassword(newPhone);
                                    } else if (phoneNumberChoice == 2) {
                                        System.out.println("Phone number remains unchanged.");
                                    }
                                    // Option to update password number
                                    System.out.println(
                                            "Do you want to change " + studentToUpdate.getName() + "'s password?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int passwordChoice = input.nextInt();
                                    if (passwordChoice == 1) {
                                        System.out.print("Enter new password: ");
                                        newPassword = input.nextLine();
                                        studentToUpdate.setPassword(newPassword);
                                    } else if (passwordChoice == 2) {
                                        System.out.println("Password number remains unchanged.");
                                    }
                                    // Option to update role
                                    System.out.println(
                                            "Do you want to change " + studentToUpdate.getName() + "'s role?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int roleChoice = input.nextInt();
                                    if (roleChoice == 1) {
                                        System.out.print("Enter new role: ");
                                        newRole = input.nextLine();
                                        studentToUpdate.setRole(newRole);
                                    } else if (roleChoice == 2) {
                                        System.out.println("Role remains unchanged.");
                                    }
                                    // Option to update address
                                    System.out.println(
                                            "Do you want to change" + studentToUpdate.getName() + "'s address?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int addressChoice = input.nextInt();
                                    if (addressChoice == 1) {
                                        System.out.print("Enter new address: ");
                                        newAddress = input.nextLine();
                                        studentToUpdate.setAddress(newAddress);
                                    } else if (addressChoice == 2) {
                                        System.out.println("Address remains unchanged.");
                                    }
                                    // Option to update credit limit
                                    System.out.println(
                                            "Do you want to update " + studentToUpdate.getName() + "'s credit limit?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int creditLimitChoice = input.nextInt();
                                    if (creditLimitChoice == 1) {
                                        System.out.print("Enter new credit limit: ");
                                        int updatedStudentcreditLimit = input.nextInt();
                                        studentToUpdate.setCreditLimit(updatedStudentcreditLimit);
                                    } else if (creditLimitChoice == 2) {
                                        System.out.println("Credit limit number remains unchanged.");
                                    }
                                }
                                break;
                            case 5:
                                System.out.println(
                                        "\nPlease enter the requiered information to update instructors profiles.");
                                System.out.print("\nEnter instructor name: ");
                                String instructorName = input.nextLine();

                                System.out.print("\nEnter student ID: ");
                                String instructorId = input.next();

                                Instructor instructorToUpdate = null;

                                for (Instructor i : instructors) {
                                    if (i.getName().equals(instructorName) && i.getId().equals(instructorId)) {
                                        instructorToUpdate = i;
                                        break;
                                    }
                                }
                                if (instructorToUpdate != null) {
                                    System.out.println("Update " + instructorToUpdate.getName() + " details below: ");

                                    // Start with default values
                                    String newName = instructorToUpdate.getName();
                                    String newID = instructorToUpdate.getId();
                                    String newPassword = instructorToUpdate.getPassword();
                                    String newEmail = instructorToUpdate.getEmail();
                                    String newPhone = instructorToUpdate.getPhoneNumber();
                                    String newRole = instructorToUpdate.getRole();
                                    String newAddress = instructorToUpdate.getAddress();

                                    // Option to update name
                                    System.out
                                            .println("Would you like to update " + instructorToUpdate.getName()
                                                    + "'s name?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int nameChoice = input.nextInt();
                                    if (nameChoice == 1) {
                                        System.out.print("Enter new name: ");
                                        newName = input.nextLine();
                                        instructorToUpdate.setName(newName);
                                    } else if (nameChoice == 2) {
                                        System.out.println("Name remains unchanged.");
                                    }
                                    // Option to update ID
                                    System.out.println(
                                            "Do you want to change " + instructorToUpdate.getName() + "'s ID?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int IDChoice = input.nextInt();
                                    if (IDChoice == 1) {
                                        System.out.print("Enter new ID: ");
                                        newID = input.nextLine();
                                        instructorToUpdate.setId(newID);
                                    } else if (IDChoice == 2) {
                                        System.out.println("ID remains unchanged.");
                                    }
                                    // Option to update password
                                    System.out.println(
                                            "Do you want to change " + instructorToUpdate.getName() + "'s password?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int passwordChoice = input.nextInt();
                                    if (passwordChoice == 1) {
                                        System.out.print("Enter new password number: ");
                                        newPassword = input.nextLine();
                                        instructorToUpdate.setPassword(newPassword);
                                    } else if (passwordChoice == 2) {
                                        System.out.println("Password number remains unchanged.");
                                    }

                                    // Option to update email
                                    System.out.println(
                                            "Do you want to change " + instructorToUpdate.getEmail() + "'s email?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int emailChoice = input.nextInt();
                                    if (emailChoice == 1) {
                                        System.out.print("Enter new email: ");
                                        newEmail = input.nextLine();
                                        instructorToUpdate.setEmail(newEmail);
                                    } else if (emailChoice == 2) {
                                        System.out.println("Email remains unchanged.");
                                    }
                                    // Option to update phone number
                                    System.out.println(
                                            "Do you want to change " + instructorToUpdate.getName()
                                                    + "'s phone number?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int phoneNumberChoice = input.nextInt();
                                    if (phoneNumberChoice == 1) {
                                        System.out.print("Enter new phone number: ");
                                        newPhone = input.nextLine();
                                        instructorToUpdate.setPhoneNumber(newPhone);
                                        ;
                                    } else if (phoneNumberChoice == 2) {
                                        System.out.println("Phone number remains unchanged.");
                                    }
                                    // Option to update role
                                    System.out.println(
                                            "Do you want to change " + instructorToUpdate.getName() + "'s role?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int roleChoice = input.nextInt();
                                    if (roleChoice == 1) {
                                        System.out.print("Enter new phone number: ");
                                        newRole = input.nextLine();
                                        instructorToUpdate.setRole(newRole);
                                    } else if (roleChoice == 2) {
                                        System.out.println("Role remains unchanged.");
                                    }

                                    // Option to update address
                                    System.out.println(
                                            "Do you want to change" + instructorToUpdate.getName() + "'s address?");
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");
                                    int addressChoice = input.nextInt();
                                    if (addressChoice == 1) {
                                        System.out.print("Enter new address: ");
                                        newAddress = input.nextLine();
                                        instructorToUpdate.setAddress(newAddress);
                                    } else if (addressChoice == 2) {
                                        System.out.println("Address remains unchanged.");
                                    }

                                }
                                break;
                            case 6:
                                // Show the admin the avaliale courses to pick from.
                                System.out.println("\nAvailable Courses:");
                                for (int i = 0; i < courses.size(); i++) {
                                    // The i+1 is for the indext of which the admin will select. The i is for 'this'
                                    // spesific course.
                                    System.out.println((i + 1) + ". " + courses.get(i).getCourseName() + " (Code: "
                                            + courses.get(i).getCourseCode() + ")");
                                }
                                System.out.print("Enter the number of the course to assign the instructor to: ");
                                int courseIndex = input.nextInt() - 1;
                                input.nextLine(); // Buffer

                                // Basic validation.
                                if (courseIndex < 0 || courseIndex >= courses.size()) {
                                    System.out.println("Invalid course selection.");
                                    return;
                                }

                                Course selectedCourse = courses.get(courseIndex);

                                System.out.print("Enter the ID of the instructor to assign: ");
                                String instructorID2 = input.next();

                                Instructor instructorToUpdate2 = null;

                                for (Instructor i : instructors) {
                                    if (i.getId().equals(instructorID2)) {
                                        instructorToUpdate2 = i;
                                        break;
                                    }
                                }
                                if (instructorToUpdate2 != null) {
                                    administrator.assignInstructor(selectedCourse, instructorToUpdate2);
                                } else {
                                    System.out.println("Instructor with ID " + instructorID2 + " not found.");
                                }
                                break;
                            case 7:
                                System.out.println("\nEnter the details requiered to close a course.");
                                System.out.println("\nEnter coure name and course code: ");
                                String closeCourseName = input.nextLine();
                                String closeCourseCode = input.next();
                                Course courseToClose = null;
                                for (Course c : courses) {
                                    if (c.getCourseName().equals(closeCourseName)
                                            && c.getCourseCode().equals(closeCourseCode)) {
                                        courseToClose = c;
                                        break;
                                    }
                                }

                                if (courseToClose != null) {
                                    administrator.closeCourse(courseToClose, courses);
                                } else {
                                    System.out.println("Course not found.");
                                }
                                break;
                            case 8:
                                administrator.logout();
                                exitAdmin = true;
                                break;
                            default:
                                System.out.println(
                                        "No administrator record was found with the ID and password provided.");
                        }
                    }
                    break;
                case "exit":
                    exit = true;
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println("Invalid role. Try again.");
            }
        }
        input.close();
    }
}
