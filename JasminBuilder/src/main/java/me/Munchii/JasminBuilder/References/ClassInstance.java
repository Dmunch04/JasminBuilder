package me.Munchii.JasminBuilder.References;

import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.DataTypes.ReferenceType;
import me.Munchii.JasminBuilder.JasminPassable;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.LocalVariableStatement;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Statements.ObjectStatement;
import me.Munchii.JasminBuilder.Types.LocalVariableType;
import me.Munchii.JasminBuilder.Types.NoParameterType;
import me.Munchii.JasminBuilder.Types.ObjectType;
import me.Munchii.JasminBuilder.Utils.MethodInvocation;
import me.Munchii.JasminBuilder.Variable;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class ClassInstance implements Variable, JasminPassable {

    private final String name;
    private final String className;
    private int index;
    private final DataType type;
    private final List<JasminPassable> arguments;
    private final List<DataType> paramTypes;

    public ClassInstance(String name, String className, List<JasminPassable> arguments, DataType... paramTypes) {
        this.name = name;
        this.className = className;
        this.index = -1;
        this.type = new ReferenceType(className);
        this.arguments = arguments;
        this.paramTypes = asList(paramTypes);
    }

    @Override
    public DataType getType() {
        return type;
    }

    @Override
    public JasminStatement store() {
        switch (index) {
            case 0:
                return new NoParameterStatement(NoParameterType.STORE_REFERENCE_INTO_LOCAL_VARIABLE_0);
            case 1:
                return new NoParameterStatement(NoParameterType.STORE_REFERENCE_INTO_LOCAL_VARIABLE_1);
            case 2:
                return new NoParameterStatement(NoParameterType.STORE_REFERENCE_INTO_LOCAL_VARIABLE_2);
            case 3:
                return new NoParameterStatement(NoParameterType.STORE_REFERENCE_INTO_LOCAL_VARIABLE_3);
            default:
                return new LocalVariableStatement(LocalVariableType.STORE_REFERENCE, index);
        }
    }

    @Override
    public List<JasminStatement> declare() {
        List<JasminStatement> Statements = new ArrayList<JasminStatement>();

        Statements.add(new ObjectStatement(ObjectType.NEW, className));
        Statements.add(new NoParameterStatement(NoParameterType.DUPLICATE_TOP_STACK_VALUE));
        Statements.addAll(MethodInvocation.callSpecialMethod(className, "<init>", arguments, DataType.VOID, paramTypes).pushToStack());
        Statements.add(store());

        return Statements;
    }

    @Override
    public List<JasminStatement> pushToStack() {
        switch (index) {
            case 0:
                return asList(new NoParameterStatement(NoParameterType.LOAD_REFERENCE_FROM_LOCAL_VARIABLE_0));
            case 1:
                return asList(new NoParameterStatement(NoParameterType.LOAD_REFERENCE_FROM_LOCAL_VARIABLE_1));
            case 2:
                return asList(new NoParameterStatement(NoParameterType.LOAD_REFERENCE_FROM_LOCAL_VARIABLE_2));
            case 3:
                return asList(new NoParameterStatement(NoParameterType.LOAD_REFERENCE_FROM_LOCAL_VARIABLE_3));
            default:
                return asList(new LocalVariableStatement(LocalVariableType.LOAD_REFERENCE, index));
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    // TODO: Hmm
    public JasminPassable getValue() {
        return null;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }
}
