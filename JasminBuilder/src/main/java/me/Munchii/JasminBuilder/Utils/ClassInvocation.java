package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.DataTypes.ReferenceType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Statements.ObjectStatement;
import me.Munchii.JasminBuilder.Types.NoParameterType;
import me.Munchii.JasminBuilder.Types.ObjectType;

import java.util.ArrayList;
import java.util.List;

public class ClassInvocation
{

    public static JasminPassable NewClassInstance (JasminClass Class)
    {
        return NewClassInstance (Class, new ArrayList<JasminPassable> ());
    }

    public static JasminPassable NewClassInstance (String Class)
    {
        return NewClassInstance (Class, new ArrayList<JasminPassable> ());
    }

    public static JasminPassable NewClassInstance (JasminClass Class, List<JasminPassable> Arguments, DataType... ParamTypes)
    {
        return NewClassInstance (Class.GetClassName (), Arguments, ParamTypes);
    }

    public static JasminPassable NewClassInstance (String Class, List<JasminPassable> Arguments, DataType... ParamTypes)
    {
        return new JasminPassable () {
            @Override
            public List<JasminStatement> PushToStack ()
            {
                List<JasminStatement> Statements = new ArrayList<JasminStatement> ();

                // TODO: Why is there a random newline after `dup`?
                Statements.add (new ObjectStatement (ObjectType.New, Class));
                Statements.add (new NoParameterStatement (NoParameterType.DuplicateTopStackValue));
                Statements.addAll (MethodInvocation.CallSpecialMethod (Class, "<init>", Arguments, DataType.Void, ParamTypes).PushToStack ());

                return Statements;
            }

            @Override
            public DataType GetType ()
            {
                return new ReferenceType (Class);
            }
        };
    }

}
