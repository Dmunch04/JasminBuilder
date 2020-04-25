package me.Munchii.JasminBuilder.Methods;

import me.Munchii.JasminBuilder.Statements.JasminStatement;

import java.util.ArrayList;
import java.util.List;

public class JasminScope
{

    private List<JasminStatement> Statements;

    public JasminScope ()
    {
        this.Statements = new ArrayList<JasminStatement> ();
    }

    public JasminScope AddStatement (JasminStatement Statement)
    {
        Statements.add (Statement);
        return this;
    }

    public JasminScope AddStatements (List<JasminStatement> StatementList)
    {
        Statements.addAll (StatementList);
        return this;
    }

    public List<JasminStatement> GetStatements ()
    {
        return Statements;
    }

}
