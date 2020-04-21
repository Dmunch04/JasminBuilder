package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.DataType;

public class NewArrayStatement implements JasminStatement
{

    private DataType Type;

    public NewArrayStatement (DataType Type)
    {
        this.Type = Type;
    }

    @Override
    public String ToOutputString ()
    {
        return "newarray " + Type.GetRepresentation ();
    }

}
