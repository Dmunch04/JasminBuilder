package me.Munchii.JasminBuilder.Statements;

import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Types.MethodInvocationType;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.List;

public class MethodInvocationStatement implements JasminStatement
{

    private MethodInvocationType Type;
    private String MethodName;
    private DataType MethodReturnType;
    private List<DataType> Args;

    public MethodInvocationStatement(MethodInvocationType Type, JasminMethod Method)
    {
        this (Type, Method.GetMethodName (), Method.GetMethodReturnType (), Method.GetArgs ());
    }

    public MethodInvocationStatement(MethodInvocationType Type, String MethodName, DataType MethodReturnType, List<DataType> Args)
    {
        this.Type = Type;
        this.MethodName = MethodName;
        this.MethodReturnType = MethodReturnType;
        this.Args = Args;
    }

    @Override
    public String ToOutputString ()
    {
        return Type.GetRepresentation () + " " + Helper.MakeMethodSpec (MethodName, MethodReturnType, Args);
    }

}
