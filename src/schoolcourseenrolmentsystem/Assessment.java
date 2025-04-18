
package schoolcourseenrolmentsystem;

import java.util.*;

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

    public void viewGradesByExamType(List<Assessment> grades, Student student) {
        List<Course> enrolledCourses = student.getEnrolledCourses();
        if (enrolledCourses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }

        System.out.println("Select a course:");
        for (int i = 0; i < enrolledCourses.size(); i++) {
            System.out.println((i + 1) + ". " + enrolledCourses.get(i).getCourseName()
                    + " (" + enrolledCourses.get(i).getCourseCode() + ")");
        }

        int courseChoice = Helpers.getSafeIntInput("Choice: ");
        if (courseChoice < 1 || courseChoice > enrolledCourses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        String courseCode = enrolledCourses.get(courseChoice - 1).getCourseCode();

        System.out.println("Select exam type to view:");
        Assessment.ExamType[] examTypes = Assessment.ExamType.values();
        for (int i = 0; i < examTypes.length; i++) {
            System.out.println((i + 1) + ". " + examTypes[i]);
        }

        int examChoice = Helpers.getSafeIntInput("Choice: ");
        if (examChoice < 1 || examChoice > examTypes.length) {
            System.out.println("Invalid exam type selection.");
            return;
        }

        Assessment.ExamType selectedExamType = examTypes[examChoice - 1];

        // Show all grades of that type and their average
        viewSpecificGrade(grades, student.getId(), courseCode, selectedExamType);
        viewTotalAverageGrade(grades, courseCode, courseCode);
    }

    public void assignGrade(List<Assessment> grades, String studentId, String courseCode,
            Assessment.ExamType examType, double score) {
        boolean gradeUpdated = false;

        for (Assessment g : grades) {
            if (g.getCourseCode().equals(courseCode)
                    && g.getStudentId().equals(studentId)
                    && g.getExamType() == examType) {
                g.setScore(score); // Update existing score
                System.out.println("Grade updated successfully.");
                gradeUpdated = true;
                break;
            }
        }
        if (!gradeUpdated) {
            Assessment newGrade = new Assessment(studentId, courseCode, examType, score, examType.name());
            grades.add(newGrade);
            System.out.println("Grade recorded successfully.");
        }
    }

    public void viewSpecificGrade(List<Assessment> grades, String studentId, String courseCode,
            Assessment.ExamType examType) {
        boolean found = false;

        for (Assessment g : grades) {
            if (g.getCourseCode().equals(courseCode)
                    && g.getStudentId().equals(studentId)
                    && g.getExamType() == examType) {
                System.out.println("Your " + examType + " score: " + g.getScore());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No " + examType + " grades found for this course.");
        }
    }

    public void viewTotalAverageGrade(List<Assessment> grades, String studentId, String courseCode) {
        double total = 0;
        int count = 0;

        for (Assessment g : grades) {
            if (g.getStudentId().equals(studentId) && g.getCourseCode().equals(courseCode)) {
                total += g.getScore();
                count++;
            }
        }
        if (count > 0) {
            double average = total / count;
            System.out.println("Your total average grade in " + courseCode + " is: " + average);
        } else {
            System.out.println("No grades found for this course.");
        }
    }
}