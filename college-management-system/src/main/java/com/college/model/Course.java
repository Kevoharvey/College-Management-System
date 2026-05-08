package com.college.model;

public class Course {
    private String code;
    private String name;
    private int studentCount;

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