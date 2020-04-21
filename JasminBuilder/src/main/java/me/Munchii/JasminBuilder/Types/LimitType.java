package me.Munchii.JasminBuilder.Types;

public enum LimitType
{

    Stack ("stack"),
    Locals ("locals");

    private String Representation;

    private LimitType (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
