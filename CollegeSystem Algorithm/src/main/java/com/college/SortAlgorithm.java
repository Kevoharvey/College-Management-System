package com.college;

import java.util.ArrayList;

public class SortAlgorithm {
    public static void bubbleSortDepartments(ArrayList<Department> list) {
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getName().compareToIgnoreCase(list.get(j + 1).getName()) > 0) {
                    Department temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    public static void selectionSortCourses(ArrayList<Course> list) {
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (list.get(j).getNumberOfStudents() < list.get(minIndex).getNumberOfStudents()) {
                    minIndex = j;
                }
            }

            Course temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    public static void insertionSortStudents(ArrayList<Student> list) {
        for (int i = 1; i < list.size(); i++) {
            Student key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).getGpa() < key.getGpa()) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }
}
