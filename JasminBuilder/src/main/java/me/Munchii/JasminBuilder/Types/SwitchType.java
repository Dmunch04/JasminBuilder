package me.Munchii.JasminBuilder.Types;

public enum SwitchType
{

    // Access jump table by key match and jump
    LookupSwitch ("lookupswitch"),

    // Access jump table by index and jump
    TableSwitch ("tableswitch");

    private String Representation;

    private SwitchType (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
