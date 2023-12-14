package me.munchii.Jasmin.type;

import me.munchii.Jasmin.instruction.IJasminInstruction;

public enum PrimitiveType implements ReturnableType, ValueType {
    BOOLEAN("Z"),
    BYTE("B"),
    CHAR("C"),
    DOUBLE("D"),
    FLOAT("F"),
    INTEGER("I"),
    LONG("J"),
    SHORT("S");

    private final String representation;

    PrimitiveType(String representation) {
        this.representation = representation;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }
}
