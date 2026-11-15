import java.util.List;

/**
 * Specialist class that inherits common practitioner information and records a specialty.
 */
public class Specialist extends MedicalPractitioner {
    private final String specialty; // Specialty, such as cardiology or paediatrics; cannot be changed after initialization

    public Specialist(int practitionerId, String name, List<String> workingDays, String specialty) {
        // Call the superclass constructor to initialize the practitioner ID, name, and working days
        super(practitionerId, name, workingDays);
        // Store this specialist's specialty
        this.specialty = specialty;
    }

    @Override
    public String getSpecialInfo() {
        // Return the practitioner type and specialty
        return "[Specialist] Specialty: " + specialty;
    }
}
