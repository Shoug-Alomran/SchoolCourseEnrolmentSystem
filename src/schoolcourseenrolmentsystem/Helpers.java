package schoolcourseenrolmentsystem;

import java.util.*;

public class Helpers {
    /// <T extends User<T>> This method works with any User (Admin, Instructor,
    /// Student). T inherits from User<T>.
    /// The single T will return a single object of type T.
    public static <T extends User<T>> T loginWithRetry(List<T> usersList, Scanner input, T tempUser) {
        // Check if the id is even in the system or not.
        System.out.print("Please enter your ID: ");
        String id = input.next();
        input.nextLine(); // Buffer

        // Check if the ID exists
        boolean idExists = false;
        for (T user : usersList) {
            if (user.getId().equals(id)) {
                idExists = true;
                break;
            }
        }
        // If user ID is not found
        if (!idExists) {
            System.out.println("No user found with the entered ID.");
            return null;
        }
        // Max tries is to not allow the user to try so many times.
        int maxAttempts = 3;
        int attempts = 0;
        T loggedInUser = null;

        while (attempts < maxAttempts) {
            System.out.print("\nPlease enter your password: ");
            String password = input.nextLine();

            loggedInUser = tempUser.login(usersList, id, password);

            if (loggedInUser != null) {
                return loggedInUser;
            } else {
                System.out.println("Invalid credentials. Please try again.");
                attempts++;
            }
        }
        System.out.println("Too many failed attempts. Returning to main menu.");
        return null;
    }

