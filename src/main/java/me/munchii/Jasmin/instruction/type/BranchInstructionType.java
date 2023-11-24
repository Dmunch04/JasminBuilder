package me.munchii.Jasmin.instruction.type;

public enum BranchInstructionType {
    // Branch always
    GOTO("goto"),

    // Branch always (wide index)
    GOTO_W("goto_w"),

    // Branch if reference comparison succeeds
    IF_REFERENCE_COMPARE_EQUALS("if_acmpeq"),
    IF_REFERENCE_COMPARE_NOT_EQUALS("if_acmne"),

    // Branch if int comparison succeeds
    IF_INTEGER_COMPARE_EQUALS("if_icmpeq"),
    IF_INTEGER_COMPARE_NOT_EQUALS("if_icmpne"),
    IF_INTEGER_COMPARE_LESS_THAN("if_icmplt"),
    IF_INTEGER_COMPARE_LESS_EQUALS("if_icmple"),
    IF_INTEGER_COMPARE_GREATER_THAN("if_icmpgt"),
    IF_INTEGER_COMPARE_GREATER_EQUALS("if_icmpge"),

    // Branch if int comparison with zero succeeds
    IF_EQUALS("ifeq"),
    IF_NOT_EQUALS("ifne"),
    IF_LESS_THAN("iflt"),
    IF_LESS_EQUALS("ifle"),
    IF_GREATER_THAN("ifgt"),
    IF_GREATER_EQUALS("ifge"),

    // Branch if reference not null
    IF_NON_NULL("ifnonnull"),

    // Branch if reference is null
    IF_NULL("ifnull"),

    // Jump subroutine
    JUMP_SUBROUTINE("jsr"),

    // Jump subroutine (wide index)
    JUMP_SUBROUTINE_W("jsr_w");

    private final String representation;

    BranchInstructionType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
