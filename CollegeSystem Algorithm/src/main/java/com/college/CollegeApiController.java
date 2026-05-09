package com.college;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CollegeApiController {
    private final CollegeDataService dataService;

    public CollegeApiController(CollegeDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/dashboard")
    public DashboardStats dashboard() {
        return dataService.getDashboardStats();
    }

    @GetMapping("/departments")
    public List<Department> departments(@RequestParam(defaultValue = "false") boolean sorted) {
        return sorted ? dataService.getDepartmentsSortedByName() : dataService.getDepartments();
    }

    @PostMapping("/departments")
    public ResponseEntity<?> addDepartment(@RequestBody Department department) {
        if (department.getId() <= 0 || isBlank(department.getName())) {
            return badRequest("Department ID must be positive and name cannot be empty.");
        }

        if (dataService.departmentIdExists(department.getId())) {
            return badRequest("A department with this ID already exists.");
        }

        department.setName(department.getName().trim());
        return ResponseEntity.status(HttpStatus.CREATED).body(dataService.addDepartment(department));
    }

    @GetMapping("/departments/search")
    public ResponseEntity<?> searchDepartment(@RequestParam String name) {
        if (isBlank(name)) {
            return badRequest("Enter a department name to search.");
        }

        return dataService.findDepartmentByName(name.trim())
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Department not found.")));
    }

    @GetMapping("/courses")
    public List<Course> courses(@RequestParam(defaultValue = "false") boolean sorted) {
        return sorted ? dataService.getCoursesSortedByEnrollment() : dataService.getCourses();
    }

    @PostMapping("/courses")
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        if (isBlank(course.getCode()) || isBlank(course.getName()) || course.getNumberOfStudents() < 0) {
            return badRequest("Course code and name are required. Student count must be zero or more.");
        }

        if (dataService.courseCodeExists(course.getCode())) {
            return badRequest("A course with this code already exists.");
        }

        course.setCode(course.getCode().trim().toUpperCase());
        course.setName(course.getName().trim());
        return ResponseEntity.status(HttpStatus.CREATED).body(dataService.addCourse(course));
    }

    @GetMapping("/students")
    public List<Student> students(@RequestParam(defaultValue = "false") boolean sorted) {
        return sorted ? dataService.getStudentsSortedByGpa() : dataService.getStudents();
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        if (student.getId() <= 0 || isBlank(student.getName()) || isBlank(student.getPhone())) {
            return badRequest("Student ID, name, and phone are required.");
        }

        if (student.getGpa() < 0 || student.getGpa() > 4 || student.getYear() < 1 || student.getYear() > 5) {
            return badRequest("GPA must be between 0 and 4. Year must be between 1 and 5.");
        }

        if (dataService.studentIdExists(student.getId())) {
            return badRequest("A student with this ID already exists.");
        }

        student.setName(student.getName().trim());
        student.setPhone(student.getPhone().trim());
        return ResponseEntity.status(HttpStatus.CREATED).body(dataService.addStudent(student));
    }

    @GetMapping("/students/search")
    public ResponseEntity<?> searchStudent(@RequestParam int id) {
        return dataService.findStudentById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Student not found.")));
    }

    private ResponseEntity<Map<String, String>> badRequest(String message) {
        return ResponseEntity.badRequest().body(Map.of("message", message));
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
