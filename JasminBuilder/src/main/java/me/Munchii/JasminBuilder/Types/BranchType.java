package me.Munchii.JasminBuilder.Types;

public enum BranchType
{

    // Branch always
    Goto ("goto"),

    // Branch always (wide index)
    GotoW ("goto_w"),

    // Branch if reference comparison succeeds
    IfReferenceCompareEquals ("if_acmpeq"),
    IfReferenceCompareNotEquals ("if_acmne"),

    // Branch if int comparison succeeds
    IfIntegerCompareEquals ("if_icmpeq"),
    IfIntegerCompareNotEquals ("if_icmpne"),
    IfIntegerCompareGreaterEquals ("if_icmpge"),
    IfIntegerCompareGreaterThan ("if_icmpgt"),
    IfIntegerCompareLessEquals ("if_icmple"),
    IfIntegerCompareLessThan ("if_icmplt"),

    // Branch if int comparison with zero succeeds
    IfEquals ("ifeq"),
    IfNotEquals ("ifne"),
    IfGreaterEquals ("ifge"),
    IfGreaterThan ("ifgt"),
    IfLessEquals ("ifle"),
    IfLessThan ("iflt"),

    // Branch if reference not null
    IfNonNull ("ifnonnull"),

    // Branch if reference is null
    IfNull ("ifnull"),

    // Jump subroutine
    JumpSubroutine ("jsr"),

    // Jump subroutine (wide index
    JumpSubroutineW ("jsr_w");

    private String Representation;

    private BranchType (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
