package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.LocalVariableStatement;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Types.DataType;
import me.Munchii.JasminBuilder.Types.LocalVariableType;
import me.Munchii.JasminBuilder.Types.NoParameterType;

import java.util.List;

import static java.util.Arrays.asList;

public class JasminVariable implements JasminPassable
{

    private String Name;
    private int Index;
    private JasminPassable Value;
    private DataType Type;

    public JasminVariable (String Name, JasminPassable Value)
    {
        this (Name, -1, Value);
    }

    public JasminVariable (String Name, int Index, JasminPassable Value)
    {
        this.Name = Name;
        this.Index = Index;
        this.Value = Value;
        this.Type = Value.GetType ();
    }

    // TODO: Implement rest
    public JasminStatement Store ()
    {
        switch (Type)
        {
            case Double: {
                switch (Index)
                {
                    case 0: return new NoParameterStatement (NoParameterType.StoreDoubleIntoLocalVariable0);
                    case 1: return new NoParameterStatement (NoParameterType.StoreDoubleIntoLocalVariable1);
                    case 2: return new NoParameterStatement (NoParameterType.StoreDoubleIntoLocalVariable2);
                    case 3: return new NoParameterStatement (NoParameterType.StoreDoubleIntoLocalVariable3);
                    default: return new LocalVariableStatement (LocalVariableType.StoreDouble, Index);
                }
            }

            case Float: {
                switch (Index)
                {
                    case 0: return new NoParameterStatement (NoParameterType.StoreFloat0);
                    case 1: return new NoParameterStatement (NoParameterType.StoreFloat1);
                    case 2: return new NoParameterStatement (NoParameterType.StoreFloat2);
                    case 3: return new NoParameterStatement (NoParameterType.StoreFloat3);
                    default: return new LocalVariableStatement (LocalVariableType.StoreFloat, Index);
                }
            }

            case Integer: {
                switch (Index)
                {
                    case 0: return new NoParameterStatement (NoParameterType.StoreInteger0);
                    case 1: return new NoParameterStatement (NoParameterType.StoreInteger1);
                    case 2: return new NoParameterStatement (NoParameterType.StoreInteger2);
                    case 3: return new NoParameterStatement (NoParameterType.StoreInteger3);
                    default: return new LocalVariableStatement (LocalVariableType.StoreInteger, Index);
                }
            }

            case Long: {
                switch (Index)
                {
                    case 0: return new NoParameterStatement (NoParameterType.StoreLong0);
                    case 1: return new NoParameterStatement (NoParameterType.StoreLong1);
                    case 2: return new NoParameterStatement (NoParameterType.StoreLong2);
                    case 3: return new NoParameterStatement (NoParameterType.StoreLong3);
                    default: return new LocalVariableStatement (LocalVariableType.StoreLong, Index);
                }
            }

            case String:
            case StringInstance:
            case Object:
            case ObjectInstance:
            case Custom:
            case CustomInstance: {
                switch (Index)
                {
                    case 0: return new NoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable0);
                    case 1: return new NoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable1);
                    case 2: return new NoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable2);
                    case 3: return new NoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable3);
                    default: return new LocalVariableStatement (LocalVariableType.StoreReference, Index);
                }
            }
        }

        throw new IllegalArgumentException ("Could not match data type: " + Type.GetRepresentation ());
    }

    // TODO: Implement rest
    @Override
    public List<JasminStatement> PushToStack ()
    {
        switch (Type)
        {
            case Double: {
                switch (Index)
                {
                    case 0: return asList (new NoParameterStatement (NoParameterType.LoadDoubleFromLocalVariable0));
                    case 1: return asList (new NoParameterStatement (NoParameterType.LoadDoubleFromLocalVariable1));
                    case 2: return asList (new NoParameterStatement (NoParameterType.LoadDoubleFromLocalVariable2));
                    case 3: return asList (new NoParameterStatement (NoParameterType.LoadDoubleFromLocalVariable3));
                    default: return asList (new LocalVariableStatement (LocalVariableType.LoadDouble, Index));
                }
            }

            case Float: {
                switch (Index)
                {
                    case 0: return asList (new NoParameterStatement (NoParameterType.LoadFloat0));
                    case 1: return asList (new NoParameterStatement (NoParameterType.LoadFloat1));
                    case 2: return asList (new NoParameterStatement (NoParameterType.LoadFloat2));
                    case 3: return asList (new NoParameterStatement (NoParameterType.LoadFloat3));
                    default: return asList (new LocalVariableStatement (LocalVariableType.LoadFloat, Index));
                }
            }

            case Integer: {
                switch (Index)
                {
                    case 0: return asList (new NoParameterStatement (NoParameterType.LoadInteger0));
                    case 1: return asList (new NoParameterStatement (NoParameterType.LoadInteger1));
                    case 2: return asList (new NoParameterStatement (NoParameterType.LoadInteger2));
                    case 3: return asList (new NoParameterStatement (NoParameterType.LoadInteger3));
                    default: return asList (new LocalVariableStatement (LocalVariableType.LoadInteger, Index));
                }
            }

            case Long: {
                switch (Index)
                {
                    case 0: return asList (new NoParameterStatement (NoParameterType.LoadLong0));
                    case 1: return asList (new NoParameterStatement (NoParameterType.LoadLong1));
                    case 2: return asList (new NoParameterStatement (NoParameterType.LoadLong2));
                    case 3: return asList (new NoParameterStatement (NoParameterType.LoadLong3));
                    default: return asList (new LocalVariableStatement (LocalVariableType.LoadLong, Index));
                }
            }

            case String:
            case StringInstance:
            case Object:
            case ObjectInstance:
            case Custom:
            case CustomInstance: {
                switch (Index)
                {
                    case 0: return asList (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable0));
                    case 1: return asList (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable1));
                    case 2: return asList (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable2));
                    case 3: return asList (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable3));
                    default: return asList (new LocalVariableStatement (LocalVariableType.LoadReference, Index));
                }
            }
        }

        throw new IllegalArgumentException ("Could not match data type: " + Type.GetRepresentation ());
    }

    public String GetName ()
    {
        return Name;
    }

    public int GetIndex ()
    {
        return Index;
    }

    public void SetIndex (int Index)
    {
        this.Index = Index;
    }

    public JasminPassable GetValue ()
    {
        return Value;
    }

    @Override
    public DataType GetType ()
    {
        return Type;
    }

}
