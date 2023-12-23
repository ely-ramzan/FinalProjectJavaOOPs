package com.example.hellofx;

import java.io.File;
import java.sql.SQLException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.controlsfx.control.Notifications;

import static com.example.hellofx.AdminFX.createAddDoctorScreen;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.rgb;

public class DoctorAppointmentSystem extends Application {
    private Button patientButton;  // Fix: Changed the type to Button
    private Button doctorButton;
    private Button adminButton;


    public static void main(String[] args) {

        launch(args);
    }
    @Override
    public void start(Stage stage) {
        try {
            CreateObservableLists.addObjectsToAppointmentsFromDatabase(CreateObservableLists.appointments);
            CreateObservableLists.addObjectsToPatientsFromDatabase();
            CreateObservableLists.addObjectsToDoctorsFromDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //add icon:
        Image logoImage = new Image(new File("E:\\java practice\\projectDAS\\src\\main\\java\\com\\example\\hellofx\\logoImage.jpeg").toURI().toString());
        stage.getIcons().add(logoImage);
        ImageView logoImageView = new ImageView(logoImage);

        // Create VBox for the logo
        VBox logoContainer = new VBox(20);
        logoContainer.getChildren().add(logoImageView);

        // Load background image
        Image backgroundImageView = new Image(new File("E:\\java practice\\projectDAS\\src\\main\\java\\com\\example\\hellofx\\mainMenuBackgroundImage.jpeg").toURI().toString());

        // Creating ImageView
        ImageView backgroundImageViewNode = new ImageView(backgroundImageView);
        backgroundImageViewNode.setFitWidth(500);
        backgroundImageViewNode.setFitHeight(350);
        ;
        // Creating VBox and HBox
        VBox selectionPage = new VBox(20);
        selectionPage.setStyle("-fx-background-color:#87CEEB"); // Set background color for VBox
        HBox selectionMenu = new HBox(40);
        selectionPage.getChildren().addAll(backgroundImageViewNode);

        // Set the scene to the stage
        Scene scene = new Scene(selectionPage, 800, 600);
        //aligning elements in center:
        selectionPage.setAlignment(Pos.CENTER);
        selectionMenu.setAlignment(Pos.CENTER);

        //label:
        Label areYouLabel = new Label("Are You a?");
        areYouLabel.getStyleClass().add("label");
        areYouLabel.setStyle("-fx-font-size: 50px;\n" +
                "    -fx-font-family: 'Roboto ';\n" +
                "    -fx-font-weight: BOLD;" +
                "-fx-text-fill:  #191970");

        //Button:
        Button patientButton = new Button("Patient");
        //patientButton.setStyle("-fx-background-color: #00ff00;"); // Green color


        patientButton.setStyle("-fx-background-color: #00CED1;\n" +
                "    -fx-text-fill: #191970;\n" +
                "    -fx-font-size: 17px;\n" +
                "    -fx-border-radius: 12px;" +
                "-fx-font-family: 'MS UI Gothic'");


        //patient code here:
        patientButton.setOnAction(
                e -> {


                    //login page:
                    VBox patientPannelV = new VBox(20);
                    patientPannelV.setAlignment(Pos.CENTER);

                    patientPannelV.setBackground(new Background(new BackgroundFill(rgb(122,196,199),CornerRadii.EMPTY,Insets.EMPTY)));
                    Scene patientScreen = new Scene(patientPannelV, 420, 350);

                    //heading:
                    Text patientText = new Text("Welcome to Health Care");
                    patientText.setStyle("-fx-font-size: 20;" +
                            "-fx-font-weight: BOLD");

                    // username:
                    VBox userNameBox = new VBox(10);
                    userNameBox.setAlignment(Pos.CENTER);
                    Label userNameLabel = new Label("UserName");
                    TextField userNameTextField = new TextField();
                    userNameTextField.setMaxWidth(300);
                    userNameBox.getChildren().addAll(userNameLabel, userNameTextField);

                    //password:
                    VBox passwordBox = new VBox(10);
                    passwordBox.setAlignment(Pos.CENTER);
                    Label passWordLabel = new Label("Password");
                    PasswordField passwordField = new PasswordField();
                    passwordField.setMaxWidth(300);
                    passwordBox.getChildren().addAll(passWordLabel, passwordField);

                    // Submit Buttons:
                    HBox buttonBox = new HBox(50);
                    buttonBox.setAlignment(Pos.CENTER);
                    Button loginButton = new Button("Login");
                    Button signupButton = new Button("Signup");
                    Button backButton = new Button("Back");

                    //back button functionality
                    backButton.setOnAction(
                            a -> {
                                switchToScene(stage,scene);
                            });

                    // Patient login Button Action
                    loginButton.setOnAction(
                            q -> {

                                int id = verifyLoginDetailsFromUser(userNameTextField.getText(), passwordField.getText());
                                if(id  > 0){

                                    // Login page:
                                    VBox patientPortalVBOX = new VBox(20);
                                    patientPortalVBOX.setAlignment(Pos.CENTER);
                                    patientPortalVBOX.setBackground(new Background(new BackgroundFill(rgb(122,196,199),CornerRadii.EMPTY,Insets.EMPTY)));
                                    Scene patientPortalScene = new Scene(patientPortalVBOX,420,350);

                                    // Heading:
                                    Text loginText = new Text("Patient Portal");
                                    loginText.setStyle("-fx-font-size: 20;" +
                                            "-fx-font-weight: BOLD");

                                    Button back = new Button("Back");

//                                     Back button functionality
                                    back.setOnAction(
                                            a -> {
                                                switchToScene(stage, patientScreen);
                                            });

                                    // Add Appointment Button Action
                                    Button addAppointmentButton = new Button("Add Appointment");
                                    addAppointmentButton.setOnAction(
                                            event -> {
                                                // Logic for add appointment
                                                Scene scene1 = AppointmentHandlingFX.showAddAppointmentUI(id,stage,patientPortalScene);
                                                switchToScene(stage,scene1);

                                            }
                                    );

                                    // Show Appointment Button Action
                                    Button showAppointmentButton = new Button("Show Appointments");
                                    showAppointmentButton.setOnAction(
                                            event -> {
                                                AppointmentHandlingFX.showMyBookedAppointments(id,stage,patientPortalScene);
                                            }
                                    );

                                    // Delete Appointment Button Action
                                    Button deleteAppointmentButton = new Button("Delete Appointment");
                                    deleteAppointmentButton.setOnAction(
                                            event -> {
                                                // Logic for delete appointment
                                                AppointmentHandlingFX.getIntFromFXAndDeleteAppointment();
                                            }
                                    );

                                    HBox patientPortalButtonHBOX = new HBox(50);
                                    buttonBox.setAlignment(Pos.CENTER);
                                    buttonBox.getChildren().addAll(addAppointmentButton, showAppointmentButton, deleteAppointmentButton);

                                    // Add to vbox
                                    patientPortalVBOX.getChildren().addAll(loginText,addAppointmentButton, showAppointmentButton, deleteAppointmentButton, backButton);

                                    //scene:

                                    stage.setScene(patientPortalScene);
                                    stage.setTitle("Patient Portal");
                                    stage.setFullScreen(true);
                                    stage.show();

                                } else {
                                    showInvalidCredentialsWarning();
                                }

                            }
                    );



                    signupButton.setOnAction(
                            s -> {
                                //patient data: name,age,phone,opd/emergency/regular,medical-condition,username,password
                                //rootBox:
                                VBox signUpBox = new VBox(20);
                                signUpBox.setAlignment(Pos.CENTER);

                                //nameBox:
                                VBox nameBox = new VBox(10);
                                nameBox.setAlignment(Pos.CENTER);
                                Label nameLabel = new Label("Name");
                                TextField nameField = new TextField();
                                nameField.setMaxWidth(300);
                                nameBox.getChildren().addAll(nameLabel, nameField);

                                //age:
                                // Create a Slider for age selection
                                VBox ageBox = new VBox(10);
                                ageBox.setAlignment(Pos.CENTER);
                                Slider ageSlider = new Slider(0, 100, 20); // Min, Max, Default Value
                                ageSlider.setShowTickMarks(true);
                                ageSlider.setShowTickLabels(true);
                                ageSlider.setMajorTickUnit(10);
                                ageSlider.setMaxWidth(300);
                                //create label
                                Label ageLabel = new Label("Selected Age: " + (int) ageSlider.getValue());

                                // Update the Label when the Slider value changes
                                ageSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                                        ageLabel.setText("Selected Age: " + newValue.intValue()));
                                ageBox.getChildren().addAll(ageLabel, ageSlider);

                                //phone number:
                                VBox phoneBox = new VBox(10);
                                phoneBox.setAlignment(Pos.CENTER);
                                Label phoneNumberLabel = new Label("Phone Number");
                                TextField phoneNumberField = new TextField();
                                phoneNumberField.setMaxWidth(300);
                                phoneBox.getChildren().addAll(phoneNumberLabel, phoneNumberField);

                                //option:
                                VBox optionsBox = new VBox(10);
                                optionsBox.setAlignment(Pos.CENTER);
                                ChoiceBox<String> healthcareOptions = new ChoiceBox<>();
                                healthcareOptions.getItems().addAll("OPD", "emergency", "regular");
                                healthcareOptions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                                    if (newValue == null) {
                                        healthcareOptions.setValue("regular");
                                    }
                                });
                                healthcareOptions.setMaxWidth(200);
                                optionsBox.getChildren().add(healthcareOptions);

                                // username:
                                VBox userNameBoxSU = new VBox(10);
                                userNameBoxSU.setAlignment(Pos.CENTER);
                                Label userNameLabelSU = new Label("UserName");
                                TextField userNameTextFieldSU = new TextField();
                                userNameTextFieldSU.setMaxWidth(300);
                                userNameBoxSU.getChildren().addAll(userNameLabelSU, userNameTextFieldSU);

                                //password:
                                VBox passwordBoxSU = new VBox(10);
                                passwordBoxSU.setAlignment(Pos.CENTER);
                                Label passWordLabelSU = new Label("Password");
                                PasswordField passwordFieldSU = new PasswordField();
                                passwordFieldSU.setMaxWidth(300);
                                passwordBoxSU.getChildren().addAll(passWordLabelSU, passwordFieldSU);

                                //get data:


                                //text:
                                VBox textBox = new VBox(10);
                                Text text = new Text("Enter Your Details to SIGNUP");
                                textBox.setAlignment(Pos.CENTER);
                                text.setStyle("--body-font-size: 20;" +
                                        "-fx-font-weight: BOLD;");

                                //Button:
                                HBox buttonBoxSU = new HBox(50);
                                buttonBoxSU.setAlignment(Pos.CENTER);

                                Button enterButton = new Button("Enter");
                                Button backkButton = new Button("Back");
                                backButton.setOnAction(
                                        backFromSignup -> {
                                            switchToScene(stage,patientScreen);
                                        });

                                buttonBoxSU.getChildren().addAll(enterButton, backButton);

                                enterButton.setOnAction(
                                        signup -> {
                                            String namePatientSignup = nameField.getText();
                                            int agePatientSignup = (int) ageSlider.getValue();
                                            String phoneNumberPatientSignup = phoneNumberField.getText();
                                            String selectedOptionPatientSignup = healthcareOptions.getValue();
                                            String usernamePatientSignup = userNameTextFieldSU.getText();
                                            String passwordPatientSignup = passwordFieldSU.getText();

                                            if(!JDBC.addPatientToDataBase(namePatientSignup,agePatientSignup,phoneNumberPatientSignup,selectedOptionPatientSignup,usernamePatientSignup,passwordPatientSignup))
                                                showUserNotAddedWarning("Warning","Patient Not Added");
                                            else
                                                showUserNotAddedWarning("Success","User Added Successfully");
                                        }
                                );


                                signUpBox.getChildren().addAll(text, nameBox, ageBox, phoneBox, optionsBox, userNameBoxSU, passwordBoxSU, enterButton,backButton);
                                Scene signupPage = new Scene(signUpBox, 420, 350);
                                stage.setScene(signupPage);
                                stage.setTitle("Patient SignUp");
                                stage.show();
                                stage.setFullScreen(true);
                            }
                    );


                    buttonBox.getChildren().addAll(loginButton, signupButton,backButton);

                    //add to vbox
                    patientPannelV.getChildren().addAll(patientText, userNameBox, passwordBox,buttonBox);
                    //add to scene

                    //add to stage
                    stage.setScene(patientScreen);
                    stage.setTitle("Patient Panel");
                    stage.setFullScreen(true);
                    stage.show();
                }
        );


        //doctor code:
        Button doctorButton = new Button("Doctor");
        doctorButton.setStyle("-fx-background-color: #00CED1;\n" +
                "    -fx-text-fill: #191970;\n" +
                "    -fx-font-size: 17px;\n" +
                "    -fx-border-radius: 12px;" +
                "-fx-font-family: 'MS UI Gothic'");


        doctorButton.setOnAction(
                e -> {
                    //login page:
                    VBox patientPannelV = new VBox(20);
                    patientPannelV.setAlignment(Pos.CENTER);

                    patientPannelV.setBackground(new Background(new BackgroundFill(rgb(180,212,238),CornerRadii.EMPTY,Insets.EMPTY)));
                    Text patientText = new Text("Welcome to Health Care");
                    patientText.setStyle("-fx-font-size: 20;" +
                            "-fx-font-weight: BOLD");

                    // username:
                    VBox userNameBox = new VBox(10);
                    userNameBox.setAlignment(Pos.CENTER);
                    Label userNameLabel = new Label("UserName");
                    TextField userNameTextField = new TextField();
                    userNameTextField.setMaxWidth(300);
                    userNameBox.getChildren().addAll(userNameLabel, userNameTextField);

                    //password:
                    VBox passwordBox = new VBox(10);
                    passwordBox.setAlignment(Pos.CENTER);
                    Label passWordLabel = new Label("Password");
                    PasswordField passwordField = new PasswordField();
                    passwordField.setMaxWidth(300);
                    passwordBox.getChildren().addAll(passWordLabel, passwordField);

                    // Submit Buttons:
                    Button loginButton = new Button("Login");
                    Button backButton = new Button("Back");
                    backButton.setOnAction(
                            a -> {
                                switchToScene(stage, scene);
                            });
                    loginButton.setOnAction(
                            q -> {
                                // Login page:

                                int docID = verifyLoginDetailsFromDoctors(userNameTextField.getText(),passwordField.getText());
                                if(docID > 0) {
                                    VBox doctorPanelV = new VBox(20);
                                    doctorPanelV.setAlignment(Pos.CENTER);
                                    doctorPanelV.setBackground(new Background(new BackgroundFill(rgb(180,212,238),CornerRadii.EMPTY,Insets.EMPTY)));
                                    Scene doctorScreen = new Scene(doctorPanelV , 420, 350);

                                    // Heading:
                                    Text loginText = new Text("Doctor login");
                                    loginText.setStyle("-fx-font-size: 20;" +
                                            "-fx-font-weight: BOLD");

                                    Button back = new Button("Back");

                                    // Back button functionality
                                    backButton.setOnAction(
                                            a -> {
                                                switchToScene(stage, scene);
                                            });

                                    // Add Appointment Button Action
                                    Button addAppointmentButton = new Button("Add Appointment");
                                    addAppointmentButton.setOnAction(
                                            event -> {
                                                Scene scene1 = AppointmentHandlingFX.showAddAppointmentUIDoctor(docID,stage,doctorScreen);
                                                switchToScene(stage,scene1);
                                            }
                                    );

                                    // Choose Appointment Button Action
                                    Button showAppointmentButton = new Button("Show Appointment");
                                    showAppointmentButton.setOnAction(
                                            event -> {
                                                AppointmentHandlingFX.showMyBookedAppointmentsDoctor(userNameTextField.getText(),stage,doctorScreen);
                                            }
                                    );

                                    // Delete Appointment Button Action
                                    Button deleteAppointmentButton = new Button("Delete Appointment");
                                    deleteAppointmentButton.setOnAction(
                                            event -> {
                                                AppointmentHandlingFX.getIntFromFXAndDeleteAppointment();
                                            }
                                    );

                                    HBox button = new HBox(50);
                                    button.setAlignment(Pos.CENTER);
                                    button.getChildren().addAll(addAppointmentButton, showAppointmentButton, deleteAppointmentButton);

                                    // Add to vbox
                                    doctorPanelV.getChildren().addAll(loginText, addAppointmentButton, showAppointmentButton, deleteAppointmentButton, backButton);


                                    // Add to stage
                                    stage.setScene(doctorScreen);
                                    stage.setTitle("Doctor Panel");
                                    stage.setFullScreen(true);
                                    stage.show();
                                } else {
                                    showInvalidCredentialsWarning();
                                }
                            }
                    );

                    // Submit Buttons:
                    HBox buttonBox = new HBox(10);
                    buttonBox.setAlignment(Pos.CENTER);
                    buttonBox.getChildren().addAll(loginButton, backButton);

                    //add to vbox
                    patientPannelV = new VBox(20);
                    patientPannelV.setAlignment(Pos.CENTER);
                    patientPannelV.getChildren().addAll(patientText, userNameBox, passwordBox, buttonBox);

                    //add to scene
                    Scene patientScreen = new Scene(patientPannelV, 420, 350);
                    //add to stage
                    stage.setScene(patientScreen);
                    stage.setTitle("Doctor Panel");
                    stage.setFullScreen(true);
                    stage.show();

                }
        );

        Button adminButton = new Button("Admin");
        adminButton.setStyle(
                "-fx-background-color: #00CED1;\n" +
                        "    -fx-text-fill: #191970;\n" +
                        "    -fx-font-size: 17px;\n" +
                        "    -fx-border-radius: 12px;" +
                        "-fx-font-family: 'MS UI Gothic'");

        adminButton.setOnAction(e -> {

            // Login page:
            VBox adminPanelVBox = new VBox(20);
            adminPanelVBox.setAlignment(Pos.CENTER);

            adminPanelVBox.setBackground(new Background(new BackgroundFill(Color.rgb(166, 202, 189),CornerRadii.EMPTY,Insets.EMPTY)));

            Scene adminPanelScene = new Scene(adminPanelVBox, 420, 350);

            // Heading:
            Text mainAdminText = new Text("Welcome to Health Care");
            mainAdminText.setStyle("-fx-font-size: 20;" +
                    "-fx-font-weight: BOLD");

            // Username:
            VBox userNameBox = new VBox(10);
            userNameBox.setAlignment(Pos.CENTER);
            Label userNameLabel = new Label("Username");
            TextField userNameTextFieldAdmin = new TextField();
            userNameTextFieldAdmin.setMaxWidth(300);
            userNameBox.getChildren().addAll(userNameLabel, userNameTextFieldAdmin);

            // Password:
            VBox passwordBox = new VBox(10);
            passwordBox.setAlignment(Pos.CENTER);
            Label passwordLabel = new Label("Password");
            PasswordField passwordFieldAdmin = new PasswordField();
            passwordFieldAdmin.setMaxWidth(300);
            passwordBox.getChildren().addAll(passwordLabel, passwordFieldAdmin);

            Text adminText = new Text("Admin's Choice");
            adminText.setStyle("-fx-font-size: 20;" +
                    "-fx-font-weight: BOLD");

            // Submit Buttons:
            Button enterButton = new Button("Log In");

            Button backButton = new Button("Back");

            backButton.setOnAction(
                    a -> {
                        switchToScene(stage, adminPanelScene);
                    });

            VBox adminVBox = new VBox(170); // Set spacing between components
            adminVBox.setAlignment(Pos.CENTER);

            HBox buttonsBox = new HBox(50); // Set spacing between buttons
            buttonsBox.setAlignment(Pos.CENTER);


            Button addButton = new Button("Add");
            addButton.setOnAction(
                    r -> {

                        VBox addPanelVBox = new VBox(20);
                        addPanelVBox.setAlignment(Pos.CENTER);
                        addPanelVBox.setBackground(new Background(new BackgroundFill(Color.rgb(166, 202, 189),CornerRadii.EMPTY,Insets.EMPTY)));

                        Scene addPanelScene = new Scene(addPanelVBox, 420, 350);

                        // Heading:
                        Text addText = new Text("Admin can add");
                        addText.setStyle("-fx-font-size: 20;" +
                                "-fx-font-weight: BOLD");

                        Button back = new Button("Back");

                        // Back button functionality
                        back.setOnAction(
                                a -> {
                                    switchToScene(stage, adminPanelScene);
                                });

                        // Add Doctor
                        Button docButton = new Button("Add Doctor");
                        docButton.setOnAction(
                                event -> {
                                    Scene addDoctorScene1 = createAddDoctorScreen(stage,adminPanelScene);
                                    switchToScene(stage,addDoctorScene1);
                                }
                        );

                        // Add Patient
                        Button patButton = new Button("Add Patient");
                        patButton.setOnAction(
                                event -> {
                                    VBox signUpBox = new VBox(20);
                                    signUpBox.setAlignment(Pos.CENTER);

                                    signUpBox.setBackground(new Background(new BackgroundFill(Color.rgb(166, 202, 189),CornerRadii.EMPTY,Insets.EMPTY)));

                                    //nameBox:
                                    VBox nameBox = new VBox(10);
                                    nameBox.setAlignment(Pos.CENTER);
                                    Label nameLabel = new Label("Name");
                                    TextField nameField = new TextField();
                                    nameField.setMaxWidth(300);
                                    nameBox.getChildren().addAll(nameLabel, nameField);

                                    //age:
                                    // Create a Slider for age selection
                                    VBox ageBox = new VBox(10);
                                    ageBox.setAlignment(Pos.CENTER);
                                    Slider ageSlider = new Slider(0, 100, 20); // Min, Max, Default Value
                                    ageSlider.setShowTickMarks(true);
                                    ageSlider.setShowTickLabels(true);
                                    ageSlider.setMajorTickUnit(10);
                                    ageSlider.setMaxWidth(300);
                                    //create label
                                    Label ageLabel = new Label("Selected Age: " + (int) ageSlider.getValue());

                                    // Update the Label when the Slider value changes
                                    ageSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                                            ageLabel.setText("Selected Age: " + newValue.intValue()));
                                    ageBox.getChildren().addAll(ageLabel, ageSlider);

                                    //phone number:
                                    VBox phoneBox = new VBox(10);
                                    phoneBox.setAlignment(Pos.CENTER);
                                    Label phoneNumberLabel = new Label("Phone Number");
                                    TextField phoneNumberField = new TextField();
                                    phoneNumberField.setMaxWidth(300);
                                    phoneBox.getChildren().addAll(phoneNumberLabel, phoneNumberField);

                                    //option:
                                    VBox optionsBox = new VBox(10);
                                    optionsBox.setAlignment(Pos.CENTER);
                                    ChoiceBox<String> healthcareOptions = new ChoiceBox<>();
                                    healthcareOptions.getItems().addAll("OPD", "emergency", "regular");
                                    healthcareOptions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                                        if (newValue == null) {
                                            healthcareOptions.setValue("regular");
                                        }
                                    });
                                    healthcareOptions.setMaxWidth(200);
                                    optionsBox.getChildren().add(healthcareOptions);

                                    // username:
                                    VBox userNameBoxSU = new VBox(10);
                                    userNameBoxSU.setAlignment(Pos.CENTER);
                                    Label userNameLabelSU = new Label("UserName");
                                    TextField userNameTextFieldSU = new TextField();
                                    userNameTextFieldSU.setMaxWidth(300);
                                    userNameBoxSU.getChildren().addAll(userNameLabelSU, userNameTextFieldSU);

                                    //password:
                                    VBox passwordBoxSU = new VBox(10);
                                    passwordBoxSU.setAlignment(Pos.CENTER);
                                    Label passWordLabelSU = new Label("Password");
                                    PasswordField passwordFieldSU = new PasswordField();
                                    passwordFieldSU.setMaxWidth(300);
                                    passwordBoxSU.getChildren().addAll(passWordLabelSU, passwordFieldSU);

                                    //get data:


                                    //text:
                                    VBox textBox = new VBox(10);
                                    Text text = new Text("Enter Your Details to SIGNUP");
                                    textBox.setAlignment(Pos.CENTER);
                                    text.setStyle("--body-font-size: 20;" +
                                            "-fx-font-weight: BOLD;");

                                    //Button:
                                    HBox buttonBoxSU = new HBox(50);
                                    buttonBoxSU.setAlignment(Pos.CENTER);

                                    Button enterButton1 = new Button("Enter");
                                    Button backkButton = new Button("Back");
                                    backButton.setOnAction(
                                            backFromSignup -> {
                                                switchToScene(stage,adminPanelScene);
                                            });

                                    buttonBoxSU.getChildren().addAll(enterButton1, backButton);

                                    enterButton1.setOnAction(
                                            signup -> {
                                                String namePatientSignup = nameField.getText();
                                                int agePatientSignup = (int) ageSlider.getValue();
                                                String phoneNumberPatientSignup = phoneNumberField.getText();
                                                String selectedOptionPatientSignup = healthcareOptions.getValue();
                                                String usernamePatientSignup = userNameTextFieldSU.getText();
                                                String passwordPatientSignup = passwordFieldSU.getText();

                                                if(!JDBC.addPatientToDataBase(namePatientSignup,agePatientSignup,phoneNumberPatientSignup,selectedOptionPatientSignup,usernamePatientSignup,passwordPatientSignup))
                                                    showUserNotAddedWarning("Warning","Patient Not Added");
                                                else
                                                    showUserNotAddedWarning("Success","User Added Successfully");
                                            }
                                    );


                                    signUpBox.getChildren().addAll(text, nameBox, ageBox, phoneBox, optionsBox, userNameBoxSU, passwordBoxSU, enterButton1,backButton);
                                    Scene signupPage = new Scene(signUpBox, 420, 350);
                                    stage.setScene(signupPage);
                                    stage.setTitle("Add Patient");
                                    stage.show();
                                    stage.setFullScreen(true);
                                }
                        );



                        Button appointmentButton = new Button("Add Appointment");
                        appointmentButton.setOnAction(
                                event -> {
                                    Scene scene1 = AppointmentHandlingFX.showAddAppointmentUIAdmin(stage,adminPanelScene);
                                    switchToScene(stage,scene1);
                                }
                        );


                        HBox button = new HBox(50);
                        button.setAlignment(Pos.CENTER);
                        button.getChildren().addAll(patButton, docButton,appointmentButton, back);

                        // Add to VBox
                        addPanelVBox.getChildren().addAll(addText, patButton, docButton,appointmentButton, backButton);

                        // Add to Scene


                        // Add to Stage
                        stage.setScene(addPanelScene);
                        stage.setTitle("Add Panel");
                        stage.setFullScreen(true);
                        stage.show();
                    }
            );

            // Update button functionality
            Button deleteButton = new Button("Delete");

            deleteButton.setOnAction(
                    l -> {
                        VBox updatePanelVBox = new VBox(20);
                        updatePanelVBox.setAlignment(Pos.CENTER);

                        updatePanelVBox.setBackground(new Background(new BackgroundFill(Color.rgb(166, 202, 189),CornerRadii.EMPTY,Insets.EMPTY)));
                        Scene updatePanelScene = new Scene(updatePanelVBox, 420, 350);

                        // Heading:
                        Text updateText = new Text("Admin can update");
                        updateText.setStyle("-fx-font-size: 20;" +
                                "-fx-font-weight: BOLD");

                        Button backButtonUpdate = new Button("Back");

                        // Back button functionality
                        backButtonUpdate.setOnAction(
                                a -> {
                                    switchToScene(stage, adminPanelScene);
                                });

                        // Update Doctor
                        Button docButtonUpdate = new Button("Doctor");
                        docButtonUpdate.setOnAction(
                                event -> {
                                    AppointmentHandlingFX.getIntFromFXAndDeleteDoctor();
                                }
                        );

                        Button appButtonUpdate = new Button("Appointment");
                        appButtonUpdate.setOnAction(
                                event -> {
                                    AppointmentHandlingFX.getIntFromFXAndDeleteAppointment();
                                }
                        );

                        // Update Patient
                        Button patButtonUpdate = new Button("Patient");
                        patButtonUpdate.setOnAction(
                                event -> {
                                    AppointmentHandlingFX.getIntFromFXAndDeletePatient();
                                }
                        );
                        HBox buttonUpdate = new HBox(50);
                        buttonUpdate.setAlignment(Pos.CENTER);
                        buttonUpdate.getChildren().addAll(patButtonUpdate, docButtonUpdate,appButtonUpdate, backButtonUpdate);

                        // Add to VBox
                        updatePanelVBox.getChildren().addAll(updateText, patButtonUpdate, docButtonUpdate,appButtonUpdate,backButtonUpdate);

                        // Add to Scene


                        // Add to Stage
                        stage.setScene(updatePanelScene);
                        stage.setTitle("Update Panel");
                        stage.setFullScreen(true);
                        stage.show();
                    }
            );

            // Report button functionality
            Button reportButton = new Button("Generate Report");

            reportButton.setOnAction(

                    l -> {
                        VBox reportPanelVBox = new VBox(20);
                        reportPanelVBox.setAlignment(Pos.CENTER);

                        reportPanelVBox.setBackground(new Background(new BackgroundFill(Color.rgb(166, 202, 189),CornerRadii.EMPTY,Insets.EMPTY)));

                        Scene reportPanelScene = new Scene(reportPanelVBox, 420, 350);

                        // Heading:
                        Text reportText = new Text("Admin can access records");
                        reportText.setStyle("-fx-font-size: 20;" +
                                "-fx-font-weight: BOLD");

                        Button backButtonReport = new Button("Back");

                        // Back button functionality
                        backButtonReport.setOnAction(
                                a -> {
                                    switchToScene(stage, adminPanelScene);
                                });

                        // Doctor
                        Button docRepButton = new Button("Doctor");

                        docRepButton.setOnAction(
                                event -> {
                                    Button buttonBackToDocs = new Button("Back");
                                    buttonBackToDocs.setOnAction(
                                            buttonBackToDocsEvent -> {
                                                switchToScene(stage,reportPanelScene);
                                            }
                                    );
                                    VBox vboxInDocs = showAllTables.showAllDataFromDoctorsInTables(CreateObservableLists.doctors);
                                    VBox rootBoxInDoctorReport = new VBox(5);
                                    rootBoxInDoctorReport.getChildren().addAll(vboxInDocs,backButtonReport);
                                    Scene appRepScene = new Scene(rootBoxInDoctorReport, 420, 350);
                                    switchToScene(stage,appRepScene);
                                }
                        );

                        // Patient
                        Button patRepButton = new Button("Patient");
                        patRepButton.setOnAction(
                                event -> {
                                    Button buttonBackToDocs = new Button("Back");
                                    buttonBackToDocs.setOnAction(
                                            buttonBackToDocsEvent -> {
                                                switchToScene(stage, reportPanelScene);
                                            }
                                    );
                                    VBox vboxInPat = showAllTables.showAllDataFromPatientsInTables(CreateObservableLists.patients);
                                    VBox rootBoxInPatientReport = new VBox(5);
                                    rootBoxInPatientReport.getChildren().addAll(vboxInPat,backButtonReport);
                                    Scene appRepScene = new Scene(rootBoxInPatientReport, 420, 350);
                                    switchToScene(stage, appRepScene);
                                }
                        );

                        // Appointments
                        Button appRepButton = new Button("Appointments");
                        appRepButton.setOnAction(
                                event -> {

                                    Button buttonBackToDocs = new Button("Back");
                                    buttonBackToDocs.setOnAction(
                                            buttonBackToDocsEvent -> {
                                                switchToScene(stage, reportPanelScene);
                                            }
                                    );
                                    VBox vboxinApps = showAllTables.showAllDataFromAppointmentsInTables(CreateObservableLists.appointments);
                                    VBox rootBoxInAdminReport = new VBox(5);
                                    rootBoxInAdminReport.getChildren().addAll(vboxinApps,backButtonReport);
                                    Scene appRepScene = new Scene(rootBoxInAdminReport, 420, 350);
                                    switchToScene(stage, appRepScene);

                                }
                        );

                        HBox buttonReport = new HBox(50);
                        buttonReport.setAlignment(Pos.CENTER);
                        buttonReport.getChildren().addAll(patRepButton, docRepButton, appRepButton, backButtonReport);

                        // Add to VBox
                        reportPanelVBox.getChildren().addAll(reportText, patRepButton, docRepButton, appRepButton, backButtonReport);

                        // Add to Scene

                        // Add to Stage
                        stage.setScene(reportPanelScene);
                        stage.setTitle("Report Panel");
                        stage.setFullScreen(true);
                        stage.show();
                    }
            );

            // Search button functionality
            Button searchButton = new Button("Search");

            searchButton.setOnAction(
                    l -> {
                        VBox searchPanelVBox = new VBox(20);
                        searchPanelVBox.setAlignment(Pos.CENTER);

                        searchPanelVBox.setBackground(new Background(new BackgroundFill(Color.rgb(166, 202, 189),CornerRadii.EMPTY,Insets.EMPTY)));
                        Scene searchPanelScene = new Scene(searchPanelVBox, 420, 350);

                        // Heading:
                        Text searchText = new Text("What do you want to search");
                        searchText.setStyle("-fx-font-size: 20;" +
                                "-fx-font-weight: BOLD");
                        VBox mainMenuVBox1 = new VBox(20);
                        mainMenuVBox1.setAlignment(Pos.CENTER);


                        VBox mainMenuVBox2 = new VBox(20);
                        mainMenuVBox2.setAlignment(Pos.CENTER);

                        ToggleGroup optionToggleGroup = new ToggleGroup();

                        RadioButton doctorRadioButton = createOptionRadioButton("Doctor", optionToggleGroup);
                        RadioButton patientRadioButton = createOptionRadioButton("Patient", optionToggleGroup);
                        RadioButton appointmentRadioButton = createOptionRadioButton("Appointment", optionToggleGroup);

                        Button submitButton = new Button("ok?");

                        String[] resultHolder = new String[1];
                        submitButton.setOnAction(event ->
                        {resultHolder[0] = handleOptionSelection(optionToggleGroup,submitButton);});


                        mainMenuVBox2.getChildren().addAll(doctorRadioButton, patientRadioButton, appointmentRadioButton, submitButton);

                        Text text = new Text("Click button below to enter ID");
                        text.setStyle("-fx-font-size: 20;" +
                                "-fx-font-weight: BOLD");

                        int idChosen[] = new int[1];
                        Button idButton = new Button("Enter ID");
                        idButton.setOnAction(
                                event -> {
                                    idChosen[0] = AppointmentHandlingFX.showIdInputForm();
                                }
                        );

                        Button okGoSearch = new Button("Search it");
                        okGoSearch.setOnAction(
                                event -> {
                                    VBox notificationContent = AdminFX.searchEntityByID(resultHolder[0],idChosen[0]);

                                    // Create a notification
                                    Notifications.create()
                                            .title("What I found")
                                            .text("Look!")
                                            .graphic(notificationContent)
                                            .hideAfter(Duration.seconds(10))
                                            .position(Pos.TOP_RIGHT) // Set the position of the notification
                                            .show();
                                }
                        );

                        Button backButtonSearch = new Button("Back");

                        // Back button functionality
                        backButtonSearch.setOnAction(
                                a -> {
                                    switchToScene(stage, adminPanelScene);
                                });

                        // By ID



                        HBox buttonSearch = new HBox(50);
                        buttonSearch.setAlignment(Pos.CENTER);
                        buttonSearch.getChildren().addAll(mainMenuVBox2,text,idButton,okGoSearch,backButtonSearch);

                        // Add to VBox
                        searchPanelVBox.getChildren().addAll(searchText,mainMenuVBox2,text,idButton,okGoSearch,backButtonSearch);

                        // Add to Scene

                        // Add to Stage
                        stage.setScene(searchPanelScene);
                        stage.setTitle("Search Panel");
                        stage.setFullScreen(true);
                        stage.show();
                    }
            );

            buttonsBox.getChildren().addAll(addButton, deleteButton, reportButton, searchButton);
            backButton.setOnAction(
                    backEvent -> {
                        switchToScene(stage,scene);
                    }
            );

            enterButton.setOnAction(h -> {
                String adminUserNameGot = userNameTextFieldAdmin.getText();
                String adminPasswordGot = passwordFieldAdmin.getText();

                if(Admin.returnAdminObject(adminUserNameGot,adminPasswordGot)){
                // Clear existing components in VBox
                adminPanelVBox.getChildren().clear();
                adminVBox.getChildren().add(adminText);

                // Add buttons to VBox
                adminPanelVBox.getChildren().addAll(adminText, buttonsBox,backButton);
                } else {
                    showInvalidCredentialsWarning();
                }

            });
