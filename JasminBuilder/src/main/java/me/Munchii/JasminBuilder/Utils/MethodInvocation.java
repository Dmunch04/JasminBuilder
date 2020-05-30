package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.Classes.JasminClass;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Statements.*;
import me.Munchii.JasminBuilder.Types.MethodInvocationType;
import me.Munchii.JasminBuilder.Types.VariableType;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MethodInvocation
{

    public static JasminPassable CallMethod (JasminClass Class, JasminMethod Method, JasminPassable... Arguments)
    {
        return CallMethod (Class, Method, asList (Arguments));
    }

    public static JasminPassable CallMethod (JasminClass Class, JasminMethod Method, List<JasminPassable> Arguments)
    {
        return CallMethod (Class.GetClassName (), Method.GetMethodName (), Arguments, Method.GetMethodReturnType (), Method.GetArgs ());
    }

    public static JasminPassable CallMethod (String Class, String MethodName, List<JasminPassable> Arguments, DataType ReturnType, DataType... ParamTypes)
    {
        return CallMethod (Class, MethodName, Arguments, ReturnType, asList (ParamTypes));
    }

    public static JasminPassable CallMethod (String Class, String MethodName, List<JasminPassable> Arguments, DataType ReturnType, List<DataType> ParamTypes)
    {
        return new JasminPassable () {
            @Override
            public List<JasminStatement> PushToStack ()
            {
                List<JasminStatement> Statements = new ArrayList<JasminStatement> ();

                Statements.add (new VariableStatement (VariableType.Load, "this"));

                for (JasminPassable Arg : Arguments)
                {
                    Statements.addAll (Arg.PushToStack ());
                }

                Statements.add (new MethodInvocationStatement (MethodInvocationType.InvokeVirtual, Class + "/" + MethodName, ReturnType, ParamTypes));

                return Statements;
            }

            @Override
            public DataType GetType ()
            {
                return ReturnType;
            }
        };
    }

    public static JasminPassable CallSpecialMethod (JasminClass Class, JasminMethod Method, JasminPassable... Arguments)
    {
        return CallSpecialMethod (Class, Method, asList (Arguments));
    }

    public static JasminPassable CallSpecialMethod (JasminClass Class, JasminMethod Method, List<JasminPassable> Arguments)
    {
        return CallSpecialMethod (Class.GetClassName (), Method.GetMethodName (), Arguments, Method.GetMethodReturnType (), Method.GetArgs ());
    }

    public static JasminPassable CallSpecialMethod (String Class, String MethodName, List<JasminPassable> Arguments, DataType ReturnType, DataType... ParamTypes)
    {
        return CallSpecialMethod (Class, MethodName, Arguments, ReturnType, asList (ParamTypes));
    }

    public static JasminPassable CallSpecialMethod (String Class, String MethodName, List<JasminPassable> Arguments, DataType ReturnType, List<DataType> ParamTypes)
    {
        return new JasminPassable () {
            @Override
            public List<JasminStatement> PushToStack ()
            {
                List<JasminStatement> Statements = new ArrayList<JasminStatement> ();

                for (JasminPassable Arg : Arguments)
                {
                    Statements.addAll (Arg.PushToStack ());
                }

                Statements.add (new MethodInvocationStatement(MethodInvocationType.InvokeSpecial, Class + "/" + MethodName, ReturnType, ParamTypes));

                return Statements;
            }

            @Override
            public DataType GetType ()
            {
                return ReturnType;
            }
        };
    }

}
