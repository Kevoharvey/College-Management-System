package com.college;

public class Student {
    private int id;
    private String name;
    private double gpa;
    private int year;
    private String phone;

    // Constructor
    public Student(int id, String name, double gpa, int year, String phone) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.year = year;
        this.phone = phone;
    }

    // Getters
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

    // Setter GPA
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", GPA=" + gpa +
                ", Year=" + year +
                ", Phone='" + phone + '\'' +
                '}';
    }
}
