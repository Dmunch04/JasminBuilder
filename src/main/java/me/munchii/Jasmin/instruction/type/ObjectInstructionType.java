package me.munchii.Jasmin.instruction.type;

public enum ObjectInstructionType {
    // Create new array of reference
    A_NEW_ARRAY("anewarray", 0),

    // Check whether object is of given type
    CHECK_CAST("checkcast", 0),

    // Determine if object is of given type
    INSTANCE_OF("instanceof", 0),

    // Create new object
    NEW("new", +1);

    private final String representation;
    private final int stackChange;

    ObjectInstructionType(String representation, int stackChange) {
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
