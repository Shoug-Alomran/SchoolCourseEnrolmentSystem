
package schoolcourseenrolmentsystem;

import java.util.*;
//import schoolcourseenrolmentsystem.Helpers.*;

public class SchoolCourseEnrolmentSystem {

    // We placed the lists here in order to pass them only once instead of having
    // many lists.
    // Buffer used after each input.nextInt();

    // MAIN LISTS
    private static List<Instructor> instructors = new ArrayList<>();
    private static List<Administrator> administrators = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();// List to store enrolled students
    private static Set<Course> courses = new HashSet<>(); // List to store courses based on unique key, no duplicates allowed.
    private static Set<String> allUserIds = new HashSet<>(); // We don't want duplicates.
    
    public static void main(String[] args) {
        // I'm putting them outside the switch because multiple cases use them so this
        // helps my code be less dense.

        boolean exit = false;
        Scanner input = new Scanner(System.in);

        Administrator defaultAdmin = new Administrator("Shoug", "1127357489", "Default12345", "S.Alomran@gmail.com",
                "0531110904", User.Role.ADMIN, "Qirawan district");
        administrators.add(defaultAdmin);
        // Grades gradeManager = new Grades(null, null, null, 0);

        System.out.println("\nWelcome to School Course Enrolment System!");
        while (!exit) {
            System.out
                    .println("Enter your role from the current options: (Student/Instructor/Admin) or exit to finish.");
            String role = input.next();
            input.nextLine(); // Buffer

            // Menu
            switch (role.toLowerCase()) {
                case "student":
                    // We need to let an student log in. To do that, we must call the login
                    // method from the Student class. But login is not a static method, so we
                    // can’t call it without an object. So we create a fake (temporary) student
                    // with dummy info, just to access the login method.
                    Student tempStudent = new Student("temp", "temp", "temp", "temp", "temp", "Student", "temp", 0,
                            null);
                    Student loggedInStudent = Helpers.loginWithRetry(students, input, tempStudent);
                    /// Inside the switch the student logged in is the student that will be able to
                    /// control its own information.
                    // If login fails, skip to next loop iteration (try another role).
                    if (loggedInStudent == null) {
                        continue;
                    }
                    Student student = loggedInStudent;
                    // Needed for logout method later on.
                    boolean studentExit = false;
                    while (!studentExit) {
                        Helpers.showStudentMenu();
                        int studentChoice = Helpers.getSafeIntInput(input, "Enter your choice: ");

                        switch (studentChoice) {
                            case 1:
                                student.viewAvailableCourses(courses);
                                break;
                            case 2:
                                Helpers.studentCase1(courses, student, input);
                                break;
                            case 3:
                                student.viewEnrolledCourses();
                                break;
                            case 4:
                                student.viewCreditLimit(students);
                                break;
                            case 5:
                                Helpers.studentCase5(courses, student, input);
                                break;
                            case 6:

                                break;
                            case 7:
                                Helpers.studentCase7(student, input);
                                break;
                            case 8:
                                student.logout(student);
                                studentExit = true;
                                break;
                            default:
                                System.out.println("No student record was found with the ID and password provided.");
                        }
                    }
                    break;
                case "instructor":
                    // We need to let an instructor log in. To do that, we must call the login
                    // method from the Instructor class. But login is not a static method, so we
                    // can’t call it without an object. So we create a fake (temporary) instructor
                    // with dummy info, just to access the login method.
                    Instructor tempInstructor = new Instructor("temp", "temp", "temp", "temp", "temp",
                            User.Role.INSTRUCTOR,
                            "temp", new ArrayList<>());
                    // We now use that temp object to try logging in using the list of real
                    // instructors.
                    Instructor loggedInInstructor = Helpers.loginWithRetry(instructors, input, tempInstructor);
                    // If login works, we store the result (the actual instructor) in a new variable
                    // to use later.
                    Instructor instructor = loggedInInstructor;

                    // If login fails, skip to next loop iteration (try another role).
                    if (loggedInInstructor == null) {
                        continue;
                    }
                    boolean exitInstructor = false;
                    while (!exitInstructor) {
                        Helpers.showInstructorMenu();
                        int instructorChoice = Helpers.getSafeIntInput(input, "Enter your choice: ");
                        input.nextLine(); // Buffer

                        switch (instructorChoice) {
                            case 1:
                                // The instructor comes from the Instructor class while the courses is a list
                                instructor.viewEnrolledStudents(instructor, courses);
                                break;
                            case 2:
                                Helpers.instructorCase2(input);
                                break;
                            case 3:
                                Helpers.instructorCase3(instructor, courses, input);
                                break;
                            case 4:
                                Helpers.instructorCase4(instructor, input);
                                break;
                            case 5:
                                instructor.logout(instructor);
                                exitInstructor = true;
                                break;
                            default:
                                System.out.println("No instructor record was found with the ID and password provided.");
                                break;
                        }
                    }
                    break;
                case "admin":
                    // We need to let an Admin login. To do that, we must call the login
                    // method from the Admin class. But login is not a static method, so we
                    // can’t call it without an object. So we create a fake (temporary) admin
                    // with dummy info, just to access the login method.
                    Administrator temp = new Administrator("temp", "temp",
                            "temp", "temp", "temp", User.Role.ADMIN, "temp");
                    // We now use that temp object to try logging in using the list of real admins.
                    Administrator loggedInAdministrator = Helpers.loginWithRetry(administrators, input, temp);

                    // If login fails, skip to next loop iteration (try another role).
                    if (loggedInAdministrator == null) {
                        continue;
                    }
                    // If everything is ok, we can now use the loggedInAdministrator object.
                    Administrator administrator = loggedInAdministrator;

                    boolean exitAdmin = false;
                    while (!exitAdmin) {
                        Helpers.showAdminMenu();
                        System.out.println();
                        int adminChoice = Helpers.getSafeIntInput(input, "Enter your option: ");

                        switch (adminChoice) {
                            case 1:
                                Helpers.adminCase1(administrator, students,allUserIds, input);
                                break;
                            case 2:
                                Helpers.adminCase2(administrator, instructors, allUserIds, input);
                                break;
                            case 3:
                                Helpers.adminCase3(administrator, courses, instructors, input);

                                break;
                            case 4:
                                Helpers.adminCase4(administrator, students, input);
                                break;
                            case 5:
                                Helpers.adminCase5(administrator, instructors, input);
                                break;
                            case 6:
                                Helpers.adminCase6(administrator, courses, instructors, input);
                                break;
                            case 7:
                                Helpers.adminCase7(administrator, courses, input);
                                break;
                            case 8:
                                Helpers.adminCase8();
                                break;
                            case 9:
                                Helpers.adminCase9();
                                break;
                            case 10:
                                administrator.logout(administrator);
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