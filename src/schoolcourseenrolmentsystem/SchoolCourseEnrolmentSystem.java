// package schoolcourseenrolmentsystem;

// import java.util.*;

// public class SchoolCourseEnrolmentSystem {
//     // To achieve data constiencey, we instantiate the main lists in here and pass
//     // them along other functions to act upon them.

//     // MAIN LISTS
//     private static List<Instructor> Instructors = new ArrayList<>();
//     private static List<Administrator> Administrators = new ArrayList<>();
//     private static List<Student> Students = new ArrayList<>();
//     private static List<Course> Courses = new ArrayList<>();
//     private static List<Assessment> grades = new ArrayList<>();

//     // Default admin
//     private static Administrator DefaultAdmin = new Administrator("Shoug", "1122334455", "Default12345",
//             "S.Alomran@gmail.com",
//             "0531110904", "Qirawan district");

//     public static void main(String[] args) {
//         Scanner input = Helpers.input;
//         try {
//             Administrators.add(DefaultAdmin);
//             boolean exit = false;

//             System.out.println("\nWelcome to School Course Enrolment System!");
//             while (!exit) {
//                 System.out.println(
//                         "Enter your role from the current options: (Student/Instructor/Admin) or exit to finish.");
//                 String role = input.next();
//                 input.nextLine(); // Buffer

//                 // Menu
//                 switch (role.toLowerCase()) {
//                     case "student":
//                         Student tempStudent = new Student();
//                         Student loggedInStudent = Helpers.login(Students, tempStudent);

//                         if (loggedInStudent == null) {
//                             continue;
//                         }
//                         Student student = loggedInStudent;
//                         // Needed for logout method later on.
//                         boolean studentExit = false;
//                         while (!studentExit) {
//                             Helpers.showStudentMenu();
//                             int studentChoice = Helpers.getSafeIntInput("Option: ");

//                             switch (studentChoice) {
//                                 case 1:
//                                     student.viewAvailableCourses(Courses);
//                                     break;
//                                 case 2:
//                                     Helpers.enroll_In_Course_Student(Courses, student);
//                                     break;
//                                 case 3:
//                                     student.viewEnrolledCourses();
//                                     break;
//                                 case 4:
//                                     student.viewCreditLimit(Students);
//                                     break;
//                                 case 5:
//                                     Helpers.dropCourse(Courses, student);
//                                     break;
//                                 case 6:
//                                     student.viewGrades(grades);
//                                     break;
//                                 case 7:
//                                     Helpers.updateStudentProfile(student);
//                                     break;
//                                 case 8:
//                                     student.logout(student);
//                                     studentExit = true;
//                                     break;
//                                 default:
//                                     System.out
//                                             .println("No student record was found with the ID and password provided.");
//                             }
//                         }
//                         break;
//                     case "instructor":
//                         Instructor tempInstructor = new Instructor();

//                         Instructor loggedInInstructor = Helpers.login(Instructors, tempInstructor);

//                         Instructor instructor = loggedInInstructor;

//                         if (loggedInInstructor == null) {
//                             continue;
//                         }
//                         boolean exitInstructor = false;
//                         while (!exitInstructor) {
//                             Helpers.showInstructorMenu();
//                             int instructorChoice = Helpers.getSafeIntInput("Enter your choice: ");

//                             switch (instructorChoice) {
//                                 case 1:
//                                     // The instructor comes from the Instructor class while the courses is a list
//                                     instructor.viewEnrolledStudents(instructor, Courses);
//                                     break;
//                                 case 2:
//                                     instructor.gradeStudent(grades, Courses);
//                                     break;
//                                 case 3:
//                                     Helpers.updateCourseInfo(instructor, Courses);
//                                     break;
//                                 case 4:
//                                     Helpers.updateInstructorProfile(instructor);
//                                     break;
//                                 case 5:
//                                     instructor.logout(instructor);
//                                     exitInstructor = true;
//                                     break;
//                                 default:
//                                     System.out.println(
//                                             "No instructor record was found with the ID and password provided.");
//                                     break;
//                             }
//                         }
//                         break;
//                     case "admin":
//                         Administrator temp = new Administrator();
//                         Administrator loggedInAdministrator = Helpers.login(Administrators, temp);
//                         // 12we4rdtfghvnbma, 3940916830
//                         if (loggedInAdministrator == null) {
//                             continue;
//                         }
//                         Administrator administrator = loggedInAdministrator;

