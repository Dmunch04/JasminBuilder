package me.munchii.Jasmin.instruction;

import me.munchii.Jasmin.instruction.type.MethodInvokationInstructionType;
import me.munchii.Jasmin.type.JasminType;
import me.munchii.Jasmin.type.ReferenceType;
import me.munchii.Jasmin.util.MethodSpec;

public class MethodInvokationInstruction implements IJasminInstruction {
    private final MethodInvokationInstructionType type;
    private final String methodSpec;

    public MethodInvokationInstruction(MethodInvokationInstructionType type, ReferenceType classReference, String methodName, JasminType returnType, JasminType... paramTypes) {
        this(type, MethodSpec.makeMethodSpec(classReference, methodName, returnType, paramTypes));
    }

    public MethodInvokationInstruction(MethodInvokationInstructionType type, String methodSpec) {
        this.type = type;
        this.methodSpec = methodSpec;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(type.getRepresentation()).append(" ").append(methodSpec);
    }
}
