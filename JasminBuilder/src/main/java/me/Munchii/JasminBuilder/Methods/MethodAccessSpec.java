package me.Munchii.JasminBuilder.Methods;

public enum MethodAccessSpec
{

    Public ("public"),
    Private ("private"),
    Protected ("protected"),
    Static ("static"),
    Final ("final"),
    Synchronized ("synchronized"),
    Native ("native"),
    Abstract ("abstract");

    private String Representation;

    private MethodAccessSpec (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
