package com.contactbook;

import java.util.Scanner;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("==========================================");
        System.out.println("        SUPER CONTACT BOOK v3.0");
        System.out.println("==========================================");
        System.out.println("How would you like to run the app?");
        System.out.println("1. Command Line Interface (CLI)");
        System.out.println("2. Web Interface (Browser)");
        System.out.print("Choose mode (1 or 2): ");
        
        String mode = scanner.nextLine();

        if (mode.equals("2")) {
            // WEB MODE
            WebServer web = new WebServer();
            web.start();
            System.out.println("\nPress [Enter] to stop server...");
            scanner.nextLine();
            System.exit(0);
        } else {
            // CLI MODE
            runCliMode(scanner);
        }
    }

    private static void runCliMode(Scanner scanner) {
        ContactService service = new ContactService();
        service.createTable();

        while (true) {
            System.out.println("\n--- CLI Menu ---");
            System.out.println("1. Add New Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Update Contact (Name & Phone)");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    service.insertContact(name, phone);
                    break;
                case "2":
                    List<Contact> contacts = service.getAllContacts();
                    System.out.println("\n--- Your Contacts ---");
                    for (Contact c : contacts) System.out.println(c.toString());
                    break;
                case "3":
                    // UPDATE LOGIC FIXED HERE
                    try {
                        System.out.print("Enter ID to update: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        
                        System.out.print("Enter NEW Name: ");
                        String newName = scanner.nextLine();
                        
                        System.out.print("Enter NEW Phone: ");
                        String newPhone = scanner.nextLine();
                        
                        service.updateContact(id, newName, newPhone);
                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                    }
                    break;
                case "4":
                    System.out.print("Enter ID to delete: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        service.deleteContact(id);
                    } catch (Exception e) {
                        System.out.println("Invalid ID.");
                    }
                    break;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}