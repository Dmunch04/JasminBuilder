package me.Munchii.JasminBuilder.Classes;

import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Types.DataType;

import java.util.ArrayList;
import java.util.List;

public class JasminClass implements Builder
{

    // TODO: Add constructor method for class

    private ClassAccessSpec AccessSpec;
    private String ClassName;
    private String Super;
    private List<String> Implements;

    private List<JasminField> Fields;
    private List<JasminMethod> Methods;

    public JasminClass (ClassAccessSpec AccessSpec, String ClassName)
    {
        this (AccessSpec, ClassName, DataType.Object.GetRepresentation ());
    }

    public JasminClass (ClassAccessSpec AccessSpec, String ClassName, String Super)
    {
        this.AccessSpec = AccessSpec;
        this.ClassName = ClassName;
        this.Super = Super;
        this.Implements = new ArrayList<String> ();
        this.Fields = new ArrayList<JasminField> ();
        this.Methods = new ArrayList<JasminMethod> ();
    }

    @Override
    public String ToOutputString ()
    {
        StringBuilder Builder = new StringBuilder ();
        Builder.append (".class").append (" ");
        Builder.append (AccessSpec.GetRepresentation ()).append (" ");
        Builder.append (ClassName).append ("\n");
        Builder.append (".super").append (" ");
        Builder.append (Super).append ("\n");

        for (String Implement : Implements)
        {
            Builder.append (".super").append (" ");
            Builder.append (Implement).append ("\n");
        }

        Builder.append ("\n");

        for (JasminField Field : Fields)
        {
            Builder.append (Field.ToOutputString ()).append ("\n");
        }

        if (!Fields.isEmpty ())
            Builder.append ("\n");

        for (JasminMethod Method : Methods)
        {
            Builder.append (Method.ToOutputString ()).append ("\n").append ("\n");
        }

        Builder.deleteCharAt (Builder.length () - 1);

        return Builder.toString ();
    }

    // ========================
    // Getters & Setters
    // ========================

    public ClassAccessSpec GetAccessSpec ()
    {
        return AccessSpec;
    }

    public String GetClassName ()
    {
        return ClassName;
    }

    public String GetSuper ()
    {
        return Super;
    }

    public List<String> GetImplements ()
    {
        return Implements;
    }

    public void AddImplement (DataType Type)
    {
        AddImplement (Type.GetRepresentation ());
    }

    public void AddImplement (String Class)
    {
        Implements.add (Class);
    }

    public List<JasminField> GetFields ()
    {
        return Fields;
    }

    public void AddField (JasminField Field)
    {
        Fields.add (Field);
    }

    public List<JasminMethod> GetMethods ()
    {
        return Methods;
    }

    public void AddMethod (JasminMethod Method)
    {
        Methods.add (Method);
    }

}
