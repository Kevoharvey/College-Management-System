package com.college.model;

public class Course {
    private final String code;
    private final String name;
    private final int studentCount;

    public Course(String code, String name, int studentCount) {
        this.code = code;
        this.name = name;
        this.studentCount = studentCount;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getStudentCount() {
        return studentCount;
    }
}
