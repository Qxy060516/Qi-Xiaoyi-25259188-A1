/**
 * Patient class used to store basic patient information.
 */
public class Patient {
    private final String patientName; // Patient name; cannot be changed after initialization
    private final String phoneNumber; // Patient phone number; cannot be changed after initialization

    public Patient(String patientName, String phoneNumber) {
        // Initialize the patient name and phone number
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
    }

    public String getPatientName() {
        // Return the patient name
        return patientName;
    }

    public String getPhoneNumber() {
        // Return the patient phone number
        return phoneNumber;
    }

    @Override
    public String toString() {
        // Return a string containing the patient name and phone number
        return "Patient: " + patientName + ", Phone: " + phoneNumber;
    }
}
