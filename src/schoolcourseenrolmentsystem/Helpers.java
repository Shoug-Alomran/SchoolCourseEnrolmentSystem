package schoolcourseenrolmentsystem;

import java.util.*;

public class Helpers {
    public static Scanner input = new Scanner(System.in);

    /// <T extends User<T>> This method works with any User (Admin, Instructor,
    /// Student). T inherits from User<T>.
    /// The single T will return a single object of type T.
    public static <T extends User<T>> T loginWithRetry(List<T> usersList, T tempUser) {
        T loggedInUser = null;
        try {
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
            if (!idExists) {
                System.out.println("No user found with the entered ID.");
            } else {
                System.out.print("\nPlease enter your password: ");
                String password = input.nextLine();
                T tempUser2 = tempUser.login(usersList, id, password);
                if (null != tempUser2) {
                    loggedInUser = tempUser2;
                }
            }
            return loggedInUser;
        } catch (Exception e) {
            return loggedInUser;
        } finally {
            input.close();
        }
    }

    public static boolean ValidatePassword(String password) {
        boolean isValid = false;
        try {
            if (password != null && password != "" && password.length() > 8) {
                isValid = true;
            }
        } catch (Exception e) {
        }
        return isValid;
    }

    public static boolean ValidateEmail(String email) {
        boolean isValid = false;
        try {
            if (email != null && email != "" && email.contains("@") && email.contains(".")) {
                isValid = true;
            }
        } catch (Exception e) {
        }
        return isValid;
    }

    public static boolean ValidatePhoneNumber(String phoneNumber) {
        boolean isValid = false;
        try {
            if (phoneNumber != null && phoneNumber != "" && phoneNumber.length() == 10) {
                isValid = true;
            }
        } catch (Exception e) {
        }
        return isValid;
    }

    public static boolean ValidateAddress(String address) {
        boolean isValid = false;
        try {
            if (null != address && address != "" && address.length() > 3) {
                isValid = true;
            }
        } catch (Exception e) {
        }
        return isValid;
    }

    public static boolean ValidateId(String ID) {
        boolean isValid = false;
        try {
            if (ID != null && ID != "" && ID.length() == 9) {
                isValid = true;
            }
        } catch (Exception e) {
        }
        return isValid;
    }

    public static Boolean ValidateName(String name) {
        boolean isValid = false;
        try {
            if (null != name && name != "" && name.length() > 3) {
                isValid = true;
            }
        } catch (Exception e) {
        }
        return isValid;
    }

