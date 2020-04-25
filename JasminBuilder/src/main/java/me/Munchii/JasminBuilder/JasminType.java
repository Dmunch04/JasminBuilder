package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Types.DataType;

public class JasminType
{

    private final String Descriptor;

    /**
     * @param Type The data type
     */
    public JasminType (DataType Type)
    {
        this (Type.GetRepresentation ());
    }

    private JasminType (String Descriptor)
    {
        this.Descriptor = Descriptor;
    }

    /**
     * @param Class The class spec
     * @return A new jasmin type
     */
    public static JasminType CustomClass (String Class)
    {
        return new JasminType ("L" + Class);
    }

    /**
     * @return The type descriptor
     */
    public String GetDescriptor ()
    {
        return Descriptor;
    }

}
