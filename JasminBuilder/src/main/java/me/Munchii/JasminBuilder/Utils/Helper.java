package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.JasminType;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Statements.*;
import me.Munchii.JasminBuilder.Types.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Helper
{

    public static String MakeMethodSpec (String MethodName, DataType MethodReturnType, List<DataType> Args)
    {
        StringBuilder Builder = new StringBuilder ();
        Builder.append (MethodName).append ("(");

        for (DataType Arg : Args)
        {
            Builder.append (Arg.GetRepresentation ());
        }

        Builder.append (")").append (MethodReturnType.GetRepresentation ());

        return Builder.toString ();
    }

    public static String MakeFieldSpec (String Class, String Field)
    {
        return Class + "/" + Field;
    }

    public static List<DataType> DataTypeArrayToList (DataType[] Types)
    {
        if (Types != null)
            return asList (Types);
        else
            return new ArrayList<DataType> ();
    }

    public static JasminStatement PushValueToStack (JasminValue Value)
    {
        switch (Value.GetType ())
        {
            case Byte: {
                byte NewValue = (byte) Value.GetValue ();

                return new IntegerPushStatement (IntegerPushType.BiPush, NewValue);
            }
            // TODO: Is this the correct way?
            case Char: {
                return new NoParameterStatement (NoParameterType.LoadCharFromArray);
            }
            case Double: {
                double NewValue = (double) Value.GetValue ();

                if (NewValue == 0.0d) return new NoParameterStatement (NoParameterType.PushDouble0);
                if (NewValue == 1.0d) return new NoParameterStatement (NoParameterType.PushDouble1);
                else return new LoadConstantStatement (LoadConstantType.LoadConstantW, new JasminValue (NewValue, DataType.Double));
            }
            case Float: {
                float NewValue = (float) Value.GetValue ();

                if (NewValue == 0.0f) return new NoParameterStatement (NoParameterType.FloatConstant0);
                if (NewValue == 1.0f) return new NoParameterStatement (NoParameterType.FloatConstant1);
                if (NewValue == 2.0f) return new NoParameterStatement (NoParameterType.FloatConstant2);
                else return new LoadConstantStatement (LoadConstantType.LoadConstant, new JasminValue (NewValue, DataType.Float));
            }
            case Integer: {
                int NewValue = (int) Value.GetValue ();

                if (NewValue == -1) return new NoParameterStatement (NoParameterType.IntegerConstantNegative1);
                if (NewValue == 0) return new NoParameterStatement (NoParameterType.IntegerConstant0);
                if (NewValue == 1) return new NoParameterStatement (NoParameterType.IntegerConstant1);
                if (NewValue == 2) return new NoParameterStatement (NoParameterType.IntegerConstant2);
                if (NewValue == 3) return new NoParameterStatement (NoParameterType.IntegerConstant3);
                if (NewValue == 4) return new NoParameterStatement (NoParameterType.IntegerConstant4);
                if (NewValue == 5) return new NoParameterStatement (NoParameterType.IntegerConstant5);
                else return new IntegerPushStatement (IntegerPushType.BiPush, NewValue);
            }
            case Long: {
                long NewValue = (long) Value.GetValue ();

                if (NewValue == 0) return new NoParameterStatement (NoParameterType.LongConstant0);
                if (NewValue == 1) return new NoParameterStatement (NoParameterType.LongConstant1);
                else return new LoadConstantStatement (LoadConstantType.LoadConstantW, new JasminValue (NewValue, DataType.Long));
            }
            case String:
            case StringInstance: {
                String NewValue = String.valueOf (Value.GetValue ());

                // TODO: Idk about `StringInstance`
                return new LoadConstantStatement (LoadConstantType.LoadConstant, new JasminValue (NewValue, DataType.String));
            }
            case Object:
            case ObjectInstance: {
                return new NoParameterStatement (NoParameterType.Nop);
            }
            // AKA null
            case Void: {
                return new NoParameterStatement (NoParameterType.PushNull);
            }
            case Short: {
                short NewValue = (short) Value.GetValue ();

                return new IntegerPushStatement (IntegerPushType.SiPush, NewValue);
            }
            // TODO: Is this the correct way?
            case Custom:
            case CustomInstance: {
                String NewValue = String.valueOf (Value.GetValue ());

                return new LoadConstantStatement (LoadConstantType.LoadConstant, new JasminValue (NewValue, DataType.Custom));
            }
            // TODO: Find a way
            case Array: {
                return new NoParameterStatement(NoParameterType.Nop);
            }
            // TODO: Is this the correct way??
            case Boolean: {
                boolean NewValue = (boolean) Value.GetValue ();

                if (NewValue == true)
                {
                    return new NoParameterStatement(NoParameterType.IntegerConstant1);
                }

                else
                {
                    return new NoParameterStatement(NoParameterType.IntegerConstant0);
                }
            }
        }

        throw new IllegalArgumentException ("Could not match value");
    }

}
