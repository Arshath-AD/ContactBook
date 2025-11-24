⚙️ How to Run

Prerequisites

    Java JDK 8 or higher installed.

    Apache Maven installed.

Steps

    Clone the repository:
    Bash

git clone [https://github.com/Arshath-AD/ContactBook.git](https://github.com/Arshath-AD/ContactBook.git)
cd ContactBook

Build the project:
Bash

mvn clean compile

Run the application:
Bash

    # Run using Maven
    mvn exec:java -Dexec.mainClass="com.contactbook.App"

    # OR run from your IDE (VS Code / IntelliJ)

🖥️ Usage Guide

When you run the app, you will see a startup menu:
Plaintext

==========================================
      📒 SUPER CONTACT BOOK v3.0
==========================================
How would you like to run the app?
1. 🖥️  Command Line Interface (CLI)
2. 🌐  Web Interface (Browser)

Option 1: CLI Mode

Use the terminal to manage contacts using simple number commands.

    1: Add Contact

    2: View All Contacts

    3: Update Contact

    4: Delete Contact

    5: Exit

Option 2: Web Mode

The application will start a web server at http://localhost:7000.

    Open your browser and go to http://localhost:7000.

    You can View, Add, Edit, and Delete contacts using the graphical interface.

📱 Mobile Access (Bonus)

You can access the Web Interface from your phone!

    Connect your phone to the same Wi-Fi or via USB Tethering.

    Find your computer's IP address (e.g., 192.168.x.x).

    Open Chrome on your phone and visit: http://YOUR_PC_IP:7000.

👨‍💻 Author

Arshath AD

    College Project - 2025