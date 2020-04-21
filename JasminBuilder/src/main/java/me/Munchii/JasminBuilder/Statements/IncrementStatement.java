package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Statements.JasminStatement;

public class IncrementStatement implements JasminStatement
{

    private int VariableIndex;
    private int Amount;

    public IncrementStatement (int VariableIndex, int Amount)
    {
        this.VariableIndex = VariableIndex;
        this.Amount = Amount;
    }

    @Override
    public String ToOutputString ()
    {
        return "iinc " + VariableIndex + " " + Amount;
    }

}
