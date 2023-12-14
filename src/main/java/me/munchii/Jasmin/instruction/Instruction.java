package me.munchii.Jasmin.instruction;

public class Instruction implements IJasminInstruction {
    private final String instruction;
    private final int stackChange;

    public Instruction(JasminInstructions instruction, String... args) {
        this(instruction.getRepresentation() + " " + String.join(" ", args), instruction.getStackChange());
    }

    public Instruction(String rawInstruction, int stackChange) {
        this.instruction = rawInstruction.trim();
        this.stackChange = stackChange;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append(instruction);
    }

    @Override
    public int getStackChange() {
        return stackChange;
    }
}
