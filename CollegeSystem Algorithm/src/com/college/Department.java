package com.college;

public class Department {
    private int id;
    private String name;

    // Constructor
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Department{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                '}';
    }
}
