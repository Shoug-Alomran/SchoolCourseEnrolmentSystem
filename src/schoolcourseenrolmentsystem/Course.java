package schoolcourseenrolmentsystem;
import java.util.*;

public class Course {
    // Similar to final but with 2+ options.
    public enum EnrollmentStatusEnum {
        Closed,
        Open
    }

    // Attributes
    private String courseName, courseCode, schedule, description;
    private EnrollmentStatusEnum enrollmentStatus;
    private Instructor instructor;
    private int capacity;
    private List<Assessment> grades;
    private int creditHours;

    // Fixes java.lang.StackOverflowError 
    private List<Student> enrolledStudents;

    // Constructor
    public Course(String courseName, String courseCode, String schedule, String description,
            EnrollmentStatusEnum enrollmentStatus, Instructor instructor, int capacity, List<Assessment> listOfGrades,
            int creditHours) {
        setCourseName(courseName);
        setCourseCode(courseCode);
        setSchedule(schedule);
        setDescription(description);
        setEnrollmentStatus(enrollmentStatus);
        setInstructor(instructor);
        setCapacity(capacity);
        setAssesment(listOfGrades);
        setCreditHours(creditHours);
        // Make sure enrolledStudents is initialized when a Course is created to avoid
        // null.
        this.enrolledStudents = new ArrayList<>();
    }
    public Course() {
    }

    public void setEnrolledStudents(List<Student> enrolledStudentList) {
        this.enrolledStudents = enrolledStudentList;
    }

    // Setters & Getters
    public EnrollmentStatusEnum getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(EnrollmentStatusEnum enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if (courseName == null || courseName.isEmpty()) {
          System.out.println("Course name cannot be null or empty.");
          return;
        } else {
            this.courseName = courseName;
        }
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        if (creditHours > 0) {
            this.creditHours = creditHours;
        } else {
            System.out.println("Credit hours can not be <= 0");
            return;
        }
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        if (courseCode == null || courseCode.isEmpty()) {
       System.out.println("Course code cannot be null or empty.");
       return;
        } else {
            this.courseCode = courseCode;
        }
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        if (schedule == null || schedule.isEmpty()) {
            this.schedule = null;
        } else {
            this.schedule = schedule;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            this.description = null;
        } else {
            this.description = description;
        }
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        if (instructor == null) {
            this.instructor = null;
            return;
        }
        this.instructor = instructor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            System.out.println("Error: Capacity must be > 0.");
            return;
        }
    }

    public List<Assessment> getGrades() {
        return grades;
    }

    public List<Assessment> setAssesment(List<Assessment> listOfGrades) {
        if (listOfGrades == null) {
            this.grades = null;
            return null;
        } else {
            this.grades = listOfGrades;
            return listOfGrades;
        }
    }

    public void setGrades(List<Assessment> listOfGrades) {
        if (listOfGrades == null) {
            this.grades = null;
        } else {
            this.grades = listOfGrades;
        }
    }
    // Methods
    // Check if the course is full
    public boolean isFull() {
        return enrolledStudents != null && this.getEnrolledStudents().size() >= capacity;
    }
}