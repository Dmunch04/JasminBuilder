package me.munchii.Jasmin.method;

import me.munchii.Jasmin.IWritable;
import me.munchii.Jasmin.classes.JasminClass;
import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.instruction.Instruction;
import me.munchii.Jasmin.instruction.JasminInstructions;
import me.munchii.Jasmin.statement.Statement;
import me.munchii.Jasmin.type.ClassType;
import me.munchii.Jasmin.type.ReturnableType;
import me.munchii.Jasmin.type.ValueType;
import me.munchii.Jasmin.value.JasminValue;
import me.munchii.Jasmin.value.Returnable;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class JasminMethod implements IWritable, InstructionAcceptor<JasminMethod> {
    public final JasminClass parent;
    public final EnumSet<MethodAccessSpec> accessSpec;
    public final String methodName;
    public final ReturnableType returnType;
    public final List<ValueType> paramTypes;

    private List<IJasminInstruction> instructions;
    private Map<String, JasminBlock> blocks;

    protected final Map<String, LocalVariable> localVariableMap;
    private int variablePointer = 0;
    private int lastVariablePointer = -1;

    private int stackLimit = 100;
    private int localsLimit = 100;
    private boolean autoLimits = true;

    public JasminMethod(JasminClass parent, String methodName, EnumSet<MethodAccessSpec> accessSpec, ReturnableType returnType) {
        this(parent, methodName, accessSpec, returnType, new ArrayList<>());
    }

    public JasminMethod(JasminClass parent, String methodName, EnumSet<MethodAccessSpec> accessSpec, ReturnableType returnType, List<ValueType> paramTypes) {
        this.parent = parent;
        this.accessSpec = accessSpec;
        this.methodName = methodName;
        this.returnType = returnType;
        this.paramTypes = paramTypes;

        this.instructions = new ArrayList<>();
        this.blocks = new HashMap<>();
        this.localVariableMap = new HashMap<>();

        if (!accessSpec.contains(MethodAccessSpec.STATIC)) {
            //localVariableMap.put("this", new LocalVariable("this", 0, new JasminValue("this", new ReferenceType("this"))));
            // TODO: class or reference type?
            localVariableMap.put("this", new VariableReference("this", 0, new ClassType(parent.className)));
            variablePointer++;
        }

        AtomicInteger i = new AtomicInteger(1);
        paramTypes.forEach(param -> {
            String name = "arg" + i;
            localVariableMap.put(name, new VariableReference(name, variablePointer, param));
            variablePointer++;
            i.getAndIncrement();
        });

        parent.registerMethod(this);
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

    @Override
    public void write(StringBuilder builder) {
        if (autoLimits) {
            localsLimit = paramTypes.size() + localVariableMap.size();
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
    public JasminMethod addInstruction(IJasminInstruction instruction) {
        instructions.add(instruction);

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

    @Override
    public JasminMethod addStatement(Statement statement) {
        statement.write(this);

        return this;
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

    public JasminMethod returnEnd() {
        instructions.add(new Instruction(JasminInstructions.RETURN));

        return this;
    }

    public void returnEnd(Returnable returnable) {
        // push returnable value to stack
        // push correct return instruction
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

    public JasminMethod setAutoLimits(boolean enabled) {
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

        blocks.forEach((label, jasminBlock) -> {
            jasminBlock.getInstructions().forEach(instruction -> {
                currentStack.addAndGet(instruction.getStackChange());

                if (currentStack.get() > highestStack.get()) {
                    highestStack.set(currentStack.get());
                }
            });
        });

        return highestStack.get();
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
