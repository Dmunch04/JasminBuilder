package me.Munchii.JasminBuilder.Blocks;

import me.Munchii.JasminBuilder.*;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Instructions.JasminInstruction;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.References.VariableReference;
import me.Munchii.JasminBuilder.Statements.*;
import me.Munchii.JasminBuilder.Types.*;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * JasminBlock represents a block in Jasmin. It has a label and a list of statements
 */
public class JasminBlock {

    private final String name;
    private final List<JasminStatement> statements;

    /**
     * @param name The label of the block
     */
    public JasminBlock(String name) {
        this.name = name;
        this.statements = new ArrayList<>();
    }

    /**
     * @param method The target method it should write the statements to
     */
    public void write(JasminMethod method) {
        for (JasminStatement statement : statements) {
            if (statement instanceof VariableStatement) {
                VariableStatement variable = (VariableStatement) statement;
                switch (variable.getType()) {
                    case DECLARE:
                        method.declareVariable(variable.getVariable());
                        break;
                    case STORE:
                        method.storeVariable(variable.getVariable(), variable.getValue());
                        break;
                    case LOAD:
                        method.loadVariable(variable.getReference());
                        break;
                }
                continue;
            } else if (statement instanceof LimitStatement) {
                LimitStatement Limit = (LimitStatement) statement;
                switch (Limit.getType()) {
                    case STACK:
                        method.addStackLimit(Limit.getAmount());
                        break;
                    case LOCALS:
                        method.addLocalsLimit(Limit.getAmount());
                        break;
                }
                continue;
            }

            method.addStatement(statement);
        }
    }

    public JasminBlock addInstruction(JasminInstruction instruction) {
        instruction.write(this);
        return this;
    }

    public JasminBlock addStackLimit(int amount) {
        statements.add(new LimitStatement(LimitType.STACK, amount));
        return this;
    }

    public JasminBlock addLocalsLimit(int amount) {
        statements.add(new LimitStatement(LimitType.LOCALS, amount));
        return this;
    }

    public JasminBlock addComment(String s) {
        return addStatement(new CommentStatement(s));
    }

    public JasminBlock addMethodInvocationStatement(MethodInvocationType type, String methodName, DataType methodReturnType, DataType... paramTypes) {
        return addStatement(new MethodInvocationStatement(type, methodName, methodReturnType, Helper.dataTypeArrayToList(paramTypes)));
    }

    public JasminBlock addFieldManipulationStatement(FieldManipulationType type, String fieldSpec, DataType fieldType) {
        return addStatement(new FieldManipulationStatement(type, fieldSpec, fieldType.getRepresentation()));
    }

    public JasminBlock addLoadConstantStatement(LoadConstantType type, JasminValue value) {
        return addStatement(new LoadConstantStatement(type, value));

    }

    public JasminBlock addLocalVariableStatement(LocalVariableType type, int i) {
        return addStatement(new LocalVariableStatement(type, i));
    }

    public JasminBlock addBranchStatement(BranchType type, String s) {
        return addStatement(new BranchStatement(type, s));
    }

    public JasminBlock addObjectStatement(ObjectType type, String s) {
        return addStatement(new ObjectStatement(type, s));
    }

    public JasminBlock addNoParameterStatement(NoParameterType type) {
        return addStatement(new NoParameterStatement(type));
    }

    public JasminBlock addSwitchStatement(SwitchType type) {
        return addStatement(new SwitchStatement(type));
    }

    public JasminBlock addIntegerPushStatement(IntegerPushType type, int value) {
        return addStatement(new IntegerPushStatement(type, value));
    }

    /**
     * @param statement The statement which will be added to the blocks statements
     * @return The updated block
     */
    public JasminBlock addStatement(JasminStatement statement) {
        statements.add(statement);
        return this;
    }

    /**
     * @param statements A list of statements which will be added to the blocks statements
     * @return The updated block
     */
    public JasminBlock addStatements(List<JasminStatement> statements) {
        this.statements.addAll(statements);
        return this;
    }

    /**
     * @param variable The variable to be declared
     * @return The updated block
     */
    public JasminBlock declareVariable(Variable variable) {
        statements.add(new VariableStatement(VariableType.DECLARE, variable));
        return this;
    }

    /**
     * @param variable The variable the value should be stored in
     * @param value    The value the variable should store
     * @return The updated block
     */
    public JasminBlock storeVariable(Variable variable, JasminPassable value) {
        statements.add(new VariableStatement(VariableType.STORE, variable, value));
        return this;
    }

    /**
     * @param reference The variable reference
     * @return The updated block
     */
    public JasminBlock loadVariable(VariableReference reference) {
        statements.add(new VariableStatement(VariableType.LOAD, reference.name));
        return this;
    }

    public JasminBlock addValue(JasminPassable value) {
        addStatements(value.pushToStack());
        return this;
    }

    public JasminBlock returnValue(JasminPassable value) {
        if (value == null || value.getType() == DataType.VOID) {
            statements.add(new NoParameterStatement(NoParameterType.RETURN));
            return this;
        }

        statements.addAll(value.pushToStack());

        NoParameterType returnType;
        switch (value.getType().getType()) {
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case SHORT:
            case INTEGER:
                returnType = NoParameterType.RETURN_INTEGER;
                break;

            case FLOAT:
                returnType = NoParameterType.RETURN_FLOAT;
                break;

            case DOUBLE:
                returnType = NoParameterType.RETURN_DOUBLE;
                break;

            case LONG:
                returnType = NoParameterType.RETURN_LONG;
                break;

            case ARRAY:
            case REFERENCE:
                returnType = NoParameterType.RETURN_REFERENCE;
                break;

            default:
                returnType = NoParameterType.RETURN;
                break;
        }
        statements.add(new NoParameterStatement(returnType));

        return this;
    }

    // ========================
    // Getters & Setters
    // ========================

    /**
     * @return The blocks label
     */
    public String getLabel() {
        return name;
    }

    public List<JasminStatement> getStatements() {
        return statements;
    }

}
