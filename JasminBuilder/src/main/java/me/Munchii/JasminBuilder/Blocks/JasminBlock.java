package me.Munchii.JasminBuilder.Blocks;

import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.JasminVariable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.VariableStatement;
import me.Munchii.JasminBuilder.Types.VariableType;
import me.Munchii.JasminBuilder.Utils.MethodCreator;

import java.util.ArrayList;
import java.util.List;

public class JasminBlock implements Builder
{

    private String Name; // Label
    private List<JasminStatement> Statements;

    public JasminBlock (String Name)
    {
        this.Name = Name;
        this.Statements = new ArrayList<JasminStatement> ();
    }

    @Override
    public String ToOutputString ()
    {
        if (Method == null)
            throw new IllegalArgumentException ("No method hooked for block construction!");

        StringBuilder Builder = new StringBuilder ();
        Builder.append (Name).append (":").append ("\n");

        for (JasminStatement Statement : Statements)
        {
            if (Statement instanceof VariableStatement)
            {
                VariableStatement Variable = (VariableStatement) Statement;
                if (Variable.GetType () == VariableType.Load)
                {
                    for (JasminStatement VariableLoadStatement : Method.GetLoadVariableStatements (Variable.GetName ()))
                    {
                        Builder.append ("\t").append (VariableLoadStatement.ToOutputString ()).append ("\n");
                    }

                    continue;
                }

                else if (Variable.GetType () == VariableType.Declare)
                {
                    for (JasminStatement VariableDeclareStatement : Method.GetDeclareVariableStatements (Variable.GetVariable ()))
                    {
                        Builder.append ("\t").append (VariableDeclareStatement.ToOutputString ()).append ("\n");
                    }

                    continue;
                }

                else if (Variable.GetType () == VariableType.Stack)
                {
                    Builder.append ("\t").append (Method.GetDeclareStackVariableStatement (Variable.GetVariable ()).ToOutputString ()).append ("\n");

                    continue;
                }
            }

            Builder.append ("\t").append (Statement.ToOutputString ()).append ("\n");
        }

        Builder.append ("\n");

        return Builder.toString ();
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

    public JasminBlock DeclareStackVariable (JasminVariable Variable)
    {
        Statements.add (new VariableStatement (VariableType.Stack, Variable));
        return this;
    }

    private JasminMethod Method = null;
    public void Hook (JasminMethod Method)
    {
        this.Method = Method;
    }

}
