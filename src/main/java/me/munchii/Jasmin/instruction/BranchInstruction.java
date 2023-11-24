package me.munchii.Jasmin.instruction;

import me.munchii.Jasmin.instruction.type.BranchInstructionType;

public class BranchInstruction implements IJasminInstruction {
    private final BranchInstructionType type;
    private final String label;

    public BranchInstruction(BranchInstructionType type, String label) {
        this.type = type;
        this.label = label;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(type.getRepresentation()).append(" ").append(label);
    }
}
