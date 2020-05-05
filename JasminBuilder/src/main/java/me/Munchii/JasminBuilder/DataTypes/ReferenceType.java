package me.Munchii.JasminBuilder.DataTypes;

public class ReferenceType extends DataType
{

    public ReferenceType (String Class)
    {
        this (Class, false);
    }

    public ReferenceType (String Class, boolean IsInstance)
    {
        super (IsInstance ? "L" + Class + ";" : Class, ValueType.Reference);
    }

}
