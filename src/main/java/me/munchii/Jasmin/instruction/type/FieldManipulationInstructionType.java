package me.munchii.Jasmin.instruction.type;

public enum FieldManipulationInstructionType {
    // Fetch field from object
    GET_FIELD("getfield", 0),

    // Get static field from class
    GET_STATIC("getstatic", +1),

    // Set field in object
    PUT_FIELD("putfield", -2),

    // Set static field in class
    PUT_STATIC("putstatic", -1);

    private final String representation;
    private final int stackChange;

    FieldManipulationInstructionType(String representation, int stackChange) {
        this.representation = representation;
        this.stackChange = stackChange;
    }

    public String getRepresentation() {
        return representation;
    }

    public int getStackChange() {
        return stackChange;
    }
}
