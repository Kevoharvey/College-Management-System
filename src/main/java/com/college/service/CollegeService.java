package com.college.service;

import com.college.model.Course;
import com.college.model.Department;
import com.college.model.Student;
import com.college.search.BinarySearch;
import com.college.sorting.BubbleSort;
import com.college.sorting.MergeSort;
import com.college.sorting.SelectionSort;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CollegeService {
    private final List<Department> departments = List.of(
            new Department(1, "Computer Science"),
            new Department(2, "Business"),
            new Department(3, "Engineering"),
            new Department(4, "Mathematics")
    );

    private final List<Course> courses = List.of(
            new Course("CS101", "Introduction to Programming", 42),
            new Course("BUS210", "Accounting Principles", 35),
            new Course("ENG150", "Engineering Design", 28),
            new Course("MATH201", "Calculus II", 31)
    );

    private final List<Student> students = List.of(
            new Student(1001, "Amina Hassan", 3.8, 2, "010-555-0148"),
            new Student(1002, "Omar Ali", 3.4, 1, "010-555-0190"),
            new Student(1003, "Mariam Youssef", 3.9, 3, "010-555-0122"),
            new Student(1004, "Youssef Nabil", 3.2, 4, "010-555-0177")
    );

    public List<Department> getDepartmentsByName() {
        List<Department> sortedDepartments = new ArrayList<>(departments);
        BubbleSort.sortDepartments(sortedDepartments);
        return sortedDepartments;
    }

    public List<Course> getCoursesByEnrollment() {
        List<Course> sortedCourses = new ArrayList<>(courses);
        SelectionSort.sortCourses(sortedCourses);
        return sortedCourses;
    }

    public List<Student> getStudentsByGpa() {
        List<Student> sortedStudents = new ArrayList<>(students);
        MergeSort.sortStudents(sortedStudents);
        return sortedStudents;
    }

    public Department findDepartment(String name) {
        List<Department> sortedDepartments = getDepartmentsByName();
        int index = BinarySearch.findDepartmentByName(sortedDepartments, name);
        return index >= 0 ? sortedDepartments.get(index) : null;
    }
}