//                         boolean exitAdmin = false;
//                         while (!exitAdmin) {
//                             Helpers.showAdminMenu();
//                             System.out.println();
//                             int adminChoice = Helpers.getSafeIntInput("\nEnter your option: ");

//                             switch (adminChoice) {
//                                 case 1:
//                                     Helpers.addStudent(administrator, Students);
//                                     break;
//                                 case 2:
//                                     Helpers.removeStudent(administrator, Students);
//                                     break;
//                                 case 3:
//                                     administrator.viewStudentList(Students);
//                                     break;
//                                 case 4:
//                                     Helpers.addInstructor(administrator, Instructors);
//                                     break;
//                                 case 5:
//                                     Helpers.removeInstructor(administrator, Instructors);
//                                     break;
//                                 case 6:
//                                     administrator.viewInstructorList(Instructors);
//                                     break;
//                                 case 7:
//                                     Helpers.addCourse(administrator, Courses, Instructors);
//                                     break;
//                                 case 8:
//                                     Helpers.closeCourse(administrator, Courses);
//                                     break;
//                                 case 9:
//                                     Helpers.adminUpdateStudentProfile(administrator, Students);
//                                     break;
//                                 case 10:
//                                     Helpers.adminUpdateInstructorProfile(administrator, Instructors);
//                                     break;
//                                 case 11:
//                                     Helpers.assignInstructor(administrator, Courses, Instructors);
//                                     break;
//                                 case 12:
//                                     Helpers.viewEnrollmentStatistics(Courses);
//                                     break;
//                                 case 13:
//                                     Helpers.generateReports(Courses);
//                                     break;
//                                 case 14:
//                                     administrator.logout(administrator);
//                                     exitAdmin = true;
//                                     break;
//                                 default:
//                                     System.out.println(
//                                             "No administrator record was found with the ID and password provided.");
//                             }
//                         }
//                         break;
//                     case "exit":
//                         exit = true;
//                         System.out.println("Goodbye.");
//                         break;
//                     default:
//                         System.out.println("Invalid role. Try again.");
//                 }
//             }
//         } catch (Exception e) {
//             System.out.println(e.getMessage());
//         }
//     }
// }
package schoolcourseenrolmentsystem;

import java.util.*;

public class SchoolCourseEnrolmentSystem {
    public static dataManager dataManager = DataManager.getInstance();

