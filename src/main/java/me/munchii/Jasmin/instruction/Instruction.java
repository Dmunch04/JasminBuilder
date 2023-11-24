package me.munchii.Jasmin.instruction;

public class Instruction implements IJasminInstruction {
    private final String instruction;

    public Instruction(JasminInstructions instruction, String... args) {
        this(instruction.getRepresentation() + " " + String.join(" ", args));
    }

    public Instruction(String rawInstruction) {
        this.instruction = rawInstruction.trim();
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(instruction);
    }
}
