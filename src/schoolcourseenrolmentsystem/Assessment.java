
package schoolcourseenrolmentsystem;

import java.util.List;
import java.util.Scanner;

public class Assessment {

    public enum ExamType {
        Quiz_1,
        Quiz_2,
        Quiz_3,
        Quiz_4,
        Midterm_1,
        Midterm_2,
        Final,
        Project
    }

    // Attributes
    private String studentId;
    private String courseCode;
    private ExamType examType;
    private double score;
    private String assessmentName;

    // Constructor
    public Assessment(String studentId, String courseCode, ExamType examType, double score, String assessmentName) {
        setStudentId(studentId);
        setCourseCode(courseCode);
        setExamType(examType);
        setScore(score);
        setAssessmentName(assessmentName);
    }

    // Setters & Getters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    // Methods
    public void assignGrade(List<Assessment> grades, String studentId, String courseCode, Assessment.ExamType examType,
            double score) {
        Assessment gradeCheck = null;

        for (Assessment g : grades) {
            if (g.getCourseCode().equals(courseCode) && g.getStudentId().equals(studentId)
                    && g.getExamType().equals(examType)) {
                gradeCheck = g;
                g.setScore(score); // Update existing score
                System.out.println("Grade updated successfully.");
                break;
            }
        }
        if (gradeCheck != null) {
            Assessment newGrade = new Assessment(studentId, courseCode, examType, score, assessmentName);
            grades.add(newGrade);
            System.out.println("Grade recorded successfully.");
        }
    }

    public void viewGradesByExamType(Scanner input, List<Assessment> grades, Student student) {
        // Let the student pick a course.
        List<Course> enrolledCourses = student.getEnrolledCourses();
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }

        System.out.println("Select a course: ");
        for (int i = 0; i < enrolledCourses.size(); i++) {
            System.out.println((i + 1) + ". " + enrolledCourses.get(i).getCourseName()
                    + " (" + enrolledCourses.get(i).getCourseCode() + ")");
        }
        // Check if the choice is valid.
        int courseChoice = Helpers.getSafeIntInput("Choice: ");

        if (courseChoice < 1 || courseChoice > enrolledCourses.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        String courseCode = enrolledCourses.get(courseChoice - 1).getCourseCode(); // Subtract 1 to match java indexing.

        // Select exam type.
        System.out.println("Select exam to view:");
        Assessment.ExamType[] examTypes = Assessment.ExamType.values(); // Built-in method provided by Java for enums.
        for (int i = 0; i < examTypes.length; i++) {
            System.out.println((i + 1) + ". " + examTypes[i]);
        }

        int examChoice = Helpers.getSafeIntInput( "Choice: ");
        if (examChoice < 1 || examChoice > examTypes.length) {
            System.out.println("Invalid exam type selection.");
            return;
        }
        Assessment.ExamType selectedExamType = examTypes[examChoice - 1]; // Subtract 1 to match java indexing.

        // View results.
        // TO-DO
        // viewSpecificGrade(grades, student.getId(), courseCode, selectedExamType);
        // viewAverageGrade(grades, student.getId(), courseCode, selectedExamType);
    }

    public void viewAverageGrade(List<Assessment> grades, String studentId, String courseCode,
            Assessment.ExamType examtype) {
        // We will use this to calcualte the average
        double total = 0;
        int count = 0;

        for (Assessment g : grades) {
            if (g.getCourseCode().equals(courseCode) && g.getStudentId().equals(studentId)
                    && g.getExamType().equals(examType)) {
                total += g.getScore();
                count++;
            }
        }
        if (count > 0) {
            double average = total / count;
            System.out.println("Your average in " + courseCode + " is: " + average);
        } else {
            System.out.println("No grades found for this course.");
        }
    }

    public void viewSpecificGrade(List<Assessment> grades, String studentId, String courseCode,
            Assessment.ExamType examtype) {
        boolean found = false;
        for (Assessment g : grades) {
            if (g.getCourseCode().equals(courseCode) && g.getStudentId().equals(studentId)
                    && g.getExamType().equals(examtype)) {
                System.out.println(grades + " score: " + g.getScore());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(grades + " grade not found for this course.");
        }
    }
}