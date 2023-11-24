package me.munchii.Jasmin.statement;

import me.munchii.Jasmin.instruction.Instruction;
import me.munchii.Jasmin.instruction.JasminInstructions;
import me.munchii.Jasmin.method.JasminMethod;
import me.munchii.Jasmin.type.JavaStd;
import me.munchii.Jasmin.type.ValueType;
import me.munchii.Jasmin.util.MethodSpec;
import me.munchii.Jasmin.value.IValueContainer;

public class PrintStatement implements Statement {
    public static void print(JasminMethod method, IValueContainer value) {
        method.addInstruction(new Instruction(JasminInstructions.GET_STATIC, "java/lang/System/out", JavaStd.JAVA_PRINT_STREAM_INSTANCE.getRepresentation()));
        method.addInstruction(value.pushValue());
        method.addInstruction(new Instruction(JasminInstructions.INVOKE_VIRTUAL, MethodSpec.makeMethodSpec(JavaStd.JAVA_PRINT_STREAM_REFERENCE, "print", ValueType.VOID_TYPE, value.getValue().getValueType())));
    }

    public static void println(JasminMethod method, IValueContainer value) {
        method.addInstruction(new Instruction(JasminInstructions.GET_STATIC, "java/lang/System/out", JavaStd.JAVA_PRINT_STREAM_INSTANCE.getRepresentation()));
        method.addInstruction(value.pushValue());
        method.addInstruction(new Instruction(JasminInstructions.INVOKE_VIRTUAL, MethodSpec.makeMethodSpec(JavaStd.JAVA_PRINT_STREAM_REFERENCE, "println", ValueType.VOID_TYPE, value.getValue().getValueType())));
    }

    public static void printfln(JasminMethod method, IValueContainer value) {
        method.addInstruction(new Instruction(JasminInstructions.GET_STATIC, "java/lang/System/out", JavaStd.JAVA_PRINT_STREAM_INSTANCE.getRepresentation()));
        method.addInstruction(value.pushValue());
        method.addInstruction(new Instruction(JasminInstructions.INVOKE_VIRTUAL, MethodSpec.makeMethodSpec(JavaStd.JAVA_PRINT_STREAM_REFERENCE, "printfln", ValueType.VOID_TYPE, value.getValue().getValueType())));
    }

    @Override
    public void writeToMethod(JasminMethod method) {

    }
}
