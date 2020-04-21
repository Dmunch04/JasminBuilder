package me.Munchii.JasminBuilder.Types;

public enum FieldManipulationType
{

    // Fetch field from object
    GetField ("getfield"),

    // Get static field from class
    GetStatic ("getstatic"),

    // Set field in object
    PutField ("putfield"),

    // Set static field in class
    PutStatic ("putstatic");

    private String Representation;

    private FieldManipulationType(String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
