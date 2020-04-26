package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Instructions.JasminInstruction;
import me.Munchii.JasminBuilder.References.VariableReference;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Types.*;

import java.util.List;

public interface StatementHolder<T>
{

    T AddInstruction (JasminInstruction Instruction);

    T AddStackLimit (int Amount);
    T AddLocalsLimit (int Amount);

    T AddComment (String Comment);
    T AddMethodInvokationStatement (MethodInvokationType Type, String MethodName, DataType MethodReturnType, DataType... Args);
    T AddFieldManipulationStatement (FieldManipulationType Type, String FieldSpec, DataType FieldType);
    T AddLoadConstantStatement (LoadConstantType Type, JasminValue Value);
    T AddLocalVariableStatement (LocalVariableType Type, int Index);
    T AddBranchStatement (BranchType Type, String Label);
    T AddObjectStatement (ObjectType Type, String Class);
    T AddNoParameterStatement (NoParameterType Type);
    T AddSwitchStatement (SwitchType Type);
    T AddIntegerPushStatement (IntegerPushType Type, int Value);

    T AddStatement (JasminStatement Statement);
    T AddStatements (List<JasminStatement> StatementList);

    T DeclareVariable (JasminVariable Variable);
    T StoreVariable (JasminVariable Variable, JasminPassable Value);
    T LoadVariable (VariableReference Reference);

}
