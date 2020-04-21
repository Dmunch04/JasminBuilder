package me.Munchii.JasminBuilder.Types;

// TODO: Maybe rename this + it's statement (`IntegerPushStatement`) + everything related to this
public enum IntegerPushType
{

    // Push byte
    BiPush ("bipush"),

    // Push short
    SiPush ("sipush");

    private String Representation;

    private IntegerPushType(String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
