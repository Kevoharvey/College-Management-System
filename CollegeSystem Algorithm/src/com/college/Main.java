package com.college;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        ArrayList<Department> departments = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n====== College Management System ======");
            System.out.println("1- Add Department");
            System.out.println("2- Add Course");
            System.out.println("3- Add Student");
            System.out.println("4- Show All Data");
            System.out.println("5- Sort Departments");
            System.out.println("6- Sort Courses");
            System.out.println("7- Sort Students");
            System.out.println("8- Search Department by Name");
            System.out.println("9- Search Student by ID");
            System.out.println("10- Exit");
            System.out.println("\n=========================================");

            System.out.print("Enter choice: ");
            choice = input.nextInt();

            switch (choice) {

                // ➤ Add Department
                case 1:

                    int depId;

                    while (true) {
                        System.out.print("Enter Department ID: ");
                        depId = input.nextInt();

                        if (depId > 0)
                            break;

                        System.out.println("❌ ID must be > 0");
                    }

                    boolean depExists = false;

                    for (Department d : departments) {
                        if (d.getId() == depId) {
                            depExists = true;
                            break;
                        }
                    }

                    if (depExists) {
                        System.out.println("❌ Department ID already exists!");
                        break;
                    }

                    input.nextLine();

                    String depName;

                    while (true) {
                        System.out.print("Enter Department Name: ");
                        depName = input.nextLine();

                        if (!depName.trim().isEmpty())
                            break;

                        System.out.println("❌ Name cannot be empty");
                    }

                    departments.add(new Department(depId, depName));

                    System.out.println("✔ Department Added Successfully!");
                    break;

                // ➤ Add Course
                case 2:

                    input.nextLine();

                    System.out.print("Enter Course Code: ");
                    String code = input.nextLine();

                    boolean courseExists = false;

                    for (Course c : courses) {
                        if (c.getCode().equalsIgnoreCase(code)) {
                            courseExists = true;
                            break;
                        }
                    }

                    if (courseExists) {
                        System.out.println("❌ Course Code already exists!");
                        break;
                    }

                    String courseName;

                    while (true) {
                        System.out.print("Enter Course Name: ");
                        courseName = input.nextLine();

                        if (!courseName.trim().isEmpty())
                            break;

                        System.out.println("❌ Name cannot be empty");
                    }

                    int numStudents;

                    while (true) {
                        System.out.print("Enter Number of Students: ");
                        numStudents = input.nextInt();

                        if (numStudents >= 0)
                            break;

                        System.out.println("❌ Must be >= 0");
                    }

                    courses.add(new Course(code, courseName, numStudents));

                    System.out.println("✔ Course Added Successfully!");
                    break;

                // ➤ Add Student
                case 3:

                    int stuId;

                    while (true) {
                        System.out.print("Enter Student ID: ");
                        stuId = input.nextInt();

                        if (stuId > 0)
                            break;

                        System.out.println("❌ ID must be > 0");
                    }

                    boolean stuExists = false;

                    for (Student s : students) {
                        if (s.getId() == stuId) {
                            stuExists = true;
                            break;
                        }
                    }

                    if (stuExists) {
                        System.out.println("❌ Student ID already exists!");
                        break;
                    }

                    input.nextLine();

                    String stuName;

                    while (true) {
                        System.out.print("Enter Student Name: ");
                        stuName = input.nextLine();

                        if (!stuName.trim().isEmpty())
                            break;

                        System.out.println("❌ Invalid Name");
                    }

                    double gpa;

                    while (true) {
                        System.out.print("Enter GPA (0 - 4): ");
                        gpa = input.nextDouble();

                        if (gpa >= 0 && gpa <= 4)
                            break;

                        System.out.println("❌ Invalid GPA");
                    }

                    int year;

                    while (true) {
                        System.out.print("Enter Year (1 - 5): ");
                        year = input.nextInt();

                        if (year >= 1 && year <= 5)
                            break;

                        System.out.println("❌ Invalid Year");
                    }

                    input.nextLine();

                    String phone;

                    while (true) {
                        System.out.print("Enter Phone: ");
                        phone = input.nextLine();

                        if (!phone.trim().isEmpty())
                            break;

                        System.out.println("❌ Invalid Phone");
                    }

                    students.add(new Student(stuId, stuName, gpa, year, phone));

                    System.out.println("✔ Student Added Successfully!");
                    break;

                // ➤ Show Data
                case 4:

                    System.out.println("\n--- Departments ---");

                    for (Department d : departments)
                        System.out.println(d);

                    System.out.println("\n--- Courses ---");

                    for (Course c : courses)
                        System.out.println(c);

                    System.out.println("\n--- Students ---");

                    for (Student s : students)
                        System.out.println(s);

                    break;

                // ➤ Sort Departments
                case 5:

                    SortAlgorithm.bubbleSortDepartments(departments);

                    System.out.println("✔ Departments Sorted Successfully!");
                    break;

                // ➤ Sort Courses
                case 6:

                    SortAlgorithm.selectionSortCourses(courses);

                    System.out.println("✔ Courses Sorted Successfully!");
                    break;

                // ➤ Sort Students
                case 7:

                    SortAlgorithm.insertionSortStudents(students);

                    System.out.println("✔ Students Sorted Successfully!");
                    break;

                // ➤ Search Department
                case 8:

                    input.nextLine();

                    System.out.print("Enter Department Name: ");
                    String searchDep = input.nextLine();

                    Department depResult =
                            SearchingAlgorithm.searchDepartmentByName(departments, searchDep);

                    System.out.println(depResult != null ?
                            depResult : "Department not found!");

                    break;

                // ➤ Search Student
                case 9:

                    System.out.print("Enter Student ID: ");
                    int searchId = input.nextInt();

                    Student stuResult =
                            SearchingAlgorithm.searchStudentById(students, searchId);

                    System.out.println(stuResult != null ?
                            stuResult : "Student not found!");

                    break;

                // ➤ Exit
                case 10:

                    System.out.println("Exiting system... Bye 👋");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 10);

        input.close();
    }
}