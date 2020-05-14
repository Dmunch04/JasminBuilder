package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.DataTypes.ReferenceType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Types.FieldManipulationType;
import me.Munchii.JasminBuilder.Types.MethodInvokationType;

public class PrintInstruction implements JasminInstruction
{

    private final JasminPassable Value;

    public PrintInstruction (JasminPassable Value)
    {
        this.Value = Value;
    }

    @Override
    public void Write (JasminMethod Method)
    {
        // Printing requires stack +2
        Method.AddFieldManipulationStatement (FieldManipulationType.GetStatic, "java/lang/System/out", new ReferenceType ("java/io/PrintStream", true))
                .AddValue (Value)
                .AddMethodInvokationStatement (MethodInvokationType.InvokeVirtual, "java/io/PrintStream/println", DataType.Void, ReferenceType.MakeReferenceInstance (Value.GetType ()))
                .AddStackLimit (2);
    }

    @Override
    public void Write (JasminBlock Block)
    {
        // Printing require stack +2
        Block.AddFieldManipulationStatement (FieldManipulationType.GetStatic, "java/lang/System/out", new ReferenceType ("java/io/PrintStream", true))
                .AddValue (Value)
                .AddMethodInvokationStatement (MethodInvokationType.InvokeVirtual, "java/io/PrintStream/println", DataType.Void, ReferenceType.MakeReferenceInstance (Value.GetType ()))
                .AddStackLimit (2);
    }

}
