package me.Munchii.JasminBuilder.DataTypes;

public class ArrayType extends DataType {

    public ArrayType(DataType type) {
        this(type, 1);
    }

    public ArrayType(DataType type, int dimensions) {
        super("[".repeat(dimensions) + type.getRepresentation(), ValueType.ARRAY);
    }

}
