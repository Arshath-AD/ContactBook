package com.contactbook; // Make sure this matches your package name from the pom.xml

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContactService {

    // This will create a file named 'contacts.db' in your project's root folder
    private static final String DB_URL = "jdbc:sqlite:contacts.db";

    // Method to get a connection to the database
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
        return conn;
    }

    // Method to create the 'contacts' table if it doesn't exist
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS contacts ("
                   + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + " name TEXT NOT NULL,"
                   + " phone TEXT NOT NULL"
                   + ");";

        // Try-with-resources statement ensures that resources are closed
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            
            // create a new table
            stmt.execute(sql);
            System.out.println("Table 'contacts' is ready.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    // New Method: Insert a new contact
    public void insertContact(String name, String phone) {
        String sql = "INSERT INTO contacts(name, phone) VALUES(?, ?)";

        try (Connection conn = this.connect();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set the values for the SQL query
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            
            // Execute the update
            pstmt.executeUpdate();
            System.out.println("SUCCESS: Added new contact: " + name);
            
        } catch (SQLException e) {
            System.out.println("ERROR adding contact: " + e.getMessage());
        }
    }

    // New Method: Get all contacts from the database
    public List<Contact> getAllContacts() {
        String sql = "SELECT id, name, phone FROM contacts";
        
        // This list will hold all our contact objects
        List<Contact> contacts = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // Loop through each row in the result set
            while (rs.next()) {
                // Create a new Contact object for each row
                Contact contact = new Contact(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone")
                );
                // Add the new contact to our list
                contacts.add(contact);
            }
        } catch (SQLException e) {
            System.out.println("ERROR reading contacts: " + e.getMessage());
        }
        
        // Return the complete list
        return contacts;
    }

    // Update a contact's name or phone number using their ID
    public void updateContact(int id, String newName, String newPhone) {
        // SQL query now changes both columns
        String sql = "UPDATE contacts SET name = ?, phone = ? WHERE id = ?";

        try (Connection conn = this.connect();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newName);
            pstmt.setString(2, newPhone);
            pstmt.setInt(3, id);
            
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "✅ Updated ID " + id : "❌ ID " + id + " not found.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // 2. NEW: Get a single contact (Needed for the Web Edit Form)
    public Contact getContactById(int id) {
        String sql = "SELECT id, name, phone FROM contacts WHERE id = ?";
        Contact contact = null;

        try (Connection conn = this.connect();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                contact = new Contact(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contact;
    }

    // Delete a contact using their ID
    public void deleteContact(int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";

        try (Connection conn = this.connect();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("SUCCESS: Contact ID " + id + " deleted.");
            } else {
                System.out.println("ERROR: Contact ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}