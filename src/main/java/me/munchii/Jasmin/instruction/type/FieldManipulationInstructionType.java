package me.munchii.Jasmin.instruction.type;

public enum FieldManipulationInstructionType {
    // Fetch field from object
    GET_FIELD("getfield"),

    // Get static field from class
    GET_STATIC("getstatic"),

    // Set field in object
    PUT_FIELD("putfield"),

    // Set static field in class
    PUT_STATIC("putstatic");

    private final String representation;

    FieldManipulationInstructionType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
