package me.munchii.Jasmin.method;

import me.munchii.Jasmin.IWritable;
import me.munchii.Jasmin.instruction.IJasminInstruction;
import me.munchii.Jasmin.type.IDataType;

import java.util.List;

public class ConstructorMethod implements IWritable, InstructionAcceptor<ConstructorMethod> {
    private final int stackLimit = 100;

    // how do we know which fields to set
    private List<IDataType> paramTypes;

    @Override
    public ConstructorMethod addInstruction(IJasminInstruction... instruction) {
        return null;
    }

    @Override
    public ConstructorMethod addInstruction(IJasminInstruction instruction) {
        return null;
    }

    @Override
    public ConstructorMethod addInstructions(IJasminInstruction... instructions) {
        return null;
    }

    @Override
    public ConstructorMethod addInstructions(List<IJasminInstruction> instructions) {
        return null;
    }

    @Override
    public ConstructorMethod removeInstruction(IJasminInstruction instruction) {
        return null;
    }

    @Override
    public List<IJasminInstruction> getInstructions() {
        return null;
    }

    @Override
    public void write(StringBuilder builder) {

    }
}
