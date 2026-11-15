import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Appointment manager responsible for adding, filtering, sorting, cancelling, and printing appointment records.
 */
public class AppointmentManager {
    private final List<Appointment> appointmentList; // Store all appointment records
    // Valid appointment slots: 08:00 to 16:00, at 30-minute intervals
    private final List<String> validTimeSlots = List.of(
            "08:00", "08:30", "09:00", "09:30",
            "10:00", "10:30", "11:00", "11:30",
            "12:00", "12:30", "13:00", "13:30",
            "14:00", "14:30", "15:00", "15:30", "16:00"
    );

    public AppointmentManager() {
        // Initialize an empty appointment list
        appointmentList = new ArrayList<>();
    }

    // Add an appointment: validate the time slot and prevent double-booking a practitioner at the same time
    public boolean addAppointment(Appointment app) {
        // 1. Check whether the time slot is valid; reject the appointment if it is invalid
        if (!validTimeSlots.contains(app.getTimeSlot())) {
            System.out.println("❌ Add failed: Time slot [" + app.getTimeSlot() + "] is outside business hours!");
            return false;
        }
        // 2. Iterate through existing appointments and check for a duplicate practitioner ID and time slot
        for (Appointment a : appointmentList) {
            if (a.getPractitioner().getPractitionerId() == app.getPractitioner().getPractitionerId()
                    && a.getTimeSlot().equals(app.getTimeSlot())) {
                System.out.println("❌ Add failed: Practitioner [ID:" + a.getPractitioner().getPractitionerId() + "]" +
                        " already has an appointment at " + app.getTimeSlot() + "!");
                return false;
            }
        }
        // 3. Add the appointment to the list after validation
        appointmentList.add(app);
        System.out.println("✅ Appointment added successfully!");
        return true;
    }

    // Filter appointments by practitioner ID
    public List<Appointment> filterByPractitionerId(int pid) {
    List<Appointment> result = new ArrayList<>();
    for (Appointment a : appointmentList) {
        if (a.getPractitioner().getPractitionerId() == pid) {
            result.add(a);
        }
    }
    return result;
}

    // Return all appointments sorted by time slot
    public List<Appointment> getAllSortedAppointments() {
        // Create a copy so sorting does not change the order of the original list
        List<Appointment> sorted = new ArrayList<>(appointmentList);
        // Sort using the comparison rule defined in Appointment
        sorted.sort(Appointment::compareTo);
        return sorted;
    }

    public List<Appointment> getAppointmentList() {
        // Return the original appointment list
        return appointmentList;
    }

    // Cancel an appointment only when the practitioner ID, time slot, patient name, and phone number all match
    public boolean cancelAppointment(int practitionerId, String timeSlot, String name, String phone) {
        // Iterate through the list to find a record matching all four details
        for (Appointment a : appointmentList) {
            if (a.getPractitioner().getPractitionerId() == practitionerId
                    && a.getTimeSlot().equals(timeSlot)
                    && a.getPatient().getPatientName().equals(name)
                    && a.getPatient().getPhoneNumber().equals(phone)) {
                // Remove the matching appointment and exit the method
                appointmentList.remove(a);
                System.out.println("✅ Appointment cancelled successfully!");
                return true;
            }
        }
        // Return cancellation failure if no matching record is found
        System.out.println("❌ Cancellation failed: Information does not match, no such appointment found!");
        return false;
    }

    // Helper method to print the specified appointment list
    public void printAppointmentList(List<Appointment> list) {
        // Print a message and skip iteration when the list is empty
        if (list.isEmpty()) {
            System.out.println("(No appointment records)");
            return;
        }
        // Print each appointment record
        for (Appointment a : list) {
            System.out.println(a);
        }
    }
}
