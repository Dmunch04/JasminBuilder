package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.Types.BranchType;

public class BranchStatement implements JasminStatement {

    private final BranchType type;
    private final String label;

    public BranchStatement(BranchType type, String s) {
        this.type = type;
        this.label = s;
    }

    @Override
    public String toOutputString() {
        return "; jump(" + this.type.name() + "):" + this.label + "\n\t" + type.getRepresentation() + " " + label;
    }

}
