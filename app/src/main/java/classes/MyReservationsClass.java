package classes;

public class MyReservationsClass {
    int doctor_id;
    String doctorName;
    int clinic_id;
    String clinicName;
    int dayId;
    String day;
    String start;
    String end;

    public MyReservationsClass(int doctor_id, String doctorName, int clinic_id, String clinicName, int dayId, String day, String start, String end) {
        this.doctor_id = doctor_id;
        this.doctorName = doctorName;
        this.clinic_id = clinic_id;
        this.clinicName = clinicName;
        this.dayId = dayId;
        this.day = day;
        this.start = start;
        this.end = end;

    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public int getClinic_id() {
        return clinic_id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public int getDayId() {
        return dayId;
    }

    public String getDay() {
        return day;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
