# 📒 Super Contact Book (Java + SQLite + Web)

A full-stack Contact Management System built with **Java** and **Maven**. This project demonstrates a hybrid architecture, allowing users to interact with the application via a classic **Command Line Interface (CLI)** or a modern **Web Interface** accessible from a browser or mobile device.

## 🚀 Features

* **Dual Interface:** choose between CLI console mode or a Responsive Web UI on startup.
* **Persistent Storage:** Uses **SQLite** database (`contacts.db`) to save data permanently.
* **Full CRUD:** Create, Read, Update, and Delete contacts (Name and Phone).
* **Modern Tech Stack:** Built with **Javalin** (Web Framework) and **Thymeleaf** (Template Engine).
* **Cross-Platform:** Runs on Windows, Linux, and macOS.
* **Mobile Ready:** The web interface can be accessed from a smartphone via local network or USB tethering.

## 🛠️ Tech Stack

* **Language:** Java 11 (Compatible with JDK 1.8+)
* **Build Tool:** Apache Maven
* **Database:** SQLite (JDBC)
* **Web Framework:** Javalin 5.6.3
* **Frontend:** HTML5, CSS3, Thymeleaf

## 📂 Project Structure

```text
ContactBook/
├── src/main/java/com/contactbook/
│   ├── App.java            # Main Entry Point (Master Switch)
│   ├── ContactService.java # Business Logic & Database Handling
│   ├── Contact.java        # Data Model
│   └── WebServer.java      # Web Server Controller & Routes
│
├── src/main/resources/
│   ├── public/css/         # CSS Stylesheets
│   └── templates/          # HTML Templates (Thymeleaf)
│
└── pom.xml                 # Maven Dependencies