package me.munchii.Jasmin.method;

import me.munchii.Jasmin.IWritable;
import me.munchii.Jasmin.classes.JasminClass;
import me.munchii.Jasmin.field.JasminField;
import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.instruction.Instruction;
import me.munchii.Jasmin.instruction.JasminInstructions;
import me.munchii.Jasmin.instruction.MethodInvokationInstruction;
import me.munchii.Jasmin.instruction.type.MethodInvokationInstructionType;
import me.munchii.Jasmin.type.ReferenceType;
import me.munchii.Jasmin.type.ReturnableType;
import me.munchii.Jasmin.type.ValueType;
import me.munchii.Jasmin.util.MethodSpec;
import me.munchii.Jasmin.value.JasminValue;

import java.util.EnumSet;
import java.util.List;

public class ConstructorMethod implements IWritable, InstructionAcceptor<ConstructorMethod> {
    public final JasminClass parent;
    public final EnumSet<MethodAccessSpec> accessSpec;
    public final String methodName = "<init>";
    public final ReturnableType returnType = ReturnableType.VOID;
    public final List<ValueType> paramTypes;

    private List<IJasminInstruction> instructions;

    private final int stackLimit = 100;

    public ConstructorMethod(JasminClass parent, EnumSet<MethodAccessSpec> accessSpec, List<ValueType> paramTypes) {
        this.parent = parent;
        this.accessSpec = accessSpec;
        this.paramTypes = paramTypes;

        parent.registerConstructor(this);
    }

    public ConstructorMethod initSuper() {
        addInstruction(new Instruction(JasminInstructions.LOAD_REFERENCE_FROM_LOCAL_VARIABLE_0));
        addInstruction(new MethodInvokationInstruction(
                MethodInvokationInstructionType.INVOKE_VIRTUAL,
                MethodSpec.makeMethodSpec(new ReferenceType(parent.superClass), "<init>", ReturnableType.VOID, List.of())
        ));

        return this;
    }

    // TODO: init fields?
    public ConstructorMethod initField(JasminField field, JasminValue value) {
        // check if parent even has field?
        // maybe constant value?

        return this;
    }

    public void returnEnd() {
        instructions.add(new Instruction(JasminInstructions.RETURN));
    }

    @Override
    public ConstructorMethod addInstruction(IJasminInstruction instruction) {
        instructions.add(instruction);

        return this;
    }

    @Override
    public ConstructorMethod addInstructions(List<IJasminInstruction> instructions) {
        this.instructions.addAll(instructions);

        return this;
    }

    @Override
    public ConstructorMethod removeInstruction(IJasminInstruction instruction) {
        instructions.remove(instruction);

        return this;
    }

    @Override
    public List<IJasminInstruction> getInstructions() {
        return instructions;
    }

    @Override
    public void write(StringBuilder builder) {

    }
}
