package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Types.LoadConstantType;

public class LoadConstantStatement implements JasminStatement
{

    private LoadConstantType Type;
    private JasminValue Constant;

    public LoadConstantStatement (LoadConstantType Type, JasminValue Constant)
    {
        this.Type = Type;
        this.Constant = Constant;
    }

    @Override
    public String ToOutputString ()
    {
        return Type.GetRepresentation () + " " + Constant.ToOutputString ();
    }

}
