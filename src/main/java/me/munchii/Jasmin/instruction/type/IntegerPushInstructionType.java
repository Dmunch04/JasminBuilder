package me.munchii.Jasmin.instruction.type;

public enum IntegerPushInstructionType {
    // Push byte
    BI_PUSH("bipush"),

    // Push short
    SI_PUSH("sipush");

    private final String representation;

    IntegerPushInstructionType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
