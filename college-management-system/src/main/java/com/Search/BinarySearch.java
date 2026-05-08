package com.college.search;

import com.college.model.Department;
import java.util.List;

public class BinarySearch {
    public static int binarySearch(List<Department> list, String key) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = list.get(mid).getName().compareTo(key);

            if (cmp == 0)
                return mid; // found
            if (cmp < 0)
                low = mid + 1; // go right
            else
                high = mid - 1; // go left
        }
        return -1; // not found
    }
}