package com.example.hellofx;

import static com.example.hellofx.CreateObservableLists.*;

public class DeleteObjectsFromList {
    public static boolean deletePatientFromObservableList(int id) {
        Patient patientToRemove = null;
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                patientToRemove = patient;
                break;
            }
        }
        if (patientToRemove != null) {
            patients.remove(patientToRemove);
            JDBC.deleteAppointmentFromDataBase(id);
            return true; // Successfully deleted
        }
        return false; // Patient not found
    }

    public static boolean deleteDoctorFromObservableList(int id) {
        Doctor doctorToRemove = null;
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorID() == id) {
                doctorToRemove = doctor;
                break;
            }
        }

        if (doctorToRemove != null) {
            doctors.remove(doctorToRemove);
            JDBC.deleteDoctorFromDataBase(id);
            return true; // Successfully deleted
        }
        return false; // Doctor not found
    }

    public static boolean deleteAppointmentFromObservableList(int id) {
        Appointments appointmentToRemove = null;
        for (Appointments appointment : appointments) {
            if (appointment.getAppointmentID() == id) {
                appointmentToRemove = appointment;
                break;
            }
        }

        if (appointmentToRemove != null) {
            appointments.remove(appointmentToRemove);
            JDBC.deleteAppointmentFromDataBase(id);
            return true; // Successfully deleted
        }
        return false; // Appointment not found
    }


}

