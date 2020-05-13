package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.DataTypes.ArrayType;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.DataTypes.IntegerType;
import me.Munchii.JasminBuilder.DataTypes.ReferenceType;
import me.Munchii.JasminBuilder.JasminArray;
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

    public static String GetDataTypeName (DataType Type)
    {
        switch (Type.GetType ())
        {
            case Boolean: return "bool";
            case Byte: return "byte";
            case Char: return "char";
            case Double: return "double";
            case Float: return "float";
            case Integer: return "int";
            case Long: return "long";
            case Void: return "void";
            case Short: return "short";
            case Array: return GetDataTypeArrayName (Type);
            case Reference: return GetCustomDataTypeName (Type);
        }

        throw new IllegalArgumentException ("Could not match value");
    }

    public static String GetCustomDataTypeName (DataType Type)
    {
        String Rep = Type.GetRepresentation ();
        Rep = Rep.replace ("[", "");

        if (Rep.startsWith ("L") && Rep.endsWith (";"))
            Rep = Rep.substring (1, Rep.length () - 1);

        String[] ClassPaths = Rep.split ("/");

        return ClassPaths[ClassPaths.length - 1];
    }

    public static String GetDataTypeArrayName (DataType Type)
    {
        String Rep = Type.GetRepresentation ();
        int Dimensions = Rep.length () - Rep.replace ("[", "").length ();
        Rep = Rep.replace ("[", "");

        if (Rep.startsWith ("L") && Rep.endsWith (";"))
            Rep = Rep.substring (1, Rep.length () - 1);

        String[] ClassPaths = Rep.split ("/");
        String ClassName = ClassPaths[ClassPaths.length - 1];

        if (ClassName.equals ("String") || ClassName.equals ("Object"))
        {
            return ClassName.toLowerCase () + "[]".repeat (Dimensions);
        }

        // TODO: Fix that primitive types just gets displayed as their representation

        else
        {
            return Rep + "[]".repeat (Dimensions);
        }
    }

    public static JasminStatement PushValueToStack (JasminValue Value)
    {
        switch (Value.GetType ().GetType ())
        {
            case Byte: {
                byte NewValue = (byte) Value.GetValue ();

                return new IntegerPushStatement (IntegerPushType.BiPush, NewValue);
            }

            case Char: {
                return new IntegerPushStatement (IntegerPushType.BiPush, CharToInt ((char) Value.GetValue ()));
            }

            case Double: {
                double NewValue = (double) Value.GetValue ();

                if (NewValue == 0.0d) return new NoParameterStatement (NoParameterType.PushDouble0);
                if (NewValue == 1.0d) return new NoParameterStatement (NoParameterType.PushDouble1);
                else return new LoadConstantStatement (LoadConstantType.LoadConstant2Wide, new JasminValue (NewValue, DataType.Double));
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
                else return new LoadConstantStatement (LoadConstantType.LoadConstant2Wide, new JasminValue (NewValue, DataType.Long));
            }

            // AKA null
            // TODO: Should we introduce a `Null` type? If so, Couldn't it get confused with `Void` in some cases?
            case Void: {
                return new NoParameterStatement (NoParameterType.PushNull);
            }

            case Short: {
                short NewValue = (short) Value.GetValue ();

                return new IntegerPushStatement (IntegerPushType.SiPush, NewValue);
            }

            // TODO: Is this the correct way?
            case Reference: {
                return new LoadConstantStatement (LoadConstantType.LoadConstant, new JasminValue (String.valueOf (Value.GetValue ()), Value.GetType ()));
            }

            case Array: {
                //? TODO: Should this be allowed?
                return new ObjectStatement (ObjectType.ANewArray, Value.GetType ().GetRepresentation ());
            }

            case Boolean: {
                boolean NewValue = (boolean) Value.GetValue ();

                // true = 1
                // false = 0
                return NewValue ? new NoParameterStatement (NoParameterType.IntegerConstant1) : new NoParameterStatement (NoParameterType.IntegerConstant0);
            }
        }

        throw new IllegalArgumentException ("Could not match value");
    }

    public static String ConditionTypeToRepresentation (ConditionType Type)
    {
        switch (Type)
        {
            case Equals: return "==";
            case NotEquals: return "!=";
            case LessThan: return "<";
            case LessThanEquals: return "<=";
            case GreaterThan: return ">";
            case GreaterThanEquals: return ">=";
            default: return "?";
        }
    }

    public static int CharToInt (char Char)
    {
        return Char;
    }

}
