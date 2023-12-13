package me.munchii.Jasmin.statement;

import me.munchii.Jasmin.instruction.Instruction;
import me.munchii.Jasmin.instruction.JasminInstructions;
import me.munchii.Jasmin.method.JasminMethod;
import me.munchii.Jasmin.type.JavaStd;
import me.munchii.Jasmin.type.PrimitiveType;
import me.munchii.Jasmin.util.MethodSpec;
import me.munchii.Jasmin.value.IValueContainer;

import java.util.Collections;

public class PrintStatement implements Statement {
    public static void print(JasminMethod method, IValueContainer value) {
        method.addInstruction(new Instruction(JasminInstructions.GET_STATIC, "java/lang/System/out", JavaStd.JAVA_PRINT_STREAM_INSTANCE.getRepresentation()));
        method.addInstruction(value.pushValue());
        method.addInstruction(new Instruction(JasminInstructions.INVOKE_VIRTUAL, MethodSpec.makeMethodSpec(JavaStd.JAVA_PRINT_STREAM_REFERENCE, "print", PrimitiveType.VOID_TYPE, Collections.singletonList(value.getValue().getValueType()))));
    }

    public static void println(JasminMethod method, IValueContainer value) {
        method.addInstruction(new Instruction(JasminInstructions.GET_STATIC, "java/lang/System/out", JavaStd.JAVA_PRINT_STREAM_INSTANCE.getRepresentation()));
        method.addInstruction(value.pushValue());
        method.addInstruction(new Instruction(JasminInstructions.INVOKE_VIRTUAL, MethodSpec.makeMethodSpec(JavaStd.JAVA_PRINT_STREAM_REFERENCE, "println", PrimitiveType.VOID_TYPE, Collections.singletonList(value.getValue().getValueType()))));
    }

    public static void printfln(JasminMethod method, IValueContainer value) {
        method.addInstruction(new Instruction(JasminInstructions.GET_STATIC, "java/lang/System/out", JavaStd.JAVA_PRINT_STREAM_INSTANCE.getRepresentation()));
        method.addInstruction(value.pushValue());
        method.addInstruction(new Instruction(JasminInstructions.INVOKE_VIRTUAL, MethodSpec.makeMethodSpec(JavaStd.JAVA_PRINT_STREAM_REFERENCE, "printfln", PrimitiveType.VOID_TYPE, Collections.singletonList(value.getValue().getValueType()))));
    }

    @Override
    public void writeToMethod(JasminMethod method) {

    }
}
