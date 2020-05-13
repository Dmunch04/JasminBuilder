package me.Munchii.JasminBuilder.DataTypes;

public abstract class DataType
{

    private String Representation;
    private ValueType Type;

    public DataType (String Representation, ValueType Type)
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

    @Override
    public String toString ()
    {
        return Type.name () + "(" + Representation + ")";
    }

    // Common java std classes
    public static final DataType String = new ReferenceType ("java/lang/String");
    public static final DataType StringInstance = new ReferenceType (String.Representation, true);
    public static final DataType Object = new ReferenceType ("java/lang/Object");
    public static final DataType ObjectInstance = new ReferenceType (Object.Representation, true);
    public static final DataType System = new ReferenceType ("java/lang/System");
    public static final DataType SystemInstance = new ReferenceType (System.Representation, true);
    public static final DataType Math = new ReferenceType ("java/lang/Math");
    public static final DataType MathInstance = new ReferenceType (Math.Representation, true);

    // All types so they're easier to access
    public static final DataType Boolean = new BooleanType ();
    public static final DataType Byte = new ByteType ();
    public static final DataType Char = new CharType ();
    public static final DataType Double = new DoubleType ();
    public static final DataType Float = new FloatType ();
    public static final DataType Integer = new IntegerType ();
    public static final DataType Long = new LongType ();
    public static final DataType Short = new ShortType ();
    public static final DataType Void = new VoidType ();
    //! Use this with caution!!:
    public static final DataType EmptyReference = new ReferenceType ("");

}
