package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Types.NoParameterType;

import java.util.ArrayList;
import java.util.List;

public class JasminExpression implements Builder, JasminPassable
{

    private JasminPassable BaseValue;
    private List<JasminStatement> Statements;
    private DataType Type;

    public JasminExpression (JasminPassable BaseValue)
    {
        this.BaseValue = BaseValue;
        this.Statements = new ArrayList<JasminStatement> ();
        Statements.addAll (BaseValue.PushToStack ());
        this.Type = BaseValue.GetType ();
    }

    // For stack use (Dangerous)
    public JasminExpression (DataType Type)
    {
        this.BaseValue = null;
        this.Statements = new ArrayList<JasminStatement> ();
        this.Type = Type;
    }

    public JasminExpression Negate ()
    {
        switch (Type)
        {
            // TODO: Implement more types?
            case Double: Statements.add (new NoParameterStatement (NoParameterType.NegateDouble));
            case Float: Statements.add (new NoParameterStatement (NoParameterType.NegateFloat));
            case Integer: Statements.add (new NoParameterStatement (NoParameterType.NegateInteger));
            case Long: Statements.add (new NoParameterStatement (NoParameterType.NegateLong));
        }

        return this;
    }

    public JasminExpression Add (JasminPassable Other)
    {
        if (Other.GetType () != Type)
            throw new IllegalArgumentException ("Second value not of same type as base value!");

        Statements.addAll (Other.PushToStack ());

        switch (Type)
        {
            // TODO: Implement more types?
            case Double: Statements.add (new NoParameterStatement (NoParameterType.AddDouble));
            case Float: Statements.add (new NoParameterStatement (NoParameterType.AddFloat));
            case Integer: Statements.add (new NoParameterStatement (NoParameterType.AddInteger));
            case Long: Statements.add (new NoParameterStatement (NoParameterType.AddLong));
        }

        return this;
    }

    public JasminExpression Subtract (JasminPassable Other)
    {
        if (Other.GetType () != Type)
            throw new IllegalArgumentException ("Second value not of same type as base value!");

        Statements.addAll (Other.PushToStack ());

        switch (Type)
        {
            // TODO: Implement more types
            case Double: Statements.add (new NoParameterStatement (NoParameterType.SubtractDouble));
            case Float: Statements.add (new NoParameterStatement (NoParameterType.SubtractFloat));
            case Integer: Statements.add (new NoParameterStatement (NoParameterType.SubtractInteger));
            case Long: Statements.add (new NoParameterStatement (NoParameterType.SubtractLong));
        }

        return this;
    }

    @Override
    public String ToOutputString ()
    {
        return "";
    }

    @Override
    public List<JasminStatement> PushToStack ()
    {
        return null;
    }

    @Override
    public DataType GetType ()
    {
        return Type;
    }

}
