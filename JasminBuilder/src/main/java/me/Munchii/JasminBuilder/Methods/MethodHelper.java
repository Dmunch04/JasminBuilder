package me.Munchii.JasminBuilder.Methods;

import me.Munchii.JasminBuilder.DataTypes.ArrayType;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.DataTypes.ValueType;
import me.Munchii.JasminBuilder.JasminValue;
import me.Munchii.JasminBuilder.Statements.*;
import me.Munchii.JasminBuilder.Types.*;

public class MethodHelper
{

    private JasminMethod Method;

    public MethodHelper (JasminMethod Method)
    {
        this.Method = Method;
    }

    public JasminMethod GetMethod ()
    {
        return Method;
    }

    // -- Helper Functions --

    public JasminMethod InvokeNonVirtualMethod (String MethodName, DataType MethodReturnType, DataType... Args)
    {
        //Statements.add (new MethodInvokationStatement(MethodInvokationType.InvokeNonVirtual, MethodName, MethodReturnType, Helper.DataTypeArrayToList (Args)));
        Method.AddMethodInvokationStatement (MethodInvocationType.InvokeNonVirtual, MethodName, MethodReturnType, Args);
        return Method;
    }

    public JasminMethod InvokeStaticMethod (String MethodName, DataType MethodReturnType, DataType... Args)
    {
        //Statements.add (new MethodInvokationStatement (MethodInvokationType.InvokeStatic, MethodName, MethodReturnType, Helper.DataTypeArrayToList (Args)));
        Method.AddMethodInvokationStatement (MethodInvocationType.InvokeStatic, MethodName, MethodReturnType, Args);
        return Method;
    }

    public JasminMethod InvokeVirtual (String MethodName, DataType MethodReturnType, DataType... Args)
    {
        //Statements.add (new MethodInvokationStatement (MethodInvokationType.InvokeVirtual, MethodName, MethodReturnType, Helper.DataTypeArrayToList (Args)));
        Method.AddMethodInvokationStatement (MethodInvocationType.InvokeVirtual, MethodName, MethodReturnType, Args);
        return Method;
    }

    public JasminMethod InvokeSpecial (String MethodName, DataType MethodReturnType, DataType... Args)
    {
        //Statements.add (new MethodInvokationStatement (MethodInvokationType.InvokeSpecial, MethodName, MethodReturnType, Helper.DataTypeArrayToList (Args)));
        Method.AddMethodInvokationStatement (MethodInvocationType.InvokeSpecial, MethodName, MethodReturnType, Args);
        return Method;
    }

    public JasminMethod GetField (String FieldSpec, DataType Type)
    {
        //Statements.add (new FieldManipulationStatement(FieldManipulationType.GetField, FieldSpec, Type.GetRepresentation ()));
        Method.AddFieldManipulationStatement (FieldManipulationType.GetField, FieldSpec, Type);
        return Method;
    }

    public JasminMethod GetStaticField (String FieldSpec, DataType Type)
    {
        //Statements.add (new FieldManipulationStatement (FieldManipulationType.GetStatic, FieldSpec, Type.GetRepresentation ()));
        Method.AddFieldManipulationStatement (FieldManipulationType.GetStatic, FieldSpec, Type);
        return Method;
    }

    public JasminMethod PutField (String FieldSpec, DataType Type)
    {
        //Statements.add (new FieldManipulationStatement (FieldManipulationType.PutField, FieldSpec, Type.GetRepresentation ()));
        Method.AddFieldManipulationStatement (FieldManipulationType.PutField, FieldSpec, Type);
        return Method;
    }

    public JasminMethod PutStaticField (String FieldSpec, DataType Type)
    {
        //Statements.add (new FieldManipulationStatement (FieldManipulationType.PutStatic, FieldSpec, Type.GetRepresentation ()));
        Method.AddFieldManipulationStatement (FieldManipulationType.PutStatic, FieldSpec, Type);
        return Method;
    }

    public JasminMethod CreateNewArray (DataType Type)
    {
        //Statements.add (new NewArrayStatement(Type));
        Method.AddStatement (new NewArrayStatement (Type));
        return Method;
    }

    public JasminMethod CreateNewMultiArray (DataType Type, int Dimensions)
    {
        //Statements.add (new MultiANewArrayStatement(DataType.MakeArray (Dimensions, Type), Dimensions));
        Method.AddStatement (new MultiANewArrayStatement (new ArrayType (Type, Dimensions), Dimensions));
        return Method;
    }

