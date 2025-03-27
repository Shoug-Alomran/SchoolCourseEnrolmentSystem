
package schoolcourseenrolmentsystem;

import java.util.List;

public class ExamType {

    private List<Course> allCourses;
    private List<Student> allStudents;
    private String examType;
    private double grades;

    public ExamType(List<Course> allCourses, List<Student> allStudents, String examType, double grades) {
        setExamType(examType);
        setGrades(grades);
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public double getGrades() {
        return grades;
    }

    public void setGrades(double grades) {
        this.grades = grades;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }

}
