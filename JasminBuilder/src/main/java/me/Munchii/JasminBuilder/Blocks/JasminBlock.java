package me.Munchii.JasminBuilder.Blocks;

import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.JasminVariable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.VariableStatement;
import me.Munchii.JasminBuilder.Types.VariableType;

import java.util.ArrayList;
import java.util.List;

public class JasminBlock
{

    private String Name; // Label
    private List<JasminStatement> Statements;

    public JasminBlock (String Name)
    {
        this.Name = Name;
        this.Statements = new ArrayList<JasminStatement> ();
    }

    public List<JasminStatement> Write (JasminMethod Method)
    {
        return null;
    }

    public JasminBlock AddStatement (JasminStatement Statement)
    {
        Statements.add (Statement);
        return this;
    }

    public JasminBlock DeclareVariable (JasminVariable Variable)
    {
        Statements.add (new VariableStatement (VariableType.Declare, Variable));
        return this;
    }

    public JasminBlock StoreVariable (JasminVariable Variable, JasminPassable Value)
    {
        Statements.add (new VariableStatement (VariableType.Store, Variable, Value));
        return this;
    }

    public JasminBlock LoadVariable (String Name)
    {
        Statements.add (new VariableStatement (VariableType.Load, Name));
        return this;
    }

    public String GetLabel ()
    {
        return Name;
    }

}
