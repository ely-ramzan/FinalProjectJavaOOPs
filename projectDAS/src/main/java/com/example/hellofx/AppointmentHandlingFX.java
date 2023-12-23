package com.example.hellofx;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class AppointmentHandlingFX extends Application {

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        CreateObservableLists.addObjectsToDoctorsFromDatabase();
        CreateObservableLists.addObjectsToPatientsFromDatabase();
        CreateObservableLists.addObjectsToAppointmentsFromDatabase(CreateObservableLists.appointments);
//        showAddAppointmentUI();
//        showMyBookedAppointments(1);
//        System.out.println(DeleteObjectsFromList.deleteAppointmentFromObservableList(5));
        getIntFromFXAndDeleteAppointment();
    }



    public static Scene showAddAppointmentUI(int id,Stage stage,Scene backScene) {


        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        // Doctor ChoiceBox
        Label doctorLabel = new Label("Select Doctor:");
        ChoiceBox<String> doctorChoiceBox = new ChoiceBox<>();
        CreateObservableLists.doctors.forEach(doctor -> doctorChoiceBox.getItems().add(doctor.getDoctorID() + "-" + doctor.getDoctorName()));

        // Date Picker
        Label dateLabel = new Label("Select Date:");
        DatePicker datePicker = new DatePicker();

        // Medical Condition Input
        Label medicalConditionLabel = new Label("Medical Condition:");
        TextField medicalConditionField = new TextField();

        // Add Button
        Button addButton = new Button("Add Appointment");


        addButton.setOnAction(event ->
        {
            int docId = Character.getNumericValue(doctorChoiceBox.getValue().charAt(0));
            if(JDBC.addAppointmentToDataBase(id,docId,
                java.sql.Date.valueOf(datePicker.getValue()), medicalConditionField.getText()))
                DoctorAppointmentSystem.showUserNotAddedWarning("Success","Appointment Added Successfully");
            else
                DoctorAppointmentSystem.showUserNotAddedWarning("Failed","Appoitnment not added");

            doctorChoiceBox.setValue(null);
            datePicker.setValue(null);
            medicalConditionField.clear();
        }
        );

        Button back = new Button("Back");
        back.setOnAction(
                e -> {
                    DoctorAppointmentSystem.switchToScene(stage,backScene);
                }
        );



        // Add controls to the VBox
        vbox.getChildren().addAll(doctorLabel, doctorChoiceBox, dateLabel,
                datePicker, medicalConditionLabel, medicalConditionField, addButton,back);

        return new Scene(vbox,420,350);
    }

    public static void showMyBookedAppointments(int patID,Stage stage,Scene backScene){

        Button back = new Button("Back");
        back.setOnAction(
                e -> {
                    DoctorAppointmentSystem.switchToScene(stage,backScene);
                }
        );

        VBox vBox = showAllTables.showAllDataFromAppointmentsInTables(SearchObjectsFromList.showMyAppointmentsUser(patID));
        vBox.getChildren().add(back);
        Scene scene = new Scene(vBox,420,350);
        DoctorAppointmentSystem.switchToScene(stage,scene);

    }

    public static int showIdInputForm() {
        // Create a new stage
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Enter Appointment ID");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        Label idLabel = new Label("Enter ID:");
        TextField idTextField = new TextField();

        // Create an "Enter" button
        Button enterButton = new Button("Enter");
        enterButton.setOnAction(event -> stage.close()); // Close the stage when the button is pressed

        vbox.getChildren().addAll(idLabel, idTextField, enterButton);

        // Create a scene and set it to the stage
        Scene scene = new Scene(vbox, 200, 150);
        stage.setScene(scene);

        // Show the stage and wait for user input
        stage.showAndWait();

        // Return the entered ID as an integer, or -1 if no input is provided
        try {
            return Integer.parseInt(idTextField.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void getIntFromFXAndDeleteAppointment(){
        if(DeleteObjectsFromList.deleteAppointmentFromObservableList(showIdInputForm()))
            DoctorAppointmentSystem.showUserNotAddedWarning("Success","Deleted Successfully");
        else
            DoctorAppointmentSystem.showUserNotAddedWarning("Failed","Failed to delete");
    }


    public static void getIntFromFXAndDeleteDoctor() {
        if (DeleteObjectsFromList.deleteDoctorFromObservableList(showIdInputForm())) {
            DoctorAppointmentSystem.showUserNotAddedWarning("Success", "Doctor Deleted Successfully");
        } else {
            DoctorAppointmentSystem.showUserNotAddedWarning("Failed", "Failed to delete Doctor");
        }
    }

    public static void getIntFromFXAndDeletePatient() {
        if (DeleteObjectsFromList.deletePatientFromObservableList(showIdInputForm())) {
            DoctorAppointmentSystem.showUserNotAddedWarning("Success", "Patient Deleted Successfully");
        } else {
            DoctorAppointmentSystem.showUserNotAddedWarning("Failed", "Failed to delete Patient");
        }
    }

    public static Scene showAddAppointmentUIDoctor(int id,Stage stage,Scene backScene) {


        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        // Doctor ChoiceBox
        Label doctorLabel = new Label("Select Patient:");
        ChoiceBox<String> patientChoiceBox = new ChoiceBox<>();
        CreateObservableLists.patients.forEach(patient -> patientChoiceBox.getItems().add(patient.getId() + "-" + patient.getPatientName()));

        // Date Picker
        Label dateLabel = new Label("Select Date:");
        DatePicker datePicker = new DatePicker();

        // Medical Condition Input
        Label medicalConditionLabel = new Label("Medical Condition:");
        TextField medicalConditionField = new TextField();

        // Add Button
        Button addButton = new Button("Add Appointment");


        addButton.setOnAction(event ->
                {
                    int patID = Character.getNumericValue(patientChoiceBox.getValue().charAt(0));
                    if(JDBC.addAppointmentToDataBase(patID,id,
                            java.sql.Date.valueOf(datePicker.getValue()), medicalConditionField.getText()))
                        DoctorAppointmentSystem.showUserNotAddedWarning("Success","Appointment Added Successfully");
                    else
                        DoctorAppointmentSystem.showUserNotAddedWarning("Failed","Appoitnment not added");

                    patientChoiceBox.setValue(null);
                    datePicker.setValue(null);
                    medicalConditionField.clear();
                }
        );

        Button back = new Button("Back");
        back.setOnAction(
                e -> {
                    DoctorAppointmentSystem.switchToScene(stage,backScene);
                }
        );



        // Add controls to the VBox
        vbox.getChildren().addAll(doctorLabel, patientChoiceBox, dateLabel,
                datePicker, medicalConditionLabel, medicalConditionField, addButton,back);

        return new Scene(vbox,420,350);
    }

    public static void showMyBookedAppointmentsDoctor(String username,Stage stage,Scene backScene){
        Button back = new Button("Back");
        back.setOnAction(
                e -> {
                    DoctorAppointmentSystem.switchToScene(stage,backScene);
                }
        );

        VBox vBox = showAllTables.showAllDataFromAppointmentsInTables(SearchObjectsFromList.showMyAppointmentsUser(username));
        vBox.getChildren().add(back);
        Scene scene = new Scene(vBox,420,350);
        DoctorAppointmentSystem.switchToScene(stage,scene);

    }

    public static Scene showAddAppointmentUIAdmin(Stage stage,Scene scene) {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        // Doctor ChoiceBox
        Label doctorLabel = new Label("Select Doctor:");
        ChoiceBox<String> doctorChoiceBox = new ChoiceBox<>();
        CreateObservableLists.doctors.forEach(doctor -> doctorChoiceBox.getItems().add(doctor.getDoctorID() + "-" + doctor.getDoctorName()));

        // Patient ChoiceBox
        Label patientLabel = new Label("Select Patient:");
        ChoiceBox<String> patientChoiceBox = new ChoiceBox<>();
        CreateObservableLists.patients.forEach(patient -> patientChoiceBox.getItems().add(patient.getId() + "-" + patient.getPatientName()));

        // Date Picker
        Label dateLabel = new Label("Select Date:");
        DatePicker datePicker = new DatePicker();

        // Medical Condition Input
        Label medicalConditionLabel = new Label("Medical Condition:");
        TextField medicalConditionField = new TextField();

        // Add Button
        Button addButton = new Button("Add Appointment");

        addButton.setOnAction(event -> {
            int docID = Character.getNumericValue(doctorChoiceBox.getValue().charAt(0));
            int patID = Character.getNumericValue(patientChoiceBox.getValue().charAt(0));

            if (JDBC.addAppointmentToDataBase(patID, docID,
                    java.sql.Date.valueOf(datePicker.getValue()), medicalConditionField.getText())) {
                DoctorAppointmentSystem.showUserNotAddedWarning("Success", "Appointment Added Successfully");
            } else {
                DoctorAppointmentSystem.showUserNotAddedWarning("Failed", "Appointment not added");
            }

            doctorChoiceBox.setValue(null);
            patientChoiceBox.setValue(null);
            datePicker.setValue(null);
            medicalConditionField.clear();
        });

        // Back Button
        Button back = new Button("Back");
        back.setOnAction(e -> DoctorAppointmentSystem.switchToScene(stage,scene));

        // Add controls to the VBox
        vbox.getChildren().addAll(doctorLabel, doctorChoiceBox, patientLabel, patientChoiceBox,
                dateLabel, datePicker, medicalConditionLabel, medicalConditionField, addButton, back);

        return new Scene(vbox, 420, 350);
    }


}
