package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Types.NoParameterType;

import java.util.ArrayList;
import java.util.List;

public class ExpressionBuilder implements Builder, JasminPassable
{

    private JasminPassable BaseValue;
    private List<JasminStatement> Statements;
    private DataType Type;

    public ExpressionBuilder (JasminPassable BaseValue)
    {
        this.BaseValue = BaseValue;
        this.Statements = new ArrayList<JasminStatement> ();
        Statements.addAll (BaseValue.PushToStack ());
        this.Type = BaseValue.GetType ();
    }

    // For stack use (Dangerous)
    public ExpressionBuilder (DataType Type)
    {
        this.BaseValue = null;
        this.Statements = new ArrayList<JasminStatement> ();
        this.Type = Type;
    }

    public ExpressionBuilder Negate ()
    {
        switch (Type.GetType ())
        {
            // TODO: Implement more types?
            case Double: Statements.add (new NoParameterStatement (NoParameterType.NegateDouble)); break;
            case Float: Statements.add (new NoParameterStatement (NoParameterType.NegateFloat)); break;
            case Integer: Statements.add (new NoParameterStatement (NoParameterType.NegateInteger)); break;
            case Long: Statements.add (new NoParameterStatement (NoParameterType.NegateLong)); break;
        }

        return this;
    }

    public ExpressionBuilder Add (JasminPassable Other)
    {
        if (Other.GetType () != Type)
            throw new IllegalArgumentException ("Second value not of same type as base value!");

        Statements.addAll (Other.PushToStack ());

        switch (Type.GetType ())
        {
            // TODO: Implement more types?
            case Double: Statements.add (new NoParameterStatement (NoParameterType.AddDouble)); break;
            case Float: Statements.add (new NoParameterStatement (NoParameterType.AddFloat)); break;
            case Integer: Statements.add (new NoParameterStatement (NoParameterType.AddInteger)); break;
            case Long: Statements.add (new NoParameterStatement (NoParameterType.AddLong)); break;
        }

        return this;
    }

    public ExpressionBuilder Subtract (JasminPassable Other)
    {
        if (Other.GetType () != Type)
            throw new IllegalArgumentException ("Second value not of same type as base value!");

        Statements.addAll (Other.PushToStack ());

        switch (Type.GetType ())
        {
            // TODO: Implement more types
            case Double: Statements.add (new NoParameterStatement (NoParameterType.SubtractDouble)); break;
            case Float: Statements.add (new NoParameterStatement (NoParameterType.SubtractFloat)); break;
            case Integer: Statements.add (new NoParameterStatement (NoParameterType.SubtractInteger)); break;
            case Long: Statements.add (new NoParameterStatement (NoParameterType.SubtractLong)); break;
        }

        return this;
    }

    public ExpressionBuilder Multiply (JasminPassable Other)
    {
        if (Other.GetType () != Type)
            throw new IllegalArgumentException ("Second value not of same type as base value!");

        Statements.addAll (Other.PushToStack ());

        switch (Type.GetType ())
        {
            case Double: Statements.add (new NoParameterStatement (NoParameterType.MultiplyDouble)); break;
            case Float: Statements.add (new NoParameterStatement (NoParameterType.MultiplyFloat)); break;
            case Integer: Statements.add (new NoParameterStatement (NoParameterType.MultiplyInteger)); break;
            case Long: Statements.add (new NoParameterStatement (NoParameterType.MultiplyLong)); break;
        }

        return this;
    }

    public ExpressionBuilder Divide (JasminPassable Other)
    {
        if (Other.GetType () != Type)
            throw new IllegalArgumentException ("Second value not of same type as base value!");

        Statements.addAll (Other.PushToStack ());

        switch (Type.GetType ())
        {
            case Double: Statements.add (new NoParameterStatement (NoParameterType.DivideDouble)); break;
            case Float: Statements.add (new NoParameterStatement (NoParameterType.DivideFloat)); break;
            case Integer: Statements.add (new NoParameterStatement (NoParameterType.DivideInteger)); break;
            case Long: Statements.add (new NoParameterStatement (NoParameterType.DivideLong)); break;
        }

        return this;
    }

    public static ExpressionBuilder Negate (JasminPassable Value1)
    {
        return new ExpressionBuilder (Value1).Negate ();
    }

    public static ExpressionBuilder Add (JasminPassable Value1, JasminPassable Value2)
    {
        return new ExpressionBuilder (Value1).Add (Value2);
    }

    public static ExpressionBuilder Subtract (JasminPassable Value1, JasminPassable Value2)
    {
        return new ExpressionBuilder (Value1).Subtract (Value2);
    }

    public static ExpressionBuilder Multiply (JasminPassable Value1, JasminPassable Value2)
    {
        return new ExpressionBuilder (Value1).Multiply (Value2);
    }

    public static ExpressionBuilder Divide (JasminPassable Value1, JasminPassable Value2)
    {
        return new ExpressionBuilder (Value1).Divide (Value2);
    }

    @Override
    public String ToOutputString ()
    {
        return "";
    }

    @Override
    public List<JasminStatement> PushToStack ()
    {
        return Statements;
    }

    @Override
    public DataType GetType ()
    {
        return Type;
    }

}
