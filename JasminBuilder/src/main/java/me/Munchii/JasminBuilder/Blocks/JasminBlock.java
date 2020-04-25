package me.Munchii.JasminBuilder.Blocks;

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

    public void Write (JasminMethod Method)
    {
        for (JasminStatement Statement : Statements)
        {
            if (Statement instanceof VariableStatement)
            {
                VariableStatement Variable = (VariableStatement) Statement;
                switch (Variable.GetType ())
                {
                    case Declare: Method.DeclareVariable (Variable.GetVariable ());
                    case Store: Method.StoreVariable (Variable.GetVariable (), Variable.GetValue ());
                    case Load: Method.LoadVariable (Variable.GetName ());
                }

                continue;
            }

            Method.AddStatement (Statement);
        }
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

    public List<JasminStatement> GetStatements ()
    {
        return Statements;
    }

}