//            Button backFromAdminToScreen = new Button("Back");
//            backFromAdminToScreen.setOnAction(backEvent -> switchToScene(stage,scene));

            adminPanelVBox.getChildren().addAll(userNameBox, passwordBox, enterButton, backButton);
            stage.setScene(adminPanelScene);
            stage.setTitle("Admin Panel");
            stage.setFullScreen(true);
            stage.show();
        });

        //adding buttons and labels to the screen:
        selectionPage.getChildren().add(areYouLabel);
        selectionMenu.getChildren().addAll(patientButton, doctorButton, adminButton);
        selectionPage.getChildren().add(selectionMenu);


//        Scene sc = new Scene(selectionPage, 420, 320);
//        //setting stage:
        stage.setTitle("Doctor Appointment System");
        stage.setScene(scene);
        stage.show();
    }
    //methods
    public static void switchToScene(Stage stage, Scene scene) {
        stage.setScene(scene);
        stage.setTitle("Doctor Appointment System");
        stage.setFullScreen(true);
        stage.show();
    }

    public static int verifyLoginDetailsFromUser(String username,String password){
        int id = JDBC.authenticateUser(username,password);
        return id;
    }

    public static int verifyLoginDetailsFromDoctors(String username,String password){
        int id = JDBC.authenticateDoctor(username,password);
        return id;
    }



    public static void showInvalidCredentialsWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Credentials");
        alert.setHeaderText(null);
        alert.setContentText("Username or password is invalid. Please try again.");
        alert.showAndWait();
    }

    public static void showUserNotAddedWarning(String heading,String show) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(heading);
        alert.setHeaderText(null);
        alert.setContentText(show);
        alert.showAndWait();
    }
    private RadioButton createOptionRadioButton(String option, ToggleGroup toggleGroup) {
        RadioButton radioButton = new RadioButton(option);
        radioButton.setToggleGroup(toggleGroup);
        return radioButton;
    }

    private String handleOptionSelection(ToggleGroup toggleGroup, Button selectedButton) {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        String string = null;
        if (selectedRadioButton != null) {
            String selectedOption = selectedRadioButton.getText();
            string = selectedOption;

            changeButtonColor(selectedButton, Color.GREEN); // Change button color when selected
            PauseTransition delay = new PauseTransition(Duration.seconds(10));
            delay.setOnFinished(event -> resetButtonColor(selectedButton));
            delay.play();
        }
        return string;
    }

    private void changeButtonColor(Button button, Color color) {
        BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background background = new Background(backgroundFill);
        button.setBackground(background);
    }
    private void resetButtonColor(Button button) {
        BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        button.setBackground(background);
    }

    public static void createSelectionPage(String imagePath, String backgroundColor, VBox vbox) {
        Image backgroundImageView = new Image(new File(imagePath).toURI().toString());
        ImageView backgroundImageViewNode = new ImageView(backgroundImageView);
        backgroundImageViewNode.setFitWidth(500);
        backgroundImageViewNode.setFitHeight(350);

        vbox.setStyle("-fx-background-color:" + backgroundColor);
        vbox.getChildren().addAll(backgroundImageViewNode);
    }

    public static void createSelectionPage(String imagePath, Color backgroundColor, VBox vbox) {
        Image backgroundImageView = new Image(new File(imagePath).toURI().toString());
        ImageView backgroundImageViewNode = new ImageView(backgroundImageView);
        backgroundImageViewNode.setFitWidth(500);
        backgroundImageViewNode.setFitHeight(350);

        vbox.setStyle("-fx-background-color:" + backgroundColor);
        vbox.getChildren().addAll(backgroundImageViewNode);
    }

}

//E:\java practice\projectDAS\.idea
//E:\java practice\projectDAS\src\main\java\com\example\hellofx\Admin.java