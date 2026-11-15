import java.util.List;

/**
 * Nurse class that inherits common practitioner information and records blood collection capability.
 */
public class Nurse extends MedicalPractitioner {
    private final boolean canDrawBlood; // Whether the nurse can collect blood samples; cannot be changed after initialization

    public Nurse(int practitionerId, String name, List<String> workingDays, boolean canDrawBlood) {
        // Call the superclass constructor to initialize the nurse ID, name, and working days
        super(practitionerId, name, workingDays);
        // Store this nurse's blood collection capability
        this.canDrawBlood = canDrawBlood;
    }

    @Override
    public String getSpecialInfo() {
        // Return the practitioner type and blood collection capability
        return "[Nurse] Can collect blood: " + canDrawBlood;
    }
}
