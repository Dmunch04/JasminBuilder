package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.DataTypes.ReferenceType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Types.FieldManipulationType;
import me.Munchii.JasminBuilder.Types.MethodInvocationType;

public class PrintInstruction implements JasminInstruction
{

    private final JasminPassable Value;
    private final DataType ValueType;

    public PrintInstruction (JasminPassable Value)
    {
        this.Value = Value;

        if (Value.GetType ().IsReference ()) this.ValueType = ReferenceType.MakeReferenceInstance (Value.GetType ());
        else this.ValueType = Value.GetType ();
    }

    @Override
    public void Write (JasminMethod Method)
    {
        // Printing requires stack +2
        Method.AddFieldManipulationStatement (FieldManipulationType.GetStatic, "java/lang/System/out", new ReferenceType ("java/io/PrintStream", true))
                .AddValue (Value)
                .AddMethodInvokationStatement (MethodInvocationType.InvokeVirtual, "java/io/PrintStream/println", DataType.Void, ValueType)
                .AddStackLimit (2);
    }

    @Override
    public void Write (JasminBlock Block)
    {
        // Printing require stack +2
        Block.AddFieldManipulationStatement (FieldManipulationType.GetStatic, "java/lang/System/out", new ReferenceType ("java/io/PrintStream", true))
                .AddValue (Value)
                .AddMethodInvocationStatement(MethodInvocationType.InvokeVirtual, "java/io/PrintStream/println", DataType.Void, ValueType)
                .AddStackLimit (2);
    }

}
