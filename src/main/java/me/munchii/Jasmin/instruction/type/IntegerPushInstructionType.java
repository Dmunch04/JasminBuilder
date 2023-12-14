package me.munchii.Jasmin.instruction.type;

public enum IntegerPushInstructionType {
    // Push byte
    BI_PUSH("bipush", +1),

    // Push short
    SI_PUSH("sipush", +1);

    private final String representation;
    private final int stackChange;

    IntegerPushInstructionType(String representation, int stackChange) {
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
