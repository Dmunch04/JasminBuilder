package me.Munchii.JasminBuilder.DataTypes;

public class ArrayType extends DataType
{

    public ArrayType (DataType Type)
    {
        this (Type, 1);
    }

    public ArrayType (DataType Type, int Dimensions)
    {
        super ("[".repeat (Dimensions) + Type.GetRepresentation (), ValueType.Array);
    }

}
