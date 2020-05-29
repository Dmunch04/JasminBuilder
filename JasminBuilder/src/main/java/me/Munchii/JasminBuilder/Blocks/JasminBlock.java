package me.Munchii.JasminBuilder.Blocks;

import me.Munchii.JasminBuilder.*;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Instructions.JasminInstruction;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.References.VariableReference;
import me.Munchii.JasminBuilder.Statements.*;
import me.Munchii.JasminBuilder.Types.*;
import me.Munchii.JasminBuilder.Utils.Helper;

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
                    case Declare: Method.DeclareVariable (Variable.GetVariable ()); break;
                    case Store: Method.StoreVariable (Variable.GetVariable (), Variable.GetValue ()); break;
                    case Load: Method.LoadVariable (Variable.GetReference ()); break;
                }

                continue;
            }

            else if (Statement instanceof LimitStatement)
            {
                LimitStatement Limit = (LimitStatement) Statement;
                switch (Limit.GetType ())
                {
                    case Stack: Method.AddStackLimit (Limit.GetAmount ()); break;
                    case Locals: Method.AddLocalsLimit (Limit.GetAmount ()); break;
                }

                continue;
            }

            Method.AddStatement (Statement);
        }
    }

    public JasminBlock AddInstruction (JasminInstruction Instruction)
    {
        Instruction.Write (this);
        return this;
    }

    public JasminBlock AddStackLimit (int Amount)
    {
        Statements.add (new LimitStatement (LimitType.Stack, Amount));
        return this;
    }

    public JasminBlock AddLocalsLimit (int Amount)
    {
        Statements.add (new LimitStatement (LimitType.Locals, Amount));
        return this;
    }

    public JasminBlock AddComment (String Comment)
    {
        AddStatement (new CommentStatement (Comment));
        return this;
    }

    public JasminBlock AddMethodInvocationStatement(MethodInvocationType Type, String MethodName, DataType MethodReturnType, DataType... Args)
    {
        AddStatement (new MethodInvocationStatement (Type, MethodName, MethodReturnType, Helper.DataTypeArrayToList (Args)));
        return this;
    }

    public JasminBlock AddFieldManipulationStatement (FieldManipulationType Type, String FieldSpec, DataType FieldType)
    {
        AddStatement (new FieldManipulationStatement (Type, FieldSpec, FieldType.GetRepresentation ()));
        return this;
    }

    public JasminBlock AddLoadConstantStatement (LoadConstantType Type, JasminValue Value)
    {
        AddStatement (new LoadConstantStatement (Type, Value));
        return this;
    }

    public JasminBlock AddLocalVariableStatement (LocalVariableType Type, int Index)
    {
        AddStatement (new LocalVariableStatement (Type, Index));
        return this;
    }

    public JasminBlock AddBranchStatement (BranchType Type, String Label)
    {
        AddStatement (new BranchStatement (Type, Label));
        return this;
    }

    public JasminBlock AddObjectStatement (ObjectType Type, String Class)
    {
        AddStatement (new ObjectStatement (Type, Class));
        return this;
    }

    public JasminBlock AddNoParameterStatement (NoParameterType Type)
    {
        AddStatement (new NoParameterStatement (Type));
        return this;
    }

    public JasminBlock AddSwitchStatement (SwitchType Type)
    {
        AddStatement (new SwitchStatement (Type));
        return this;
    }

    public JasminBlock AddIntegerPushStatement (IntegerPushType Type, int Value)
    {
        AddStatement (new IntegerPushStatement (Type, Value));
        return this;
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
    public JasminBlock DeclareVariable (Variable Variable)
    {
        Statements.add (new VariableStatement (VariableType.Declare, Variable));
        return this;
    }

    /**
     * @param Variable The variable the value should be stored in
     * @param Value The value the variable should store
     * @return The updated block
     */
    public JasminBlock StoreVariable (Variable Variable, JasminPassable Value)
    {
        Statements.add (new VariableStatement (VariableType.Store, Variable, Value));
        return this;
    }

    /**
     * @param Reference The variable reference
     * @return The updated block
     */
    public JasminBlock LoadVariable (VariableReference Reference)
    {
        Statements.add (new VariableStatement (VariableType.Load, Reference.Name));
        return this;
    }

    public JasminBlock AddValue (JasminPassable Value)
    {
        AddStatements (Value.PushToStack ());
        return this;
    }

    public void Return (JasminPassable Value)
    {
        Statements.addAll (Value.PushToStack ());

        NoParameterType ReturnType = NoParameterType.Return;
        switch (Value.GetType ().GetType ())
        {
            case Boolean:
            case Byte:
            case Char:
            case Short:
            case Integer: ReturnType = NoParameterType.ReturnInteger; break;

            case Float: ReturnType = NoParameterType.ReturnFloat; break;

            case Double: ReturnType = NoParameterType.ReturnDouble; break;

            case Long: ReturnType = NoParameterType.ReturnLong; break;

            case Array:
            case Reference: ReturnType = NoParameterType.ReturnReference; break;

            default: ReturnType = NoParameterType.Return; break;
        }
        Statements.add (new NoParameterStatement (ReturnType));
    }

    // ========================
    // Getters & Setters
    // ========================

    /**
     * @return The blocks label
     */
    public String GetLabel ()
    {
        return Name;
    }

    public List<JasminStatement> GetStatements ()
    {
        return Statements;
    }

}
