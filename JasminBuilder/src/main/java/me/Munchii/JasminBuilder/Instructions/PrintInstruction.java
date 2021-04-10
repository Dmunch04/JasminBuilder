package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.DataTypes.ReferenceType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Types.FieldManipulationType;
import me.Munchii.JasminBuilder.Types.MethodInvocationType;
import me.Munchii.JasminBuilder.Types.PrintType;

public class PrintInstruction implements JasminInstruction {

    private final JasminPassable value;
    private final DataType valueType;
    private final String javaPrintMethod;

    public PrintInstruction(JasminPassable value) {
        this(value, PrintType.PRINTLN);
    }

    public PrintInstruction(JasminPassable value, PrintType printType) {
        this.value = value;

        if (value.getType().isReference()) this.valueType = ReferenceType.makeReferenceInstance(value.getType());
        else this.valueType = value.getType();

        switch (printType) {
            case PRINT: this.javaPrintMethod = "java/io/PrintStream/print"; break;
            default:
            case PRINTLN: this.javaPrintMethod = "java/io/PrintStream/println"; break;
            case PRINTFLN: this.javaPrintMethod = "java/io/PrintStream/printfln"; break;
        }
    }

    @Override
    public void write(JasminMethod method) {
        // Printing requires stack +2
        method.addFieldManipulationStatement(FieldManipulationType.GET_STATIC, "java/lang/System/out", new ReferenceType("java/io/PrintStream", true))
                .addValue(value)
                .addMethodInvocationStatement(MethodInvocationType.INVOKE_VIRTUAL, this.javaPrintMethod, DataType.VOID, valueType)
                .addStackLimit(2);
    }

    @Override
    public void write(JasminBlock block) {
        // Printing require stack +2
        block.addFieldManipulationStatement(FieldManipulationType.GET_STATIC, "java/lang/System/out", new ReferenceType("java/io/PrintStream", true))
                .addValue(value)
                .addMethodInvocationStatement(MethodInvocationType.INVOKE_VIRTUAL, this.javaPrintMethod, DataType.VOID, valueType)
                .addStackLimit(2);
    }

}
