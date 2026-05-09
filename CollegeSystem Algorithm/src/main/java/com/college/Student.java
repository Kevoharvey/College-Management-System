package com.college;

public class Student {
    private int id;
    private String name;
    private double gpa;
    private int year;
    private String phone;

    public Student() {
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
