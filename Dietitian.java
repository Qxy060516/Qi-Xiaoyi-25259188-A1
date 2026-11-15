import java.util.List;

/**
 * Dietitian class that inherits common practitioner information and records an area of expertise.
 */
public class Dietitian extends MedicalPractitioner {
    private final String focusArea; // Area of expertise, such as weight loss or diabetic diets; cannot be changed after initialization

    public Dietitian(int practitionerId, String name, List<String> workingDays, String focusArea) {
        // Call the superclass constructor to initialize the dietitian ID, name, and working days
        super(practitionerId, name, workingDays);
        // Store this dietitian's area of expertise
        this.focusArea = focusArea;
    }

    @Override
    public String getSpecialInfo() {
        // Return the practitioner type and area of expertise
        return "[Dietitian] Area of expertise: " + focusArea;
    }
}
