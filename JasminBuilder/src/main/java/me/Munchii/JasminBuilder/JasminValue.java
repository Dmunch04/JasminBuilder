package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Types.DataType;

public class JasminValue
{

    private Object Value;
    private DataType Type;

    public JasminValue (Object Value)
    {
        this (Value, DataType.Void);
    }

    public JasminValue (Object Value, DataType Type)
    {
        this.Value = Value;
        this.Type = Type;
    }

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

        return Value.toString ();
    }

    public Object GetValue ()
    {
        return Value;
    }

    public DataType GetType ()
    {
        return Type;
    }

}
