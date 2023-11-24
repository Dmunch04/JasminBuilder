package me.munchii.Jasmin.method;

import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.value.JasminValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JasminBlock implements InstructionAcceptor<JasminBlock> {
    // TODO thoughts: is there a better way to write "blocks" than in a separate class? because this way it may confuse,
    // TODO in relation to if the user uses the variable system or other similar systems

    // this is temporary solution i guess. im not a fan, since i actually liked the idea of being able to reuse blocks
    private final JasminMethod method;
    private final String label;
    private final List<IJasminInstruction> instructions;

    public JasminBlock(JasminMethod method, String label) {
        this.method = method;
        this.label = label;
        this.instructions = new ArrayList<>();
    }

    public JasminBlock declareVariable(String name, JasminValue value) {
        int variableIndex = method.getCurrentVariableIndex();

        LocalVariable variable = new LocalVariable(name, variableIndex, value);
        addInstructions(variable.declare());

        method.localVariableMap.put(name, variable);

        return this;
    }

    @Override
    public JasminBlock addInstruction(IJasminInstruction... instruction) {
        instructions.addAll(Arrays.stream(instruction).toList());

        return this;
    }

    @Override
    public JasminBlock addInstruction(IJasminInstruction instruction) {
        instructions.add(instruction);

        return this;
    }

    @Override
    public JasminBlock addInstructions(IJasminInstruction... instructions) {
        return addInstruction(instructions);
    }

    @Override
    public JasminBlock addInstructions(List<IJasminInstruction> instructions) {
        instructions.forEach(this::addInstruction);

        return this;
    }

    @Override
    public JasminBlock removeInstruction(IJasminInstruction instruction) {
        instructions.remove(instruction);

        return this;
    }

    public JasminMethod getMethod() {
        return method;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public List<IJasminInstruction> getInstructions() {
        return instructions;
    }
}
