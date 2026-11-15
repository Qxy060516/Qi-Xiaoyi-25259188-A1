import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main program for the medical appointment system, providing a console-based user interface.
 */
public class AssignmentOne {
    private static final List<MedicalPractitioner> practitionerList = new ArrayList<>(); // Store predefined medical practitioners
    private static final Scanner scanner = new Scanner(System.in); // Read user input from the console

    public static void main(String[] args) {
        // Load predefined practitioners and create the appointment manager
        loadDefaultPractitioners();
        AppointmentManager manager = new AppointmentManager();

        // Create two patients and two initial appointments for demonstration
        Patient p1 = new Patient("Jack", "12345678");
        Patient p2 = new Patient("Lucy", "87654321");
        Appointment app1 = new Appointment(p1, practitionerList.get(0), "09:00");
        Appointment app2 = new Appointment(p2, practitionerList.get(2), "10:00");
        // Add the initial appointments to the appointment list
        manager.getAppointmentList().add(app1);
        manager.getAppointmentList().add(app2);

        // Display the system title and initial appointments sorted by time slot
        System.out.println("======== Medical Appointment System (Interactive) ========");
        System.out.println("【System Prompt】2 initial appointments have been loaded:");
        manager.printAppointmentList(manager.getAllSortedAppointments());

        // Display the menu repeatedly until the user selects 0 to exit
        int choice;
        do {
            printMenu();
            // Report non-integer input and discard the invalid token
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input, please enter a number!");
                scanner.next();
            }
            choice = scanner.nextInt();
            // Consume the trailing newline so it does not affect subsequent nextLine() calls
            scanner.nextLine();

            // Call the corresponding function based on the user's selection
            switch (choice) {
                case 1 -> showAllPractitioners();
                case 2 -> createNewAppointment(manager);
                case 3 -> showAllSortedAppointments(manager);
                case 4 -> filterByPid(manager);
                case 5 -> cancelOneAppointment(manager);
                case 0 -> System.out.println("Exiting system, goodbye!");
                default -> System.out.println("Invalid option, please enter again!");
            }
        } while (choice != 0);
        // Close the Scanner before the program exits to release resources
        scanner.close();
    }

    // Print the system function menu
    private static void printMenu() {
        System.out.println("\n==== Function Menu ====");
        System.out.println("1 - View all medical practitioners");
        System.out.println("2 - Create new appointment (Time slot: 08:00~16:00, every 30 minutes)");
        System.out.println("3 - View all appointments (sorted by time)");
        System.out.println("4 - Filter appointments by practitioner ID");
        System.out.println("5 - Cancel appointment");
        System.out.println("0 - Exit program");
        System.out.print("Please enter the command number: ");
    }

    // Load different types of predefined practitioners and store subclass objects in a superclass list
    private static void loadDefaultPractitioners() {
        practitionerList.add(new GP(1001, "Dr. Zhang", List.of("Monday","Wednesday","Friday"), true));
        practitionerList.add(new GP(1002, "Dr. Li", List.of("Tuesday","Thursday"), true));
        practitionerList.add(new Specialist(2001, "Dr. Wang", List.of("Monday","Tuesday"), "Cardiology"));
        practitionerList.add(new Nurse(3001, "Nurse Liu", List.of("Wednesday","Thursday"), true));
        practitionerList.add(new Dietitian(4001, "Dietitian Chen", List.of("Friday"), "Diabetic diet"));
    }

    private static void showAllPractitioners() {
        System.out.println("\n----- All Medical Practitioners -----");
        // Iterate through the list and print complete practitioner details through polymorphism
        for (MedicalPractitioner p : practitionerList) {
            System.out.println(p);
        }
    }

    // Collect information from the console and create a new appointment
    private static void createNewAppointment(AppointmentManager manager) {
        System.out.println("\n----- Create New Appointment -----");
        System.out.println("Available time slots: 08:00 ~ 16:00, every 30 minutes");
        // Read the patient name
        System.out.print("Enter patient name: ");
        String pName = scanner.nextLine();

        // Read the phone number and use a regular expression to verify that it contains exactly 8 digits
        System.out.print("Enter patient phone number (must be 8 digits): ");
        String phone = scanner.nextLine();
        if (!phone.matches("^\\d{8}$")) {
            // End this appointment operation if the phone number format is invalid
            System.out.println("❌ Phone number input failed! Must be 8 digits!");
            return;
        }

        // Create the patient object after validation
        Patient patient = new Patient(pName, phone);

        System.out.print("Enter practitioner ID: ");
        // Require an integer ID; discard invalid input and prompt again
        while (!scanner.hasNextInt()) {
            System.out.println("ID must be a number, enter again: ");
            scanner.next();
        }
        int pid = scanner.nextInt();
        scanner.nextLine();

        // Iterate through the practitioner list and find the practitioner by ID
        MedicalPractitioner targetPrac = null;
        for (MedicalPractitioner mp : practitionerList) {
            if (mp.getPractitionerId() == pid) {
                targetPrac = mp;
                break;
            }
        }
        // Stop creating the appointment if no matching practitioner is found
        if (targetPrac == null) {
            System.out.println("❌ Cannot find a practitioner with this ID!");
            return;
        }

        // Read the time slot and create an appointment from the patient, practitioner, and time slot
        System.out.print("Enter time slot (e.g. 09:00): ");
        String time = scanner.nextLine();
        Appointment app = new Appointment(patient, targetPrac, time);
        // Let the appointment manager validate the time slot and duplicates before adding the appointment
        manager.addAppointment(app);
    }

    // Retrieve and print all appointments sorted by time slot
    private static void showAllSortedAppointments(AppointmentManager manager) {
        System.out.println("\n----- All Appointments (Sorted by Time) -----");
        manager.printAppointmentList(manager.getAllSortedAppointments());
    }

    // Filter appointments using the practitioner ID entered by the user
    private static void filterByPid(AppointmentManager manager) {
        System.out.print("\nEnter practitioner ID to filter: ");
        // Discard non-integer input until a valid ID is entered
        while (!scanner.hasNextInt()) {
            System.out.println("ID must be a number, enter again: ");
            scanner.next();
        }
        int pid = scanner.nextInt();
        scanner.nextLine();
        System.out.println("----- Filter Results -----");
        // Pass the filtered results to the helper method for printing
        manager.printAppointmentList(manager.filterByPractitionerId(pid));
    }

    // Cancel an appointment: read the patient name and phone number, and validate that the phone number has 8 digits
    private static void cancelOneAppointment(AppointmentManager manager) {
        System.out.println("\n----- Cancel Appointment -----");
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();

        System.out.print("Enter patient phone number (must be 8 digits): ");
        String phone = scanner.nextLine();
        if (!phone.matches("^\\d{8}$")) {
            // End this cancellation operation if the phone number format is invalid
            System.out.println("❌ Phone number input failed! Must be 8 digits!");
            return;
        }

        System.out.print("Enter practitioner ID: ");
        // Ensure that the practitioner ID is an integer
        while (!scanner.hasNextInt()) {
            System.out.println("ID must be a number, enter again: ");
            scanner.next();
        }
        int pid = scanner.nextInt();
        scanner.nextLine();

        // Read the time slot of the appointment to cancel
        System.out.print("Enter time slot (e.g. 09:00): ");
        String time = scanner.nextLine();

        // Match the manager method's parameter order: ID, time slot, name, and phone number
        manager.cancelAppointment(pid, time, name, phone);
    }
}
