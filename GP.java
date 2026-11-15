import java.util.List;

/**
 * General practitioner class that inherits common practitioner information and records prescribing permission.
 */
public class GP extends MedicalPractitioner {
    private final boolean canPrescribe; // Whether the GP can prescribe medication; cannot be changed after initialization

    public GP(int practitionerId, String name, List<String> workingDays, boolean canPrescribe) {
        // Call the superclass constructor to initialize the practitioner ID, name, and working days
        super(practitionerId, name, workingDays);
        // Store this GP's prescribing permission
        this.canPrescribe = canPrescribe;
    }

    @Override
    public String getSpecialInfo() {
        // Return the practitioner type and prescribing permission
        return "[General Practitioner] Can prescribe: " + canPrescribe;
    }
}
