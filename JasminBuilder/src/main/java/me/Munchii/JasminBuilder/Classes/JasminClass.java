package me.Munchii.JasminBuilder.Classes;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JasminClass implements Builder
{

    private List<ClassAccessSpec> AccessSpec;
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
        this.AccessSpec = new ArrayList<ClassAccessSpec> ();
        this.AccessSpec.add (AccessSpec);
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
        AccessSpec.forEach (Spec -> Builder.append (Spec.GetRepresentation ()).append (" "));
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
            StringBuilder MethodComment = new StringBuilder ();
            MethodComment.append ("; Method: ");
            // Access specs
            Method.GetAccessSpec ().forEach (Spec -> MethodComment.append (Spec.GetRepresentation ()).append (" "));
            // Return type
            MethodComment.append (Helper.GetDataTypeName (Method.GetMethodReturnType ())).append (" ");
            // Method name
            MethodComment.append (Method.GetMethodName ()).append (" (");
            // Args
            String Args = String.join (", ", Method.GetArgs ().stream ().map (Helper::GetDataTypeName).collect (Collectors.toList ()));
            MethodComment.append (Args).append (");");

            Builder.append (MethodComment.toString ()).append ("\n");

            Builder.append (Method.ToOutputString ()).append ("\n").append ("\n");
        }

        Builder.deleteCharAt (Builder.length () - 1);

        return Builder.toString ();
    }

    // ========================
    // Getters & Setters
    // ========================

    public List<ClassAccessSpec> GetAccessSpec ()
    {
        return AccessSpec;
    }

    public JasminClass AddAccessSpec (ClassAccessSpec AccessSpec)
    {
        if (!this.AccessSpec.contains (AccessSpec))
            this.AccessSpec.add (AccessSpec);

        return this;
    }

    public JasminClass RemoveAccessSpec (ClassAccessSpec AccessSpec)
    {
        this.AccessSpec.remove (AccessSpec);

        return this;
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

    public JasminClass AddImplement (DataType Type)
    {
        return AddImplement (Type.GetRepresentation ());
    }

    public JasminClass AddImplement (String Class)
    {
        Implements.add (Class);

        return this;
    }

    public List<JasminField> GetFields ()
    {
        return Fields;
    }

    public JasminClass AddField (JasminField Field)
    {
        Fields.add (Field);
        Field.Hook (this);

        return this;
    }

    public List<JasminMethod> GetMethods ()
    {
        return Methods;
    }

    public JasminClass AddMethod (JasminMethod Method)
    {
        Methods.add (Method);

        return this;
    }

}
