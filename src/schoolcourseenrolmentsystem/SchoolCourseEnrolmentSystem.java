
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
    private static List<Course> courses = new ArrayList<>(); // List to store courses

    public static void main(String[] args) {
        // I'm putting them outside the switch because multiple cases use them so this
        // helps my code be less dense.

        boolean exit = false;
        Scanner input = new Scanner(System.in);

        Administrator defaultAdmin = new Administrator("Shoug", "1127357489", "Default12345", "S.Alomran@gmail.com",
                "0531110904", "Administrator", "Qirawan district");
        administrators.add(defaultAdmin);
        // Grades gradeManager = new Grades(null, null, null, 0);

        System.out.println("\nWelcome to School Course Enrolment System!");
        while (!exit) {
            System.out
                    .println("Enter your role from the current options: (Student/Instructor/Admin) or exit to finish.");
            String role = input.next();

            // Menu
            switch (role.toLowerCase()) {
                case "student":
                    // call login method

                    System.out.print("\nPlease enter your ID: ");
                    String studentID = input.next();
                    System.out.print("\nPlease enter your Password: ");
                    String studentPassword = input.next();

                    Student tempStudent = new Student("temp", "temp", "temp", "temp", "temp", "Student", "temp", 0,
                            null);
                    Student loggedInStudent = tempStudent.login(students, studentID, studentPassword);
                    /// Inside the switch the student logged in is the student that will be able to
                    /// control its own information.
                    if (loggedInStudent == null) {
                        break; // Don't continue if login fails
                    }
                    Student student = loggedInStudent;
                    // Needed for logout method later on.
                    boolean studentExit = false;
                    while (!studentExit) {
                        Helpers.showStudentMenu();
                        int studentChoice = input.nextInt();
                        input.nextLine(); // Buffer

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
                                Helpers.studentCase4(courses, student, input);
                                break;
                            case 5:

                                break;
                            case 6:
                                Helpers.studentCase6(student, students, input);
                                break;
                            case 7:
                                student.logout(student);
                                studentExit = true;
                                break;
                            default:
                                System.out.println("No student record was found with the ID and password provided.");
                        }
                    }
                    break;
                case "instructor":
                    // Login first
                    System.out.print("\nPlease enter your ID: ");
                    String instructorID = input.next();
                    System.out.print("\nPlease enter your Password: ");
                    String instructorPassword = input.next();

                    // The temporary temp object is only created so you can call the login() method
                    // defined in the Instructor class (which is inherited from User<Instructor>).
                    Instructor tempInstructor = new Instructor("temp", "temp", "temp", "temp", "temp", "Instructor",
                            "temp",
                            new ArrayList<>());
                    Instructor loggedInInstructor = tempInstructor.login(instructors, instructorID, instructorPassword);
                    Instructor instructor = loggedInInstructor;
                    
                    if (loggedInInstructor == null) {
                        break; // Don't continue if login fails
                    }
                    boolean exitInstructor = false;
                    while (!exitInstructor) {
                        Helpers.showInstructorMenu();
                        int instructorChoice = input.nextInt();
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
                    // Login first.
                    Administrator temp = new Administrator("temp", "temp",
                            "temp", "temp", "temp", "Administrator", "temp");
                    Administrator loggedInAdministrator = Helpers.loginWithRetry(administrators, input, temp);
                    
                   
                    if (loggedInAdministrator == null) {
                        break; // Don't continue if login fails
                    }
                    // If everything is ok, we can now use the loggedInAdministrator object.
                    Administrator administrator = loggedInAdministrator;

                    boolean exitAdmin = false;
                    while (!exitAdmin) {
                        Helpers.showAdminMenu();
                        System.out.println();
                        int adminChoice = input.nextInt();
                        input.nextLine();

                        switch (adminChoice) {
                            case 1:
                                Helpers.adminCase1(administrator, students, input);
                                break;
                            case 2:
                                Helpers.adminCase2(administrator, instructors, input);
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