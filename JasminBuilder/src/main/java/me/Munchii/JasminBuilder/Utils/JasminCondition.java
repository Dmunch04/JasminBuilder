package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.DataTypes.ArrayType;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.DataTypes.ReferenceType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Logging.Exceptions.AbortException;
import me.Munchii.JasminBuilder.Logging.Logger;
import me.Munchii.JasminBuilder.Types.ConditionType;

public class JasminCondition
{

    private final JasminPassable Value1;
    private final JasminPassable Value2;
    private final ConditionType Type;
    private final DataType ValueType;

    public JasminCondition (JasminPassable Value1, JasminPassable Value2, ConditionType Type)
    {
        if (!(Value1.GetType ().Compare (Value2.GetType ())) && !(Value1.GetType ().Compare (DataType.Void) || Value2.GetType ().Compare (DataType.Void)))
        {
            Logger.Error ("Values must be of same type!");
            throw new AbortException ();
        }

        if (
                (Value1.GetType ().Compare (DataType.Void) && !(Value2.GetType () instanceof ReferenceType || Value2.GetType () instanceof ArrayType)) ||
                (Value2.GetType ().Compare (DataType.Void) && !(Value1.GetType () instanceof ReferenceType || Value1.GetType () instanceof ArrayType))
        )
        {
            Logger.Error ("Cannot compare non-reference to null!");
            throw new AbortException ();
        }

        this.Value1 = Value1;
        this.Value2 = Value2;
        this.Type = Type;

        System.out.println(Value1.GetType());
        System.out.println(Value2.GetType());
        System.out.println(Value1.GetType().Compare (DataType.Void));
        System.out.println(Value2.GetType().Compare (DataType.Void));

        if (Value1.GetType ().Compare (DataType.Void)) this.ValueType = Value2.GetType ();
        else if (Value2.GetType ().Compare (DataType.Void)) this.ValueType = Value2.GetType (); //? TODO: Hmm. Same as above but well works
        else this.ValueType = Value1.GetType ();

        System.out.println (this.ValueType);
        System.out.println("\n");
    }

    public static JasminCondition Equals (JasminPassable Value1, JasminPassable Value2)
    {
        return new JasminCondition (Value1, Value2, ConditionType.Equals);
    }

    public static JasminCondition NotEquals (JasminPassable Value1, JasminPassable Value2)
    {
        return new JasminCondition (Value1, Value2, ConditionType.NotEquals);
    }

    public static JasminCondition LessThan (JasminPassable Value1, JasminPassable Value2)
    {
        return new JasminCondition (Value1, Value2, ConditionType.LessThan);
    }

    public static JasminCondition LessThanEquals (JasminPassable Value1, JasminPassable Value2)
    {
        return new JasminCondition (Value1, Value2, ConditionType.LessThanEquals);
    }

    public static JasminCondition GreaterThan (JasminPassable Value1, JasminPassable Value2)
    {
        return new JasminCondition (Value1, Value2, ConditionType.GreaterThan);
    }

    public static JasminCondition GreaterThanEquals (JasminPassable Value1, JasminPassable Value2)
    {
        return new JasminCondition (Value1, Value2, ConditionType.GreaterThanEquals);
    }

    public JasminPassable GetValue1 ()
    {
        return Value1;
    }

    public JasminPassable GetValue2 ()
    {
        return Value2;
    }

    public ConditionType GetType ()
    {
        return Type;
    }

    public DataType GetValueType ()
    {
        return ValueType;
    }

}