    // Returns string since its easier than to convert to Object (T).
    /// Method used for admins to check when creating a new user.
    /// Also used by instructors and students when updating thier info.
    public static <T extends User<T>> String checkValidityOfPassword(Scanner input, T tempUser) {
        String password = null;
        boolean validPassword = false;
        while (!validPassword) {
            System.out.print("Enter password (at least 8 characters): ");
            String inputCorrectPassword = input.nextLine();
            try {
                if (inputCorrectPassword.length() > 8) {
                    tempUser.setPassword(inputCorrectPassword);
                    password = inputCorrectPassword;
                    validPassword = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return password;
    }

    public static <T extends User<T>> String checkValidityOfEmail(Scanner input, T tempUser) {
        String email = null;
        boolean validEmail = false;
        while (!validEmail) {
            System.out.print("Enter email: ");
            String inputCorrectEmail = input.nextLine();
            try {
                if (inputCorrectEmail.contains("@") && inputCorrectEmail.contains(".")) {
                    tempUser.setEmail(inputCorrectEmail);
                    email = inputCorrectEmail;
                    validEmail = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return email;
    }

    public static <T extends User<T>> String checkValidityOfPhoneNumber(Scanner input, T tempUser) {
        String phoneNumber = null;
        boolean validPhoneNumber = false;
        while (!validPhoneNumber) {
            System.out.print("Enter phone number (10 digits): ");
            String inputCorrectPhoneNumber = input.nextLine();
            try {
                if (inputCorrectPhoneNumber.length() == 10) {
                    tempUser.setPhoneNumber(inputCorrectPhoneNumber);
                    phoneNumber = inputCorrectPhoneNumber;
                    validPhoneNumber = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return phoneNumber;
    }

    public static <T extends User<T>> String checkValidityOfAddress(Scanner input, T tempUser) {
        String address = null;
        boolean validAddress = false;
        while (!validAddress) {
            System.out.print("Enter address: ");
            String inputCorrectAddress = input.nextLine();
            try {
                if (inputCorrectAddress.length() > 0) {
                    tempUser.setAddress(inputCorrectAddress);
                    address = inputCorrectAddress;
                    validAddress = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return address;
    }

    public static <T extends User<T>> String checkValidityOfID(Scanner input, T tempUser) {
        String id = null;
        boolean validID = false;
        while (!validID) {
            System.out.print("Enter ID: ");
            String inputCorrectID = input.nextLine();
            try {
                if (inputCorrectID.length() > 0) {
                    tempUser.setId(inputCorrectID);
                    id = inputCorrectID;
                    validID = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return id;
    }

    public static <T extends User<T>> String checkValidityOfName(Scanner input, T tempUser) {
        String name = null;
        boolean validName = false;
        while (!validName) {
            System.out.print("Enter individuals name: ");
            String inputCorrectName = input.nextLine();
            try {
                if (inputCorrectName.length() > 0) {
                    tempUser.setName(inputCorrectName);
                    name = inputCorrectName;
                    validName = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return name;
    }

    public static User.Role checkValididtyOfRole(Scanner input) {
        while (true) {
            System.out.println("Select a role:");
            System.out.println("1. STUDENT");
            System.out.println("2. INSTRUCTOR");
            System.out.println("3. ADMIN");
            System.out.print("Your choice: ");

            int choice;
            try {
                choice = input.nextInt();
                input.nextLine(); // buffer

                switch (choice) {
                    case 1:
                        return User.Role.STUDENT;
                    case 2:
                        return User.Role.INSTRUCTOR;
                    case 3:
                        return User.Role.ADMIN;
                    default:
                        System.out.println("Invalid selection. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // clear the invalid input
            }
        }
    }

    /// Method created to avoid InputMismatchException
    public static int getSafeIntInput(Scanner input, String prompt) {
        int number = -1; // -1 is typically related to false or not true. This is a default number.
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(prompt);
                number = input.nextInt();
                input.nextLine();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
            }
        }
        return number;
    }

    // MENUS
    public static void showStudentMenu() {
        System.out.println("\nEnter the number for the choice you want.");
        System.out.println("1. View avaliable courses.");
        System.out.println("2. Enroll in course.");
        System.out.println("3. View enrolled courses.");
        System.out.println("4. View credit limit.");
        System.out.println("5. Drop course.");
        System.out.println("6. View grades.");
        System.out.println("7. Update personal information.");
        System.out.println("8. Logout.");
    }

    public static void showInstructorMenu() {
        System.out.println("1. View enrolled students.");
        System.out.println("2. Grade assignments.");
        System.out.println("3. Update course information.");
        System.out.println("4. Update personal information.");
        System.out.println("5. Logout.");
    }

    public static void showAdminMenu() {
        System.out.println("\n1. Add/Remove Students.");
        System.out.println("2. Add/Remove Instructors.");
        System.out.println("3. Add Course.");
        System.out.println("4. Update Student Info.");
        System.out.println("5. Update Instructor Info.");
        System.out.println("6. Assign Instructor to Course.");
        System.out.println("7. Close Course.");
        System.out.println("8. View Enrollment Statistics.");
        System.out.println("9. Generate Reports.");
        System.out.println("10.Logout.");
    }

    // CASES
    // INSTRUCTORS CASES

    public static void instructorCase2(Scanner input) {
        System.out.println("\nEnter the students ID you would like to enter their grade for.");
        // = input.next();
        System.out.println(
                "\n Please choose from the menu of what assignment type you would like to grade.");
        System.out.println("1. Quizes.");
        System.out.println("2. Midterms.");
        System.out.println("3. Homework.");
        System.out.println("4. Final exam.");
        System.out.println("5. Total average grade");
        int examChoice = Helpers.getSafeIntInput(input, "Option: "); // -> check parameters
        input.nextLine(); // Buffer
        if (examChoice == 1) {
            // instructor.assignGrade(grades, inputStudentGrade, courseCode, examType,
            // score);
        }
        if (examChoice == 2) {

        }
        if (examChoice == 3) {

        }
        if (examChoice == 4) {

        }
        if (examChoice == 5) {

        }
    }

    // needs acutal logic ^
    public static void instructorCase3(Instructor instructor, List<Course> courses, Scanner input) {
        System.out.print("\nEnter the course code  of the course you would like to update: ");
        String courseCode = input.next();
        input.nextLine(); // Buffer

        System.out.print("\nEnter new schedule:");
        String newSchedule = input.nextLine();

        System.out.print("\nEnter new description: ");
        String newDescription = input.nextLine();

        instructor.updateCourseInfo(courseCode, courses, newSchedule, newDescription);
    }

    public static void instructorCase4(Instructor instructor, List<Course> courses, List<Instructor> instructors,
            Scanner input) {
        System.out.println("\nPlease fill out the form to update your profile.");

        // Start with default (existing) values
        String newPassword = instructor.getPassword();
        String newEmail = instructor.getEmail();
        String newPhone = instructor.getPhoneNumber();
        String newAddress = instructor.getAddress();

        // Option to update password
        System.out.println("\nDo you want to change your password?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int passwordChoice = Helpers.getSafeIntInput(input, "Option: ");
        if (passwordChoice == 1) {
            System.out.print("\nEnter new password: ");
            newPassword = Helpers.checkValidityOfPassword(input, instructor);
            // The password is already set and validated within the helper method.
        } else if (passwordChoice == 2) {
            System.out.println("Previous password kept.");
        }
        // Option to update email
        System.out.println("\nDo you want to change your email?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int emailChoice = Helpers.getSafeIntInput(input, "Option: ");
        if (emailChoice == 1) {
            System.out.print("\nEnter new email: ");
            newEmail = Helpers.checkValidityOfEmail(input, instructor);
            instructor.setEmail(newEmail);
        } else if (emailChoice == 2) {
            System.out.println("Email remains unchanged.");
        }
        // Option to update phone number
        System.out.println("\nDo you want to change your phone number?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int phoneNumberChoice = Helpers.getSafeIntInput(input, "Option: ");
        if (phoneNumberChoice == 1) {
            System.out.print("\nEnter new phone number: ");
            newPhone = Helpers.checkValidityOfPhoneNumber(input, instructor);
            instructor.setPhoneNumber(newPhone);
        } else if (phoneNumberChoice == 2) {
            System.out.println("Phone number remains unchanged.");
        }
        // Option to update address
        System.out.println("\nDo you want to change your address?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int addressChoice = Helpers.getSafeIntInput(input, "Option: ");
        if (addressChoice == 1) {
            System.out.print("\nEnter new address: ");
            newAddress = Helpers.checkValidityOfAddress(input, instructor);
            instructor.setAddress(newAddress);
        } else if (addressChoice == 2) {
            System.out.println("Address number remains unchanged.");
        }
        instructor.updateInstructorPersonalInfo(instructors, newAddress, newPassword, newEmail, newPhone, newAddress);
    }

    // STUDENT CASES
    public static void studentCase1(List<Course> courses, Student student, Scanner input) {
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
    }

    public static void studentCase5(List<Course> courses, Student student, Scanner input) {
        System.out.print(
                "\nEnter course code of the course you would like to drop: ");
        String dropCourseCode = input.next();
        input.nextLine(); // Buffer
        Course courseCodeToDrop = null;
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(dropCourseCode)) {
                courseCodeToDrop = c;
                break;
            }
        }
        if (courseCodeToDrop != null) {
            student.dropCourse(courseCodeToDrop);
        } else {
            System.out.println("No course found with the code" + dropCourseCode + "'.");
        }
    }

    public static void studentCase6() {

    }

    public static void studentCase7(Student student, List<Student> students, Scanner input) {
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
        int passwordChoice = Helpers.getSafeIntInput(input, "Option: ");
        if (passwordChoice == 1) {
            System.out.print("\nEnter new password: ");
            newPassword = Helpers.checkValidityOfPassword(input, student);
            student.setPassword(newPassword);
        } else if (passwordChoice == 2) {
            System.out.println("Previous password kept.");
        }
        // Option to update email
        System.out.println("\nDo you want to change your email?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int emailChoice = Helpers.getSafeIntInput(input, "Option: ");
        if (emailChoice == 1) {
            System.out.print("\nEnter new email: ");
            newEmail = Helpers.checkValidityOfEmail(input, student);
            student.setEmail(newEmail);
        } else if (emailChoice == 2) {
            System.out.println("Email remains unchanged.");
        }
        // Option to update phone number
        System.out.println("\nDo you want to change your phone number?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int phoneNumberChoice = Helpers.getSafeIntInput(input, "Option: ");
        if (phoneNumberChoice == 1) {
            System.out.print("\nEnter new phone number: ");
            newPhone = Helpers.checkValidityOfPhoneNumber(input, student);
            student.setPhoneNumber(newPhone);
        } else if (phoneNumberChoice == 2) {
            System.out.println("Phone number remains unchanged.");
        }
        // Option to update address
        System.out.println("\nDo you want to change your address?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int addressChoice = Helpers.getSafeIntInput(input, "Option: ");
        input.nextLine(); // Buffer
        if (addressChoice == 1) {
            System.out.print("\nEnter new address: ");
            newAddress = Helpers.checkValidityOfAddress(input, student);
            student.setAddress(newAddress);
        } else if (addressChoice == 2) {
            System.out.println("Address number remains unchanged.");
        }
        student.updatePersonalInfo(students, newAddress, newPassword, newEmail, newPhone, newAddress);
    }

    // ADMIN CASES
    public static void adminCase1(Administrator administrator, List<Student> students, Scanner input) {
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
            input.nextLine(); // Buffer

            Student tempStudent = new Student("temp", "temp", "temp", "temp", "temp",
                    "temp", "temp", 0, null);
            String password = Helpers.checkValidityOfPassword(input, tempStudent);

            System.out.print("\nEnter email: ");
            String email = Helpers.checkValidityOfEmail(input, tempStudent);

            System.out.print("\nEnter phone number: ");
            String phoneNumber = Helpers.checkValidityOfPhoneNumber(input, tempStudent);

            System.out.print("\nEnter address: ");
            String address = Helpers.checkValidityOfAddress(input, null);

            System.out.print("\nEnter credit limit: ");
            int creditLimit = input.nextInt();
            input.nextLine(); // Buffer
            // input.close();
            Student newStudent = new Student(name, ID, password, email, phoneNumber, "Student", address, creditLimit,
                    new ArrayList<>());
            administrator.addStudent(newStudent, students);

        } else if (remove_add == 2) {
            System.out.print("\nEnter the ID of the student to remove: ");
            String targetId = input.next();
            input.nextLine(); // Buffer
            administrator.removeStudent(students, targetId);
        } else {
            System.out.println("Invalid option.");
        }
    }

    public static void adminCase2(Administrator administrator, List<Instructor> instructors, Scanner input) {
        System.out.println("1. Add instructor.");
        System.out.println("2. Remove instructor.");
        int add_remove = Helpers.getSafeIntInput(input, "Option: ");

        if (add_remove == 1) {
            Instructor tempInstructor = new Instructor(
                    "temp", "temp", "temp", "temp", "temp",
                    User.Role.INSTRUCTOR, "temp", new ArrayList<>());
            System.out.print("\nEnter instructor name: ");
            String name = Helpers.checkValidityOfName(input, tempInstructor);
            String ID = Helpers.checkValidityOfID(input, tempInstructor);

            // This will validate input and set it only if correct
            String password = Helpers.checkValidityOfPassword(input, tempInstructor);
            String email = Helpers.checkValidityOfEmail(input, tempInstructor);
            String phoneNumber = Helpers.checkValidityOfPhoneNumber(input, tempInstructor);
            String address = Helpers.checkValidityOfAddress(input, tempInstructor);

            Instructor newInstructor = new Instructor(
                    name, ID, password, email, phoneNumber,
                    User.Role.INSTRUCTOR, address, new ArrayList<>());

            administrator.addInstructor(newInstructor, instructors);

        } else if (add_remove == 2) {
            System.out.print("Enter the ID of the instructor you want to remove: ");
            String targetId = input.next();
            input.nextLine(); // Buffer
            administrator.removeInstructor(instructors, targetId);
        } else {
            System.out.println("Invalid option.");
        }
    }

    public static void adminCase3(Administrator administrator, List<Course> courses, List<Instructor> instructors,
            Scanner input) {
        System.out.println("\nEnter the course's information that you would like to add.");
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
        Course newCourse = new Course(courseName, courseCode, null, null, null, null,
                courseCapacity, null, courseCreditHours);

        // Assign instructor
        System.out.print("\nAssign instructor by ID: ");
        String assignInstructorID = input.nextLine();

        // Validate instructor list is not empty.
        if (instructors.isEmpty()) {
            System.out.println("No instructors available. Please add an instructor before creating a course.");
            return; // Stop the method early
        }
        Instructor selectedInstructor = null;
        for (Instructor i : instructors) {
            if (i.getId().equals(assignInstructorID)) {
                selectedInstructor = i;
                break;
            }
        }
        administrator.assignInstructor(newCourse, selectedInstructor);
        if (selectedInstructor == null) {
            System.out.println("Instructor with ID " + assignInstructorID + " not found.");
        }

        // Assign course status
        System.out.println("Enter course status: ");
        System.out.println("1. Open");
        System.out.println("2. Closed");
        int courseStatus = Helpers.getSafeIntInput(input, "");
        input.nextLine(); // Buffer

        if (courseStatus == 1) {
            newCourse.setEnrollmentStatus(Course.EnrollmentStatusEnum.Open);
        } else if (courseStatus == 2) {
            newCourse.setEnrollmentStatus(Course.EnrollmentStatusEnum.Closed);
        }
        administrator.addCourse(courses, newCourse);
    }

    public static void adminCase4(Administrator administrator, List<Student> students, Scanner input) {
        System.out.println(
                "\nPlease enter the requiered information to update students profiles.");
        System.out.print("\nEnter student name: ");
        String name = input.nextLine();

        System.out.print("\nEnter student ID: ");
        String ID = input.next();
        input.nextLine(); // Buffer

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
            User.Role newRole = studentToUpdate.getRole();
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
                studentToUpdate.setPhoneNumber(newPhone);
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
            System.out
                    .println("Do you want to change " + studentToUpdate.getName() + "'s role?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int roleChoice = input.nextInt();
            if (roleChoice == 1) {
                System.out.print("Enter new role: ");
                newRole = Helpers.checkValididtyOfRole(input);
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
    }

    public static void adminCase5(Administrator administrator, List<Instructor> instructors, Scanner input) {
        System.out.println(
                "\nPlease enter the requiered information to update instructors profiles.");
        System.out.print("\nEnter instructor name: ");
        String instructorName = input.nextLine();

        System.out.print("\nEnter student ID: ");
        String instructorId = input.next();
        input.nextLine(); // Buffer

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
            // Ruins data persistancy.
            // String newID = instructorToUpdate.getId();
            String newPassword = instructorToUpdate.getPassword();
            String newEmail = instructorToUpdate.getEmail();
            String newPhone = instructorToUpdate.getPhoneNumber();
            User.Role newRole = instructorToUpdate.getRole();
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
                newName = Helpers.checkValidityOfName(input, instructorToUpdate);
                instructorToUpdate.setName(newName);
            } else if (nameChoice == 2) {
                System.out.println("Name remains unchanged.");
            }

            // Option to update password
            System.out.println(
                    "Do you want to change " + instructorToUpdate.getName() + "'s password?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int passwordChoice = input.nextInt();
            if (passwordChoice == 1) {
                System.out.print("Enter new password number: ");
                newPassword = Helpers.checkValidityOfPassword(input, instructorToUpdate);
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
                newEmail = Helpers.checkValidityOfEmail(input, instructorToUpdate);
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
                newPhone = Helpers.checkValidityOfPhoneNumber(input, instructorToUpdate);
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
                newRole = Helpers.checkValididtyOfRole(input);
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
                newAddress = Helpers.checkValidityOfAddress(input, instructorToUpdate);
                instructorToUpdate.setAddress(newAddress);
            } else if (addressChoice == 2) {
                System.out.println("Address remains unchanged.");
            }
        }
    }

    public static void adminCase6(Administrator administrator, List<Course> courses,
            List<Instructor> instructors, Scanner input) {
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
        input.nextLine(); // Buffer

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
    }

    public static void adminCase7(Administrator administrator, List<Course> courses,
            Scanner input) {
        System.out.println("\nEnter the details requiered to close the course.");
        System.out.print("\nEnter course name: ");
        String closeCourseName = input.nextLine();
        System.out.print("\nEnter course code: ");
        String closeCourseCode = input.next();
        input.nextLine(); // Buffer
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
    }

    public static void adminCase8() {

    }

    public static void adminCase9() {

    }

}