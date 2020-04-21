package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Types.DataType;

public class JasminType
{

    private String Descriptor;

    public JasminType (DataType Type)
    {
        this (Type.GetRepresentation ());
    }

    private JasminType (String Descriptor)
    {
        this.Descriptor = Descriptor;
    }

    public static JasminType CustomClass (String Class)
    {
        return new JasminType ("L" + Class);
    }

    public String GetDescriptor ()
    {
        return Descriptor;
    }

}
