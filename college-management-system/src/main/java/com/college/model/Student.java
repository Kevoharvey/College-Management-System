package com.college.model;

public class Student {
    private int id;
    private String name;
    private double gpa;
    private int year;
    private String phone;

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