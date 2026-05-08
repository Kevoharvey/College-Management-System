package com.college.sorting;

import com.college.model.Student;
import java.util.*;

public class MergeSort {
    public static void sortStudents(List<Student> students) {
        students.sort(Comparator.comparingDouble(Student::getGpa));
    }
}