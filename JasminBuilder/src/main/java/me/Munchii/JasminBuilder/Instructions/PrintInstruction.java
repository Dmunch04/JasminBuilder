package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.DataTypes.ReferenceType;
import me.Munchii.JasminBuilder.DataTypes.VoidType;
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

        if (Value.GetType () == null)
            throw new IllegalArgumentException ("Value has no type specified. TIP: If it's a variable reference, specify it's type!");
    }

    @Override
    public void Write (JasminMethod Method)
    {
        // Printing requires stack +2
        Method.AddFieldManipulationStatement (FieldManipulationType.GetStatic, "java/lang/System/out", new ReferenceType ("java/io/PrintStream", true))
                //.AddStatements (Value.PushToStack ())
                .AddValue (Value)
                .AddMethodInvokationStatement (MethodInvokationType.InvokeVirtual, "java/io/PrintStream/println", new VoidType (), Value.GetType ())
                .AddStackLimit (2);
    }

    @Override
    public void Write (JasminBlock Block)
    {
        // Printing require stack +2
        Block.AddFieldManipulationStatement (FieldManipulationType.GetStatic, "java/lang/System/out", new ReferenceType ("java/io/PrintStream", true))
                .AddStatements (Value.PushToStack ()) //- this would still work, but not recommended i guess
                .AddValue (Value)
                .AddMethodInvokationStatement (MethodInvokationType.InvokeVirtual, "java/io/PrintStream/println", new VoidType (), Value.GetType ())
                .AddStackLimit (2);
    }

}
