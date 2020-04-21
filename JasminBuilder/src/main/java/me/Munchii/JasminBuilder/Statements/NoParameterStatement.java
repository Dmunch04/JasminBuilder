package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.NoParameterType;

public class NoParameterStatement implements JasminStatement
{

    private NoParameterType Type;

    public NoParameterStatement (NoParameterType Type)
    {
        this.Type = Type;
    }

    @Override
    public String ToOutputString ()
    {
        return Type.GetRepresentation ();
    }

}
