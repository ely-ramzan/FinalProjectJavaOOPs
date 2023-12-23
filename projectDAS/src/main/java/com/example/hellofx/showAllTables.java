package com.example.hellofx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Date;

public class showAllTables extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
//        showAllDataFromPatientsInTables(stage);
//        showAllDataFromDoctorsInTables(stage);
//        showAllDataFromAppointmentsInTables(stage);
    }

    public static VBox showAllDataFromPatientsInTables(ObservableList<Patient> patients){
//        try {
////            CreateObservableLists.addObjectsToPatientsFromDatabase();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        // Create a ChoiceBox for selecting sorting variable
        ChoiceBox<String> sortingChoiceBox = new ChoiceBox<>();
        sortingChoiceBox.getItems().addAll("ID", "Name", "Age", "Type");
        sortingChoiceBox.setValue("ID"); // Default choice

        // Create a TableView
        TableView<Patient> tableView = new TableView<>();
        tableView.setItems(CreateObservableLists.patients);

        // Define columns
        TableColumn<Patient, Integer> idCol = new TableColumn<>("ID");
//        idCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        idCol.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("id"));

        TableColumn<Patient, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Patient,String>("patientName"));

        TableColumn<Patient, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("patientAge"));

        TableColumn<Patient, String> phoneNumberCol = new TableColumn<>("Phone Number");
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Patient,String>("patientPhoneNumber"));

        TableColumn<Patient, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<Patient,String>("patientType"));

        TableColumn<Patient, String> userNameCol = new TableColumn<>("Username");
        userNameCol.setCellValueFactory(new PropertyValueFactory<Patient,String>("patientUserName"));

        TableColumn<Patient, String> passwordCol = new TableColumn<>("Password");
        passwordCol.setCellValueFactory(new PropertyValueFactory<Patient,String>("patientPassword"));

        // Add columns to the table
        tableView.getColumns().addAll(idCol, nameCol, ageCol, phoneNumberCol, typeCol, userNameCol, passwordCol);

        // Set sorting functionality based on user choice
        sortingChoiceBox.setOnAction(event -> {
            String selectedVariable = sortingChoiceBox.getValue();
            tableView.getSortOrder().clear();

            switch (selectedVariable) {
                case "ID":
                    idCol.setSortType(TableColumn.SortType.ASCENDING);
                    tableView.getSortOrder().add(idCol);
                    break;
                case "Name":
                    nameCol.setSortType(TableColumn.SortType.ASCENDING);
                    tableView.getSortOrder().add(nameCol);
                    break;
                case "Age":
                    ageCol.setSortType(TableColumn.SortType.ASCENDING);
                    tableView.getSortOrder().add(ageCol);
                    break;
                case "Type":
                    typeCol.setSortType(TableColumn.SortType.ASCENDING);
                    tableView.getSortOrder().add(typeCol);
                    break;
            }
        });


        VBox vBox = new VBox(sortingChoiceBox,tableView);
        return vBox;
        // Create a scene and set it to the stage
