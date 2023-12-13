package me.munchii.Jasmin.method;

import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.value.JasminValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JasminBlock implements InstructionAcceptor<JasminBlock> {
    // TODO thoughts: is there a better way to write "blocks" than in a separate class? because this way it may confuse,
    // TODO in relation to if the user uses the variable system or other similar systems

    // no reusable blocks anymore. no reusable anything anymore
    private final JasminMethod parent;
    private final String label;
    private final List<IJasminInstruction> instructions;

    public JasminBlock(JasminMethod parent, String label) {
        this.parent = parent;
        this.label = label;
        this.instructions = new ArrayList<>();

        parent.registerBlock(this);
    }

    public JasminBlock declareVariable(String name, JasminValue value) {
        int variableIndex = parent.getCurrentVariableIndex();

        LocalVariable variable = new LocalVariable(name, variableIndex, value);
        addInstructions(variable.declare());

        parent.localVariableMap.put(name, variable);

        return this;
    }

    @Override
    public JasminBlock addInstruction(IJasminInstruction instruction) {
        instructions.add(instruction);

        return this;
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
        return parent;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public List<IJasminInstruction> getInstructions() {
        return instructions;
    }
}
