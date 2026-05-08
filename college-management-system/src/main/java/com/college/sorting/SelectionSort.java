package com.college.sorting;

import com.college.model.Course;
import java.util.List;

public class SelectionSort {
    public static void sortCourses(List<Course> courses) {
        for (int i = 0; i < courses.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < courses.size(); j++) {
                if (courses.get(j).getStudentCount() < courses.get(minIndex).getStudentCount()) {
                    minIndex = j;
                }
            }
            Course temp = courses.get(i);
            courses.set(i, courses.get(minIndex));
            courses.set(minIndex, temp);
        }
    }
}