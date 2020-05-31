package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.DataTypes.ReferenceType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Types.FieldManipulationType;
import me.Munchii.JasminBuilder.Types.MethodInvocationType;

public class PrintInstruction implements JasminInstruction {

    private final JasminPassable value;
    private final DataType valueType;

    public PrintInstruction(JasminPassable value) {
        this.value = value;

        if (value.getType().isReference()) this.valueType = ReferenceType.makeReferenceInstance(value.getType());
        else this.valueType = value.getType();
    }

    @Override
    public void write(JasminMethod method) {
        // Printing requires stack +2
        method.addFieldManipulationStatement(FieldManipulationType.GET_STATIC, "java/lang/System/out", new ReferenceType("java/io/PrintStream", true))
                .addValue(value)
                .addMethodInvocationStatement(MethodInvocationType.INVOKE_VIRTUAL, "java/io/PrintStream/println", DataType.VOID, valueType)
                .addStackLimit(2);
    }

    @Override
    public void write(JasminBlock block) {
        // Printing require stack +2
        block.addFieldManipulationStatement(FieldManipulationType.GET_STATIC, "java/lang/System/out", new ReferenceType("java/io/PrintStream", true))
                .addValue(value)
                .addMethodInvocationStatement(MethodInvocationType.INVOKE_VIRTUAL, "java/io/PrintStream/println", DataType.VOID, valueType)
                .addStackLimit(2);
    }

}
