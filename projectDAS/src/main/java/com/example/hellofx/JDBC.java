package com.example.hellofx;
import java.sql.*;
import java.util.ArrayList;

public class JDBC {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String username = "root";
    private static final String password = "WapdaCadetCo123";


    public static Statement execute() {
        // load driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Statement statement = null;
        try {
            //create connection
            Connection connection = DriverManager.getConnection(url, username, password);

            //
            statement = connection.createStatement();

        } catch (SQLException e) {
            System.out.println("Error in execution");
        }
        return statement;
    }


    public static boolean addPatientToDataBase(String name, int age, String phoneNumber, String patientType, String username, String password) {
        boolean isAdded = true;

        String query = String.format("INSERT INTO patients(patientName, age, patientPhoneNumber, patientType, patientUserName, patientPassword) " +
                "VALUES('%s', %d, '%s', '%s', '%s', '%s')", name, age, phoneNumber, patientType, username, password);

        try (Statement statement = JDBC.execute();
             PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    CreateObservableLists.patients.add(searchPatientFromDatabaseAndCreateObject(resultSet.getInt(1)));
                }
            } else {
                isAdded = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            isAdded = false;
        }

        return isAdded;
    }



    public static boolean addDoctorToDataBase(String name, String doctorType, String username, String password, boolean available) {
        boolean isAdded = true;

        String query = "INSERT INTO doctors(doctorName, doctorType, doctorUsername, doctorPassword, isAvailable) VALUES (?, ?, ?, ?, ?)";

        try (Statement statement = JDBC.execute();
             PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, doctorType);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setBoolean(5, available);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    CreateObservableLists.doctors.add(searchDoctorAndCreateObject(resultSet.getInt(1)));
                }
            } else {
                isAdded = false;
            }

        } catch (SQLException e) {
            isAdded = false;
            e.printStackTrace();
        }

        return isAdded;
    }


    public static boolean addAppointmentToDataBase(int patId, int docId, Date date, String medical) {
        boolean isAdded = true;

        String query = "INSERT INTO appointments (appointedDoctor, appointedPatient, appointmentDate, medicalCondition) VALUES (?, ?, ?, ?)";
        try (Statement statement = JDBC.execute();
             PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Convert java.util.Date to java.sql.Timestamp
            java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

            preparedStatement.setInt(1, docId);
            preparedStatement.setInt(2, patId);
            preparedStatement.setTimestamp(3, timestamp);
            preparedStatement.setString(4, medical);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    CreateObservableLists.appointments.add(searchAppointmentAndCreateObject(resultSet.getInt(1)));
                }
            } else {
                isAdded = false;
            }

        } catch (SQLException e) {
            isAdded = false;
            e.printStackTrace();
        }

        return isAdded;
    }




    public static ArrayList<String> searchDoctorByType(String type){
        String query = "SELECT doctorName,doctorID FROM doctors WHERE doctorType LIKE \'%" + type + "%\'";
        Statement statement = JDBC.execute();
        ArrayList<String> doctors = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            int i = 0;
            while(resultSet.next()){
                String string = resultSet.getInt(i) + " " + resultSet.getString(i);
                doctors.add(string);
                i ++;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctors;
    }

    public static Doctor searchDoctorAndCreateObject(int id){

        String query = "SELECT * FROM doctors WHERE doctorID = " + id;

        Statement statement = JDBC.execute();
        Doctor doctor = null;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            int i = 0;
            while(resultSet.next()){
                doctor = new Doctor(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getBoolean(6));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctor;
    }

    public static Appointments searchAppointmentAndCreateObject(int id) {
        String query = "SELECT * FROM appointments WHERE appointmentID = " + id;

        Statement statement = JDBC.execute();
        Appointments appointment = null;

        try {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int appointmentID = resultSet.getInt("appointmentID");
                int doctorID = resultSet.getInt("appointedDoctor");
                int patientID = resultSet.getInt("appointedPatient");
                Date date = resultSet.getDate("appointmentDate");
                String medicalCondition = resultSet.getString("medicalCondition");

                appointment = new Appointments(appointmentID, doctorID, patientID, medicalCondition, date);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return appointment;
    }


    public static Patient searchPatientFromDatabaseAndCreateObject(int id){
        String query = "SELECT * FROM patients WHERE id = " + id;

        Statement statement = JDBC.execute();
        Patient patient = null;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            int i = 0;
            while(resultSet.next()){
                patient = new Patient(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return patient;
    }

    public static Appointments searchAppointmentsFromDatabaseAndCreateObject(int id){
        String query = "SELECT * FROM appointments WHERE id = " + id;

        Statement statement = JDBC.execute();
        Appointments appointment = null;
        try {
            ResultSet resultSet = statement.executeQuery(query);
            int i = 0;
            while(resultSet.next()){
                appointment = new Appointments(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),new Date(resultSet.getDate(5).getTime()));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return appointment;
    }

    public static boolean deleteDoctorFromDataBase(int id){
        boolean isDeleted = true;

        String query = String.format("DELETE FROM doctors WHERE doctorID = " + id);
        Statement statement = JDBC.execute();

        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            isDeleted = false;
        }
        return isDeleted;
    }

    public static boolean deletePatientFromDataBase(int id) {
        boolean isDeleted = true;

        String query = String.format("DELETE FROM patients WHERE id = " + id);
        Statement statement = JDBC.execute();

        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            isDeleted = false;
        }
        return isDeleted;
    }

    public static boolean deleteAppointmentFromDataBase(int id) {
        boolean isDeleted = true;

        String query = String.format("DELETE FROM appointments WHERE appointmentID = " + id);
        Statement statement = JDBC.execute();

        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            isDeleted = false;
        }
        return isDeleted;
    }

    public static ResultSet getAllPatientsFromDatabase(){
        String query = "Select * from patients";
        Statement statement = JDBC.execute();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultSet;
    }

    public static ResultSet getAllDoctorsFromDatabase(){
        String query = "Select * from doctors";
        Statement statement = JDBC.execute();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultSet;
    }

    public static ResultSet getAllAppointmentsFromDatabase(){
        String query = "Select * from appointments";
        Statement statement = JDBC.execute();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultSet;
    }

    public static boolean updatePatientInDatabase(Patient patient){
        boolean isUpdated = false;
        String query = String.format("UPDATE patients SET patientName='%s', age=%d, patientPhoneNumber='%s', " +
                        "patientType='%s', patientUserName='%s', patientPassword='%s' WHERE id=%d",
                patient.getPatientName(), patient.getPatientAge(), patient.getPatientPhoneNumber(),
                patient.getPatientType(), patient.getPatientUserName(), patient.getPatientPassword(),
                patient.getId());

        try (Statement statement = JDBC.execute()) {
            int rowsAffected = statement.executeUpdate(query);
            isUpdated = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    public static int authenticateUser(String username, String password) {
        try {
            // Prepare SQL statement
            String query = "SELECT id FROM patients WHERE patientUserName = ? AND patientPassword = ?";
            Statement statement = JDBC.execute();

            // Create a PreparedStatement from the existing Statement
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the user exists
            if (resultSet.next()) {
                // User exists, return the user ID
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // User not found
        return -1;
    }

    public static int authenticateDoctor(String username, String password) {
        try {
            // Prepare SQL statement
            String query = "SELECT doctorID FROM doctors WHERE doctorUsername = ? AND doctorPassword = ?";

            Statement statement = JDBC.execute();

            // Create a PreparedStatement from the existing Statement
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the user exists
            if (resultSet.next()) {
                // User exists, return the user ID
                return resultSet.getInt("doctorID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // User not found
        return -1;
    }


}