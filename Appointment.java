import java.util.Date;

public class Appointment {
    private Doctor doctor;
    private Patient patient;
    private String notes;
    private Date date;
    private String time;

    public Appointment(Doctor doctor, Patient patient, String notes, Date date, String time) {
        this.doctor = doctor;
        this.patient = patient;
        this.notes = notes;
        this.date = date;
        this.time = time;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getNotes() {
        return notes;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "doctor=" + doctor.getName() +
                ", patient=" + patient.getName() +
                ", notes='" + notes + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                '}';
    }
}


