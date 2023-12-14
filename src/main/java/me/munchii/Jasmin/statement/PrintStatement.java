package me.munchii.Jasmin.statement;

import me.munchii.Jasmin.instruction.Instruction;
import me.munchii.Jasmin.instruction.JasminInstructions;
import me.munchii.Jasmin.method.InstructionAcceptor;
import me.munchii.Jasmin.method.JasminMethod;
import me.munchii.Jasmin.type.JavaStd;
import me.munchii.Jasmin.type.ReturnableType;
import me.munchii.Jasmin.util.MethodSpec;
import me.munchii.Jasmin.value.IValueContainer;

import java.util.Collections;
import java.util.Locale;

public class PrintStatement implements Statement {
    public enum PrintType {
        PRINT,
        PRINTLN,
        PRINTFLN;
    }

    private final PrintType type;
    private final IValueContainer value;

    public PrintStatement(PrintType type, IValueContainer value) {
        this.type = type;
        this.value = value;
    }

    public static void print(JasminMethod method, IValueContainer value) {
        method.addInstruction(new Instruction(JasminInstructions.GET_STATIC, "java/lang/System/out", JavaStd.JAVA_PRINT_STREAM_INSTANCE.getRepresentation()));
        method.addInstruction(value.pushValue());
        method.addInstruction(new Instruction(JasminInstructions.INVOKE_VIRTUAL, MethodSpec.makeMethodSpec(JavaStd.JAVA_PRINT_STREAM_REFERENCE, "print", ReturnableType.VOID, Collections.singletonList(value.getValue().getValueType()))));
    }

    public static void println(JasminMethod method, IValueContainer value) {
        method.addInstruction(new Instruction(JasminInstructions.GET_STATIC, "java/lang/System/out", JavaStd.JAVA_PRINT_STREAM_INSTANCE.getRepresentation()));
        method.addInstruction(value.pushValue());
        method.addInstruction(new Instruction(JasminInstructions.INVOKE_VIRTUAL, MethodSpec.makeMethodSpec(JavaStd.JAVA_PRINT_STREAM_REFERENCE, "println", ReturnableType.VOID, Collections.singletonList(value.getValue().getValueType()))));
    }

    public static void printfln(JasminMethod method, IValueContainer value) {
        method.addInstruction(new Instruction(JasminInstructions.GET_STATIC, "java/lang/System/out", JavaStd.JAVA_PRINT_STREAM_INSTANCE.getRepresentation()));
        method.addInstruction(value.pushValue());
        method.addInstruction(new Instruction(JasminInstructions.INVOKE_VIRTUAL, MethodSpec.makeMethodSpec(JavaStd.JAVA_PRINT_STREAM_REFERENCE, "printfln", ReturnableType.VOID, Collections.singletonList(value.getValue().getValueType()))));
    }

    @Override
    public <T> void write(InstructionAcceptor<T> acceptor) {
        String printMethodName = type.toString().toLowerCase(Locale.ROOT);

        acceptor.addInstruction(new Instruction(JasminInstructions.GET_STATIC, "java/lang/System/out", JavaStd.JAVA_PRINT_STREAM_INSTANCE.getRepresentation()));
        acceptor.addInstruction(value.pushValue());
        acceptor.addInstruction(new Instruction(JasminInstructions.INVOKE_VIRTUAL, MethodSpec.makeMethodSpec(JavaStd.JAVA_PRINT_STREAM_REFERENCE, printMethodName, ReturnableType.VOID, Collections.singletonList(value.getValue().getValueType()))));
    }
}
