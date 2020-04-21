package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.LimitType;

public class LimitStatement implements JasminStatement
{

    private int Amount;
    private LimitType Type;

    public LimitStatement (int Amount, LimitType Type)
    {
        this.Amount = Amount;
        this.Type = Type;
    }

    @Override
    public String ToOutputString ()
    {
        return ".limit " + Type.GetRepresentation () + " " + Amount;
    }

}
