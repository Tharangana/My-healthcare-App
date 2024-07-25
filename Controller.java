import java.util.*;

public class Controller {
    public static ArrayList<Doctor> allDoctors = new ArrayList<>();
    public static ArrayList<Patient> allPatients = new ArrayList<>();
    public static int NUMBER_OF_SLOTS = 3;

    public static void addDoctor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Name: ");
        String name = sc.nextLine();
        System.out.println("Enter your birthday: ");
        String birthday = sc.nextLine();
        System.out.println("Enter your Specialization: ");
        String specialization = sc.nextLine();
        System.out.println("Enter your Contact: ");
        String contact = sc.nextLine();

        Random random = new Random();
        Doctor tempDoctor = new Doctor(random.nextInt(1000), name, birthday, specialization, contact);
        allDoctors.add(tempDoctor);
    }

    public static void addAvailabilityForDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the doctor Id you want to add availability for: ");
        int docId = scanner.nextInt();
        Doctor selectedDoctor = getDoctorById(docId);

        if (selectedDoctor == null) {
            System.out.println("No Doctor Found");
            return;
        }

        System.out.println("Enter the Day you want to add Availability: ");
        int day = scanner.nextInt();
        System.out.println("Enter the Month you want to add Availability: ");
        int month = scanner.nextInt();
        System.out.println("Enter the Year you want to add Availability: ");
        int year = scanner.nextInt();
        Date bookingDate = new GregorianCalendar(year, month, day).getTime();
        selectedDoctor.setAvailability(bookingDate);
    }

    public static void viewAllDoctors() {
        for (Doctor doc : allDoctors) {
            System.out.println(doc.getName() + " has a specialization of " + doc.getSpecialization() + " has an id of " + doc.getDoctorId() + " and has availability on " + doc.getAvailabilities().toString());
        }
    }

    public static void addPatient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter patient Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter patient's Id: ");
        String id = scanner.nextLine();
        System.out.println("Enter patient's Contact Information: ");
        String contact = scanner.nextLine();
        Patient tempPatient = new Patient(id, name, contact);
        allPatients.add(tempPatient);
        System.out.println("Patient is Added");
        System.out.println(allPatients.toString());
    }

    public static void viewAllPatients() {
        for (Patient patient : allPatients) {
            System.out.println("Patient ID: " + patient.getPatientId() + ", Name: " + patient.getName() + ", Contact: " + patient.getContact());
        }
    }

    public static void bookAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Doctor's Id you want to make an appointment with: ");
        int docId = scanner.nextInt();
        System.out.println("Enter your patient's Id: ");
        String patientId = scanner.next();

        System.out.println("Enter the Day you want to book the Appointment: ");
        int day = scanner.nextInt();
        System.out.println("Enter the Month you want to book the Appointment: ");
        int month = scanner.nextInt();
        System.out.println("Enter the Year you want to book the Appointment: ");
        int year = scanner.nextInt();

        Patient selectedPatient = getPatientById(patientId);
        Doctor selectedDoc = getDoctorById(docId);
        if (selectedDoc == null || selectedPatient == null) {
            System.out.println("Invalid doctor or patient id");
            return;
        }
        Date appointmentDate = new GregorianCalendar(year, month, day).getTime();

        if (checkAvailability(selectedDoc, appointmentDate)) {
            String appTime = getTimeForBooking(selectedDoc, appointmentDate);
            if (appTime != null) {
                Appointment appointment = new Appointment(selectedDoc, selectedPatient, "No Notes", appointmentDate, appTime);
                selectedDoc.setAppointment(appointment, appointmentDate);
                System.out.println(selectedDoc.getAllAppointments().toString());
            } else {
                System.out.println("All the slots are filled");
            }
        } else {
            System.out.println("Doctor is not available on the selected Date");
        }
    }

    public static void cancelAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Doctor's Id for which you want to cancel the appointment: ");
        int docId = scanner.nextInt();
        System.out.println("Enter patient's Id: ");
        String patientId = scanner.next();

        System.out.println("Enter the Day of the Appointment: ");
        int day = scanner.nextInt();
        System.out.println("Enter the Month of the Appointment: ");
        int month = scanner.nextInt();
        System.out.println("Enter the Year of the Appointment: ");
        int year = scanner.nextInt();

        Patient selectedPatient = getPatientById(patientId);
        Doctor selectedDoc = getDoctorById(docId);
        if (selectedDoc == null || selectedPatient == null) {
            System.out.println("Invalid doctor or patient id");
            return;
        }
        Date appointmentDate = new GregorianCalendar(year, month, day).getTime();

        if (selectedDoc.getAllAppointments().containsKey(appointmentDate)) {
            ArrayList<Appointment> appointments = selectedDoc.getAllAppointments().get(appointmentDate);
            appointments.removeIf(appointment -> appointment.getPatient().getPatientId().equals(patientId));
            System.out.println("Appointment canceled successfully.");
        } else {
            System.out.println("No appointment found on the given date.");
        }
    }

    private static String getTimeForBooking(Doctor selectedDoctor, Date dateOfBooking) {
        for (Map.Entry<Date, ArrayList<Appointment>> appointment : selectedDoctor.getAllAppointments().entrySet()) {
            if (appointment.getKey().equals(dateOfBooking)) {
                int numberOfSlots = appointment.getValue().size();
                if (numberOfSlots > NUMBER_OF_SLOTS - 1) {
                    return null;
                }
                int time = 17 + appointment.getValue().size();
                return time + ":00";
            }
        }
        return "17:00";
    }

    private static Boolean checkAvailability(Doctor selectedDoctor, Date dateOfBooking) {
        for (Date day : selectedDoctor.getAvailabilities()) {
            if (day.equals(dateOfBooking)) {
                return true;
            }
        }
        return false;
    }

    public static Patient getPatientById(String id) {
        for (Patient patient : allPatients) {
            if (patient.getPatientId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    public static Doctor getDoctorById(int id) {
        for (Doctor doc : allDoctors) {
            if (doc.getDoctorId() == id) {
                return doc;
            }
        }
        return null;
    }
}
