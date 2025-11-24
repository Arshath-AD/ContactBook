package com.contactbook;


public class Contact {
    private int id;
    private String name;
    private String phone;

    // Constructor
    public Contact(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    // Getters (we don't need setters for this simple app)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    // A handy method to print the contact's details
    @Override
    public String toString() {
        return "ID: " + id + "\t| Name: " + name + "\t| Phone: " + phone;
    }
}