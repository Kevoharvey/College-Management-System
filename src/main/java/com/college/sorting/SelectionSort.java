package com.college.sorting;

import com.college.model.Course;
import java.util.List;

public final class SelectionSort {
    private SelectionSort() {
    }

    public static void sortCourses(List<Course> courses) {
        for (int i = 0; i < courses.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < courses.size(); j++) {
                if (courses.get(j).getStudentCount() < courses.get(minIndex).getStudentCount()) {
                    minIndex = j;
                }
            }

            Course current = courses.get(i);
            courses.set(i, courses.get(minIndex));
            courses.set(minIndex, current);
        }
    }
}
