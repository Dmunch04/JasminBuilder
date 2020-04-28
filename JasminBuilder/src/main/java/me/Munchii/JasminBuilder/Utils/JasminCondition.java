package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Types.ConditionType;

public class JasminCondition
{

    private JasminPassable Value1;
    private JasminPassable Value2;
    private ConditionType Type;

    public JasminCondition (JasminPassable Value1, JasminPassable Value2, ConditionType Type)
    {
        this.Value1 = Value1;
        this.Value2 = Value2;
        this.Type = Type;
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

}
