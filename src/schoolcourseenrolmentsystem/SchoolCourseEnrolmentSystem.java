package schoolcourseenrolmentsystem;

import java.util.*;
//import schoolcourseenrolmentsystem.Helpers.*;

public class SchoolCourseEnrolmentSystem {

    // To achieve data constiencey, we instantiate the main lists in here and pass
    // them along other functions to act upon them.
    // Buffer used after each input.nextInt();

    // MAIN LISTS
    private static List<Instructor> Instructors = new ArrayList<>();
    private static List<Administrator> Administrators = new ArrayList<>();
    private static List<Student> Students = new ArrayList<>();
    private static List<Course> Courses = new ArrayList<>();

    // Default admin
    private static Administrator DefaultAdmin = new Administrator("Shoug", "112233445", "Default12345",
            "S.Alomran@gmail.com",
            "0531110904", "Qirawan district");

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            Administrators.add(DefaultAdmin);
            boolean exit = false;

            System.out.println("\nWelcome to School Course Enrolment System!");
            while (!exit) {
                System.out.println(
                        "Enter your role from the current options: (Student/Instructor/Admin) or exit to finish.");
                String role = input.next();
                input.nextLine(); // Buffer

                // Menu
                switch (role.toLowerCase()) {
                    case "student":
                        Student tempStudent = new Student();
                        Student loggedInStudent = Helpers.loginWithRetry(Students, tempStudent);

                        if (loggedInStudent == null) {
                            continue;
                        }
                        Student student = loggedInStudent;
                        // Needed for logout method later on.
                        boolean studentExit = false;
                        while (!studentExit) {
                            Helpers.showStudentMenu();
                            int studentChoice = Helpers.getSafeIntInput("Enter your choice: ");

                            switch (studentChoice) {
                                case 1:
                                    student.viewAvailableCourses(Courses);
                                    break;
                                case 2:
                                    Helpers.enroll_In_Course_Student(Courses, student);
                                    break;
                                case 3:
                                    student.viewEnrolledCourses();
                                    break;
                                case 4:
                                    student.viewCreditLimit(Students);
                                    break;
                                case 5:
                                    Helpers.dropCourse(Courses, student);
                                    break;
                                case 6:
                                    Helpers.viewGradesStudent();
                                    break;
                                case 7:
                                    Helpers.updateStudentProfile(student);
                                    break;
                                case 8:
                                    student.logout(student);
                                    studentExit = true;
                                    break;
                                default:
                                    System.out
                                            .println("No student record was found with the ID and password provided.");
                            }
                        }
                        break;
                    case "instructor":
                        Instructor tempInstructor = new Instructor();

                        Instructor loggedInInstructor = Helpers.loginWithRetry(Instructors, tempInstructor);

                        Instructor instructor = loggedInInstructor;

                        if (loggedInInstructor == null) {
                            continue;
                        }
                        boolean exitInstructor = false;
                        while (!exitInstructor) {
                            Helpers.showInstructorMenu();
                            int instructorChoice = Helpers.getSafeIntInput("Enter your choice: ");
                            input.nextLine(); // Buffer

                            switch (instructorChoice) {
                                case 1:
                                    // The instructor comes from the Instructor class while the courses is a list
                                    instructor.viewEnrolledStudents(instructor, Courses);
                                    break;
                                case 2:
                                    Helpers.editStudentGrades();
                                    ;
                                    break;
                                case 3:
                                    Helpers.updateCourseInfo(instructor, Courses);
                                    break;
                                case 4:
                                    Helpers.updateInstructorProfile(instructor);
                                    break;
                                case 5:
                                    instructor.logout(instructor);
                                    exitInstructor = true;
                                    break;
                                default:
                                    System.out.println(
                                            "No instructor record was found with the ID and password provided.");
                                    break;
                            }
                        }
                        break;
                    case "admin":
                        Administrator temp = new Administrator();
                        Administrator loggedInAdministrator = Helpers.loginWithRetry(Administrators, temp);

                        if (loggedInAdministrator == null) {
                            continue;
                        }
                        Administrator administrator = loggedInAdministrator;

                        boolean exitAdmin = false;
                        while (!exitAdmin) {
                            Helpers.showAdminMenu();
                            System.out.println();
                            int adminChoice = Helpers.getSafeIntInput("Enter your option: ");

                            switch (adminChoice) {
                                case 1:
                                    Helpers.addStudent(administrator, Students);
                                    break;
                                case 2:
                                    Helpers.removeStudent(administrator, Students);
                                    break;
                                case 3:
                                    Helpers.addInstructor(administrator, Instructors);
                                    break;
                                case 4:
                                    Helpers.removeInstructor(administrator, Instructors);
                                    break;
                                case 5:
                                    Helpers.addCourse(administrator, Courses, Instructors);
                                    break;
                                case 6:
                                    Helpers.adminUpdateStudentProfile(administrator, Students);
                                    break;
                                case 7:
                                    Helpers.adminUpdateInstructorProfile(administrator, Instructors);
                                    break;
                                case 8:
                                    Helpers.assignInstructor(administrator, Courses, Instructors);
                                    break;
                                case 9:
                                    Helpers.closeCourse(administrator, Courses);
                                    break;
                                case 10:
                                    Helpers.viewEnrollmentStatistics();
                                    break;
                                case 11:
                                    Helpers.generateReports();
                                    break;
                                case 12:
                                    System.out.println(administrator.logout(administrator));
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            input.close();
        }
    }
}