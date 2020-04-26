package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.JasminVariable;
import me.Munchii.JasminBuilder.References.VariableReference;
import me.Munchii.JasminBuilder.Types.VariableType;

public class VariableStatement implements JasminStatement
{

    private VariableType Type;
    private JasminVariable Variable;
    private JasminPassable Value;
    private String Name;

    public VariableStatement (VariableType Type, JasminVariable Variable)
    {
        this (Type, Variable, Variable.GetValue ());
    }

    public VariableStatement (VariableType Type, JasminVariable Variable, JasminPassable Value)
    {
        this.Type = Type;
        this.Variable = Variable;
        this.Value = Value;
        this.Name = Variable.GetName ();
    }

    // Only for loading variables
    public VariableStatement (VariableType Type, String Name)
    {
        this.Type = Type;
        this.Variable = null;
        this.Value = null;
        this.Name = Name;
    }

    @Override
    public String ToOutputString ()
    {
        if (Type != VariableType.Load)
        {
            StringBuilder Builder = new StringBuilder ();

            for (JasminStatement Statement : Value.PushToStack ())
                Builder.append (Statement.ToOutputString ()).append ("\n");

            Builder.append (Variable.Store ().ToOutputString ());

            return Builder.toString ();
        }

        // Don't worry about this, as it won't be written to the code
        return "";
    }

    public VariableType GetType ()
    {
        return Type;
    }

    public JasminVariable GetVariable ()
    {
        return Variable;
    }

    public JasminPassable GetValue ()
    {
        return Value;
    }

    public String GetName ()
    {
        return Name;
    }

    public VariableReference GetReference ()
    {
        return new VariableReference (Name);
    }
}
