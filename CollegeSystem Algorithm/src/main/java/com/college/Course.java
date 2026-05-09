package com.college;

public class Course {
    private String code;
    private String name;
    private int numberOfStudents;

    public Course() {
    }

    public Course(String code, String name, int numberOfStudents) {
        this.code = code;
        this.name = name;
        this.numberOfStudents = numberOfStudents;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
}
