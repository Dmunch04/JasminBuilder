package me.munchii.Jasmin.instruction.type;

public enum BranchInstructionType {
    // Branch always
    GOTO("goto", 0),

    // Branch always (wide index)
    GOTO_W("goto_w", 0),

    // Branch if reference comparison succeeds
    IF_REFERENCE_COMPARE_EQUALS("if_acmpeq", -2),
    IF_REFERENCE_COMPARE_NOT_EQUALS("if_acmne", -2),

    // Branch if int comparison succeeds
    IF_INTEGER_COMPARE_EQUALS("if_icmpeq", -2),
    IF_INTEGER_COMPARE_NOT_EQUALS("if_icmpne", -2),
    IF_INTEGER_COMPARE_LESS_THAN("if_icmplt", -2),
    IF_INTEGER_COMPARE_LESS_EQUALS("if_icmple", -2),
    IF_INTEGER_COMPARE_GREATER_THAN("if_icmpgt", -2),
    IF_INTEGER_COMPARE_GREATER_EQUALS("if_icmpge", -2),

    // Branch if int comparison with zero succeeds
    IF_EQUALS("ifeq", -1),
    IF_NOT_EQUALS("ifne", -1),
    IF_LESS_THAN("iflt", -1),
    IF_LESS_EQUALS("ifle", -1),
    IF_GREATER_THAN("ifgt", -1),
    IF_GREATER_EQUALS("ifge", -1),

    // Branch if reference not null
    IF_NON_NULL("ifnonnull", -1),

    // Branch if reference is null
    IF_NULL("ifnull", -1),

    // Jump subroutine
    JUMP_SUBROUTINE("jsr", +1),

    // Jump subroutine (wide index)
    JUMP_SUBROUTINE_W("jsr_w", +1);

    private final String representation;
    private final int stackChange;

    BranchInstructionType(String representation, int stackChange) {
        this.representation = representation;
        this.stackChange = stackChange;
    }

    public String getRepresentation() {
        return representation;
    }

    public int getStackChange() {
        return stackChange;
    }
}
