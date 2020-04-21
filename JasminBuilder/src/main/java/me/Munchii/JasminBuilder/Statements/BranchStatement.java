package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.BranchType;

public class BranchStatement implements JasminStatement
{

    private BranchType Type;
    private String Label;

    public BranchStatement (BranchType Type, String Label)
    {
        this.Type = Type;
        this.Label = Label;
    }

    @Override
    public String ToOutputString ()
    {
        return Type.GetRepresentation () + " " + Label;
    }

}
