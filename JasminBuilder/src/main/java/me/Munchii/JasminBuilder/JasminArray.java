package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.DataTypes.ArrayType;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Logging.Exceptions.AbortException;
import me.Munchii.JasminBuilder.Logging.Logger;
import me.Munchii.JasminBuilder.Logging.Message;
import me.Munchii.JasminBuilder.Statements.*;
import me.Munchii.JasminBuilder.Types.LocalVariableType;
import me.Munchii.JasminBuilder.Types.NoParameterType;
import me.Munchii.JasminBuilder.Types.ObjectType;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class JasminArray implements Variable, JasminPassable
{

    private String Name;
    private int Index;

    private JasminPassable[] Elements;
    private DataType Type;
    private int Size;
    private int Dimensions;
    private int IndexPointer;
    private ArrayType Array;

    private final JasminPassable LengthValue;

    public JasminArray (String Name, DataType Type, int Size)
    {
        this (Name, Type, Size, 1);
    }

    public JasminArray (String Name, DataType Type, int Size, int Dimensions)
    {
        this (Name, Type, Size, Dimensions, new JasminPassable[Size]);
    }

    public JasminArray (String Name, DataType Type, int Size, JasminPassable[] Elements)
    {
        this (Name, Type, Size, 1, Elements);
    }

    public JasminArray (String Name, DataType Type, int Size, int Dimensions, JasminPassable[] Elements)
    {
        this.Name = Name;
        this.Index = -1;

        this.Elements = Elements;
        this.Type = Type;
        this.Size = Size;
        this.Dimensions = Dimensions;
        this.IndexPointer = 0;
        this.Array = new ArrayType (Type, Dimensions);

        this.LengthValue = new JasminPassable () {
            @Override
            public List<JasminStatement> PushToStack ()
            {
                // TODO: Should it be maybe long or double instead?
                return asList (Helper.PushValueToStack (new JasminValue (Size, DataType.Integer)));
            }

            @Override
            public DataType GetType ()
            {
                return Type;
            }
        };
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
            default: return new LocalVariableStatement (LocalVariableType.StoreReference, Index);
        }
    }

    @Override
    public List<JasminStatement> Declare ()
    {
        List<JasminStatement> Statements = new ArrayList<JasminStatement> ();

        if (Dimensions == 1)
        {
            Statements.add (Helper.PushValueToStack (new JasminValue (Size, DataType.Integer)));
            Statements.add (new ObjectStatement (ObjectType.ANewArray, Type.GetRepresentation ()));
        }

        else
        {
            for (int Dimension = 0; Dimension < Dimensions; Dimension++)
            {
                // TODO: Allow for multiple sizes, ex. `String[x][y]` instead of `String[x][x]`
                Statements.add (Helper.PushValueToStack (new JasminValue (Size, DataType.Integer)));
            }

            Statements.add (new MultiANewArrayStatement (Type, Dimensions));
        }

        Statements.add (Store ());

        for (int ElementIndex = 0; ElementIndex < Size; ElementIndex++)
        {
            JasminPassable Element = Elements[ElementIndex];
            if (Element != null)
            {
                Statements.addAll (PushToStack ());
                Statements.add (Helper.PushValueToStack (new JasminValue (ElementIndex, DataType.Integer)));
                Statements.addAll (Element.PushToStack ());
                Statements.add (StoreElement ());
            }
        }

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
            default: return asList (new LocalVariableStatement (LocalVariableType.LoadReference, Index));
        }
    }

    //* Simple wrapper for `GetElement` method to avoid stack overflow error, since it can't access this classes `PushToStack()`
    private List<JasminStatement> Load ()
    {
        return PushToStack ();
    }

    public JasminStatement StoreElement ()
    {
        switch (Type.GetType ())
        {
            case Boolean:
            case Byte: return new NoParameterStatement (NoParameterType.StoreIntoByteBooleanArray);

            case Char: return new NoParameterStatement (NoParameterType.StoreIntoCharArray);

            case Double: return new NoParameterStatement (NoParameterType.StoreIntoDoubleArray);

            case Float: return new NoParameterStatement (NoParameterType.StoreIntoFloatArray);

            case Integer: return new NoParameterStatement (NoParameterType.StoreIntoIntegerArray);

            case Long: return new NoParameterStatement (NoParameterType.StoreIntoLongArray);

            case Short: return new NoParameterStatement (NoParameterType.StoreIntoShortArray);

            case Array:
            case Reference: return new NoParameterStatement (NoParameterType.StoreIntoReferenceArray);
        }

        Logger.Error (String.format (Message.CouldNotMatchType, Helper.GetDataTypeName (Type)));
        throw new AbortException();
    }

    public JasminStatement LoadElement ()
    {
        switch (Type.GetType ())
        {
            case Boolean:
            case Byte: return new NoParameterStatement (NoParameterType.LoadByteBooleanFromArray);

            case Char: return new NoParameterStatement (NoParameterType.LoadCharFromArray);

            case Double: return new NoParameterStatement (NoParameterType.LoadDoubleFromArray);

            case Float: return new NoParameterStatement (NoParameterType.LoadFloatFromArray);

            case Integer: return new NoParameterStatement (NoParameterType.LoadIntegerFromArray);

            case Long: return new NoParameterStatement (NoParameterType.LoadLongFromArray);

            case Short: return new NoParameterStatement (NoParameterType.LoadShortFromArray);

            case Array:
            case Reference: return new NoParameterStatement (NoParameterType.LoadReferenceFromArray);
        }

        Logger.Error (String.format (Message.CouldNotMatchType, Helper.GetDataTypeName (Type)));
        throw new AbortException ();
    }

    public JasminArray AddElement (JasminPassable Element)
    {
        return AddElement (IndexPointer, Element);
    }

    public JasminArray AddElement (int ElementIndex, JasminPassable Element)
    {
        CheckType (Element.GetType ());
        CheckIndex (ElementIndex);

        Elements[ElementIndex] = Element;
        IndexPointer = ElementIndex + 1;

        return this;
    }

    public JasminPassable GetElement (int ElementIndex)
    {
        CheckIndex (ElementIndex);

        // TODO: We can maybe make this more efficient by having the `JasminPassable` values in a list?
        // ^^ This can maybe be done when reworking the array to allow for multi dimensions
        return new JasminPassable () {
            @Override
            public List<JasminStatement> PushToStack ()
            {
                List<JasminStatement> Statements = new ArrayList<JasminStatement> ();
                Statements.addAll (Load ());
                Statements.add (Helper.PushValueToStack (new JasminValue (ElementIndex, DataType.Integer)));
                Statements.add (LoadElement ());

                return Statements;
            }

            @Override
            public DataType GetType ()
            {
                return Type;
            }
        };
    }

    private void CheckType (DataType TargetType)
    {
        if (!TargetType.Compare (Type))
        {
            Logger.Error (String.format (Message.ValueMustBeSameType, Helper.GetDataTypeName (Type)));
            System.exit (0x1);
        }
    }

    private void CheckIndex (int ElementIndex)
    {
        if (ElementIndex >= Size)
        {
            Logger.Error (String.format (Message.IndexOutOfRange, ElementIndex));
            System.exit (0x1);
        }
    }

    @Override
    public String GetName ()
    {
        return Name;
    }

    @Override
    public JasminPassable GetValue ()
    {
        // TODO: Is this really the best way lol?
        return Elements.length > 0 ? Elements[0] : new JasminValue (0, DataType.Integer);
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

    @Override
    public DataType GetType ()
    {
        return Array;
    }

    public void SetIndexPointer (int Position)
    {
        this.IndexPointer = Position;
    }

    public int GetIndexPointer ()
    {
        return IndexPointer;
    }

    public void IncrementIndexPointer (int Amount)
    {
        this.IndexPointer += Amount;
    }

    public void DecrementIndexPointer (int Amount)
    {
        this.IndexPointer -= Amount;
    }

    public JasminPassable GetLengthValue ()
    {
        return LengthValue;
    }

}
