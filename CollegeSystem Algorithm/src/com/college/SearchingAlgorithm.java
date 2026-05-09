package com.college;

import java.util.ArrayList;
import java.util.Comparator;

public class SearchingAlgorithm {

    /**
     * Binary Search - Department by Name (case-insensitive)
     * Time Complexity: O(log n) after sorting
     * Prerequisite: List must be sorted by department name alphabetically
     * 
     * @param list ArrayList of departments (must be sorted by name)
     * @param name Department name to search for
     * @return Department if found, null otherwise
     */
    public static Department searchDepartmentByName(ArrayList<Department> list, String name) {
        // Sort list by department name (case-insensitive) before binary search
        list.sort(Comparator.comparing(d -> d.getName().toLowerCase()));
        
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;  // Prevent integer overflow
            int cmp = list.get(mid).getName().compareToIgnoreCase(name);

            if (cmp == 0) {
                return list.get(mid);  // Found!
            } else if (cmp < 0) {
                low = mid + 1;         // Search right half (alphabetically after)
            } else {
                high = mid - 1;        // Search left half (alphabetically before)
            }
        }

        return null;  // Not found
    }

    /**
     * Binary Search - Student by ID
     * Time Complexity: O(log n) after sorting
     * Prerequisite: List must be sorted by student ID in ascending order
     * 
     * @param list ArrayList of students (must be sorted by ID ascending)
     * @param id Student ID to search for
     * @return Student if found, null otherwise
     */
    public static Student searchStudentById(ArrayList<Student> list, int id) {
        // Sort list by student ID before binary search
        list.sort(Comparator.comparingInt(Student::getId));
        
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;  // Prevent integer overflow
            int midId = list.get(mid).getId();

            if (midId == id) {
                return list.get(mid);  // Found!
            } else if (midId < id) {
                low = mid + 1;         // Search right half (higher IDs)
            } else {
                high = mid - 1;        // Search left half (lower IDs)
            }
        }

        return null;  // Not found
    }

}