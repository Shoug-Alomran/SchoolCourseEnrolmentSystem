
package schoolcourseenrolmentsystem;

import java.util.List;

public class Assesment {
    
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
    public Assesment(String studentId, String courseCode, ExamType examType, double score) {
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
    public void assignGrade(List<Assesment> grades, String studentId, String courseCode, ExamType examType,
            double score) {
        Assesment gradeCheck = null;

        for (Assesment g : grades) {
            if (g.getCourseCode().equals(courseCode) && g.getStudentId().equals(studentId)
                    && g.getExamType().equals(examType)) {
                gradeCheck = g;
                g.setScore(score); // Update existing score
                System.out.println("Grade updated successfully.");
                break;
            }
        }
        if (gradeCheck != null) {
            Assesment newGrade = new Assesment(studentId, courseCode, examType, score);
            grades.add(newGrade);
            System.out.println("Grade recorded successfully.");
        }
    }

    public void viewAverageGrade(List<Assesment> grades, String studentId, String courseCode) {

        // We will use this to calcualte the average
        double total = 0;
        int count = 0;

        for (Assesment g : grades) {
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

    public void viewSpecificGrade(List<Assesment> grades, String studentId, String courseCode, ExamType examtype) {
        boolean found = false;
        for (Assesment g : grades) {
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
