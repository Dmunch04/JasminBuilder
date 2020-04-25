package me.Munchii.JasminBuilder.Fields;

import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Statements.FieldManipulationStatement;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Types.FieldManipulationType;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class JasminField implements Builder, JasminPassable
{

    // TODO: Surely there must be a better way, than using the `Hook` method

    private List<FieldAccessSpec> AccessSpec;
    private String FieldName;
    private DataType Descriptor;
    private JasminValue Value;

    /**
     * @param AccessSpec The access modifiers for the field
     * @param FieldName The fields name. Used together with class name to access it later on
     * @param Descriptor The fields data type
     */
    public JasminField (FieldAccessSpec AccessSpec, String FieldName, DataType Descriptor)
    {
        this (AccessSpec, FieldName, Descriptor, null);
    }

    /**
     * @param AccessSpec The access modifiers for the field
     * @param FieldName The fields name. Used together with class name to access it later on
     * @param Descriptor The fields data type
     * @param Value The fields type
     */
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

    @Override
    public List<JasminStatement> PushToStack ()
    {
        if (Class == null)
            throw new IllegalArgumentException ("Field class is not set!");

        // TODO: How would we make field spec without knowing the class?
        // Like this?
        if (AccessSpec.contains (FieldAccessSpec.Static))
            return asList (new FieldManipulationStatement (FieldManipulationType.GetStatic, Helper.MakeFieldSpec (Class.GetClassName (), FieldName), Descriptor.GetRepresentation ()));

        return asList (new FieldManipulationStatement (FieldManipulationType.GetField, Helper.MakeFieldSpec (Class.GetClassName (), FieldName), Descriptor.GetRepresentation ()));
    }

    @Override
    public DataType GetType ()
    {
        return Descriptor;
    }

    // Like this?
    private JasminClass Class = null;
    public JasminField Hook (JasminClass Class)
    {
        this.Class = Class;
        return this;
    }
    public JasminField Dehook ()
    {
        this.Class = null;
        return this;
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
