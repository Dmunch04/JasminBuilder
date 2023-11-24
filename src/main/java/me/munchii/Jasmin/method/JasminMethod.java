package me.munchii.Jasmin.method;

import me.munchii.Jasmin.IWritable;
import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.type.ClassType;
import me.munchii.Jasmin.type.IDataType;
import me.munchii.Jasmin.value.JasminValue;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JasminMethod implements IWritable, InstructionAcceptor<JasminMethod> {
    public final MethodAccessSpec accessSpec;
    public final String methodName;
    public final IDataType returnType;
    public final List<IDataType> paramTypes;

    private List<IJasminInstruction> instructions;
    private Map<String, JasminBlock> blocks;

    protected final Map<String, LocalVariable> localVariableMap;
    private int variablePointer = 0;
    private int lastVariablePointer = -1;

    private int stackLimit = 100;
    private int localsLimit = 100;

    public JasminMethod(String methodName, MethodAccessSpec accessSpec, IDataType returnType) {
        this(methodName, accessSpec, returnType, new ArrayList<>());
    }

    public JasminMethod(String methodName, MethodAccessSpec accessSpec, IDataType returnType, List<IDataType> paramTypes) {
        this.accessSpec = accessSpec;
        this.methodName = methodName;
        this.returnType = returnType;
        this.paramTypes = paramTypes;

        this.instructions = new ArrayList<>();
        this.blocks = new HashMap<>();
        this.localVariableMap = new HashMap<>();

        if (!accessSpec.getValue().contains("static")) {
            //localVariableMap.put("this", new LocalVariable("this", 0, new JasminValue("this", new ReferenceType("this"))));
            localVariableMap.put("this", new VariableReference("this", 0, new ClassType("this")));
            variablePointer++;
        }

        AtomicInteger i = new AtomicInteger(1);
        paramTypes.forEach(param -> {
            String name = "arg" + i;
            localVariableMap.put(name, new VariableReference(name, variablePointer, param));
            variablePointer++;
            i.getAndIncrement();
        });
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
        builder.append(accessSpec.getValue()).append(" ");
        builder.append(getDescriptor(true));

        return builder.toString();
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(getSignature()).append("\n")
                .append("\t").append(".limit stack ").append(stackLimit).append("\n")
                .append("\t").append(".limit locals ").append(localsLimit).append("\n");

        instructions.forEach(instruction -> {
            builder.append("\t");
            instruction.write(builder);
            builder.append("\n");
        });

        blocks.forEach((label, jasminBlock) -> {
            builder.append(label).append(":").append("\n");
            jasminBlock.getInstructions().forEach(instruction -> {
                builder.append("\t");
                instruction.write(builder);
                builder.append("\n");
            });
        });

        builder.append(".end method");
    }

    @Override
    public JasminMethod addInstruction(IJasminInstruction... instruction) {
        instructions.addAll(List.of(instruction));

        return this;
    }

    @Override
    public JasminMethod addInstruction(IJasminInstruction instruction) {
        instructions.add(instruction);

        return this;
    }

    @Override
    public JasminMethod addInstructions(IJasminInstruction... instructions) {
        addInstruction(instructions);

        return this;
    }

    @Override
    public JasminMethod addInstructions(List<IJasminInstruction> instructions) {
        this.instructions.addAll(instructions);

        return this;
    }

    @Override
    public JasminMethod removeInstruction(IJasminInstruction instruction) {
        instructions.remove(instruction);

        return this;
    }

    @Override
    public List<IJasminInstruction> getInstructions() {
        return instructions;
    }

    /**
     * @param name Name of the variable. Only used internally
     * @param value Value of the variable
     * @return {@link JasminMethod} as builder
     */
    public JasminMethod declareVariable(String name, JasminValue value) {
        int variableIndex = getCurrentVariableIndex();

        LocalVariable variable = new LocalVariable(name, variableIndex, value);
        addInstructions(variable.declare());

        localVariableMap.put(name, variable);

        return this;
    }

    public JasminMethod registerBlock(JasminBlock block) {
        blocks.put(block.getLabel(), block);

        return this;
    }

    public JasminMethod removeBlock(String label) {
        blocks.remove(label);

        return this;
    }

    public int getStackLimit() {
        return stackLimit;
    }

    public int getLocalsLimit() {
        return localsLimit;
    }

    public JasminMethod setStackLimit(int limit) {
        stackLimit = limit;

        return this;
    }

    public JasminMethod setLocalsLimit(int limit) {
        localsLimit = limit;

        return this;
    }

    public int getCurrentVariableIndex() {
        return lastVariablePointer != -1 ? lastVariablePointer : variablePointer;
    }

    public Optional<LocalVariable> getVariable(String name) {
        if (localVariableMap.containsKey(name)) {
            return Optional.of(localVariableMap.get(name));
        }

        return Optional.empty();
    }

    public Optional<LocalVariable> getArg(int index) {
        return getVariable("arg" + index);
    }
}
