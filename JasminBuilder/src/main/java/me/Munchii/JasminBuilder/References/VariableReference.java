package me.Munchii.JasminBuilder.References;

import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.VariableStatement;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Types.VariableType;

import java.util.List;

import static java.util.Arrays.asList;

public class VariableReference implements JasminPassable
{

    public String Name;
    public DataType Type;

    // TODO: Is this really the best way to handle types?
    public VariableReference (String Name)
    {
        this (Name, null);
    }

    public VariableReference (String Name, DataType Type)
    {
        this.Name = Name;
        this.Type = Type;
    }

    @Override
    public List<JasminStatement> PushToStack ()
    {
        return asList (new VariableStatement (VariableType.Load, Name));
    }

    @Override
    public DataType GetType ()
    {
        // TODO: Again, is this really the best way? Can very easily create confusing null errors
        return Type;
    }
}
