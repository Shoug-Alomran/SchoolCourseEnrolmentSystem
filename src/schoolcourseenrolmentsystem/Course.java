
package schoolcourseenrolmentsystem;

import java.util.*;

public class Course {
    // Similar to final but with 2+ options.
    public enum EnrollmentStatusEnum {
        Closed,
        Open
    };

    // Attributes
    private String courseName, courseCode, schedule, description;
    public EnrollmentStatusEnum enrollmentStatus;
    private Instructor instructor;
    private int capacity;
    private ExamType examType;
    private int creditHours;

    // Constructor
    public Course(String courseName, String courseCode, String schedule, String description,
            EnrollmentStatusEnum enrollmentStatus, Instructor instructor, int capacity, ExamType examType,
            int creditHours) {
        setCourseName(courseName);
        setCourseCode(courseCode);
        setSchedule(schedule);
        setDescription(description);
        setEnrollmentStatus(enrollmentStatus);
        setInstructor(instructor);
        setCapacity(capacity);
        setExamType(examType);
        setCreditHours(creditHours);

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
            throw new IllegalArgumentException("Course name cannot be null or empty.");
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
            System.out.println("Credit hours can not be >= 0");
        }
    }

    public List<Student> getEnrolledStudents() {
        return getEnrolledStudents();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        if (courseCode == null || courseCode.isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be null or empty.");
        } else {
            this.courseCode = courseCode;
        }
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        if (schedule == null || schedule.isEmpty()) {
            throw new IllegalArgumentException("Schedule cannot be null or empty.");
        } else {
            this.schedule = schedule;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        } else {
            this.description = description;
        }
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        if (instructor == null) {
            throw new IllegalArgumentException("Instructor cannot be null.");
        } else {
            this.instructor = instructor;
        }

    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            System.out.println("Error: Capacity must be a >0.");
        }
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        if (examType == null) {
            throw new IllegalArgumentException("Exam type cannot be null.");
        } else {
            this.examType = examType;
        }
    }

    // Methods
    public void viewGrades(ExamType examType) {

    }

    // Check if the course is full
    public boolean isFull() {
        if (this.getEnrolledStudents().size() >= capacity) {
            return true;
        } else {
            return false;
        }
    }

}
