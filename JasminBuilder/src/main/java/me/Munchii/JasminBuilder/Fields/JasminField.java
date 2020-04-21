package me.Munchii.JasminBuilder.Fields;

import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Types.DataType;

public class JasminField implements Builder
{

    private FieldAccessSpec AccessSpec;
    private String FieldName;
    private DataType Descriptor;
    private JasminValue Value;

    public JasminField (FieldAccessSpec AccessSpec, String FieldName, DataType Descriptor)
    {
        this (AccessSpec, FieldName, Descriptor, null);
    }

    public JasminField (FieldAccessSpec AccessSpec, String FieldName, DataType Descriptor, JasminValue Value)
    {
        this.AccessSpec = AccessSpec;
        this.FieldName = FieldName;
        this.Descriptor = Descriptor;
        this.Value = Value;
    }

    @Override
    public String ToOutputString ()
    {
        StringBuilder Builder = new StringBuilder ();
        Builder.append (".field").append (" ");
        Builder.append (AccessSpec.GetRepresentation ()).append (" ");
        Builder.append (FieldName).append (" ");
        Builder.append (Descriptor.GetRepresentation ());

        if (Value != null)
            Builder.append (" = ").append (Value.ToOutputString ());

        return Builder.toString ();
    }

    // ========================
    // Getters & Setters
    // ========================

    public FieldAccessSpec GetAccessSpec ()
    {
        return AccessSpec;
    }

    public String GetFieldName ()
    {
        return FieldName;
    }

    public DataType GetDescriptor ()
    {
        return Descriptor;
    }

    public JasminValue GetValue ()
    {
        return Value;
    }

}
