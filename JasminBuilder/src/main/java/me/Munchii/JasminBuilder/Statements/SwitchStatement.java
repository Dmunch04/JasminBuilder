package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.SwitchType;

public class SwitchStatement implements JasminStatement
{

    private SwitchType Type;

    public SwitchStatement (SwitchType Type)
    {
        this.Type = Type;
    }

    @Override
    public String ToOutputString ()
    {
        return Type.GetRepresentation ();
    }

}
