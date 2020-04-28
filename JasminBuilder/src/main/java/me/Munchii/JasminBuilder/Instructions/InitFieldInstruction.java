package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.Fields.FieldAccessSpec;
import me.Munchii.JasminBuilder.Fields.JasminField;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Types.FieldManipulationType;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.stream.Collectors;

public class InitFieldInstruction implements JasminInstruction
{

    private final String Class;
    private final JasminField Field;
    private final JasminPassable Value;
    private final String Comment;

    public InitFieldInstruction (String Class, JasminField Field)
    {
        this (Class, Field, Field.GetValue ());
    }

    public InitFieldInstruction (JasminClass Class, JasminField Field)
    {
        this (Class, Field, Field.GetValue ());
    }

    public InitFieldInstruction (JasminClass Class, JasminField Field, JasminPassable Value)
    {
        this (Class.GetClassName (), Field, Value);
    }

    public InitFieldInstruction (String Class, JasminField Field, JasminPassable Value)
    {
        this.Field = Field;
        this.Value = Value;
        this.Class = Class;

        StringBuilder FieldComment = new StringBuilder ();
        FieldComment.append ("Field: ");
        Field.GetAccessSpec ().forEach (Spec -> FieldComment.append (Spec.GetRepresentation ()).append (" "));
        FieldComment.append (Helper.GetDataTypeName (Field.GetType ())).append (" ");
        // TODO: Find a way to represent value (`JasminPassable`)
        FieldComment.append (Field.GetFieldName ()).append (" = ").append (Value).append (";");
        this.Comment = FieldComment.toString ();
    }

    @Override
    public void Write (JasminMethod Method)
    {
        Method.AddComment (Comment);

        Method.AddStatements (Value.PushToStack ());

        if (Field.GetAccessSpec ().contains (FieldAccessSpec.Static))
        {
            Method.AddFieldManipulationStatement (FieldManipulationType.PutStatic, Helper.MakeFieldSpec (Class, Field.GetFieldName ()), Field.GetType ());
        }

        else
        {
            Method.AddFieldManipulationStatement (FieldManipulationType.PutField, Helper.MakeFieldSpec (Class, Field.GetFieldName ()), Field.GetType ());
        }
    }

    @Override
    public void Write (JasminBlock Block)
    {
        Block.AddComment (Comment);

        Block.AddStatements (Value.PushToStack ());

        if (Field.GetAccessSpec ().contains (FieldAccessSpec.Static))
        {
            Block.AddFieldManipulationStatement (FieldManipulationType.PutStatic, Helper.MakeFieldSpec (Class, Field.GetFieldName ()), Field.GetType ());
        }

        else
        {
            Block.AddFieldManipulationStatement (FieldManipulationType.PutField, Helper.MakeFieldSpec (Class, Field.GetFieldName ()), Field.GetType ());
        }
    }
}
