package me.Munchii.JasminBuilder.Statements;

public class CommentStatement implements JasminStatement {

    private final String comment;

    public CommentStatement(String s) {
        this.comment = s;
    }

    @Override
    public String toOutputString() {
        return "; " + comment;
    }

}
