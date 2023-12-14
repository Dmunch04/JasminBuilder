package me.munchii.Jasmin.instruction;

public class IntegerIncrementInstruction implements IJasminInstruction {
    private final int variableIndex;
    private final int value;

    public IntegerIncrementInstruction(int variableIndex, int value) {
        this.variableIndex = variableIndex;
        this.value = value;
    }

    @Override
    public void write(StringBuilder builder) {
        builder.append("iinc ").append(variableIndex).append(" ").append(value);
    }

    @Override
    public int getStackChange() {
        return JasminInstructions.INCREMENT.getStackChange();
    }
}
