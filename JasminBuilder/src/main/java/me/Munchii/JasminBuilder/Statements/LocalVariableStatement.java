package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.LocalVariableType;

public class LocalVariableStatement implements JasminStatement
{

    private LocalVariableType Type;
    private int VariableNumber;

    public LocalVariableStatement (LocalVariableType Type, int VariableNumber)
    {
        this.Type = Type;
        this.VariableNumber = VariableNumber;
    }

    public String ToOutputString ()
    {
        return Type.GetRepresentation () + " "  + VariableNumber;
    }

}
