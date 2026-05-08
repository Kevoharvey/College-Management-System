package com.college.model;

public class Student {
    private final int id;
    private final String name;
    private final double gpa;
    private final int year;
    private final String phone;

    public Student(int id, String name, double gpa, int year, String phone) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.year = year;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public int getYear() {
        return year;
    }

    public String getPhone() {
        return phone;
    }
}
