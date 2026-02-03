# Hospital Management System (MVC Java Webapp)

Overview
--------
This project is a Java Servlet/JSP web application implementing a basic Hospital Management System following MVC architecture. It includes models, DAOs, controllers (servlets), and JSP views. Data is persisted in MySQL.

Setup
-----
1. Create the database and tables using: `sql/schema.sql`.
2. Update database credentials in `src/main/java/com/hms/util/DBConnection.java`.
3. Build with Maven: `mvn clean package` and deploy the generated WAR to Tomcat.

Workflow
--------
- User accesses `index.jsp` and logs in via `/auth`.
- `AuthServlet` validates against `users` table and creates an HTTP session.
- `PatientServlet`, `DoctorServlet`, etc., act as controllers: they call DAO methods (Model) and forward to JSPs (View).

Files of interest
- `src/main/java/com/hms/model` — entity classes
- `src/main/java/com/hms/dao` — JDBC DAOs
- `src/main/java/com/hms/controller` — Servlets
- `src/main/webapp` — JSP views
- `sql/schema.sql` — database schema

Notes
-----
- Passwords are stored in plain text in the demo seed; replace with hashed passwords in production.
- Input validation is minimal; expand as needed.
## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
