# SchoolCourseEnrolmentSystem
# -----------------------------------------------------------
# A Java-based console application that simulates a simplified
# university course management system. It provides students,
# instructors, and administrators with role-specific functionality
# to manage courses, users, and assessments.
#
# Developed as part of the CS102 course at Prince Sultan University.
# -----------------------------------------------------------

## Features
# -----------------------------------------------------------
# Students:
#   - View available courses
#   - Enroll in or drop courses
#   - Track credit limits
#   - View enrolled courses
#   - View grades (quizzes, midterms, finals, projects)
#   - Update personal information
#
# Instructors:
#   - Login and manage assigned courses
#   - View enrolled students
#   - Assign or update student grades
#   - Edit course schedule and description
#   - Update personal profile
#
# Administrators:
#   - Add / remove / update students and instructors
#   - Add and manage courses
#   - Assign instructors to courses
#   - Open / close courses for enrollment
#   - View system statistics and generate reports
# -----------------------------------------------------------

## Project Structure
# -----------------------------------------------------------
# src/
# ├── Administrator.java
# ├── Assessment.java
# ├── Course.java
# ├── Helpers.java
# ├── Instructor.java
# ├── SchoolCourseEnrolmentSystem.java   ← Main entry point
# ├── Student.java
# ├── User.java
# └── dataManager/
#
# Core Classes:
#   - User<T>: Generic abstract base class
#   - Student / Instructor / Administrator: extend User with role-specific logic
#   - Course: represents course details, capacity, status
#   - Assessment: handles student performance data
#
# Utility:
#   - Helpers: input validation, login, UI menus, shared operations
#
# Data Management:
#   - dataManager: CRUD operations + persistent storage (text files)
#     - students.txt, instructors.txt, administrators.txt, courses.txt, assessments.txt
#
# Main:
#   - SchoolCourseEnrolmentSystem.java handles initialization,
#     authentication, role routing, and data persistence.
# -----------------------------------------------------------

## Technologies Used
# -----------------------------------------------------------
# Language:       Java
# Paradigm:       Object-Oriented Programming
# Environment:    Console-based application
# Persistence:    Text file storage
# -----------------------------------------------------------

## Workflows
# -----------------------------------------------------------
# Student:
#   Login → View courses → Enroll/Drop → Manage credits/grades → Logout
#
# Instructor:
#   Login → View students → Grade assessments → Edit course info → Logout
#
# Administrator:
#   Login → Manage users & courses → View stats/reports → Logout
# -----------------------------------------------------------

## Exception Handling
# -----------------------------------------------------------
# The entire main loop is wrapped in try-catch to gracefully
# handle unexpected errors. The system won't crash on invalid input.
# -----------------------------------------------------------