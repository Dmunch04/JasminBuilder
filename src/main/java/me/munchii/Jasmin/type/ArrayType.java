package me.munchii.Jasmin.type;

public class ArrayType implements JasminType, ReturnableType {
    // TODO: primitive?
    private final JasminType type;
    private final int dimensions;

    public ArrayType(JasminType type) {
        this(type, 1);
    }

    public ArrayType(JasminType type, int dimensions) {
        this.type = type;
        this.dimensions = dimensions;
    }

    @Override
    public String getRepresentation() {
        return "[".repeat(dimensions) + type.getRepresentation();
    }

    @Override
    public boolean isArray() {
        return true;
    }

    public JasminType getType() {
        return type;
    }

    public int getDimensions() {
        return dimensions;
    }
}
