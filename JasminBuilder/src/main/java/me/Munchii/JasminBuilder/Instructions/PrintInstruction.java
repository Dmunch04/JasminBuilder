package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Types.FieldManipulationType;
import me.Munchii.JasminBuilder.Types.MethodInvokationType;
import me.Munchii.JasminBuilder.Utils.Helper;

public class PrintInstruction implements JasminInstruction
{

    // TODO: Allow for local variables to be passed

    private JasminValue Value;
    private int VariableIndex;
    private DataType ValueType;

    public PrintInstruction (JasminValue Value)
    {
        this.Value = Value;
    }

    public PrintInstruction (int VariableIndex, DataType ValueType)
    {
        this.VariableIndex = VariableIndex;
        this.ValueType = ValueType;
    }

    @Override
    public void Write (JasminMethod Method)
    {
        // Printing requires stack +2
        Method.AddFieldManipulationStatement (FieldManipulationType.GetStatic, "java/lang/System/out", DataType.MakeCustomClassInstance ("java/io/PrintStream"))
                .AddStatement (Helper.PushValueToStack (Value))
                .AddMethodInvokationStatement (MethodInvokationType.InvokeVirtual, "java/io/PrintStream/println", DataType.Void, DataType.StringInstance)
                .AddStackLimit (2);
    }

}
