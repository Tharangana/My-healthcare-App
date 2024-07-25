import javax.print.Doc;
import java.util.*;

public class Main {
    public static void adminMenu(){
        boolean runAdmin = true;
        while (runAdmin){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 1 to add a doctor, press 2 to add a doctor availability, and press 3 to exit");
            int userObjective = scanner.nextInt();
            if(userObjective == 1){
//            get the relevant data
                Controller.addDoctor();
                System.out.println("Doctor is added");


            } else if (userObjective==2) {
                //add doc availability
                Controller.addAvailabilityForDoctor();

            } else if (userObjective==3) {
                runAdmin = false;
                System.out.println("Exit");
            }
            else {
                System.out.println("Invalid");
            }
        }


    }
    public static void patientMenu(){
        boolean  runPatient = true;

        while (runPatient){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 1 to view doctors, press 2 to book an appointment, press 3 to view a selected doctor’s bookings, press 4 exit, press 5 to add patients, press 6 to cancel appointment, press 7 to view patients");
            int userObjective = scanner.nextInt();
            if(userObjective == 1){
                Controller.viewAllDoctors();
            } else if (userObjective==2) {
                Controller.bookAppointment();
            } else if (userObjective==3) {
                System.out.println("View a selected Doctor");
            } else if (userObjective==4) {
                runPatient= false;
                System.out.println("Exit");

            } else if (userObjective==5) {
                Controller.addPatient();
            } else if(userObjective == 6){
                Controller.cancelAppointment();
            } else if(userObjective == 7){
                Controller.viewAllPatients();
            } else {
                System.out.println("Invalid");
            }

        }

    }

    public static void run(){

        boolean runMain  = true;
        while (runMain){
            Scanner scanner = new Scanner(System.in);
            System.out.println("If you are a hospital administrator please press 1, If you are a patient please press 2, Press 3 to exit");
            int userType = scanner.nextInt();

            if(userType == 1){
                adminMenu();
            }
            else if(userType == 2){
                patientMenu();

            } else if (userType==3) {
                runMain = false;
                System.out.println("Exit");
            }
            else {
                System.out.println("Invalid Input");
            }
        }
    }


    public static void main(String[] args) {
        Doctor sampleDoc = new Doctor(123,"prasad pathirana","22.05.1963","Gynocologist","077-333-9900");
        Patient samplePatient = new Patient("D-20", "Maheesha Tharangana",  "0763712902");
        Controller.allDoctors.add(sampleDoc);
        Controller.allPatients.add(samplePatient);

        run();

    }
}