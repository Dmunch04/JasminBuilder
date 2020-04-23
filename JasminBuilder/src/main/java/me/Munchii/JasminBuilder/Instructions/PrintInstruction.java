package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Types.FieldManipulationType;
import me.Munchii.JasminBuilder.Types.MethodInvokationType;
import me.Munchii.JasminBuilder.Utils.Helper;

public class PrintInstruction implements JasminInstruction
{

    private JasminPassable Value;

    public PrintInstruction (JasminPassable Value)
    {
        this.Value = Value;
    }

    @Override
    public void Write (JasminMethod Method)
    {
        // Printing requires stack +2
        Method.AddFieldManipulationStatement (FieldManipulationType.GetStatic, "java/lang/System/out", DataType.MakeCustomClassInstance ("java/io/PrintStream"))
                .AddStatements (Value.PushToStack ())
                .AddMethodInvokationStatement (MethodInvokationType.InvokeVirtual, "java/io/PrintStream/println", DataType.Void, DataType.StringInstance)
                .AddStackLimit (2);
    }

}
