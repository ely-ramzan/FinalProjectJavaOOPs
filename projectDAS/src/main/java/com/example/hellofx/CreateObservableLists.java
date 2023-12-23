package com.example.hellofx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
public  class CreateObservableLists  {
    public static void main(String[] args) {
        try {
            addObjectsToDoctorsFromDatabase();
            printPatients();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ObservableList<Patient> patients = FXCollections.observableArrayList();
    public static ObservableList<Doctor> doctors = FXCollections.observableArrayList();
    public static ObservableList<Appointments> appointments = FXCollections.observableArrayList();

    public static void addObjectsToPatientsFromDatabase() throws SQLException {
        ResultSet resultSet = JDBC.getAllPatientsFromDatabase();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");  // Assuming "id" is the column name in the ResultSet
            String patientName = resultSet.getString("patientName");
            int patientAge = resultSet.getInt("age");
            String patientPhoneNumber = resultSet.getString("patientPhoneNumber");
            String patientType = resultSet.getString("patientType");
            String patientUserName = resultSet.getString("patientUserName");
            String patientPassword = resultSet.getString("patientPassword");
            Patient patient = new Patient(id, patientName, patientAge, patientPhoneNumber,
                    patientType, patientUserName, patientPassword);
            patients.add(patient);
        }

    }

    public static void addObjectsToDoctorsFromDatabase() throws SQLException {
        ResultSet resultSet = JDBC.getAllDoctorsFromDatabase();

        while (resultSet.next()) {
            int id = resultSet.getInt("doctorID");
            String doctorName = resultSet.getString("doctorName");
            String doctorType = resultSet.getString("doctorType");
            String username = resultSet.getString("doctorUsername");
            String password = resultSet.getString("doctorPassword");
            boolean isAvailable = resultSet.getBoolean("isAvailable");
            Doctor doctor = new Doctor(id, doctorName, doctorType, username, password, isAvailable);
            doctors.add(doctor);
        }
    }

    public static void addObjectsToAppointmentsFromDatabase(ObservableList<Appointments> appointments1) throws SQLException {
        ResultSet resultSet = JDBC.getAllAppointmentsFromDatabase();
        while (resultSet.next()) {
            int appointmentID = resultSet.getInt("appointmentID");

            int doctorID = resultSet.getInt("appointedDoctor");
            Doctor doctor = searchDoctorById(doctorID);
            int patientID = resultSet.getInt("appointedPatient");
            Patient patient = searchPatientBasedOnID(patientID);
                    String medicalCondition = resultSet.getString("medicalCondition");
            Date date = resultSet.getDate("appointmentDate");
            Appointments appointment = new Appointments(appointmentID, doctor,doctorID,  patient,patientID, medicalCondition, new Date(date.getTime()));
            appointments1.add(appointment);
        }
    }

    public static Patient searchPatientBasedOnID(int id){

        for(Patient patient:patients){
            if(patient.getId() == id)
                return patient;
        }

        return null;
    }

    public static Doctor searchDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorID() == id) {
                return doctor;
            }
        }
        return null; // Return null if the doctor is not found
    }


    public static void printPatients(){
        System.out.println("called");
        for(Appointments appointments1:appointments)
            System.out.println(appointments1);
    }


}
