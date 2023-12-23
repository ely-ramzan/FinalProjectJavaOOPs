package com.example.hellofx;

public class Doctor {
    private int doctorID;
    private String doctorName;
    private DoctorType doctorType;
    private String doctorUserName;
    private String doctorPassword;
    private boolean isAvailable;


    public Doctor(String doctorName, String doctorCatagory, String id, String password, boolean isAvailable) {
        this.doctorName = doctorName;
        this.doctorType = DoctorType.checkType(doctorCatagory);
        this.doctorUserName = id;
        this.doctorPassword = password;
        this.isAvailable = isAvailable;
    }

    public Doctor(int id,String doctorName, String doctorCatagory, String username, String password, boolean isAvailable) {
        this.doctorID = id;
        this.doctorName = doctorName;
        this.doctorType = DoctorType.checkType(doctorCatagory);
        this.doctorUserName = username;
        this.doctorPassword = password;
        this.isAvailable = isAvailable;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public DoctorType getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(DoctorType doctorType) {
        this.doctorType = doctorType;
    }

    public String getDoctorUserName() {
        return doctorUserName;
    }

    public void setDoctorUserName(String doctorUserName) {
        this.doctorUserName = doctorUserName;
    }

    public String getDoctorPassword() {
        return doctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Doctor assignDoctorBasedOnID(int id){
        if(id == doctorID)
            return this;
        else
            return null;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorID=" + doctorID +
                ", doctorName='" + doctorName + '\'' +
                ", doctorType=" + doctorType +
                ", doctorUserName='" + doctorUserName + '\'' +
                ", doctorPassword='" + doctorPassword + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

