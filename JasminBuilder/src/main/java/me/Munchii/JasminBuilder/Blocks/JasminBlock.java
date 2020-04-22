package me.Munchii.JasminBuilder.Blocks;

import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.Statements.JasminStatement;

import java.util.ArrayList;
import java.util.List;

public class JasminBlock implements Builder
{

    private String Name; // Label
    private List<JasminStatement> Statements;

    public JasminBlock (String Name)
    {
        this.Name = Name;
        this.Statements = new ArrayList<JasminStatement> ();
    }

    @Override
    public String ToOutputString ()
    {
        StringBuilder Builder = new StringBuilder ();
        Builder.append (Name).append (":").append ("\n");

        for (JasminStatement Statement : Statements)
        {
            Builder.append ("\t").append (Statement.ToOutputString ()).append ("\n");
        }

        Builder.append ("\n");

        return Builder.toString ();
    }

    public JasminBlock AddStatement (JasminStatement Statement)
    {
        Statements.add (Statement);
        return this;
    }

}
