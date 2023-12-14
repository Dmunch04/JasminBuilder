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

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConstructorMethod implements IWritable, InstructionAcceptor<ConstructorMethod> {
    // TODO: maybe constructor method should just extend JasminMethod?

    public final JasminClass parent;
    public final EnumSet<MethodAccessSpec> accessSpec;
    public final String methodName = "<init>";
    public final ReturnableType returnType = ReturnableType.VOID;
    public final List<ValueType> paramTypes;

    private List<IJasminInstruction> instructions;

    private int stackLimit = 100;
    private int localsLimit = 100;
    private boolean autoLimits = true;

    public ConstructorMethod(JasminClass parent, EnumSet<MethodAccessSpec> accessSpec, List<ValueType> paramTypes) {
        this.parent = parent;
        this.accessSpec = accessSpec;
        this.paramTypes = paramTypes;

        this.instructions = new ArrayList<>();

        parent.registerConstructor(this);
    }

    public String getDescriptor() {
        return getDescriptor(false);
    }

    public String getDescriptor(boolean includeMethodName) {
        StringBuilder builder = new StringBuilder();
        if (includeMethodName) {
            builder.append(methodName);
        }

        builder.append("(");
        paramTypes.forEach(param -> builder.append(param.getRepresentation()));
        builder.append(")");
        builder.append(returnType.getRepresentation());

        return builder.toString();
    }

    public String getSignature() {
        StringBuilder builder = new StringBuilder();

        builder.append(".method ");
        accessSpec.forEach(spec -> builder.append(spec.getValue()).append(" "));
        builder.append(getDescriptor(true));

        return builder.toString();
    }

    public ConstructorMethod initSuper() {
        addInstruction(new Instruction(JasminInstructions.LOAD_REFERENCE_FROM_LOCAL_VARIABLE_0));
        addInstruction(new MethodInvokationInstruction(
                MethodInvokationInstructionType.INVOKE_VIRTUAL,
                MethodSpec.makeMethodSpec(new ReferenceType(parent.superClass), "<init>", ReturnableType.VOID, List.of()),
                paramTypes.size()
        ));

        return this;
    }

    // TODO: init fields?
    public ConstructorMethod initField(JasminField field, JasminValue value) {
        // check if parent even has field?
        // maybe constant value?

        return this;
    }

    public ConstructorMethod returnEnd() {
        instructions.add(new Instruction(JasminInstructions.RETURN));

        return this;
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
        if (autoLimits) {
            localsLimit = paramTypes.size() /*+ localVariableMap.size()*/ + 1;
            stackLimit = calculateMaxStack();
        }

        builder.append(getSignature()).append("\n")
                .append("\t").append(".limit stack ").append(stackLimit).append("\n")
                .append("\t").append(".limit locals ").append(localsLimit).append("\n");

        instructions.forEach(instruction -> {
            builder.append("\t");
            instruction.write(builder);
            builder.append("\n");
        });

        /*
        blocks.forEach((label, jasminBlock) -> {
            builder.append(label).append(":").append("\n");
            jasminBlock.getInstructions().forEach(instruction -> {
                builder.append("\t");
                instruction.write(builder);
                builder.append("\n");
            });
        });
         */

        builder.append(".end method");
    }

    public int getStackLimit() {
        return stackLimit;
    }

    public int getLocalsLimit() {
        return localsLimit;
    }

    public ConstructorMethod setStackLimit(int limit) {
        stackLimit = limit;

        return this;
    }

    public ConstructorMethod setLocalsLimit(int limit) {
        localsLimit = limit;

        return this;
    }

    public ConstructorMethod setAutoLimits(boolean enabled) {
        autoLimits = enabled;

        return this;
    }

    public int calculateMaxStack() {
        AtomicInteger highestStack = new AtomicInteger();
        AtomicInteger currentStack = new AtomicInteger();

        instructions.forEach(instruction -> {
            currentStack.addAndGet(instruction.getStackChange());

            if (currentStack.get() > highestStack.get()) {
                highestStack.set(currentStack.get());
            }
        });

        /*
        blocks.forEach((label, jasminBlock) -> {
            jasminBlock.getInstructions().forEach(instruction -> {
                currentStack.addAndGet(instruction.getStackChange());

                if (currentStack.get() > highestStack.get()) {
                    highestStack.set(currentStack.get());
                }
            });
        });
         */

        return highestStack.get();
    }
}
