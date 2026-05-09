package com.college;

public class Course {
    private String code;
    private String name;
    private int numberOfStudents;

    // Constructor
    public Course(String code, String name, int numberOfStudents) {
        this.code = code;
        this.name = name;
        this.numberOfStudents = numberOfStudents;
    }

    // Getters
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    // Setter
    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    @Override
    public String toString() {
        return "Course{" +
                "Code='" + code + '\'' +
                ", Name='" + name + '\'' +
                ", Students=" + numberOfStudents +
                '}';
    }
}
