package com.example.hellofx;



public class Patient {

    private int id;
    private String patientName;
    private int patientAge;
    private String patientPhoneNumber;
    private PatientType patientType;
    private String patientUserName;
    private String patientPassword;

    public Patient(int id, String patientName, int patientAge, String patientPhoneNumber, String patientType, String patientUserName, String patientPassword) {
        this.id = id;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientPhoneNumber = patientPhoneNumber;
        this.patientType = PatientType.checkType(patientType);
        this.patientUserName = patientUserName;
        this.patientPassword = patientPassword;
    }

    public Patient(String patientName, int patientAge, String patientPhoneNumber, String patientType, String patientUserName, String patientPassword) {
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientPhoneNumber = patientPhoneNumber;
        this.patientType = PatientType.checkType(patientType);
        this.patientUserName = patientUserName;
        this.patientPassword = patientPassword;
    }

    // Getter and Setter for id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

        public String getPatientType() {
        return patientType + "";
    }

    public void setPatientType(PatientType patientType) {
        this.patientType = patientType;
    }

    public String getPatientUserName() {
        return patientUserName;
    }

    public void setPatientUserName(String patientUserName) {
        this.patientUserName = patientUserName;
    }

    public String getPatientPassword() {
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }


    // Additional methods...

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", patientName='" + patientName + '\'' +
                ", patientAge=" + patientAge +
                ", patientPhoneNumber='" + patientPhoneNumber + '\'' +
                ", patientType=" + patientType +
                ", patientUserName='" + patientUserName + '\'' +
                ", patientPassword='" + patientPassword + '\'' +
                '}';
    }

}

