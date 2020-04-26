package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.LimitType;

public class LimitStatement implements JasminStatement
{

    private LimitType Type;
    private int Amount;

    public LimitStatement (LimitType Type, int Amount)
    {
        this.Type = Type;
        this.Amount = Amount;
    }

    @Override
    public String ToOutputString ()
    {
        return ".limit " + Type.GetRepresentation () + " " + Amount;
    }

    public LimitType GetType ()
    {
        return Type;
    }

    public int GetAmount ()
    {
        return Amount;
    }

}