//        Scene scene = new Scene(vBox, 800, 600);
//        stage.setScene(scene);
//
//        // Set the stage title and show it
//        stage.setTitle("Patient List");
//        stage.show();

    }

    public static VBox showAllDataFromDoctorsInTables(ObservableList<Doctor> doctors){
//        try {
//            CreateObservableLists.addObjectsToDoctorsFromDatabase();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        ChoiceBox<String> sortChoiceBox = new ChoiceBox<>();
        sortChoiceBox.getItems().addAll("ID", "Name", "Category");
        sortChoiceBox.setValue("ID"); // Default sorting column

        // Create a TableView
        TableView<Doctor> tableView = new TableView<>();
        tableView.setItems(CreateObservableLists.doctors);

        // Define columns
        TableColumn<Doctor, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Doctor,Integer>("doctorID"));

        TableColumn<Doctor, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Doctor,String>("doctorName"));

        TableColumn<Doctor, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<Doctor,String>("doctorType"));

        TableColumn<Doctor, String> userNameCol = new TableColumn<>("Username");
        userNameCol.setCellValueFactory(new PropertyValueFactory<Doctor,String>("doctorUserName"));

        TableColumn<Doctor, String> passwordCol = new TableColumn<>("Password");
        passwordCol.setCellValueFactory(new PropertyValueFactory<Doctor,String>("doctorPassword"));

        TableColumn<Doctor, Boolean> availabilityCol = new TableColumn<>("Availability");
        availabilityCol.setCellValueFactory(new PropertyValueFactory<Doctor,Boolean>("isAvailable"));

        // Add columns to the table
        tableView.getColumns().addAll(idCol, nameCol, categoryCol, userNameCol, passwordCol, availabilityCol);

        tableView.getSortOrder().add(idCol);

        // Bind sorting property to the choice box
        sortChoiceBox.setOnAction(event -> {
            String selectedColumn = sortChoiceBox.getValue();
            TableColumn<Doctor, ?> column;
            switch (selectedColumn) {
                case "ID":
                    column = idCol;
                    break;
                case "Name":
                    column = nameCol;
                    break;
                case "Category":
                    column = categoryCol;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid column: " + selectedColumn);
            }
            tableView.getSortOrder().setAll(column);
        });


        // Create a VBox to hold ChoiceBox and TableView
        VBox vBox = new VBox(sortChoiceBox, tableView);
        return vBox;

//        // Create a scene and set it to the stage
//        Scene scene = new Scene(vBox, 800, 600);
//        stage.setScene(scene);
//
//        // Set the stage title and show it
//        stage.setTitle("Doctor List");
//        stage.show();
    }

    public static VBox showAllDataFromAppointmentsInTables(ObservableList<Appointments> appointments) {
//        try {
//            CreateObservableLists.addObjectsToAppointmentsFromDatabase(appointments);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        // Create a ChoiceBox for sorting
        ChoiceBox<String> sortChoiceBox = new ChoiceBox<>();
        sortChoiceBox.getItems().addAll("ID", "Doctor ID", "Patient ID", "Medical Condition", "Date");
        sortChoiceBox.setValue("ID"); // Default sorting column

        // Create a TableView
        TableView<Appointments> tableView = new TableView<>();
        tableView.setItems(appointments);

        // Define columns
        TableColumn<Appointments, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));

        TableColumn<Appointments, Integer> doctorIdCol = new TableColumn<>("Doctor ID");
        doctorIdCol.setCellValueFactory(new PropertyValueFactory<>("doctorID"));

        TableColumn<Appointments, Integer> patientIdCol = new TableColumn<>("Patient ID");
        patientIdCol.setCellValueFactory(new PropertyValueFactory<>("patientID"));

        TableColumn<Appointments, String> medicalConditionCol = new TableColumn<>("Medical Condition");
        medicalConditionCol.setCellValueFactory(new PropertyValueFactory<>("medicalCondition"));

        TableColumn<Appointments, Date> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Add columns to the table
        tableView.getColumns().addAll(idCol, doctorIdCol, patientIdCol, medicalConditionCol, dateCol);

        // Enable sorting
        tableView.getSortOrder().add(idCol);

        // Bind sorting property to the choice box
        sortChoiceBox.setOnAction(event -> {
            String selectedColumn = sortChoiceBox.getValue();
            TableColumn<Appointments, ?> column;
            switch (selectedColumn) {
                case "ID":
                    column = idCol;
                    break;
                case "Doctor ID":
                    column = doctorIdCol;
                    break;
                case "Patient ID":
                    column = patientIdCol;
                    break;
                case "Medical Condition":
                    column = medicalConditionCol;
                    break;
                case "Date":
                    column = dateCol;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid column: " + selectedColumn);
            }
            tableView.getSortOrder().setAll(column);
        });

        // Create a VBox to hold ChoiceBox and TableView
        VBox vBox = new VBox(sortChoiceBox, tableView);

        return vBox;
    }



}
