package me.Munchii.JasminBuilder.Types;

public enum ObjectType
{

    // Create new array of reference
    ANewArray ("anewarray"),

    // Check whether object is of given type
    CheckCast ("checkcast"),

    // Determine if object is of given type
    InstanceOf ("instanceof"),

    // Create new object
    New ("new");

    private String Representation;

    private ObjectType (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