    public static User.Role checkValidityOfRole() {
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
            } finally {
                input.close();
            }
        }
    }

    /// Method created to avoid InputMismatchException
    public static int getSafeIntInput(String prompt) {
        Scanner input2 = new Scanner(System.in);
        try {
            int number = -1; // -1 is typically related to false or not true. This is a default number.
            boolean valid = false;
            while (!valid) {
                try {
                    System.out.print(prompt);
                    number = input2.nextInt();
                    input2.nextLine();
                    valid = true;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number.");
                    input2.nextLine();
                }
            }
            return number;
        } catch (Exception e) {
            return -1;
        } finally {
            input2.close();
        }
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
        System.out.println("\n1. Add Students.");
        System.out.println("2. Remove Students.");
        System.out.println("3. Add Instructors.");
        System.out.println("4. Remove Instructors.");
        System.out.println("5. Add Course.");
        System.out.println("6. Update Student Info.");
        System.out.println("7. Update Instructor Info.");
        System.out.println("8. Assign Instructor to Course.");
        System.out.println("9. Close Course.");
        System.out.println("10. View Enrollment Statistics.");
        System.out.println("11. Generate Reports.");
        System.out.println("12.Logout.");
    }

    // CASES
    // INSTRUCTORS CASES

    public static void editStudentGrades() {
        try {
            System.out.println("\nEnter the students ID you would like to enter their grade for.");
            // = input.next();
            System.out.println(
                    "\n Please choose from the menu of what assignment type you would like to grade.");
            System.out.println("1. Quizes.");
            System.out.println("2. Midterms.");
            System.out.println("3. Homework.");
            System.out.println("4. Final exam.");
            System.out.println("5. Total average grade");
            int examChoice = Helpers.getSafeIntInput("Option: "); // -> check parameters
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

        } catch (Exception e) {
        } finally {
            input.close();
        }
    }

    // needs acutal logic ^
    public static void updateCourseInfo(Instructor instructor, List<Course> courses) {
        try {
            System.out.print("\nEnter the course code  of the course you would like to update: ");
            String courseCode = input.next();
            input.nextLine(); // Buffer

            System.out.print("\nEnter new schedule:");
            String newSchedule = input.nextLine();

            System.out.print("\nEnter new description: ");
            String newDescription = input.nextLine();

            instructor.updateCourseInfo(courseCode, courses, newSchedule, newDescription);
        } catch (Exception e) {
        } finally {
            input.close();
        }
    }

    public static void updateInstructorProfile(Instructor instructor) {
        try {
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
            int passwordChoice = Helpers.getSafeIntInput("Option: ");

            if (passwordChoice == 1) {
                System.out.print("\nEnter new password: ");
                String passwordInput = input.next();
                input.nextLine(); // Buffer
                while (!Helpers.ValidatePassword(passwordInput)) {
                    System.out.println("Invalid password. Please try again.");
                    System.out.print("Enter password: ");
                    passwordInput = input.nextLine();
                }
                String newPasswordInstructor = passwordInput;
                instructor.setPassword(newPasswordInstructor);
            } else if (passwordChoice == 2) {
                System.out.println("Previous password kept.");
            }
            // Option to update email
            System.out.println("\nDo you want to change your email?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int emailChoice = Helpers.getSafeIntInput("Option: ");
            if (emailChoice == 1) {
                System.out.print("\nEnter new email: ");
                String emailInput = input.next();
                input.nextLine(); // Buffer
                while (!Helpers.ValidateEmail(emailInput)) {
                    System.out.println("Invalid email. Please try again.");
                    System.out.print("Enter email: ");
                    emailInput = input.nextLine();
                }
                String newEmailInstructor = emailInput;
                instructor.setEmail(newEmailInstructor);
            } else if (emailChoice == 2) {
                System.out.println("Email remains unchanged.");
            }
            // Option to update phone number
            System.out.println("\nDo you want to change your phone number?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int phoneNumberChoice = Helpers.getSafeIntInput("Option: ");
            if (phoneNumberChoice == 1) {
                System.out.print("\nEnter new phone number: ");
                String phoneNumberInput = input.next();
                input.nextLine(); // Buffer
                while (!Helpers.ValidatePhoneNumber(phoneNumberInput)) {
                    System.out.println("Invalid phone number. Please try again.");
                    System.out.print("Enter phone number: ");
                    phoneNumberInput = input.nextLine();
                }
                String newPhoneInstructor = phoneNumberInput;
                instructor.setPhoneNumber(newPhoneInstructor);
            } else if (phoneNumberChoice == 2) {
                System.out.println("Phone number remains unchanged.");
            }
            // Option to update address
            System.out.println("\nDo you want to change your address?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int addressChoice = Helpers.getSafeIntInput("Option: ");
            if (addressChoice == 1) {
                System.out.print("\nEnter new address: ");
                String addressInput = input.nextLine();
                while (!Helpers.ValidateAddress(addressInput)) {
                    System.out.println("Invalid address. Please try again.");
                    System.out.print("Enter address: ");
                    addressInput = input.nextLine();
                }
                String newAddressInstructor = addressInput;
                instructor.setAddress(newAddressInstructor);
            } else if (addressChoice == 2) {
                System.out.println("Address number remains unchanged.");
            }
            instructor.updateInstructorPersonalInfo(newPassword, newEmail, newPhone, newAddress);
            System.out.println("Instructor " + instructor.getName() + "'s information updated.");
        } catch (Exception e) {
        } finally {
            input.close();
        }
    }

    // STUDENT CASES
    public static void enroll_In_Course_Student(List<Course> courses, Student student) {
        try {
            System.out.println("Courses avaliable to enroll in: ");
            // Sub-List to the main
            List<Course> availableCourses = new ArrayList<>();
            int index = 1;

            for (Course c : courses) {
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
        } catch (Exception e) {
        } finally {
            input.close();
        }
    }

    public static void dropCourse(List<Course> courses, Student student) {
        try {
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
        } catch (Exception e) {
        } finally {
            input.close();
        }
    }

    public static void viewGradesStudent() {
        System.out.println("What would you like to view?");
        System.out.println("1. View grades by exam type.");
        System.out.println("2. View total average grade.");
        int choice = Helpers.getSafeIntInput("Option: ");
        if (choice == 1) {
            student.viewGradesByExamType(grades, student);
        } else if (choice == 2) {
            student.viewAverageGrade(grades, student.getId(), courseCode, examType);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public static void updateStudentProfile(Student student) {
        try {
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
            int passwordChoice = Helpers.getSafeIntInput("Option: ");
            if (passwordChoice == 1) {
                System.out.print("\nEnter new password: ");
                String passwordInput = input.next();
                input.nextLine(); // Buffer
                while (!Helpers.ValidatePassword(passwordInput)) {
                    System.out.println("Invalid password. Please try again.");
                    System.out.print("Enter password: ");
                    passwordInput = input.nextLine();
                }
                String newPasswordStudent = passwordInput;
                student.setPassword(newPasswordStudent);
            } else if (passwordChoice == 2) {
                System.out.println("Previous password kept.");
            }
            // Option to update email
            System.out.println("\nDo you want to change your email?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int emailChoice = Helpers.getSafeIntInput("Option: ");
            if (emailChoice == 1) {
                System.out.print("\nEnter new email: ");
                String emailInput = input.next();
                input.nextLine(); // Buffer
                while (!Helpers.ValidateEmail(emailInput)) {
                    System.out.println("Invalid email. Please try again.");
                    System.out.print("Enter email: ");
                    emailInput = input.nextLine();
                }
                String newEmailStudent = emailInput;
                student.setEmail(newEmailStudent);
            } else if (emailChoice == 2) {
                System.out.println("Email remains unchanged.");
            }
            // Option to update phone number
            System.out.println("\nDo you want to change your phone number?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int phoneNumberChoice = Helpers.getSafeIntInput("Option: ");
            if (phoneNumberChoice == 1) {
                System.out.print("\nEnter new phone number: ");
                String phoneNumberInput = input.next();
                input.nextLine(); // Buffer
                while (!Helpers.ValidatePhoneNumber(phoneNumberInput)) {
                    System.out.println("Invalid phone number. Please try again.");
                    System.out.print("Enter phone number: ");
                    phoneNumberInput = input.nextLine();
                }
                String newPhoneStudent = phoneNumberInput;
                student.setPhoneNumber(newPhoneStudent);
            } else if (phoneNumberChoice == 2) {
                System.out.println("Phone number remains unchanged.");
            }
            // Option to update address
            System.out.println("\nDo you want to change your address?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int addressChoice = Helpers.getSafeIntInput("Option: ");
            input.nextLine(); // Buffer
            if (addressChoice == 1) {
                System.out.print("\nEnter new address: ");
                String addressInput = input.nextLine();
                while (Helpers.ValidateAddress(addressInput)) {
                    System.out.println("Invalid address. Please try again.");
                    System.out.print("Enter address: ");
                    addressInput = input.nextLine();
                }
                String newAddressStudent = addressInput;
                student.setAddress(newAddressStudent);
            } else if (addressChoice == 2) {
                System.out.println("Address number remains unchanged.");
            }
            student.updatePersonalInfo(newPassword, newEmail, newPhone, newAddress);

        } catch (Exception e) {
        } finally {
            input.close();
        }
    }

    // ADMIN CASES
    public static void addStudent(Administrator administrator, List<Student> students) {
        try {
            String studentName = "";
            while (true) {
                System.out.print("\nEnter student name: ");
                studentName = input.nextLine();
                boolean isValid = Helpers.ValidateName(studentName);
                if (!isValid) {
                    System.out.println("Invalid name. Please enter a valid name.");
                    continue;
                }
                String studentID = "";
                while (true) {
                    System.out.print("\nEnter student ID: ");
                    studentID = input.next();
                    input.nextLine(); // Buffer

                    for (Student s : students) {
                        if (s.getId().equals(studentID)) {
                            System.out.println("ID already in use. Please try a different one.");
                            break;
                        }
                    }
                    break;
                }
                // Password
                System.out.print("\nEnter password: ");
                String passwordInput = input.nextLine();
                while (!Helpers.ValidatePassword(passwordInput)) {
                    System.out.println("Invalid password. Please try again.");
                    System.out.print("Enter password: ");
                    passwordInput = input.nextLine();
                }
                String passwordStudent = passwordInput;
                // Email
                System.out.print("\nEnter email: ");
                String emailInput = input.next();
                input.nextLine(); // Buffer
                while (!Helpers.ValidateEmail(emailInput)) {
                    System.out.println("Invalid email. Please try again.");
                    System.out.print("Enter email: ");
                    emailInput = input.nextLine();
                }
                String emailStudent = emailInput;

                // Phone number
                System.out.print("\nEnter phone number: ");
                String phoneNumberInput = input.next();
                input.nextLine(); // Buffer
                while (!Helpers.ValidatePhoneNumber(phoneNumberInput)) {
                    System.out.println("Invalid phone number. Please try again.");
                    System.out.print("Enter phone number: ");
                    phoneNumberInput = input.nextLine();
                }
                String phoneNumberStudent = phoneNumberInput;
                // Address
                System.out.print("\nEnter address: ");
                String addressInput = input.nextLine();
                while (!Helpers.ValidateAddress(addressInput)) {
                    System.out.println("Invalid address. Please try again.");
                    System.out.print("Enter address: ");
                    addressInput = input.nextLine();
                }
                String addressStudent = addressInput;

                System.out.print("\nEnter credit limit: ");
                int creditLimit = input.nextInt();
                input.nextLine(); // Buffer

                Student newStudent = new Student(studentName, studentID, passwordStudent, emailStudent,
                        phoneNumberStudent, addressStudent, creditLimit,
                        new ArrayList<>());
                administrator.addStudent(newStudent, students);

            }
        } catch (Exception e) {
        } finally {
            input.close();
        }
    }

    public static void removeStudent(Administrator administrator, List<Student> students) {
        try {
            System.out.print("\nEnter the ID of the student to remove: ");
            String targetId = input.next();
            input.nextLine(); // Buffer
            administrator.removeStudent(students, targetId);

        } catch (Exception e) {
        } finally {
            input.close();
        }
        System.out.println("Invalid option.");
    }

    public static void addInstructor(Administrator administrator, List<Instructor> instructors) {
        try {
            String name = "";
            while (true) {
                System.out.print("\nEnter instructor name: ");
                name = input.nextLine();
                boolean isValid = Helpers.ValidateName(name);
                if (!isValid) {
                    System.out.println("Invalid name. Please enter a valid name.");
                    continue;
                }
                break;
            }
            String ID = "";
            while (true) {
                System.out.print("\nEnter instructor ID: ");
                ID = input.next();
                input.nextLine(); // Buffer

                for (Instructor instructor : instructors) {
                    if (instructor.getId().equals(ID)) {
                        System.out.println("ID already in use. Please try a different one.");
                        break;
                    }
                }
                break;
            }

            // This will validate input and set it only if correct
            System.out.print("Enter password: ");
            String passwordInput = input.next();
            input.nextLine(); // Buffer
            while (!Helpers.ValidatePassword(passwordInput)) {
                System.out.println("Invalid password. Please try again.");
                System.out.print("Enter password: ");
                passwordInput = input.nextLine();
            }
            String passwordInstructor = passwordInput;
            System.out.print("Enter email: ");
            String emailInput = input.next();
            input.nextLine(); // Buffer
            while (!Helpers.ValidateEmail(emailInput)) {
                System.out.println("Invalid email. Please try again.");
                System.out.print("Enter email: ");
                emailInput = input.nextLine();
            }
            String emailInstructor = emailInput;

            System.out.print("Enter phone number: ");
            String phoneNumberInput = input.next();
            input.nextLine(); // Buffer
            while (!Helpers.ValidatePhoneNumber(phoneNumberInput)) {
                System.out.println("Invalid phone number. Please try again.");
                System.out.print("Enter phone number: ");
                phoneNumberInput = input.nextLine();
            }
            String phoneNumberInstructor = phoneNumberInput;
            System.out.print("\nEnter address: ");
            String addressInput = input.nextLine();
            while (!Helpers.ValidateAddress(addressInput)) {
                System.out.println("Invalid address. Please try again.");
                System.out.print("Enter address: ");
                addressInput = input.nextLine();
            }
            String addressInstructor = addressInput;

            Instructor newInstructor = new Instructor(phoneNumberInput, addressInput, passwordInstructor,
                    emailInstructor, phoneNumberInstructor, User.Role.INSTRUCTOR, addressInstructor, null);
            administrator.addInstructor(newInstructor, instructors);
        } catch (Exception e) {
        } finally {
            input.close();
        }
        System.out.println("Invalid option.");
    }

    public static void removeInstructor(Administrator administrator, List<Instructor> instructors) {
        try {
            System.out.print("\nEnter the ID of the instructor to remove: ");
            String targetId = input.next();
            input.nextLine(); // Buffer
            administrator.removeInstructor(instructors, targetId);
        } catch (Exception e) {
        } finally {
            input.close();
        }
        System.out.println("Invalid option.");
    }

    public static void addCourse(Administrator administrator, List<Course> courses, List<Instructor> instructors) {
        try {
            System.out.println("\nEnter the course's information that you would like to create.");
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
            int courseStatus = Helpers.getSafeIntInput("");
            input.nextLine(); // Buffer

            if (courseStatus == 1) {
                newCourse.setEnrollmentStatus(Course.EnrollmentStatusEnum.Open);
            } else if (courseStatus == 2) {
                newCourse.setEnrollmentStatus(Course.EnrollmentStatusEnum.Closed);
            }
            administrator.addCourse(courses, newCourse);
        } catch (Exception e) {
        } finally {
            input.close();
        }
    }

    public static void adminUpdateStudentProfile(Administrator administrator, List<Student> students) {
        try {
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
                String newPassword = studentToUpdate.getPassword();
                String newEmail = studentToUpdate.getEmail();
                String newPhone = studentToUpdate.getPhoneNumber();
                User.Role newRole = studentToUpdate.getRole();
                String newAddress = studentToUpdate.getAddress();

                // Option to update name
                System.out.println(
                        "Would you like to update " + studentToUpdate.getName() + "'s name?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int nameChoice = input.nextInt();
                if (nameChoice == 1) {
                    System.out.print("Enter new name: ");
                    newName = input.nextLine();
                    while (!Helpers.ValidateName(newName)) {
                        System.out.println("Invalid name. Please try again.");
                        System.out.print("Enter name: ");
                        newName = input.nextLine();
                    }
                    studentToUpdate.setName(newName);
                } else if (nameChoice == 2) {
                    System.out.println("Name remains unchanged.");
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
                    while (!Helpers.ValidateEmail(newEmail)) {
                        System.out.println("Invalid email. Please try again.");
                        System.out.print("Enter email: ");
                        newEmail = input.nextLine();
                    }
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
                    while (!Helpers.ValidatePhoneNumber(newPhone)) {
                        System.out.println("Invalid phone number. Please try again.");
                        System.out.print("Enter phone number: ");
                        newPhone = input.nextLine();
                    }
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
                    while (!Helpers.ValidatePassword(newPassword)) {
                        System.out.println("Invalid password. Please try again.");
                        System.out.print("Enter password: ");
                        newPassword = input.nextLine();
                    }
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
                    newRole = Helpers.checkValidityOfRole();
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
                    while (!Helpers.ValidateAddress(newAddress)) {
                        System.out.println("Invalid address. Please try again.");
                        System.out.print("Enter address: ");
                        newAddress = input.nextLine();
                    }
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
        } catch (Exception e) {
        } finally {
            input.close();
        }
    }

    public static void adminUpdateInstructorProfile(Administrator administrator, List<Instructor> instructors) {
        try {
            System.out.println(
                    "\nPlease enter the requiered information to update instructor's profile.");
            System.out.print("\nEnter instructor name: ");
            String instructorName = input.nextLine();

            System.out.print("\nEnter instructor ID: ");
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
                System.out.println("Update " + instructorToUpdate.getName() + "'s details below: ");

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
                System.out.println("Would you like to update " + instructorToUpdate.getName()
                        + "'s name?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int nameChoice = input.nextInt();
                if (nameChoice == 1) {
                    System.out.print("Enter new name: ");
                    String newNameInput = input.nextLine();
                    while (!Helpers.ValidateName(newNameInput)) {
                        System.out.println("Invalid name. Please try again.");
                        System.out.print("Enter name: ");
                        newName = input.nextLine();
                    }
                    newName = newNameInput;
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
                    String passwordInput = input.next();
                    input.nextLine(); // Buffer
                    while (!Helpers.ValidatePassword(passwordInput)) {
                        System.out.println("Invalid password. Please try again.");
                        System.out.print("Enter password: ");
                        passwordInput = input.nextLine();
                    }
                    newPassword = passwordInput;
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
                    String emailInput = input.next();
                    input.nextLine(); // Buffer
                    while (!Helpers.ValidateEmail(emailInput)) {
                        System.out.println("Invalid email. Please try again.");
                        System.out.print("Enter email: ");
                        emailInput = input.nextLine();
                    }
                    newEmail = emailInput;
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
                    String phoneNumberInput = input.next();
                    input.nextLine(); // Buffer
                    while (!Helpers.ValidatePhoneNumber(phoneNumberInput)) {
                        System.out.println("Invalid phone number. Please try again.");
                        System.out.print("Enter phone number: ");
                        phoneNumberInput = input.nextLine();
                    }
                    newPhone = phoneNumberInput;
                    instructorToUpdate.setPhoneNumber(newPhone);
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
                    System.out.print("Enter new role: ");
                    newRole = Helpers.checkValidityOfRole();
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
                    String addressInput = input.nextLine();
                    while (!Helpers.ValidateAddress(addressInput)) {
                        System.out.println("Invalid address. Please try again.");
                        System.out.print("Enter address: ");
                        addressInput = input.nextLine();
                    }
                    newAddress = addressInput;
                    instructorToUpdate.setAddress(newAddress);
                } else if (addressChoice == 2) {
                    System.out.println("Address remains unchanged.");
                }
            }
        } catch (Exception e) {
        } finally {
            input.close();
        }
    }

    public static void assignInstructor(Administrator administrator, List<Course> courses,
            List<Instructor> instructors) {
        try {
            System.out.println("\nAvailable Courses:");

            for (int i = 0; i < courses.size(); i++) {
                /// The i+1 is for the indext of which the admin will select. The i is for
                /// 'this' spesific course.
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
                administrator.assignInstructor(null, instructorToUpdate2);
            } else {
                System.out.println("Instructor with ID " + instructorID2 + " not found.");
            }
        } catch (Exception e) {
        } finally {
            input.close();
        }
    }

    public static void closeCourse(Administrator administrator, List<Course> courses) {
        try {
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
        } catch (Exception e) {
        } finally {
            input.close();
        }
    }

    public static void viewEnrollmentStatistics() {

    }

    public static void generateReports() {

    }
}