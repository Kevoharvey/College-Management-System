package com.college;

import java.util.ArrayList;

public class SearchingAlgorithm {
    public static Department searchDepartmentByName(ArrayList<Department> list, String name) {
        for (Department department : list) {
            if (department.getName().equalsIgnoreCase(name)) {
                return department;
            }
        }

        return null;
    }

    public static Student searchStudentById(ArrayList<Student> list, int id) {
        for (Student student : list) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }
}
