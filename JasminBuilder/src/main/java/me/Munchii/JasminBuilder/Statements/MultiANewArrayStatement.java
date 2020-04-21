package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.DataType;

public class MultiANewArrayStatement implements JasminStatement
{

    private DataType Type;
    private int Dimensions;

    public MultiANewArrayStatement(DataType Type, int Dimensions)
    {
        this.Type = Type;
        this.Dimensions = Dimensions;
    }

    @Override
    public String ToOutputString ()
    {
        return "multianewarray " + Type.GetRepresentation () + " " + Dimensions;
    }

}
