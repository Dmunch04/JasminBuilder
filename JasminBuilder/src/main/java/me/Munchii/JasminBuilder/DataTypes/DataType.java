package me.Munchii.JasminBuilder.DataTypes;

public abstract class DataType
{

    private String Representation;
    private ValueType Type;

    public DataType(String Representation, ValueType Type)
    {
        this.Representation = Representation;
        this.Type = Type;
    }

    public boolean Compare (DataType Type)
    {
        return Compare (Type.GetType ());
    }

    public boolean Compare (ValueType Type)
    {
        return this.Type == Type;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

    public ValueType GetType ()
    {
        return Type;
    }

    public static final DataType String = new ReferenceType ("java/lang/String");
    public static final DataType StringInstance = new ReferenceType (String.Representation, true);
    public static final DataType Object = new ReferenceType ("java/lang/Object");
    public static final DataType ObjectInstance = new ReferenceType (Object.Representation, true);
    public static final DataType System = new ReferenceType ("java/lang/System");
    public static final DataType SystemInstance = new ReferenceType (System.Representation, true);
    public static final DataType Math = new ReferenceType ("java/lang/Math");
    public static final DataType MathInstance = new ReferenceType (Math.Representation, true);

    public static final DataType EmptyArray = new ArrayType ();
    public static final DataType EmptyReference = new ReferenceType ("");

}
