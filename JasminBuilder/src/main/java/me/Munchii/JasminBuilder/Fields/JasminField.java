package me.Munchii.JasminBuilder.Fields;

import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Types.DataType;

import java.util.ArrayList;
import java.util.List;

public class JasminField implements Builder
{

    private List<FieldAccessSpec> AccessSpec;
    private String FieldName;
    private DataType Descriptor;
    private JasminValue Value;

    public JasminField (FieldAccessSpec AccessSpec, String FieldName, DataType Descriptor)
    {
        this (AccessSpec, FieldName, Descriptor, null);
    }

    public JasminField (FieldAccessSpec AccessSpec, String FieldName, DataType Descriptor, JasminValue Value)
    {
        this.AccessSpec = new ArrayList<FieldAccessSpec> ();
        this.AccessSpec.add (AccessSpec);
        this.FieldName = FieldName;
        this.Descriptor = Descriptor;
        this.Value = Value;
    }

    @Override
    public String ToOutputString ()
    {
        StringBuilder Builder = new StringBuilder ();
        Builder.append (".field").append (" ");
        AccessSpec.forEach (Spec -> Builder.append (Spec.GetRepresentation ()).append (" "));
        Builder.append (FieldName).append (" ");
        Builder.append (Descriptor.GetRepresentation ());

        if (Value != null)
            Builder.append (" = ").append (Value.ToOutputString ());

        return Builder.toString ();
    }

    // ========================
    // Getters & Setters
    // ========================

    public List<FieldAccessSpec> GetAccessSpec ()
    {
        return AccessSpec;
    }

    public JasminField AddAccessSpec (FieldAccessSpec AccessSpec)
    {
        if (!this.AccessSpec.contains (AccessSpec))
            this.AccessSpec.add (AccessSpec);

        return this;
    }

    public JasminField RemoveAccessSpec (FieldAccessSpec AccessSpec)
    {
        this.AccessSpec.remove (AccessSpec);

        return this;
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
