package me.Munchii.JasminBuilder.Blocks;

import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.JasminVariable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.VariableStatement;
import me.Munchii.JasminBuilder.Types.VariableType;

import java.util.ArrayList;
import java.util.List;

/**
 * JasminBlock represents a block in Jasmin. It has a label and a list of statements
 */
public class JasminBlock
{

    private final String Name;
    private final List<JasminStatement> Statements;

    /**
     * @param Name The label of the block
     */
    public JasminBlock (String Name)
    {
        this.Name = Name;
        this.Statements = new ArrayList<JasminStatement> ();
    }

    /**
     * @param Method The target method it should write the statements to
     */
    public void Write (JasminMethod Method)
    {
        for (JasminStatement Statement : Statements)
        {
            if (Statement instanceof VariableStatement)
            {
                VariableStatement Variable = (VariableStatement) Statement;
                switch (Variable.GetType ())
                {
                    case Declare: Method.DeclareVariable (Variable.GetVariable ());
                    case Store: Method.StoreVariable (Variable.GetVariable (), Variable.GetValue ());
                    case Load: Method.LoadVariable (Variable.GetName ());
                }

                continue;
            }

            Method.AddStatement (Statement);
        }
    }

    /**
     * @param Statement The statement which will be added to the blocks statements
     * @return The updated block
     */
    public JasminBlock AddStatement (JasminStatement Statement)
    {
        Statements.add (Statement);
        return this;
    }

    /**
     * @param StatementList A list of statements which will be added to the blocks statements
     * @return The updated block
     */
    public JasminBlock AddStatements (List<JasminStatement> StatementList)
    {
        Statements.addAll (StatementList);
        return this;
    }

    /**
     * @param Variable The variable to be declared
     * @return The updated block
     */
    public JasminBlock DeclareVariable (JasminVariable Variable)
    {
        Statements.add (new VariableStatement (VariableType.Declare, Variable));
        return this;
    }

    /**
     * @param Variable The variable the value should be stored in
     * @param Value The value the variable should store
     * @return The updated block
     */
    public JasminBlock StoreVariable (JasminVariable Variable, JasminPassable Value)
    {
        Statements.add (new VariableStatement (VariableType.Store, Variable, Value));
        return this;
    }

    /**
     * @param Name The variables name
     * @return The updated block
     */
    public JasminBlock LoadVariable (String Name)
    {
        Statements.add (new VariableStatement (VariableType.Load, Name));
        return this;
    }

    /**
     * @return The blocks label
     */
    public String GetLabel ()
    {
        return Name;
    }

}
