package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * The `JasminValue` class represents a single anonymous value in Jasmin
 */
public class JasminValue implements Builder, JasminPassable
{

    private Object Value;
    private DataType Type;

    /**
     * @param Value The value
     * @param Type The data type of the value
     */
    public JasminValue (Object Value, DataType Type)
    {
        this.Value = Value;
        this.Type = Type;
    }

    @Override
    public String ToOutputString ()
    {
        if (Value instanceof String)
        {
            return String.format ("\"%s\"", Value.toString ());
        }

        else if (Value == null)
        {
            return "";
        }

        else if (Value == Boolean.TRUE)
        {
            return "1";
        }

        else if (Value == Boolean.FALSE)
        {
            return "0";
        }

        return Value.toString ();
    }

    @Override
    public List<JasminStatement> PushToStack ()
    {
        return asList (Helper.PushValueToStack (this));
    }

    /**
     * @return The value
     */
    public Object GetValue ()
    {
        return Value;
    }

    @Override
    public DataType GetType ()
    {
        return Type;
    }

}
