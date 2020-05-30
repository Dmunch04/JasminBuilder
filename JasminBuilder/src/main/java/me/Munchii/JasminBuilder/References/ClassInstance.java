package me.Munchii.JasminBuilder.References;

import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.DataTypes.ReferenceType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.LocalVariableStatement;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Statements.ObjectStatement;
import me.Munchii.JasminBuilder.Types.LocalVariableType;
import me.Munchii.JasminBuilder.Types.NoParameterType;
import me.Munchii.JasminBuilder.Types.ObjectType;
import me.Munchii.JasminBuilder.Utils.MethodInvocation;
import me.Munchii.JasminBuilder.Variable;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class ClassInstance implements Variable, JasminPassable
{

    private String Name;
    private String ClassName;
    private int Index;
    private DataType Type;
    private List<JasminPassable> Arguments;
    private List<DataType> ParamTypes;

    public ClassInstance (String Name, String ClassName, List<JasminPassable> Arguments, DataType... ParamTypes)
    {
        this.Name = Name;
        this.ClassName = ClassName;
        this.Index = -1;
        this.Type = new ReferenceType (ClassName);
        this.Arguments = Arguments;
        this.ParamTypes = asList (ParamTypes);
    }

    @Override
    public DataType GetType ()
    {
        return Type;
    }

    @Override
    public JasminStatement Store ()
    {
        switch (Index)
        {
            case 0: return new NoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable0);
            case 1: return new NoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable1);
            case 2: return new NoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable2);
            case 3: return new NoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable3);
            default: return new LocalVariableStatement(LocalVariableType.StoreReference, Index);
        }
    }

    @Override
    public List<JasminStatement> Declare ()
    {
        List<JasminStatement> Statements = new ArrayList<JasminStatement>();

        Statements.add (new ObjectStatement (ObjectType.New, ClassName));
        Statements.add (new NoParameterStatement (NoParameterType.DuplicateTopStackValue));
        Statements.addAll (MethodInvocation.CallSpecialMethod (ClassName, "<init>", Arguments, DataType.Void, ParamTypes).PushToStack ());
        Statements.add (Store ());

        return Statements;
    }

    @Override
    public List<JasminStatement> PushToStack ()
    {
        switch (Index)
        {
            case 0: return asList (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable0));
            case 1: return asList (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable1));
            case 2: return asList (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable2));
            case 3: return asList (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable3));
            default: return asList (new LocalVariableStatement(LocalVariableType.LoadReference, Index));
        }
    }

    @Override
    public String GetName ()
    {
        return Name;
    }

    @Override
    // TODO: Hmm
    public JasminPassable GetValue ()
    {
        return null;
    }

    @Override
    public int GetIndex ()
    {
        return Index;
    }

    @Override
    public void SetIndex (int Index)
    {
        this.Index = Index;
    }
}
