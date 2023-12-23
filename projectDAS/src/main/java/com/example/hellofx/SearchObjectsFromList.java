package com.example.hellofx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static com.example.hellofx.CreateObservableLists.*;

public class SearchObjectsFromList {
    public static Patient searchPatientByID(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient; // Patient found
            }
        }
        return null; // Patient not found
    }

    public static Doctor searchDoctorByID(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorID() == id) {
                return doctor; // Doctor found
            }
        }
        return null; // Doctor not found
    }

    public static Appointments searchAppointmentByID(int id) {
        for (Appointments appointment : appointments) {
            if (appointment.getAppointmentID() == id) {
                return appointment; // Appointment found
            }
        }
        return null; // Appointment not found
    }

//    for doctor
    public static ObservableList<Appointments> showMyAppointmentsUser(String username) {
        ObservableList<Appointments> appointmentsBooked = FXCollections.observableArrayList();
        for (Appointments appointments1 : appointments) {
            Doctor doctor = searchDoctorById(appointments1.getDoctorID());
            if (doctor != null)
                if (doctor.getDoctorUserName().equals(username))
                    appointmentsBooked.add(appointments1);
        }
        return appointmentsBooked;
    }

//    for users
    public static ObservableList<Appointments> showMyAppointmentsUser(int id) {
        ObservableList<Appointments> appointmentsBooked = FXCollections.observableArrayList();
        for (Appointments appointments1 : appointments) {
            Patient patient = searchPatientBasedOnID(id);
            if (patient != null)
                if (patient.getId() == id)
                    appointmentsBooked.add(appointments1);
        }
        return appointmentsBooked;
    }


}
