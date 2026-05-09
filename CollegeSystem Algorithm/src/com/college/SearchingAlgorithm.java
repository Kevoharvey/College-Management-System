package com.college;

import java.util.ArrayList;

public class SearchingAlgorithm {
    //Search Department by Name
        public static Department searchDepartmentByName(ArrayList<Department> list, String name) {

            for (Department d : list) {

                if (d.getName().equalsIgnoreCase(name)) {
                    return d;
                }
            }

            return null; // not found
        }
        //Search Student by ID
        public static Student searchStudentById(ArrayList<Student> list, int id) {

            for (Student s : list) {

                if (s.getId() == id) {
                    return s;
                }
            }

            return null;
        }


}
