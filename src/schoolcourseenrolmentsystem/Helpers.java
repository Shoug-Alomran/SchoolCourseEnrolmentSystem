package schoolcourseenrolmentsystem;

import java.util.*;
import java.util.function.Predicate;

public class Helpers {
    public static final Scanner input = new Scanner(System.in);

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
            }
        }
    }

    /// Method created to avoid InputMismatchException
    public static int getSafeIntInput(String prompt) {
        try {
            int number = -1; // -1 is typically related to false or not true. This is a default number.
            boolean valid = false;
            while (!valid) {
                System.out.print(prompt);
                number = input.nextInt();
                input.nextLine(); // Buffer
                valid = true;
            }
            return number;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            input.nextLine(); // Clear the invalid input
            return getSafeIntInput(prompt); // Retry
        }
    }

    public static User.Role updateRolePrompt(User.Role currentRole) {
        System.out.println("\nDo you want to change the role?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = getSafeIntInput("Option: ");
        input.nextLine();

        if (choice == 1) {
            return checkValidityOfRole(); // Assuming you already have this
        } else {
            System.out.println("Role remains unchanged.");
            return currentRole;
        }
    }

    // Predicate <> helps us use premade validators.
    public static String updateFieldWithPrompt(String fieldName, String currentValue, Predicate<String> validator) {
        System.out.println("\nDo you want to change your " + fieldName + "?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int choice = Helpers.getSafeIntInput("Option: ");
        input.nextLine(); // Clear buffer

        if (choice == 1) {
            System.out.print("Enter new " + fieldName + ": ");
            String newValue = input.nextLine();
            while (!validator.test(newValue)) {
                System.out.println("Invalid " + fieldName + ". Please try again.");
                System.out.print("Enter new " + fieldName + ": ");
                newValue = input.nextLine();
            }
            return newValue;
        } else {
            System.out.println(fieldName + " remains unchanged.");
            return currentValue;
        }
    }

    public static String createUserWithPrompt(String fieldName, Predicate<String> validator) {
        String inputValue;
        while (true) {
            System.out.print("\nEnter " + fieldName + ": ");
            inputValue = input.nextLine();
            if (!validator.test(inputValue)) { // .test() method is just how you invoke the logic inside the Predicate
                                               // <>
                System.out.println("Invalid " + fieldName + ". Please try again.");
            } else {
                break;
            }
        }
        return inputValue;
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
        }
    }

    public static String GenerateRandomID() {
        String CHARACTERS = "0123456789";
        int ID_LENGTH = 10;
        Random random = new Random();

        StringBuilder id = new StringBuilder(ID_LENGTH);

        for (int i = 0; i < ID_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            id.append(randomChar);
        }
        return id.toString();
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
        }
    }

    public static void updateInstructorProfile(Instructor instructor) {
        try {
            System.out.println("\nPlease fill out the form to update your profile.");
            String updatedPassword = Helpers.updateFieldWithPrompt("password", instructor.getPassword(),
                    Helpers::ValidatePassword); // Helpers::ValidatePassword is like an inhanced for-each loop
            String updateEmail = Helpers.updateFieldWithPrompt("email", instructor.getEmail(), Helpers::ValidateEmail);
            String updatePhone = Helpers.updateFieldWithPrompt("phone number", instructor.getPhoneNumber(),
                    Helpers::ValidatePhoneNumber);
            String updateAddress = Helpers.updateFieldWithPrompt("address", instructor.getAddress(),
                    Helpers::ValidateAddress);
            instructor.updateInstructorPersonalInfo(updatedPassword, updateEmail, updatePhone, updateAddress);
            System.out.println("Instructor " + instructor.getName() + "'s information updated.");
        } catch (Exception e) {
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
        }
    }

    // TO-DO
    // public static void viewGradesStudent(Student student) {
    // System.out.println("What would you like to view?");
    // System.out.println("1. View grades by exam type.");
    // System.out.println("2. View total average grade.");
    // int choice = Helpers.getSafeIntInput("Option: ");
    // if (choice == 1) {
    // student.viewGradesByExamType(grades, student);
    // } else if (choice == 2) {
    // student.viewAverageGrade(grades, student.getId(), courseCode, examType);
    // } else {
    // System.out.println("Invalid choice.");
    // }
    // }

    public static void updateStudentProfile(Student student) {
        try {
            System.out.println("\nPlease enter the needed information to update your profile.");
            String updatedPassword = Helpers.updateFieldWithPrompt("password", student.getPassword(),
                    Helpers::ValidatePassword); // Helpers::ValidatePassword is like an inhanced for-each loop
            String updateEmail = Helpers.updateFieldWithPrompt("email", student.getEmail(), Helpers::ValidateEmail);
            String updatePhone = Helpers.updateFieldWithPrompt("phone number", student.getPhoneNumber(),
                    Helpers::ValidatePhoneNumber);
            String updateAddress = Helpers.updateFieldWithPrompt("address", student.getAddress(),
                    Helpers::ValidateAddress);

            student.updateStudentPersonalInfo(updatedPassword, updateEmail, updatePhone, updateAddress);
        } catch (Exception e) {
        }
    }

    // ADMIN CASES
    public static void addStudent(Administrator administrator, List<Student> students) {
        try {
            String studentName = "";
            while (true) {
                System.out.print("\nEnter student name: ");
                studentName = input.nextLine();

                if (!Helpers.ValidateName(studentName)) {
                    System.out.println("Invalid name. Please enter a valid name.");
                    continue;
                }
                break;
            }
            String studentID;
            while (true) {
                studentID = GenerateRandomID();
                boolean exists = false;
                for (Student s : students) {
                    if (s.getId().equals(studentID)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    break; // We found a unique ID, exit the loop
                }
            }
            String createPassword = Helpers.createUserWithPrompt("password", Helpers::ValidatePassword);
            String createEmail = Helpers.createUserWithPrompt("email", Helpers::ValidateEmail);
            String createPhoneNumber = Helpers.createUserWithPrompt("phone number", Helpers::ValidatePhoneNumber);
            String createAddress = Helpers.createUserWithPrompt("address", Helpers::ValidateAddress);
            System.out.print("\nEnter credit limit: ");
            int creditLimit = input.nextInt();
            input.nextLine(); // Buffer
            // TO-DO
            Student newStudent = new Student(studentName, studentID, createPassword, createEmail,
                    createPhoneNumber, createAddress, creditLimit,
                    new ArrayList<>());
            administrator.addStudent(newStudent, students);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeStudent(Administrator administrator, List<Student> students) {
        try {
            System.out.print("\nEnter the ID of the student to remove: ");
            String targetId = input.next();
            input.nextLine(); // Buffer
            administrator.removeStudent(students, targetId);

        } catch (Exception e) {
        }
        System.out.println("Invalid option.");
    }

    public static void addInstructor(Administrator administrator, List<Instructor> instructors) {
        try {
            String instructorName = "";
            while (true) {
                System.out.print("\nEnter instructor name: ");
                instructorName = input.nextLine();
                if (!Helpers.ValidateName(instructorName)) {
                    System.out.println("Invalid name. Please enter a valid name.");
                    continue;
                }
                break;
            }
            String instructorID;
            while (true) {
                instructorID = GenerateRandomID();
                boolean exists = false;
                for (Instructor instructor : instructors) {
                    if (instructor.getId().equals(instructorID)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    break;
                }
            }
            String createPassword = Helpers.createUserWithPrompt("password", Helpers::ValidatePassword);
            String createEmail = Helpers.createUserWithPrompt("email", Helpers::ValidateEmail);
            String createPhoneNumber = Helpers.createUserWithPrompt("phone number", Helpers::ValidatePhoneNumber);
            String createAddress = Helpers.createUserWithPrompt("address", Helpers::ValidateAddress);

            Instructor newInstructor = new Instructor(instructorName,
                    instructorID, createPassword,
                    createEmail, createPhoneNumber,
                    createAddress, new ArrayList<>());
            administrator.addInstructor(newInstructor, instructors);
        } catch (Exception e) {
            System.out.println("Invalid option.");
        }
    }

    public static void removeInstructor(Administrator administrator, List<Instructor> instructors) {
        try {
            System.out.print("\nEnter the ID of the instructor to remove: ");
            String targetId = input.next();
            input.nextLine(); // Buffer
            administrator.removeInstructor(instructors, targetId);
        } catch (Exception e) {
            System.out.println("Invalid option.");
        }
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

                String updateName = Helpers.updateFieldWithPrompt("name", studentToUpdate.getName(),
                        Helpers::ValidateName);
                String updatePassword = Helpers.updateFieldWithPrompt("password", studentToUpdate.getPassword(),
                        Helpers::ValidatePassword);
                String updateEmail = Helpers.updateFieldWithPrompt("email", studentToUpdate.getEmail(),
                        Helpers::ValidateEmail);
                String updatePhoneNumber = Helpers.updateFieldWithPrompt("phone number",
                        studentToUpdate.getPhoneNumber(), Helpers::ValidatePhoneNumber);
                String updateAddress = Helpers.updateFieldWithPrompt("address", studentToUpdate.getAddress(),
                        Helpers::ValidateAddress);
                User.Role updateRole = Helpers.updateRolePrompt(studentToUpdate.getRole());
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
                // Apply updates
                studentToUpdate.setName(updateName);
                studentToUpdate.setPassword(updatePassword);
                studentToUpdate.setEmail(updateEmail);
                studentToUpdate.setPhoneNumber(updatePhoneNumber);
                studentToUpdate.setAddress(updateAddress);
                studentToUpdate.setRole(updateRole);

                System.out.println("Student " + updateName + "'s profile updated successfully.");
            }
        } catch (Exception e) {
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

                String updateName = Helpers.updateFieldWithPrompt("name", instructorToUpdate.getName(),
                        Helpers::ValidateName);
                String updatePassword = Helpers.updateFieldWithPrompt("password", instructorToUpdate.getPassword(),
                        Helpers::ValidatePassword);
                String updateEmail = Helpers.updateFieldWithPrompt("email", instructorToUpdate.getEmail(),
                        Helpers::ValidateEmail);
                String updatePhoneNumber = Helpers.updateFieldWithPrompt("phone number",
                        instructorToUpdate.getPhoneNumber(), Helpers::ValidatePhoneNumber);
                String updateAddress = Helpers.updateFieldWithPrompt("address", instructorToUpdate.getAddress(),
                        Helpers::ValidateAddress);
                User.Role updateRole = Helpers.updateRolePrompt(instructorToUpdate.getRole());
                // Apply updates
                instructorToUpdate.setName(updateName);
                instructorToUpdate.setPassword(updatePassword);
                instructorToUpdate.setEmail(updateEmail);
                instructorToUpdate.setPhoneNumber(updatePhoneNumber);
                instructorToUpdate.setAddress(updateAddress);
                instructorToUpdate.setRole(updateRole);

                System.out.println("Instructor " + updateName + "'s profile updated successfully.");
            }
        } catch (Exception e) {
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
        }
    }

    public static void viewEnrollmentStatistics() {

    }

    public static void generateReports() {

    }
}