package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.IntegerPushType;

public class IntegerPushStatement implements JasminStatement
{

    private IntegerPushType Type;
    private int Value;

    public IntegerPushStatement (IntegerPushType Type, int Value)
    {
        this.Type = Type;
        this.Value = Value;
    }

    @Override
    public String ToOutputString ()
    {
        return Type.GetRepresentation () + " " + Value;
    }

}