    public static void main(String[] args) {
        Scanner input = Helpers.input;

        dataManager.loadAllData();

        try {
            boolean exit = false;

            System.out.println("\nWelcome to School Course Enrolment System!");
            while (!exit) {
                System.out.println(
                        "Enter your role from the current options: (Student/Instructor/Admin) or exit to finish.");
                String role = input.next();
                input.nextLine();

                switch (role.toLowerCase()) {
                    case "student":
                        Student tempStudent = new Student();
                        Student loggedInStudent = Helpers.login(dataManager.getAllStudents(), tempStudent);

                        if (loggedInStudent == null) {
                            continue;
                        }
                        boolean studentExit = false;
                        while (!studentExit) {
                            Helpers.showStudentMenu();
                            int studentChoice = Helpers.getSafeIntInput("Option: ");

                            switch (studentChoice) {
                                case 1:
                                    loggedInStudent.viewAvailableCourses(dataManager.getAllCourses());
                                    break;
                                case 2:
                                    Helpers.enroll_In_Course_Student(dataManager.getAllCourses(), loggedInStudent);
                                    break;
                                case 3:
                                    loggedInStudent.viewEnrolledCourses();
                                    break;
                                case 4:
                                    loggedInStudent.viewCreditLimit(dataManager.getAllStudents());
                                    break;
                                case 5:
                                    Helpers.dropCourse(dataManager.getAllCourses(), loggedInStudent);
                                    break;
                                case 6:
                                    loggedInStudent.viewGrades(dataManager.getAllAssessments());
                                    break;
                                case 7:
                                    Helpers.updateStudentProfile(loggedInStudent);
                                    dataManager.saveStudents(); // Save after profile update
                                    break;
                                case 8:
                                    loggedInStudent.logout(loggedInStudent);
                                    studentExit = true;
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                            }
                        }
                        break;
                    case "instructor":
                        Instructor tempInstructor = new Instructor();
                        Instructor loggedInInstructor = Helpers.login(dataManager.getAllInstructors(), tempInstructor);

                        if (loggedInInstructor == null) {
                            continue;
                        }
                        boolean exitInstructor = false;
                        while (!exitInstructor) {
                            Helpers.showInstructorMenu();
                            int instructorChoice = Helpers.getSafeIntInput("Enter your choice: ");

                            switch (instructorChoice) {
                                case 1:
                                    loggedInInstructor.viewEnrolledStudents(loggedInInstructor,
                                            dataManager.getAllCourses());
                                    break;
                                case 2:
                                    loggedInInstructor.gradeStudent(dataManager.getAllAssessments(),
                                            dataManager.getAllCourses());
                                    break;
                                case 3:
                                    Helpers.updateCourseInfo(loggedInInstructor, dataManager.getAllCourses());
                                    dataManager.saveCourses(); // Save after course update
                                    break;
                                case 4:
                                    Helpers.updateInstructorProfile(loggedInInstructor);
                                    dataManager.saveInstructors(); // Save after profile update
                                    break;
                                case 5:
                                    loggedInInstructor.logout(loggedInInstructor);
                                    exitInstructor = true;
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                            }
                        }
                        break;
                    case "admin":
                        Administrator temp = new Administrator();
                        Administrator loggedInAdministrator = Helpers.login(dataManager.getAllAdministrators(), temp);

                        if (loggedInAdministrator == null) {
                            continue;
                        }
                        boolean exitAdmin = false;
                        while (!exitAdmin) {
                            Helpers.showAdminMenu();
                            int adminChoice = Helpers.getSafeIntInput("\nEnter your option: ");

                            switch (adminChoice) {
                                case 1:
                                    Helpers.addStudent(loggedInAdministrator, dataManager.getAllStudents());
                                    break;
                                case 2:
                                    Helpers.removeStudent(loggedInAdministrator, dataManager.getAllStudents());
                                    break;
                                case 3:
                                    loggedInAdministrator.viewStudentList(dataManager.getAllStudents());
                                    break;
                                case 4:
                                    Helpers.addInstructor(loggedInAdministrator, dataManager.getAllInstructors());
                                    break;
                                case 5:
                                    Helpers.removeInstructor(loggedInAdministrator, dataManager.getAllInstructors());
                                    break;
                                case 6:
                                    loggedInAdministrator.viewInstructorList(dataManager.getAllInstructors());
                                    break;
                                case 7:
                                    Helpers.addCourse(loggedInAdministrator, dataManager.getAllCourses(),
                                            dataManager.getAllInstructors());
                                    break;
                                case 8:
                                    Helpers.closeCourse(loggedInAdministrator, dataManager.getAllCourses());
                                    break;
                                case 9:
                                    Helpers.adminUpdateStudentProfile(loggedInAdministrator,
                                            dataManager.getAllStudents());
                                    dataManager.saveStudents(); // Save after profile update
                                    break;
                                case 10:
                                    Helpers.adminUpdateInstructorProfile(loggedInAdministrator,
                                            dataManager.getAllInstructors());
                                    dataManager.saveInstructors(); // Save after profile update
                                    break;
                                case 11:
                                    Helpers.assignInstructor(loggedInAdministrator, dataManager.getAllCourses(),
                                            dataManager.getAllInstructors());
                                    break;
                                case 12:
                                    dataManager.viewEnrollmentStatistics();
                                    break;
                                case 13:
                                    dataManager.generateReports();
                                    break;
                                case 14:
                                    loggedInAdministrator.logout(loggedInAdministrator);
                                    exitAdmin = true;
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                            }
                        }
                        break;
                    case "exit":
                        exit = true;
                        dataManager.saveAllData(); // Save all data before exiting
                        System.out.println("Goodbye.");
                        break;
                    default:
                        System.out.println("Invalid role. Try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}