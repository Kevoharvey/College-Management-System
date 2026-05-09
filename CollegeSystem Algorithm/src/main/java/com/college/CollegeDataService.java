package com.college;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CollegeDataService {
    private final ArrayList<Department> departments = new ArrayList<>();
    private final ArrayList<Course> courses = new ArrayList<>();
    private final ArrayList<Student> students = new ArrayList<>();

    public CollegeDataService() {
        departments.add(new Department(10, "Computer Science"));
        departments.add(new Department(20, "Business Administration"));
        departments.add(new Department(30, "Engineering"));

        courses.add(new Course("CS101", "Data Structures", 42));
        courses.add(new Course("BUS210", "Principles of Management", 36));
        courses.add(new Course("ENG150", "Engineering Drawing", 28));

        students.add(new Student(1001, "Mariam Hassan", 3.8, 3, "01012345678"));
        students.add(new Student(1002, "Omar Ali", 3.4, 2, "01098765432"));
        students.add(new Student(1003, "Nour Ahmed", 3.9, 4, "01122334455"));
    }

    public synchronized List<Department> getDepartments() {
        return List.copyOf(departments);
    }

    public synchronized List<Course> getCourses() {
        return List.copyOf(courses);
    }

    public synchronized List<Student> getStudents() {
        return List.copyOf(students);
    }

    public synchronized Department addDepartment(Department department) {
        departments.add(department);
        return department;
    }

    public synchronized Course addCourse(Course course) {
        courses.add(course);
        return course;
    }

    public synchronized Student addStudent(Student student) {
        students.add(student);
        return student;
    }

    public synchronized List<Department> getDepartmentsSortedByName() {
        ArrayList<Department> sorted = new ArrayList<>(departments);
        SortAlgorithm.bubbleSortDepartments(sorted);
        return sorted;
    }

    public synchronized List<Course> getCoursesSortedByEnrollment() {
        ArrayList<Course> sorted = new ArrayList<>(courses);
        SortAlgorithm.selectionSortCourses(sorted);
        return sorted;
    }

    public synchronized List<Student> getStudentsSortedByGpa() {
        ArrayList<Student> sorted = new ArrayList<>(students);
        SortAlgorithm.insertionSortStudents(sorted);
        return sorted;
    }

    public synchronized Optional<Department> findDepartmentByName(String name) {
        return Optional.ofNullable(SearchingAlgorithm.searchDepartmentByName(departments, name));
    }

    public synchronized Optional<Student> findStudentById(int id) {
        return Optional.ofNullable(SearchingAlgorithm.searchStudentById(students, id));
    }

    public synchronized boolean departmentIdExists(int id) {
        return departments.stream().anyMatch(department -> department.getId() == id);
    }

    public synchronized boolean courseCodeExists(String code) {
        return courses.stream().anyMatch(course -> course.getCode().equalsIgnoreCase(code));
    }

    public synchronized boolean studentIdExists(int id) {
        return students.stream().anyMatch(student -> student.getId() == id);
    }

    public synchronized DashboardStats getDashboardStats() {
        double averageGpa = students.stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0);

        int totalEnrollments = courses.stream()
                .mapToInt(Course::getNumberOfStudents)
                .sum();

        return new DashboardStats(
                departments.size(),
                courses.size(),
                students.size(),
                totalEnrollments,
                Math.round(averageGpa * 100.0) / 100.0
        );
    }
}
