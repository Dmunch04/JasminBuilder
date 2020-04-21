package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.ObjectType;

public class ObjectStatement implements JasminStatement
{

    private ObjectType Type;
    private String Class;

    public ObjectStatement (ObjectType Type, String Class)
    {
        this.Type = Type;
        this.Class = Class;
    }

    @Override
    public String ToOutputString ()
    {
        return Type.GetRepresentation () + " " + Class;
    }

}
