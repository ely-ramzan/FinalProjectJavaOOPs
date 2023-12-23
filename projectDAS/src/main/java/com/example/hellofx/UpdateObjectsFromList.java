package com.example.hellofx;

import java.util.Date;

import static com.example.hellofx.SearchObjectsFromList.*;

public class UpdateObjectsFromList {
    public static boolean updatePatient(int id, String newName, int newAge, String newPhoneNumber, String newType, String newUserName, String newPassword) {
        Patient patientToUpdate = searchPatientByID(id);

        if (patientToUpdate != null) {
            // Update patient properties
            patientToUpdate.setPatientName(newName);
            patientToUpdate.setPatientAge(newAge);
            patientToUpdate.setPatientPhoneNumber(newPhoneNumber);
            patientToUpdate.setPatientType(PatientType.checkType(newType));
            patientToUpdate.setPatientUserName(newUserName);
            patientToUpdate.setPatientPassword(newPassword);

            return true; // Update successful
        }

        return false; // Patient not found
    }
    public static boolean updateDoctor(int id, String newName, String newCategory, String newUserName, String newPassword, boolean newAvailability) {
        Doctor doctorToUpdate = searchDoctorByID(id);

        if (doctorToUpdate != null) {
            // Update doctor properties
            doctorToUpdate.setDoctorName(newName);
            doctorToUpdate.setDoctorType(DoctorType.checkType(newCategory));
            doctorToUpdate.setDoctorUserName(newUserName);
            doctorToUpdate.setDoctorPassword(newPassword);
            doctorToUpdate.setAvailable(newAvailability);

            return true; // Update successful
        }

        return false; // Doctor not found
    }

    public static boolean updateAppointment(int id, int newDoctorID, int newPatientID, String newMedicalCondition, Date newDate) {
        Appointments appointmentToUpdate = searchAppointmentByID(id);

        if (appointmentToUpdate != null) {
            // Update appointment properties
            appointmentToUpdate.setDoctorID(newDoctorID);
            appointmentToUpdate.setPatientID(newPatientID);
            appointmentToUpdate.setMedicalCondition(newMedicalCondition);
            appointmentToUpdate.setDate(new Date(newDate.toString()));

            return true; // Update successful
        }

        return false; // Appointment not found
    }
}
