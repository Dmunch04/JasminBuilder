package me.munchii.Jasmin.type;

public class ArrayType implements IDataType {
    private final IDataType type;
    private final int dimensions;

    public ArrayType(IDataType type) {
        this(type, 1);
    }

    public ArrayType(IDataType type, int dimensions) {
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

    public IDataType getType() {
        return type;
    }

    public int getDimensions() {
        return dimensions;
    }
}
