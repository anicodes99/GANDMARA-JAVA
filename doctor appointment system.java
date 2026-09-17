import java.util.Scanner;
import java.util.ArrayList;

// ===== Doctor =====
class Doctor {
    int id;
    String name;
    String specialty;

    Doctor(int id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }
}

// ===== Patient =====
class Patient {
    int id;
    String name;

    Patient(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

// ===== Appointment =====
class Appointment {
    int id;
    int doctorId;
    int patientId;
    String date;
    String time;
    boolean cancelled;

    Appointment(int id, int doctorId, int patientId, String date, String time) {
        this.id = id;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.time = time;
        this.cancelled = false;
    }
}

// ===== Main Program =====
public class Main {
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static int nextId = 1;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Add some doctors
        doctors.add(new Doctor(1, "Smith", "Cardiology"));
        doctors.add(new Doctor(2, "Patel", "Dermatology"));

        // Add some patients
        patients.add(new Patient(1, "Alice"));
        patients.add(new Patient(2, "Bob"));

        while (true) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // clear newline

            if (choice == 1) bookAppointment();
            else if (choice == 2) cancelAppointment();
            else if (choice == 3) showAllAppointments();
            else if (choice == 4) showDoctors();
            else if (choice == 5) break; // exit
        }
    }

    static void printMenu() {
        System.out.println("\n===== Doctor Appointment System =====");
        System.out.println("1. Book Appointment");
        System.out.println("2. Cancel Appointment");
        System.out.println("3. Show All Appointments");
        System.out.println("4. Show Doctors");
        System.out.println("5. Exit");
        System.out.print("Choose: ");
    }

    // ---- Book a new appointment ----
    static void bookAppointment() {
        System.out.print("Enter Patient ID: ");
        int pid = sc.nextInt();
        System.out.print("Enter Doctor ID: ");
        int did = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Date (e.g. 2026-09-20): ");
        String date = sc.nextLine();
        System.out.print("Enter Time (e.g. 10:00): ");
        String time = sc.nextLine();

        // Check if slot is already taken
        for (Appointment a : appointments) {
            if (!a.cancelled && a.doctorId == did && a.date.equals(date) && a.time.equals(time)) {
                System.out.println("Sorry! That slot is already booked.");
                return;
            }
        }

        // Check if doctor exists
        boolean found = false;
        for (Doctor d : doctors) {
            if (d.id == did) found = true;
        }
        if (!found) {
            System.out.println("Doctor not found!");
            return;
        }

        // Create appointment
        Appointment a = new Appointment(nextId++, did, pid, date, time);
        appointments.add(a);
        System.out.println("Appointment booked! ID: " + a.id);
    }

    // ---- Cancel an appointment ----
    static void cancelAppointment() {
        System.out.print("Enter Appointment ID to cancel: ");
        int id = sc.nextInt();

        for (Appointment a : appointments) {
            if (a.id == id) {
                if (a.cancelled) {
                    System.out.println("Already cancelled.");
                } else {
                    a.cancelled = true;
                    System.out.println("Appointment cancelled.");
                }
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    // ---- Show all appointments ----
    static void showAllAppointments() {
        System.out.println("\n--- All Appointments ---");
        if (appointments.isEmpty()) {
            System.out.println("No appointments yet.");
            return;
        }
        for (Appointment a : appointments) {
            String status = a.cancelled ? "CANCELLED" : "CONFIRMED";
            System.out.println("ID:" + a.id + " | Patient:" + a.patientId
                + " | Doctor:" + a.doctorId + " | " + a.date + " " + a.time
                + " | " + status);
        }
    }

    // ---- Show all doctors ----
    static void showDoctors() {
        System.out.println("\n--- Doctors ---");
        for (Doctor d : doctors) {
            System.out.println("ID:" + d.id + " | " + d.name + " | " + d.specialty);
        }
    }
}   