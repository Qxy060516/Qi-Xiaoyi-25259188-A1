import java.util.List;

/**
 * Abstract base class for medical practitioners; cannot be instantiated directly.
 * Encapsulates the practitioner ID, name, and working days shared by all practitioner types.
 */
public abstract class MedicalPractitioner {
    private final int practitionerId; // Practitioner ID; cannot be changed after initialization
    private final String name; // Practitioner name; cannot be changed after initialization
    private final List<String> workingDays; // Working-day list; its reference cannot be reassigned after initialization

    public MedicalPractitioner(int practitionerId, String name, List<String> workingDays) {
        // Initialize basic practitioner information
        this.practitionerId = practitionerId;
        this.name = name;
        this.workingDays = workingDays;
    }

    // Provide accessors for basic information, demonstrating encapsulation
    public int getPractitionerId() {
        // Return the practitioner ID
        return practitionerId;
    }

    public String getName() {
        // Return the practitioner name
        return name;
    }

    public List<String> getWorkingDays() {
        // Return the working-day list
        return workingDays;
    }

    // Abstract method implemented by subclasses to return their specific information, demonstrating polymorphism
    public abstract String getSpecialInfo();

    @Override
    public String toString() {
        // Combine common information with subclass-specific information obtained through polymorphism
        return "ID: " + practitionerId +
                " | Name: " + name +
                " | Working Days: " + workingDays +
                " | " + getSpecialInfo();
    }
}
