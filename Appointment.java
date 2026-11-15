/**
 * Appointment record class that stores the patient, practitioner, and time slot, and supports sorting by time slot.
 */
public class Appointment implements Comparable<Appointment> {
    private final Patient patient; // Patient for this appointment; cannot be changed after initialization
    private final MedicalPractitioner practitioner; // Practitioner providing care; cannot be changed after initialization
    private final String timeSlot; // Appointment time slot; cannot be changed after initialization

    public Appointment(Patient patient, MedicalPractitioner practitioner, String timeSlot) {
        // Initialize all appointment information
        this.patient = patient;
        this.practitioner = practitioner;
        this.timeSlot = timeSlot;
    }

    public Patient getPatient() {
        // Return the patient for this appointment
        return patient;
    }

    public MedicalPractitioner getPractitioner() {
        // Return the practitioner providing care
        return practitioner;
    }

    public String getTimeSlot() {
        // Return the appointment time slot
        return timeSlot;
    }

    @Override
    public int compareTo(Appointment other) {
        // Compare time-slot strings lexicographically to sort appointment records
        return this.timeSlot.compareTo(other.timeSlot);
    }

    @Override
    public String toString() {
        // Return an appointment record containing patient, time-slot, and practitioner details
        return "=====Appointment Record=====\n" +
                patient + "\nTime Slot: " + timeSlot + "\nPractitioner: " + practitioner + "\n";
    }
}
