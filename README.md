# SchoolCourseEnrolmentSystem

The **SchoolCourseEnrolmentSystem** is a Java-based console application designed to simulate a simplified course management system within a university setting.  
It enables key academic roles — Students, Instructors, and Administrators — to interact with a shared course database through role-specific features.

---

## Features

### Student
- Login and profile management  
- View available courses  
- Enroll in and drop courses  
- View enrolled courses and credit limits  
- View exam scores and averages

### Instructor
- Login and update personal information  
- View enrolled students in assigned courses  
- Assign and update grades  
- Edit course schedule and description

### Administrator
- Login and manage users (students, instructors)  
- Add, remove, and update courses and users  
- Assign instructors to courses  
- View statistics and generate reports

### System-Wide
- Role-based menus  
- Input validation and error handling  
- ID auto-generation  
- Data persistence through text files

---

## Classes Overview

### Core Entity Classes
- **User<T>** — Abstract base class defining shared attributes and login/logout methods.  
- **Student** — Handles enrollment, credit limits, and grades.  
- **Instructor** — Manages students, grading, and course updates.  
- **Administrator** — Manages users, courses, and statistics.  
- **Course** — Represents courses with attributes like code, name, capacity, and enrolled students.  
- **Assessment / ExamType** — Manages grades for quizzes, midterms, finals, and projects.

### Utility Classes
- **Helpers** — Input validation, login workflows, menu display, statistics, and operational helpers.

### Data Management
- **dataManager** — Handles persistent storage and loading of users, courses, and assessments through text files.

### Main Application
- **SchoolCourseEnrolmentSystem** — Initializes data, handles authentication, and directs users to their role workflows.

---

## Workflows

### Student Workflow
1. Login using ID and password  
2. Access menu to view/enroll/drop courses, check credits, view grades, or update profile

### Instructor Workflow
1. Login using ID and password  
2. View students in courses, assign grades, update course info or personal profile

### Administrator Workflow
1. Login using ID and password  
2. Manage users and courses, assign instructors, generate reports and view statistics

---

## Future Improvements

- Add a GUI for better user experience  
- Encrypt stored passwords  
- Implement unit testing  
- Use a database (e.g., SQLite) for persistence  
- Export reports in CSV or PDF format

---

## Author

**Shoug Alomran**  
223410392@psu.edu.sa