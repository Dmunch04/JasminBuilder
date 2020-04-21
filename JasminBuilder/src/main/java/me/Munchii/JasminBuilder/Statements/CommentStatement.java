package me.Munchii.JasminBuilder.Statements;

public class CommentStatement implements JasminStatement
{

    private String Comment;

    public CommentStatement (String Comment)
    {
        this.Comment = Comment;
    }

    @Override
    public String ToOutputString ()
    {
        return "; " + Comment;
    }

}
