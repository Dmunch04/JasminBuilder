package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Utils.Helper;

public class JasminValue
{

    private Object Value;
    private DataType Type;

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

    public JasminStatement PushToStack ()
    {
        return Helper.PushValueToStack (this);
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
