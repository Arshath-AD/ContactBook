package com.contactbook;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinThymeleaf;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Collections;
import java.util.List;

public class WebServer {

    private ContactService service;

    public WebServer() {
        this.service = new ContactService();
    }

    public void start() {
        // --- 1. CONFIGURATION ---
        
        // Configure Thymeleaf (The Engine to read HTML files)
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/"); // Files are in resources/templates/
        resolver.setSuffix(".html");       // Files end in .html
        resolver.setTemplateMode("HTML");
        
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        
        // Register the engine with Javalin
        JavalinThymeleaf.init(engine);

        // Start Server & Configure CSS folder
        Javalin app = Javalin.create(config -> {
            // YOUR DEV PATH: Reads CSS directly from your disk so you don't have to restart
            config.staticFiles.add("/home/arshathad/Documents/MavenProjecct/ContactBook/src/main/resources/public", Location.EXTERNAL);
        }).start("0.0.0.0", 7000);


        // --- 2. ROUTES ---

        // Route 1: HOME PAGE (Show All Contacts)
        app.get("/", ctx -> {
            List<Contact> allContacts = service.getAllContacts();
            ctx.render("index.html", Collections.singletonMap("contacts", allContacts));
        });

        // Route 2: SHOW ADD FORM
        app.get("/add-form", ctx -> {
            ctx.render("add.html");
        });

        // Route 3: HANDLE ADD SUBMISSION
        app.post("/add", ctx -> {
            String name = ctx.formParam("name");
            String phone = ctx.formParam("phone");
            service.insertContact(name, phone);
            ctx.redirect("/");
        });

        // Route 4: SHOW UPDATE FORM (New!)
        app.get("/update/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Contact c = service.getContactById(id);
            
            if (c != null) {
                // Send the existing contact data to the update.html page
                ctx.render("update.html", Collections.singletonMap("contact", c));
            } else {
                ctx.redirect("/");
            }
        });

        // Route 5: HANDLE UPDATE SUBMISSION (New!)
        app.post("/update-action", ctx -> {
            int id = Integer.parseInt(ctx.formParam("id"));
            String name = ctx.formParam("name");
            String phone = ctx.formParam("phone");
            
            // Update both Name and Phone
            service.updateContact(id, name, phone);
            ctx.redirect("/");
        });

        // Route 6: HANDLE DELETE
        app.get("/delete/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            service.deleteContact(id);
            ctx.redirect("/");
        });

        System.out.println("----------------------------------------------");
        System.out.println("🌐 Web Server running at: http://localhost:7000");
        System.out.println("----------------------------------------------");
    }
}