    // TODO: Add support for `ldc2` & `ldc2_w`
    public JasminMethod LoadConstant (Object Value, DataType Type)
    {
        LoadConstant (new JasminValue(Value, Type));
        return Method;
    }

    public JasminMethod LoadConstant (JasminValue Value)
    {
        if ((Value.GetType ().Compare (ValueType.Double)) || (Value.GetType ().Compare (ValueType.Long)))
            return Method;

        Method.AddLoadConstantStatement (LoadConstantType.LoadConstant, Value);
        return Method;
    }

    public JasminMethod LoadConstantWide (Object Value, DataType Type)
    {
        LoadConstantWide (new JasminValue (Value, Type));
        return Method;
    }

    public JasminMethod LoadConstantWide (JasminValue Value)
    {
        if (!(Value.GetType ().Compare (ValueType.Double)) || !(Value.GetType ().Compare (ValueType.Long)))
            return Method;

        Method.AddLoadConstantStatement (LoadConstantType.LoadConstantWide, Value);
        return Method;
    }

    public JasminMethod Goto (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.Goto, Label));
        Method.AddBranchStatement (BranchType.Goto, Label);
        return Method;
    }

    public JasminMethod GotoWide (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.GotoW, Label));
        Method.AddBranchStatement (BranchType.GotoW, Label);
        return Method;
    }

    public JasminMethod IfReferenceEquals (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfReferenceCompareEquals, Label));
        Method.AddBranchStatement (BranchType.IfReferenceCompareEquals, Label);
        return Method;
    }

    public JasminMethod IfReferenceNotEquals (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfReferenceCompareNotEquals, Label));
        Method.AddBranchStatement (BranchType.IfReferenceCompareNotEquals, Label);
        return Method;
    }

    public JasminMethod IfIntegerEquals (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfIntegerCompareEquals, Label));
        Method.AddBranchStatement (BranchType.IfIntegerCompareEquals, Label);
        return Method;
    }

    public JasminMethod IfIntegerNotEquals (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfIntegerCompareNotEquals, Label));
        Method.AddBranchStatement (BranchType.IfIntegerCompareNotEquals, Label);
        return Method;
    }

    public JasminMethod IfIntegerGraterEquals (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfIntegerCompareGreaterEquals, Label));
        Method.AddBranchStatement (BranchType.IfIntegerCompareGreaterEquals, Label);
        return Method;
    }

    public JasminMethod IfIntegerGreaterThan (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfIntegerCompareGreaterThan, Label));
        Method.AddBranchStatement (BranchType.IfIntegerCompareGreaterThan, Label);
        return Method;
    }

    public JasminMethod IfIntegerLessEquals (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfIntegerCompareLessEquals, Label));
        Method.AddBranchStatement (BranchType.IfIntegerCompareLessEquals, Label);
        return Method;
    }

    public JasminMethod IfIntegerLessThan (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfIntegerCompareLessThan, Label));
        Method.AddBranchStatement (BranchType.IfIntegerCompareLessThan, Label);
        return Method;
    }

    public JasminMethod IfEquals (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfEquals, Label));
        Method.AddBranchStatement (BranchType.IfEquals, Label);
        return Method;
    }

    public JasminMethod IfNotEquals (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfNotEquals, Label));
        Method.AddBranchStatement (BranchType.IfNotEquals, Label);
        return Method;
    }

    public JasminMethod IfGreaterEquals (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfGreaterEquals, Label));
        Method.AddBranchStatement (BranchType.IfGreaterEquals, Label);
        return Method;
    }

    public JasminMethod IfGreaterThan (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfGreaterThan, Label));
        Method.AddBranchStatement (BranchType.IfGreaterThan, Label);
        return Method;
    }

    public JasminMethod IfLessEquals (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfLessEquals, Label));
        Method.AddBranchStatement (BranchType.IfLessEquals, Label);
        return Method;
    }

    public JasminMethod IfLessThan (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfLessThan, Label));
        Method.AddBranchStatement (BranchType.IfLessThan, Label);
        return Method;
    }

    public JasminMethod IfNonNull (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfNonNull, Label));
        Method.AddBranchStatement (BranchType.IfNonNull, Label);
        return Method;
    }

    public JasminMethod IfNull (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.IfNull, Label));
        Method.AddBranchStatement (BranchType.IfNull, Label);
        return Method;
    }

    public JasminMethod JumpSubroutine (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.JumpSubroutine, Label));
        Method.AddBranchStatement (BranchType.JumpSubroutine, Label);
        return Method;
    }

    public JasminMethod JumpSubroutineWide (String Label)
    {
        //Statements.add (new BranchStatement (BranchType.JumpSubroutineW, Label));
        Method.AddBranchStatement (BranchType.JumpSubroutineW, Label);
        return Method;
    }

    public JasminMethod ANewArray (String Class)
    {
        //Statements.add (new ObjectStatement (ObjectType.ANewArray, Class));
        Method.AddObjectStatement (ObjectType.ANewArray, Class);
        return Method;
    }

    public JasminMethod CheckCast (String Class)
    {
        //Statements.add (new ObjectStatement (ObjectType.CheckCast, Class));
        Method.AddObjectStatement (ObjectType.CheckCast, Class);
        return Method;
    }

    public JasminMethod InstanceOf (String Class)
    {
        //Statements.add (new ObjectStatement (ObjectType.InstanceOf, Class));
        Method.AddObjectStatement (ObjectType.InstanceOf, Class);
        return Method;
    }

    public JasminMethod New (String Class)
    {
        //Statements.add (new ObjectStatement (ObjectType.New, Class));
        Method.AddObjectStatement (ObjectType.New, Class);
        return Method;
    }

    public JasminMethod LoadReferenceFromArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadReferenceFromArray));
        Method.AddNoParameterStatement (NoParameterType.LoadReferenceFromArray);
        return Method;
    }

    public JasminMethod StoreIntoReferenceArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreIntoReferenceArray));
        Method.AddNoParameterStatement (NoParameterType.StoreIntoReferenceArray);
        return Method;
    }

    public JasminMethod PushNull ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.PushNull));
        Method.AddNoParameterStatement (NoParameterType.PushNull);
        return Method;
    }

    public JasminMethod LoadReferenceFromLocalVariable0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable0));
        Method.AddNoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable0);
        return Method;
    }

    public JasminMethod LoadReferenceFromLocalVariable1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable1));
        Method.AddNoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable1);
        return Method;
    }

    public JasminMethod LoadReferenceFromLocalVariable2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable2));
        Method.AddNoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable2);
        return Method;
    }

    public JasminMethod LoadReferenceFromLocalVariable3 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable3));
        Method.AddNoParameterStatement (NoParameterType.LoadReferenceFromLocalVariable3);
        return Method;
    }

    public JasminMethod ReturnReference ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.ReturnReference));
        Method.AddNoParameterStatement (NoParameterType.ReturnReference);
        return Method;
    }

    public JasminMethod ArrayLength ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.ArrayLength));
        Method.AddNoParameterStatement (NoParameterType.ArrayLength);
        return Method;
    }

    public JasminMethod StoreReferenceIntoLocalVariable0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable0));
        Method.AddNoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable0);
        return Method;
    }

    public JasminMethod StoreReferenceIntoLocalVariable1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable1));
        Method.AddNoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable1);
        return Method;
    }

    public JasminMethod StoreReferenceIntoLocalVariable2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable2));
        Method.AddNoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable2);
        return Method;
    }

    public JasminMethod StoreReferenceIntoLocalVariable3 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable3));
        Method.AddNoParameterStatement (NoParameterType.StoreReferenceIntoLocalVariable3);
        return Method;
    }

    public JasminMethod ThrowException ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadReferenceFromArray));
        Method.AddNoParameterStatement (NoParameterType.LoadReferenceFromArray);
        return Method;
    }

    public JasminMethod LoadByteBooleanFromArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadByteBooleanFromArray));
        Method.AddNoParameterStatement (NoParameterType.LoadByteBooleanFromArray);
        return Method;
    }

    public JasminMethod StoreIntoByteBooleanArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreIntoByteBooleanArray));
        Method.AddNoParameterStatement (NoParameterType.StoreIntoByteBooleanArray);
        return Method;
    }

    public JasminMethod Breakpoint ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.Breakpoint));
        Method.AddNoParameterStatement (NoParameterType.Breakpoint);
        return Method;
    }

    public JasminMethod LoadCharFromArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadCharFromArray));
        Method.AddNoParameterStatement (NoParameterType.LoadCharFromArray);
        return Method;
    }

    public JasminMethod StoreIntoCharArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreIntoCharArray));
        Method.AddNoParameterStatement (NoParameterType.StoreIntoCharArray);
        return Method;
    }

    public JasminMethod DoubleToFloat ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DoubleToFloat));
        Method.AddNoParameterStatement (NoParameterType.DoubleToFloat);
        return Method;
    }

    public JasminMethod DoubleToInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DoubleToInteger));
        Method.AddNoParameterStatement (NoParameterType.DoubleToInteger);
        return Method;
    }

    public JasminMethod DoubleToLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DoubleToLong));
        Method.AddNoParameterStatement (NoParameterType.DoubleToLong);
        return Method;
    }

    public JasminMethod AddDouble ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.AddDouble));
        Method.AddNoParameterStatement (NoParameterType.AddDouble);
        return Method;
    }

    public JasminMethod LoadDoubleFromArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadDoubleFromArray));
        Method.AddNoParameterStatement (NoParameterType.LoadDoubleFromArray);
        return Method;
    }

    public JasminMethod StoreIntoDoubleArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreIntoDoubleArray));
        Method.AddNoParameterStatement (NoParameterType.StoreIntoDoubleArray);
        return Method;
    }

    public JasminMethod CompareDoubleGreater ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.CompareDoubleGreater));
        Method.AddNoParameterStatement (NoParameterType.CompareDoubleGreater);
        return Method;
    }

    public JasminMethod CompareDoubleLess ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.CompareDoubleLess));
        Method.AddNoParameterStatement (NoParameterType.CompareDoubleLess);
        return Method;
    }

    public JasminMethod PushDouble0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.PushDouble0));
        Method.AddNoParameterStatement (NoParameterType.PushDouble0);
        return Method;
    }

    public JasminMethod PushDouble1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.PushDouble1));
        Method.AddNoParameterStatement (NoParameterType.PushDouble1);
        return Method;
    }

    public JasminMethod DivideDouble ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DivideDouble));
        Method.AddNoParameterStatement (NoParameterType.DivideDouble);
        return Method;
    }

    public JasminMethod LoadDoubleFromLocalVariable0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadDoubleFromLocalVariable0));
        Method.AddNoParameterStatement (NoParameterType.LoadDoubleFromLocalVariable0);
        return Method;
    }

    public JasminMethod LoadDoubleFromLocalVariable1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadDoubleFromLocalVariable1));
        Method.AddNoParameterStatement (NoParameterType.LoadDoubleFromLocalVariable1);
        return Method;
    }

    public JasminMethod LoadDoubleFromLocalVariable2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadDoubleFromLocalVariable2));
        Method.AddNoParameterStatement (NoParameterType.LoadDoubleFromLocalVariable2);
        return Method;
    }

    public JasminMethod LoadDoubleFromLocalVariable3 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadDoubleFromLocalVariable3));
        Method.AddNoParameterStatement (NoParameterType.LoadDoubleFromLocalVariable3);
        return Method;
    }

    public JasminMethod MultiplyDouble ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.MultiplyDouble));
        Method.AddNoParameterStatement (NoParameterType.MultiplyDouble);
        return Method;
    }

    public JasminMethod NegateDouble ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.NegateDouble));
        Method.AddNoParameterStatement (NoParameterType.NegateDouble);
        return Method;
    }

    public JasminMethod RemainderDouble ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.RemainderDouble));
        Method.AddNoParameterStatement (NoParameterType.RemainderDouble);
        return Method;
    }

    public JasminMethod ReturnDouble ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.ReturnDouble));
        Method.AddNoParameterStatement (NoParameterType.ReturnDouble);
        return Method;
    }

    public JasminMethod StoreDoubleIntoLocalVariable0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreDoubleIntoLocalVariable0));
        Method.AddNoParameterStatement (NoParameterType.StoreDoubleIntoLocalVariable0);
        return Method;
    }

    public JasminMethod StoreDoubleIntoLocalVariable1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreDoubleIntoLocalVariable1));
        Method.AddNoParameterStatement (NoParameterType.StoreDoubleIntoLocalVariable1);
        return Method;
    }

    public JasminMethod StoreDoubleIntoLocalVariable2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreDoubleIntoLocalVariable2));
        Method.AddNoParameterStatement (NoParameterType.StoreDoubleIntoLocalVariable2);
        return Method;
    }

    public JasminMethod StoreDoubleIntoLocalVariable3 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreDoubleIntoLocalVariable3));
        Method.AddNoParameterStatement (NoParameterType.StoreDoubleIntoLocalVariable3);
        return Method;
    }

    public JasminMethod SubtractDouble ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.SubtractDouble));
        Method.AddNoParameterStatement (NoParameterType.SubtractDouble);
        return Method;
    }

    public JasminMethod DuplicateTopStackValue ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DuplicateTopStackValue));
        Method.AddNoParameterStatement (NoParameterType.DuplicateTopStackValue);
        return Method;
    }

    public JasminMethod DuplicateTopStackValueX1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DuplicateTopStackValueX1));
        Method.AddNoParameterStatement (NoParameterType.DuplicateTopStackValueX1);
        return Method;
    }

    public JasminMethod DuplicateTopStackValueX2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DuplicateTopStackValueX2));
        Method.AddNoParameterStatement (NoParameterType.DuplicateTopStackValueX2);
        return Method;
    }

    public JasminMethod DuplicateTopOneTwoStackValues ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DuplicateTopOneTwoStackValues));
        Method.AddNoParameterStatement (NoParameterType.DuplicateTopOneTwoStackValues);
        return Method;
    }

    public JasminMethod DuplicateTopOneTwoStackValuesX1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DuplicateTopOneTwoStackValuesX1));
        Method.AddNoParameterStatement (NoParameterType.DuplicateTopOneTwoStackValuesX1);
        return Method;
    }

    public JasminMethod DuplicateTopOneTwoStackValuesX2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DuplicateTopOneTwoStackValuesX2));
        Method.AddNoParameterStatement (NoParameterType.DuplicateTopOneTwoStackValuesX2);
        return Method;
    }

    public JasminMethod FloatToDouble ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.FloatToDouble));
        Method.AddNoParameterStatement (NoParameterType.FloatToDouble);
        return Method;
    }

    public JasminMethod FloatToInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.FloatToInteger));
        Method.AddNoParameterStatement (NoParameterType.FloatToInteger);
        return Method;
    }

    public JasminMethod FloatToLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.FloatToLong));
        Method.AddNoParameterStatement (NoParameterType.FloatToLong);
        return Method;
    }

    public JasminMethod AddFloat ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.AddFloat));
        Method.AddNoParameterStatement (NoParameterType.AddFloat);
        return Method;
    }

    public JasminMethod LoadFloatFromArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadFloatFromArray));
        Method.AddNoParameterStatement (NoParameterType.LoadFloatFromArray);
        return Method;
    }

    public JasminMethod StoreIntoFloatArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreIntoFloatArray));
        Method.AddNoParameterStatement (NoParameterType.StoreIntoFloatArray);
        return Method;
    }

    public JasminMethod CompareFloatGreater ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.CompareFloatGreater));
        Method.AddNoParameterStatement (NoParameterType.CompareFloatGreater);
        return Method;
    }

    public JasminMethod CompareFloatLess ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.CompareFloatLess));
        Method.AddNoParameterStatement (NoParameterType.CompareFloatLess);
        return Method;
    }

    public JasminMethod FloatConstant0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.FloatConstant0));
        Method.AddNoParameterStatement (NoParameterType.FloatConstant0);
        return Method;
    }

    public JasminMethod FloatConstant1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.FloatConstant1));
        Method.AddNoParameterStatement (NoParameterType.FloatConstant1);
        return Method;
    }

    public JasminMethod FloatConstant2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.FloatConstant2));
        Method.AddNoParameterStatement (NoParameterType.FloatConstant2);
        return Method;
    }

    public JasminMethod DivideFloat ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DivideFloat));
        Method.AddNoParameterStatement (NoParameterType.DivideFloat);
        return Method;
    }

    public JasminMethod LoadFloat0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadFloat0));
        Method.AddNoParameterStatement (NoParameterType.LoadFloat0);
        return Method;
    }

    public JasminMethod LoadFloat1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadFloat1));
        Method.AddNoParameterStatement (NoParameterType.LoadFloat1);
        return Method;
    }

    public JasminMethod LoadFloat2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadFloat2));
        Method.AddNoParameterStatement (NoParameterType.LoadFloat2);
        return Method;
    }

    public JasminMethod LoadFloat3 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadFloat3));
        Method.AddNoParameterStatement (NoParameterType.LoadFloat3);
        return Method;
    }

    public JasminMethod MultiplyFloat ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.MultiplyFloat));
        Method.AddNoParameterStatement (NoParameterType.MultiplyFloat);
        return Method;
    }

    public JasminMethod NegateFloat ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.NegateFloat));
        Method.AddNoParameterStatement (NoParameterType.NegateFloat);
        return Method;
    }

    public JasminMethod RemainderFloat ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.RemainderFloat));
        Method.AddNoParameterStatement (NoParameterType.RemainderFloat);
        return Method;
    }

    public JasminMethod ReturnFloat ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.ReturnFloat));
        Method.AddNoParameterStatement (NoParameterType.ReturnFloat);
        return Method;
    }

    public JasminMethod StoreFloat0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreFloat0));
        Method.AddNoParameterStatement (NoParameterType.StoreFloat0);
        return Method;
    }

    public JasminMethod StoreFloat1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreFloat1));
        Method.AddNoParameterStatement (NoParameterType.StoreFloat1);
        return Method;
    }

    public JasminMethod StoreFloat2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreFloat2));
        Method.AddNoParameterStatement (NoParameterType.StoreFloat2);
        return Method;
    }

    public JasminMethod StoreFloat3 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreFloat3));
        Method.AddNoParameterStatement (NoParameterType.StoreFloat3);
        return Method;
    }

    public JasminMethod SubtractFloat ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.SubtractFloat));
        Method.AddNoParameterStatement (NoParameterType.SubtractFloat);
        return Method;
    }

    public JasminMethod IntegerToDouble ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerToDouble));
        Method.AddNoParameterStatement (NoParameterType.IntegerToDouble);
        return Method;
    }

    public JasminMethod IntegerToFloat ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerToFloat));
        Method.AddNoParameterStatement (NoParameterType.IntegerToFloat);
        return Method;
    }

    public JasminMethod IntegerToLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerToLong));
        Method.AddNoParameterStatement (NoParameterType.IntegerToLong);
        return Method;
    }

    public JasminMethod AddInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.AddInteger));
        Method.AddNoParameterStatement (NoParameterType.AddInteger);
        return Method;
    }

    public JasminMethod LoadIntegerFromArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadIntegerFromArray));
        Method.AddNoParameterStatement (NoParameterType.LoadIntegerFromArray);
        return Method;
    }

    public JasminMethod AndInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.AndInteger));
        Method.AddNoParameterStatement (NoParameterType.AndInteger);
        return Method;
    }

    public JasminMethod StoreIntoIntegerArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreIntoIntegerArray));
        Method.AddNoParameterStatement (NoParameterType.StoreIntoIntegerArray);
        return Method;
    }

    public JasminMethod IntegerConstantNegative1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerConstantNegative1));
        Method.AddNoParameterStatement (NoParameterType.IntegerConstantNegative1);
        return Method;
    }

    public JasminMethod IntegerConstant0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerConstant0));
        Method.AddNoParameterStatement (NoParameterType.IntegerConstant0);
        return Method;
    }

    public JasminMethod IntegerConstant1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerConstant1));
        Method.AddNoParameterStatement (NoParameterType.IntegerConstant1);
        return Method;
    }

    public JasminMethod IntegerConstant2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerConstant2));
        Method.AddNoParameterStatement (NoParameterType.IntegerConstant2);
        return Method;
    }

    public JasminMethod IntegerConstant3 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerConstant3));
        Method.AddNoParameterStatement (NoParameterType.IntegerConstant3);
        return Method;
    }

    public JasminMethod IntegerConstant4 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerConstant4));
        Method.AddNoParameterStatement (NoParameterType.IntegerConstant4);
        return Method;
    }

    public JasminMethod IntegerConstant5 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerConstant5));
        Method.AddNoParameterStatement (NoParameterType.IntegerConstant5);
        return Method;
    }

    public JasminMethod DivideInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DivideInteger));
        Method.AddNoParameterStatement (NoParameterType.DivideInteger);
        return Method;
    }

    public JasminMethod LoadInteger0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadInteger0));
        Method.AddNoParameterStatement (NoParameterType.LoadInteger0);
        return Method;
    }

    public JasminMethod LoadInteger1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadInteger1));
        Method.AddNoParameterStatement (NoParameterType.LoadInteger1);
        return Method;
    }

    public JasminMethod LoadInteger2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadInteger2));
        Method.AddNoParameterStatement (NoParameterType.LoadInteger2);
        return Method;
    }

    public JasminMethod LoadInteger3 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadInteger3));
        Method.AddNoParameterStatement (NoParameterType.LoadInteger3);
        return Method;
    }

    public JasminMethod MultiplyInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.MultiplyInteger));
        Method.AddNoParameterStatement (NoParameterType.MultiplyInteger);
        return Method;
    }

    public JasminMethod NegateInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.NegateInteger));
        Method.AddNoParameterStatement (NoParameterType.NegateInteger);
        return Method;
    }

    public JasminMethod IntegerToByte ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerToByte));
        Method.AddNoParameterStatement (NoParameterType.IntegerToByte);
        return Method;
    }

    public JasminMethod IntegerToChar ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerToChar));
        Method.AddNoParameterStatement (NoParameterType.IntegerToChar);
        return Method;
    }

    public JasminMethod IntegerToShort ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.IntegerToShort));
        Method.AddNoParameterStatement (NoParameterType.IntegerToShort);
        return Method;
    }

    public JasminMethod OrInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.OrInteger));
        Method.AddNoParameterStatement (NoParameterType.OrInteger);
        return Method;
    }

    public JasminMethod RemainderInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.RemainderInteger));
        Method.AddNoParameterStatement (NoParameterType.RemainderInteger);
        return Method;
    }

    public JasminMethod ReturnInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.ReturnInteger));
        Method.AddNoParameterStatement (NoParameterType.ReturnInteger);
        return Method;
    }

    public JasminMethod ShiftLeftInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.ShiftLeftInteger));
        Method.AddNoParameterStatement (NoParameterType.ShiftLeftInteger);
        return Method;
    }

    public JasminMethod ShiftRightInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.ShiftRightInteger));
        Method.AddNoParameterStatement (NoParameterType.ShiftRightInteger);
        return Method;
    }

    public JasminMethod StoreInteger0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreInteger0));
        Method.AddNoParameterStatement (NoParameterType.StoreInteger0);
        return Method;
    }

    public JasminMethod StoreInteger1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreInteger1));
        Method.AddNoParameterStatement (NoParameterType.StoreInteger1);
        return Method;
    }

    public JasminMethod StoreInteger2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreInteger2));
        Method.AddNoParameterStatement (NoParameterType.StoreInteger2);
        return Method;
    }

    public JasminMethod StoreInteger3 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreInteger3));
        Method.AddNoParameterStatement (NoParameterType.StoreInteger3);
        return Method;
    }

    public JasminMethod SubtractInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.SubtractInteger));
        Method.AddNoParameterStatement (NoParameterType.SubtractInteger);
        return Method;
    }

    public JasminMethod LogicalShiftRightInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LogicalShiftRightInteger));
        Method.AddNoParameterStatement (NoParameterType.LogicalShiftRightInteger);
        return Method;
    }

    public JasminMethod XORInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.XORInteger));
        Method.AddNoParameterStatement (NoParameterType.XORInteger);
        return Method;
    }

    public JasminMethod LongToDouble ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LongToDouble));
        Method.AddNoParameterStatement (NoParameterType.LongToDouble);
        return Method;
    }

    public JasminMethod LongToFloat ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LongToFloat));
        Method.AddNoParameterStatement (NoParameterType.LongToFloat);
        return Method;
    }

    public JasminMethod LongToInteger ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LongToInteger));
        Method.AddNoParameterStatement (NoParameterType.LongToInteger);
        return Method;
    }

    public JasminMethod AddLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.AddLong));
        Method.AddNoParameterStatement (NoParameterType.AddLong);
        return Method;
    }

    public JasminMethod LoadLongFromArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadLongFromArray));
        Method.AddNoParameterStatement (NoParameterType.LoadLongFromArray);
        return Method;
    }

    public JasminMethod AndLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.AndLong));
        Method.AddNoParameterStatement (NoParameterType.AndLong);
        return Method;
    }

    public JasminMethod StoreIntoLongArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreIntoLongArray));
        Method.AddNoParameterStatement (NoParameterType.StoreIntoLongArray);
        return Method;
    }

    public JasminMethod CompareLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.CompareLong));
        Method.AddNoParameterStatement (NoParameterType.CompareLong);
        return Method;
    }

    public JasminMethod LongConstant0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LongConstant0));
        Method.AddNoParameterStatement (NoParameterType.LongConstant0);
        return Method;
    }

    public JasminMethod LongConstant1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LongConstant1));
        Method.AddNoParameterStatement (NoParameterType.LongConstant1);
        return Method;
    }

    public JasminMethod DivideLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.DivideLong));
        Method.AddNoParameterStatement (NoParameterType.DivideLong);
        return Method;
    }

    public JasminMethod LoadLong0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadLong0));
        Method.AddNoParameterStatement (NoParameterType.LoadLong0);
        return Method;
    }

    public JasminMethod LoadLong1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadLong1));
        Method.AddNoParameterStatement (NoParameterType.LoadLong1);
        return Method;
    }

    public JasminMethod LoadLong2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadLong2));
        Method.AddNoParameterStatement (NoParameterType.LoadLong2);
        return Method;
    }

    public JasminMethod LoadLong3 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadLong3));
        Method.AddNoParameterStatement (NoParameterType.LoadLong3);
        return Method;
    }

    public JasminMethod MultiplyLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.MultiplyLong));
        Method.AddNoParameterStatement (NoParameterType.MultiplyLong);
        return Method;
    }

    public JasminMethod NegateLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.NegateLong));
        Method.AddNoParameterStatement (NoParameterType.NegateLong);
        return Method;
    }

    public JasminMethod OrLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.OrLong));
        Method.AddNoParameterStatement (NoParameterType.OrLong);
        return Method;
    }

    public JasminMethod RemainderLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.RemainderLong));
        Method.AddNoParameterStatement (NoParameterType.RemainderLong);
        return Method;
    }

    public JasminMethod ReturnLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.ReturnLong));
        Method.AddNoParameterStatement (NoParameterType.ReturnLong);
        return Method;
    }

    public JasminMethod ShiftLeftLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.ShiftLeftLong));
        Method.AddNoParameterStatement (NoParameterType.ShiftLeftLong);
        return Method;
    }

    public JasminMethod ShiftRightLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.ShiftRightLong));
        Method.AddNoParameterStatement (NoParameterType.ShiftRightLong);
        return Method;
    }

    public JasminMethod StoreLong0 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreLong0));
        Method.AddNoParameterStatement (NoParameterType.StoreLong0);
        return Method;
    }

    public JasminMethod StoreLong1 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreLong1));
        Method.AddNoParameterStatement (NoParameterType.StoreLong1);
        return Method;
    }

    public JasminMethod StoreLong2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreLong2));
        Method.AddNoParameterStatement (NoParameterType.StoreLong2);
        return Method;
    }

    public JasminMethod StoreLong3 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreLong3));
        Method.AddNoParameterStatement (NoParameterType.StoreLong3);
        return Method;
    }

    public JasminMethod SubtractLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.SubtractLong));
        Method.AddNoParameterStatement (NoParameterType.SubtractLong);
        return Method;
    }

    public JasminMethod LogicalShiftRightLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LogicalShiftRightLong));
        Method.AddNoParameterStatement (NoParameterType.LogicalShiftRightLong);
        return Method;
    }

    public JasminMethod XORLong ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.XORLong));
        Method.AddNoParameterStatement (NoParameterType.XORLong);
        return Method;
    }

    public JasminMethod EnterMonitor ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.EnterMonitor));
        Method.AddNoParameterStatement (NoParameterType.EnterMonitor);
        return Method;
    }

    public JasminMethod ExitMonitor ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.ExitMonitor));
        Method.AddNoParameterStatement (NoParameterType.ExitMonitor);
        return Method;
    }

    public JasminMethod Nop ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.Nop));
        Method.AddNoParameterStatement (NoParameterType.Nop);
        return Method;
    }

    public JasminMethod Pop ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.Pop));
        Method.AddNoParameterStatement (NoParameterType.Pop);
        return Method;
    }

    public JasminMethod Pop2 ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.Pop2));
        Method.AddNoParameterStatement (NoParameterType.Pop2);
        return Method;
    }

    public JasminMethod Return ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.Return));
        Method.AddNoParameterStatement (NoParameterType.Return);
        return Method;
    }

    public JasminMethod LoadShortFromArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.LoadShortFromArray));
        Method.AddNoParameterStatement (NoParameterType.LoadShortFromArray);
        return Method;
    }

    public JasminMethod StoreIntoShortArray ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.StoreIntoShortArray));
        Method.AddNoParameterStatement (NoParameterType.StoreIntoShortArray);
        return Method;
    }

    public JasminMethod Swap ()
    {
        //Statements.add (new NoParameterStatement (NoParameterType.Swap));
        Method.AddNoParameterStatement (NoParameterType.Swap);
        return Method;
    }

    public JasminMethod LookupSwitch ()
    {
        //Statements.add (new SwitchStatement (SwitchType.LookupSwitch));
        Method.AddSwitchStatement (SwitchType.LookupSwitch);
        return Method;
    }

    public JasminMethod TableSwitch ()
    {
        //Statements.add (new SwitchStatement (SwitchType.TableSwitch));
        Method.AddSwitchStatement (SwitchType.TableSwitch);
        return Method;
    }

}
