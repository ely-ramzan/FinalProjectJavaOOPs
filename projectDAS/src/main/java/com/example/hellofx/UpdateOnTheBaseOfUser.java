package com.example.hellofx;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
public class UpdateOnTheBaseOfUser extends Application{


        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            try {
                CreateObservableLists.addObjectsToPatientsFromDatabase();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Button updateButton = new Button("Update Patient");
            updateButton.setOnAction(event -> showUpdateDialog());

            VBox vbox = new VBox(10);
            vbox.setPadding(new Insets(20));
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().add(updateButton);

            Scene scene = new Scene(vbox, 300, 200);
            primaryStage.setTitle("Update Patient");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        private void showUpdateDialog() {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Update Patient");
            dialog.setHeaderText(null);

            VBox vbox = new VBox(10);
            vbox.setPadding(new Insets(10));

            TextField idField = new TextField();
            idField.setPromptText("Enter ID");

            ChoiceBox<String> fieldChoiceBox = new ChoiceBox<>();
            fieldChoiceBox.getItems().addAll("name", "age", "phoneNumber", "type", "userName", "password");
            fieldChoiceBox.getItems().addAll("Choose Field", "name", "age", "phoneNumber", "type", "userName", "password");
            fieldChoiceBox.getSelectionModel().selectFirst(); // Set the default value

            TextField valueField = new TextField();
            valueField.setPromptText("Enter New Value");

            HBox idBox = new HBox(10, new Label("ID:"), idField);
            HBox fieldBox = new HBox(10, new Label("Field:"), fieldChoiceBox);
            HBox valueBox = new HBox(10, new Label("New Value:"), valueField);

            vbox.getChildren().addAll(idBox, fieldBox, valueBox);
            dialog.getDialogPane().setContent(vbox);

            ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == updateButtonType) {
                    return idField.getText() + "," + fieldChoiceBox.getValue() + "," + valueField.getText();
                }
                return null;
            });

            dialog.showAndWait().ifPresent(result -> {
                String[] parts = result.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String field = parts[1];
                    String newValue = parts[2];

                    // Update the patient in the list
                    updatePatientInList(id, field, newValue);
                }
            });
        }

        private void updatePatientInList(int id, String field, String newValue) {
            Patient patientToUpdate = CreateObservableLists.searchPatientBasedOnID(id);

            if (patientToUpdate != null) {
                switch (field) {
                    case "name":
                        patientToUpdate.setPatientName(newValue);
                        break;
                    case "age":
                        patientToUpdate.setPatientAge(Integer.parseInt(newValue));
                        break;
                    case "phoneNumber":
                        patientToUpdate.setPatientPhoneNumber(newValue);
                        break;
                    case "type":
                        patientToUpdate.setPatientType(PatientType.checkType(newValue));
                        break;
                    case "userName":
                        patientToUpdate.setPatientUserName(newValue);
                        break;
                    case "password":
                        patientToUpdate.setPatientPassword(newValue);
                        break;
                    default:
                        System.out.println("Invalid field to update.");
                }
            } else {
                System.out.println("Patient with ID " + id + " not found.");
            }
            boolean isUpdated = JDBC.updatePatientInDatabase(patientToUpdate);

            if (!isUpdated) {
                System.out.println("Failed to update patient in the database.");
            }
            else {
                System.out.println("Patient with ID " + id + " not found.");
            }
        }

}
