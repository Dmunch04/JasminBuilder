package me.Munchii.JasminBuilder.Types;

public enum DataType
{

    // Class: L ClassName;
    // | Example: Ljava/lang/String;
    // Array: [ Type;
    // | Example: [I;
    // | Note: The amount of '[' is the amount of dimensions

    // https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html -> "Table 4.2. Interpretation of FieldType characters"
    Boolean ("Z"),
    Byte ("B"),
    Char ("C"),
    Double ("D"),
    Float ("F"),
    Integer ("I"),
    Long ("J"),
    Void ("V"),
    Short ("S"),

    // TODO: Maybe find a way to collapse reference and reference instance? Since they're the same thing pretty much
    Reference (""),
    ReferenceInstance (""),
    Array ("");

    private String Representation;

    private DataType (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

    public static DataType MakeReferenceInstance (String Class)
    {
        DataType Type = ReferenceInstance;
        Type.Representation = "L" + Class + ";";

        return Type;
    }

    public static DataType MakeReference (String Class)
    {
        DataType Type = Reference;
        Type.Representation = Class;

        return Type;
    }

    public static DataType MakeArray (int Dimensions, DataType TargetType)
    {
        DataType Type = Array;
        Type.Representation = "[".repeat (Dimensions) + TargetType.Representation;

        return Type;
    }

    public static DataType MakeInstance (DataType ClassType)
    {
        DataType Type = ReferenceInstance;
        Type.Representation = "L" + ClassType.Representation + ";";

        return Type;
    }

    public static final DataType String = MakeReference ("java/lang/String");
    public static final DataType StringInstance = MakeInstance (String);
    public static final DataType Object = MakeReference ("java/lang/Object");
    public static final DataType ObjectInstance = MakeInstance (Object);
    public static final DataType System = MakeReference ("java/lang/System");
    public static final DataType SystemInstance = MakeInstance (System);
    public static final DataType Math = MakeReference ("java/lang/Math");
    public static final DataType MathInstance = MakeInstance (Math);

}
