package me.Munchii.JasminBuilder.Types;

public enum DataType
{

    // Class: L ClassName;
    // | Example: Ljava/lang/String;
    // Array: [ Type;
    // | Example: [I;
    // | Note: The amount of '[' is the amount of dimensions

    // https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html -> "Table 4.2. Interpretation of FieldType characters"
    Byte ("B"),
    Char ("C"),
    Double ("D"),
    Float ("F"),
    Integer ("I"),
    Long ("J"),
    String ("java/lang/String"),
    StringInstance ("Ljava/lang/String;"),
    Object ("java/lang/Object"),
    ObjectInstance ("Ljava/lang/Object;"),
    Void ("V"),
    Short ("S"),
    CustomInstance ("L"),
    Custom (""),
    Array (""),
    Boolean ("Z");

    public static DataType MakeCustomClassInstance (String Class)
    {
        DataType Type = CustomInstance;
        Type.Representation = "L" + Class + ";";

        return Type;
    }

    public static DataType MakeCustomClass (String Class)
    {
        DataType Type = Custom;
        Type.Representation = Class;

        return Type;
    }

    public static DataType MakeArray (int Dimensions, DataType TargetType)
    {
        DataType Type = Array;
        Type.Representation = "[".repeat (Dimensions) + TargetType.Representation;

        return Type;
    }

    private String Representation;

    private DataType (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
