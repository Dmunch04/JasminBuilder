package me.munchii.Jasmin.type;

public class ArrayType implements ReturnableType, ValueType {
    private final ValueType type;
    private final int dimensions;

    public ArrayType(ValueType type) {
        this(type, 1);
    }

    public ArrayType(ValueType type, int dimensions) {
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
