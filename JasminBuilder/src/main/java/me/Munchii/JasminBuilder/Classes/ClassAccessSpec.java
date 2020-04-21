package me.Munchii.JasminBuilder.Classes;

public enum ClassAccessSpec
{

    Public ("public"),
    Final ("final"),
    Super ("super"),
    Interface ("interface"),
    Abstract ("abstract");

    private String Representation;

    private ClassAccessSpec (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
