package me.Munchii.JasminBuilder.Fields;

public enum FieldAccessSpec
{

    Public ("public"),
    Private ("private"),
    Protected ("protected"),
    Static ("static"),
    Final ("final"),
    Volatile ("volatile"),
    Transient ("transient");

    private String Representation;

    private FieldAccessSpec (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
