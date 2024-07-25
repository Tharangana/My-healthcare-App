import java.util.*;

public class Doctor {
    private int doctorId;
    private String name;
    private String birthday;
    private String specialization;
    private String contact;
    private ArrayList<Date> availabilities;
    private HashMap<Date, ArrayList<Appointment>> allAppointments;

    public Doctor(int doctorId, String name, String birthday, String specialization, String contact) {
        this.doctorId = doctorId;
        this.name = name;
        this.birthday = birthday;
        this.specialization = specialization;
        this.contact = contact;
        this.availabilities = new ArrayList<>();
        this.allAppointments = new HashMap<>();
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getContact() {
        return contact;
    }

    public ArrayList<Date> getAvailabilities() {
        return availabilities;
    }

    public void setAvailability(Date date) {
        this.availabilities.add(date);
    }

    public HashMap<Date, ArrayList<Appointment>> getAllAppointments() {
        return allAppointments;
    }

    public void setAppointment(Appointment appointment, Date date) {
        if (!this.allAppointments.containsKey(date)) {
            this.allAppointments.put(date, new ArrayList<>());
        }
        this.allAppointments.get(date).add(appointment);
    }
}
