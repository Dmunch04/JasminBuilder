package me.Munchii.JasminBuilder.Methods;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Builder;
import me.Munchii.JasminBuilder.Instructions.JasminInstruction;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.JasminVariable;
import me.Munchii.JasminBuilder.Statements.*;
import me.Munchii.JasminBuilder.Types.*;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class JasminMethod implements Builder
{

    private List<MethodAccessSpec> AccessSpec;
    private String MethodName;
    private DataType MethodReturnType;
    private List<DataType> Args;
    private List<JasminStatement> Statements;
    private List<JasminBlock> Blocks;

    private int Stack;
    private int Locals;
    private boolean DidReturn;

    private Map<String, JasminVariable> Variables;
    private int VariableIndex;

    private JasminScope Scope;

    public JasminMethod (MethodAccessSpec AccessSpec, String MethodName, DataType MethodReturnType)
    {
        this (AccessSpec, MethodName, MethodReturnType, null);
    }

    public JasminMethod (MethodAccessSpec AccessSpec, String MethodName, DataType MethodReturnType, DataType... Args)
    {
        this.AccessSpec = new ArrayList<MethodAccessSpec> ();
        this.AccessSpec.add (AccessSpec);
        this.MethodName = MethodName;
        this.MethodReturnType = MethodReturnType;

        if (Args != null)
            this.Args = asList (Args);
        else
            this.Args = new ArrayList<DataType> ();

        this.Statements = new ArrayList<JasminStatement> ();
        this.Blocks = new ArrayList<JasminBlock> ();

        this.Stack = 0;
        this.Locals = Args != null ? Args.length : 0;
        this.DidReturn = false;

        this.Variables = new HashMap<String, JasminVariable> ();
        this.VariableIndex = 0;

        this.Scope = new JasminScope ();

        if (AccessSpec != MethodAccessSpec.Static)
        {
            Variables.put ("this", new JasminVariable ("this", VariableIndex, new JasminValue (null, DataType.CustomInstance)));
            VariableIndex++;
        }

        if (Args != null)
        {
            for (int Index = 0; Index < Args.length; Index++)
            {
                String ArgName = "arg" + (Index + 1);
                Variables.put (ArgName, new JasminVariable (ArgName, VariableIndex, new JasminValue (null, Args[Index])));
                VariableIndex++;
            }
        }
    }

    @Override
    public String ToOutputString ()
    {
        StringBuilder Builder = new StringBuilder ();
        Builder.append (".method").append (" ");
        AccessSpec.forEach (Spec -> Builder.append (Spec.GetRepresentation ()).append (" "));
        Builder.append (Helper.MakeMethodSpec (MethodName, MethodReturnType, Args)).append ("\n");

        if (Stack != 0)
            Builder.append ("\t").append (".limit stack").append (" ").append (Stack).append ("\n");
        if (Locals != 0)
            Builder.append ("\t").append (".limit locals").append (" ").append (Locals).append ("\n");

        //for (JasminStatement Statement : Statements)
        for (JasminStatement Statement : Scope.GetStatements ())
        {
            Builder.append ("\t").append (Statement.ToOutputString ()).append ("\n");
        }

        JasminScope OuterScope = Scope;
        for (JasminBlock Block : Blocks)
        {
            Scope = new JasminScope ();
            Builder.append ("\n").append (Block.GetLabel ()).append (":\n");
            Block.Write (this);

            for (JasminStatement Statement : Scope.GetStatements ())
            {
                Builder.append ("\t").append (Statement.ToOutputString ()).append ("\n");
            }
        }
        Scope = OuterScope;

        // If the user haven't added a return statement, return void
        // TODO: Well doesn't work well with scopes ay
        if (!DidReturn)
            Builder.append ("\t").append ("return").append ("\n");

        Builder.append (".end method");

        return Builder.toString ();
    }

    // ========================
    // User methods
    // ========================

    public JasminMethod AddInstruction (JasminInstruction Instruction)
    {
        Instruction.Write (this);
        return this;
    }

    public JasminMethod AddStackLimit (int Amount)
    {
        Stack += Amount;
        return this;
    }

    public JasminMethod AddLocalsLimit (int Amount)
    {
        Locals += Amount;
        return this;
    }

    public JasminMethod AddComment (String Comment)
    {
        //Statements.add (new CommentStatement (Comment));
        AddStatement (new CommentStatement (Comment));
        return this;
    }

    public JasminMethod AddMethodInvokationStatement (MethodInvokationType Type, String MethodName, DataType MethodReturnType, DataType... Args)
    {
        AddStatement (new MethodInvokationStatement (Type, MethodName, MethodReturnType, Helper.DataTypeArrayToList (Args)));
        return this;
    }

    public JasminMethod AddFieldManipulationStatement (FieldManipulationType Type, String FieldSpec, DataType FieldType)
    {
        AddStatement (new FieldManipulationStatement (Type, FieldSpec, FieldType.GetRepresentation ()));
        return this;
    }

    public JasminMethod AddLoadConstantStatement (LoadConstantType Type, JasminValue Value)
    {
        AddStatement (new LoadConstantStatement (Type, Value));
        return this;
    }

    public JasminMethod AddLocalVariableStatement (LocalVariableType Type, int Index)
    {
        AddStatement (new LocalVariableStatement (Type, Index));
        return this;
    }

    public JasminMethod AddBranchStatement (BranchType Type, String Label)
    {
        AddStatement (new BranchStatement (Type, Label));
        return this;
    }

    public JasminMethod AddObjectStatement (ObjectType Type, String Class)
    {
        AddStatement (new ObjectStatement (Type, Class));
        return this;
    }

    public JasminMethod AddNoParameterStatement (NoParameterType Type)
    {
        // If the user defines their own return statement, we don't need to add our own void return
        if (
                Type == NoParameterType.Return ||
                Type == NoParameterType.ReturnInteger ||
                Type == NoParameterType.ReturnDouble ||
                Type == NoParameterType.ReturnFloat ||
                Type == NoParameterType.ReturnLong ||
                Type == NoParameterType.ReturnReference
        )
            DidReturn = true;

        AddStatement (new NoParameterStatement (Type));
        return this;
    }

    public JasminMethod AddSwitchStatement (SwitchType Type)
    {
        AddStatement (new SwitchStatement (Type));
        return this;
    }

    public JasminMethod AddIntegerPushStatement (IntegerPushType Type, int Value)
    {
        AddStatement (new IntegerPushStatement (Type, Value));
        return this;
    }

    public JasminMethod AddStatement (JasminStatement Statement)
    {
        //Statements.add (Statement);
        Scope.AddStatement (Statement);
        return this;
    }

    public JasminMethod AddStatements (List<JasminStatement> Statements)
    {
        //for (JasminStatement Statement : Statements)
            //AddStatement (Statement);

        Scope.AddStatements (Statements);

        return this;
    }

    public JasminMethod AddBlock (JasminBlock Block)
    {
        Blocks.add (Block);
        return this;
    }

    /**
     * @param Variable The target variable which will be initialized with it's value
     * @return Returns the updated method
     */
    public JasminMethod DeclareVariable (JasminVariable Variable)
    {
        if (Variable.GetIndex() == -1)
            Variable.SetIndex (VariableIndex);

        AddStatements (Variable.GetValue ().PushToStack ());
        AddStatement (Variable.Store ());

        VariableIndex++;

        return this;
    }

    public JasminMethod StoreVariable (JasminVariable Variable, JasminPassable Value)
    {
        AddStatements (Value.PushToStack ());
        AddStatement (Variable.Store ());

        return this;
    }

    public JasminMethod LoadVariable (String Name)
    {
        if (!Variables.containsKey (Name))
            throw new IllegalArgumentException ("No variable with name exists: " + Name);

        AddStatements (Variables.get (Name).PushToStack ());
        return this;
    }

    // ========================
    // Getters & Setters
    // ========================

    public List<MethodAccessSpec> GetAccessSpec ()
    {
        return AccessSpec;
    }

    public JasminMethod AddAccessSpec (MethodAccessSpec AccessSpec)
    {
        if (!this.AccessSpec.contains (AccessSpec))
            this.AccessSpec.add (AccessSpec);

        return this;
    }

    public JasminMethod RemoveAccessSpec (MethodAccessSpec AccessSpec)
    {
        this.AccessSpec.remove (AccessSpec);

        return this;
    }

    public String GetMethodName ()
    {
        return MethodName;
    }

    public DataType GetMethodReturnType ()
    {
        return MethodReturnType;
    }

    public List<DataType> GetArgs ()
    {
        return Args;
    }

    public List<JasminStatement> GetStatements ()
    {
        return Statements;
    }

    public List<JasminBlock> GetBlocks ()
    {
        return Blocks;
    }

    public void SetArgs (List<DataType> Args)
    {
        this.Args = Args;
    }

    public void AddArg (DataType Arg)
    {
        Args.add (Arg);
    }

}
