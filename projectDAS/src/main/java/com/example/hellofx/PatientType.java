package com.example.hellofx;

public enum PatientType {
    OPD,EMERGENCY,REGULAR;
    public static PatientType checkType(String type) {
        if (type.equals("OPD") || type.equals("opd")) {
            return PatientType.OPD;
        } else if (type.equals("EMERGENCY") || type.equals("emergency")) {
            return PatientType.EMERGENCY;
        } else if (type.equals("REGULAR") || type.equals("regular")) {
            return PatientType.REGULAR;
        } else {
            return PatientType.REGULAR;
        }
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
