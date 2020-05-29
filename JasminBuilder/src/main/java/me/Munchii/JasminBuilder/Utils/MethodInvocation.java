package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.MethodInvocationStatement;
import me.Munchii.JasminBuilder.Statements.VariableStatement;
import me.Munchii.JasminBuilder.Types.MethodInvocationType;
import me.Munchii.JasminBuilder.Types.VariableType;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class MethodInvocation
{

    public static JasminPassable CallMethod(String Class, String MethodName, List<JasminPassable> Arguments, DataType ReturnType, DataType... ParamTypes)
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

                Statements.add (new MethodInvocationStatement(MethodInvocationType.InvokeVirtual, Class + "/" + MethodName, ReturnType, asList (ParamTypes)));

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
