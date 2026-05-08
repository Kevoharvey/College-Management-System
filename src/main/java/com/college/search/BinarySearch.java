package com.college.search;

import com.college.model.Department;
import java.util.List;

public final class BinarySearch {
    private BinarySearch() {
    }

    public static int findDepartmentByName(List<Department> departments, String name) {
        int low = 0;
        int high = departments.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = departments.get(mid).getName().compareToIgnoreCase(name);

            if (comparison == 0) {
                return mid;
            }
            if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
