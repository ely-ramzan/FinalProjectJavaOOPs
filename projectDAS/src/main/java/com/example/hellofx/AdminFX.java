package com.example.hellofx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.example.hellofx.DoctorAppointmentSystem.showUserNotAddedWarning;
import static com.example.hellofx.DoctorAppointmentSystem.switchToScene;

public class AdminFX {
    public static Scene createAddDoctorScreen(Stage stage,Scene scene) {
        VBox addDoctorBox = new VBox(20);
        addDoctorBox.setAlignment(Pos.CENTER);
        addDoctorBox.setPadding(new Insets(20));

        // Doctor Name
        VBox nameBox = new VBox(10);
        nameBox.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("Doctor Name:");
        TextField nameField = new TextField();
        nameField.setMaxWidth(300);
        nameBox.getChildren().addAll(nameLabel, nameField);

        // Doctor Type
        VBox typeBox = new VBox(10);
        typeBox.setAlignment(Pos.CENTER);
        Label typeLabel = new Label("Doctor Type:");
        ChoiceBox<String> typeChoiceBox = new ChoiceBox<>();
        typeChoiceBox.getItems().addAll("ER", "GENERAL_PHYSICIAN", "PSYCHIATRIST", "DERMATOLOGIST", "ENT", "RADIOLOGIST");
        typeChoiceBox.setMaxWidth(300);
        typeBox.getChildren().addAll(typeLabel, typeChoiceBox);

        // Doctor Username
        VBox usernameBox = new VBox(10);
        usernameBox.setAlignment(Pos.CENTER);
        Label usernameLabel = new Label("Doctor Username:");
        TextField usernameField = new TextField();
        usernameField.setMaxWidth(300);
        usernameBox.getChildren().addAll(usernameLabel, usernameField);

        // Doctor Password
        VBox passwordBox = new VBox(10);
        passwordBox.setAlignment(Pos.CENTER);
        Label passwordLabel = new Label("Doctor Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(300);
        passwordBox.getChildren().addAll(passwordLabel, passwordField);

        // Availability
        VBox availabilityBox = new VBox(10);
        availabilityBox.setAlignment(Pos.CENTER);
        Label availabilityLabel = new Label("Is Available:");
        CheckBox availabilityCheckBox = new CheckBox();
        availabilityBox.getChildren().addAll(availabilityLabel, availabilityCheckBox);

        // Add Button
        Button addButton = new Button("Add Doctor");

        addButton.setOnAction(event -> {
            String doctorName = nameField.getText();
            String doctorType = typeChoiceBox.getValue();
            String doctorUsername = usernameField.getText();
            String doctorPassword = passwordField.getText();
            boolean isAvailable = availabilityCheckBox.isSelected();

            // Add validation and database insertion logic here
            if(!JDBC.addDoctorToDataBase(doctorName,doctorType,doctorUsername,doctorPassword,isAvailable))
                showUserNotAddedWarning("Warning","Patient Not Added");
            else
                showUserNotAddedWarning("Success","User Added Successfully");

            // Clear the fields after adding the doctor
            nameField.clear();
            typeChoiceBox.setValue(null);
            usernameField.clear();
            passwordField.clear();
            availabilityCheckBox.setSelected(false);
        });

        // Back Button
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> switchToScene(stage,scene));
        addDoctorBox.getChildren().addAll(nameBox, typeBox, usernameBox, passwordBox, availabilityBox, addButton, backButton);
        return new Scene(addDoctorBox, 420, 350);
    }

    public static VBox searchEntityByID(String entityType, int id) {
        // Placeholder content
        Label resultLabel = new Label("Search Result:");
        Label detailsLabel = new Label();

        // Perform actual database search based on entityType and id
        String details = performDatabaseSearch(entityType, id);

        // Display the details in the VBox
        detailsLabel.setText(details);
        VBox resultBox = new VBox(10, resultLabel, detailsLabel);
        resultBox.setAlignment(Pos.CENTER);
        resultBox.setPadding(new Insets(20));

        return resultBox;
    }

    private static String performDatabaseSearch(String entityType, int id) {
        if ("Doctor".equals(entityType)) {
            Doctor doctor = SearchObjectsFromList.searchDoctorByID(id);
            if(doctor == null)
                return "doctor not found";
            return doctor.toString();
        } else if ("Patient".equals(entityType)) {
            Patient patient = SearchObjectsFromList.searchPatientByID(id);
            if(patient == null)
                return "patient not found";
            return patient.toString();
        } else if ("Appointment".equals(entityType)) {
            Appointments appointments = SearchObjectsFromList.searchAppointmentByID(id);
            if(appointments == null)
                return "appointment not found";
            return appointments.toString();
        } else {
            return "Invalid entity type";
        }
    }
}
