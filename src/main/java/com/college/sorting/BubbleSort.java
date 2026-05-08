package com.college.sorting;

import com.college.model.Department;
import java.util.List;

public final class BubbleSort {
    private BubbleSort() {
    }

    public static void sortDepartments(List<Department> departments) {
        for (int i = 0; i < departments.size() - 1; i++) {
            for (int j = 0; j < departments.size() - i - 1; j++) {
                if (departments.get(j).getName().compareToIgnoreCase(departments.get(j + 1).getName()) > 0) {
                    Department current = departments.get(j);
                    departments.set(j, departments.get(j + 1));
                    departments.set(j + 1, current);
                }
            }
        }
    }
}
