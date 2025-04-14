
package schoolcourseenrolmentsystem;

import java.util.List;

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

    // Constructor
    public Assessment(String studentId, String courseCode, ExamType examType, double score) {
        setStudentId(studentId);
        setCourseCode(courseCode);
        setExamType(examType);
        setScore(score);
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

    // Methods
    public void assignGrade(List<Assessment> grades, String studentId, String courseCode, ExamType examType,
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
            Assessment newGrade = new Assessment(studentId, courseCode, examType, score);
            grades.add(newGrade);
            System.out.println("Grade recorded successfully.");
        }
    }

    public void viewAverageGrade(List<Assessment> grades, String studentId, String courseCode) {

        // We will use this to calcualte the average
        double total = 0;
        int count = 0;

        for (Assessment g : grades) {
            if (g.getCourseCode().equals(courseCode) && g.getStudentId().equals(studentId)
                    && g.getExamType().equals(examType)) {

                total += g.getScore();
                count++;
                System.out.println(examType + " score: " + g.getScore());
                return;
            }
        }
        if (count > 0) {
            double average = total / count;
            System.out.println("Your average in " + courseCode + " is: " + average);
        } else {
            System.out.println("No grades found for this course.");
        }
    }

    public void viewSpecificGrade(List<Assessment> grades, String studentId, String courseCode, ExamType examtype) {
        boolean found = false;
        for (Assessment g : grades) {
            if (g.getCourseCode().equals(courseCode) && g.getStudentId().equals(studentId)
                    && g.getExamType().equals(examtype)) {
                found = true;
                System.out.println(grades + " score: " + g.getScore());
                return;
            }
        }
        if (!found) {
            System.out.println(grades + " grade not found for this course.");
        }
    }
}
