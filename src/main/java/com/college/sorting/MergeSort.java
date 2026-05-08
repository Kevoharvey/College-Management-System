package com.college.sorting;

import com.college.model.Student;
import java.util.Comparator;
import java.util.List;

public final class MergeSort {
    private MergeSort() {
    }

    public static void sortStudents(List<Student> students) {
        students.sort(Comparator.comparingDouble(Student::getGpa).reversed());
    }
}
