package com.example.hellofx;

import java.util.Date;

public class Appointments {
    private int appointmentID;
    private Doctor doctor;
    private int doctorID;
    private Patient patient;
    private int patientID;
    private String medicalCondition;
    private Date date;

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Appointments(int appointmentID, Doctor doctor, int doctorID, Patient patient, int patientID, String medicalCondition, Date date) {
        this.appointmentID = appointmentID;
        this.doctor = doctor;
        this.doctorID = doctorID;
        this.patient = patient;
        this.patientID = patientID;
        this.medicalCondition = medicalCondition;
        this.date = date;
    }

    public Appointments(int appointmentID, int doctorID, int patientID, String medicalCondition, Date date) {
        this.appointmentID = appointmentID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.medicalCondition = medicalCondition;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointments{" +
                "appointmentID=" + appointmentID +
                ", doctor=" + doctor +
                ", doctorID=" + doctorID +
                ", patient=" + patient +
                ", patientID=" + patientID +
                ", medicalCondition='" + medicalCondition + '\'' +
                ", date=" + date +
                '}';
    }


}
