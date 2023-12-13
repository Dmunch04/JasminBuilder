package me.munchii.Jasmin.type;

public enum PrimitiveType implements ReturnableType, ValueType {
    BOOLEAN("Z"),
    BYTE("B"),
    CHAR("C"),
    DOUBLE("D"),
    FLOAT("F"),
    INTEGER("I"),
    LONG("J"),
    SHORT("S");

    public static final VoidType VOID_TYPE = new VoidType();
    public static final StringType STRING_TYPE = new StringType();

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
