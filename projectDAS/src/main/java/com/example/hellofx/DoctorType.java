package com.example.hellofx;

public enum DoctorType {
    ER,GENERAL_PHYSICIAN,PSYCHIATRIST,DERMATOLOGIST,ENT,RADIOLOGIST;
    public static DoctorType checkType(String type){
        if (type.equals("ER") || type.equals("er")) {
            return DoctorType.ER;
        } else if (type.equals("GENERAL_PHYSICIAN") || type.equals("general_physician")) {
            return DoctorType.GENERAL_PHYSICIAN;
        } else if (type.equals("PSYCHIATRIST") || type.equals("psychiatrist")) {
            return DoctorType.PSYCHIATRIST;
        } else if (type.equals("DERMATOLOGIST") || type.equals("dermatologist")) {
            return DoctorType.DERMATOLOGIST;
        } else if (type.equals("ENT") || type.equals("ent")) {
            return DoctorType.ENT;
        } else if (type.equals("RADIOLOGIST") || type.equals("radiologist")) {
            return DoctorType.RADIOLOGIST;
        } else {
            return DoctorType.GENERAL_PHYSICIAN;
        }
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}

