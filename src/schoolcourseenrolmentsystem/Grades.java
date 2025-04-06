
package schoolcourseenrolmentsystem;

import java.util.List;

public class Grades {

    private String studentId;
    private String courseCode;
    private String examType; // "Quiz", "Midterm", "Final", "Homework"
    private double score;

    public Grades(String studentId, String courseCode, String examType, double score) {
        setStudentId(studentId);
        setCourseCode(courseCode);
        setExamType(examType);
        setScore(score);
    }

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

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void assignGrade(List<Grades> grades, String studentId, String courseCode, String examType, double score) {

        Grades gradeCheck = null;

        for (Grades g : grades) {
            if (g.getCourseCode().equals(courseCode) && g.getStudentId().equals(studentId)
                    && g.getExamType().equalsIgnoreCase(examType)) {
                gradeCheck = g;
                g.setScore(score); // Update existing score
                System.out.println("Grade updated successfully.");
                break;
            }
        }
        if (gradeCheck != null) {
            Grades newGrade = new Grades(studentId, courseCode, examType, score);
            grades.add(newGrade);
            System.out.println("Grade recorded successfully.");
        }
    }

    public void viewAverageGrade(List<Grades> grades, String studentId, String courseCode) {

        // We will use this to calcualte the average
        double total = 0;
        int count = 0;

        for (Grades g : grades) {
            if (g.getCourseCode().equals(courseCode) && g.getStudentId().equals(studentId)
                    && g.getExamType().equalsIgnoreCase(examType)) {

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

    public void viewSpecificGrade(List<Grades> grades, String studentId, String courseCode) {
boolean found = false;
        for (Grades g : grades) {
            if (g.getCourseCode().equals(courseCode) && g.getStudentId().equals(studentId)
                    && g.getExamType().equalsIgnoreCase(examType)) {
                found = true;
                        System.out.println(examType + " score: " + g.getScore());
                return;
            }
        }if (!found) {
        System.out.println(examType + " grade not found for this course.");
    }}

}
