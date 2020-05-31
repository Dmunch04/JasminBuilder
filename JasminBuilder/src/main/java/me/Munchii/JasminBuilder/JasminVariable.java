package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.LocalVariableStatement;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Types.LocalVariableType;
import me.Munchii.JasminBuilder.Types.NoParameterType;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * `JasminVariable` represents a local variable in Jasmin.
 * It holds extra information which will not be used when writing to Jasmin code, but to make it easier for the user
 */
public class JasminVariable implements Variable, JasminPassable {

    private final String name;
    private int index;
    private final JasminPassable value;
    private final DataType type;

    /**
     * @param name  The name of the variable
     * @param value The value of the variable
     */
    public JasminVariable(String name, JasminPassable value) {
        this(name, -1, value);
    }

    /**
     * @param name  The name of the variable
     * @param index The variables local index (Use with caution)
     * @param value The variables value
     */
    public JasminVariable(String name, int index, JasminPassable value) {
        this.name = name;
        this.index = index;
        this.value = value;
        this.type = value.getType();
    }

    /**
     * @return A statement that stores the top most stack value into the variable
     */
    @Override
    public JasminStatement store() {
        // TODO: Implement rest of the types
        switch (type.getType()) {
            case DOUBLE: {
                switch (index) {
                    case 0:
                        return new NoParameterStatement(NoParameterType.STORE_DOUBLE_INTO_LOCAL_VARIABLE_0);
                    case 1:
                        return new NoParameterStatement(NoParameterType.STORE_DOUBLE_INTO_LOCAL_VARIABLE_1);
                    case 2:
                        return new NoParameterStatement(NoParameterType.STORE_DOUBLE_INTO_LOCAL_VARIABLE_2);
                    case 3:
                        return new NoParameterStatement(NoParameterType.STORE_DOUBLE_INTO_LOCAL_VARIABLE_3);
                    default:
                        return new LocalVariableStatement(LocalVariableType.STORE_DOUBLE, index);
                }
            }

            case FLOAT: {
                switch (index) {
                    case 0:
                        return new NoParameterStatement(NoParameterType.STORE_FLOAT_0);
                    case 1:
                        return new NoParameterStatement(NoParameterType.STORE_FLOAT_1);
                    case 2:
                        return new NoParameterStatement(NoParameterType.STORE_FLOAT_2);
                    case 3:
                        return new NoParameterStatement(NoParameterType.STORE_FLOAT_3);
                    default:
                        return new LocalVariableStatement(LocalVariableType.STORE_FLOAT, index);
                }
            }

            case BOOLEAN:
            case INTEGER: {
                switch (index) {
                    case 0:
                        return new NoParameterStatement(NoParameterType.STORE_INTEGER_0);
                    case 1:
                        return new NoParameterStatement(NoParameterType.STORE_INTEGER_1);
                    case 2:
                        return new NoParameterStatement(NoParameterType.STORE_INTEGER_2);
                    case 3:
                        return new NoParameterStatement(NoParameterType.STORE_INTEGER_3);
                    default:
                        return new LocalVariableStatement(LocalVariableType.STORE_INTEGER, index);
                }
            }

            case LONG: {
                switch (index) {
                    case 0:
                        return new NoParameterStatement(NoParameterType.STORE_LONG_0);
                    case 1:
                        return new NoParameterStatement(NoParameterType.STORE_LONG_1);
                    case 2:
                        return new NoParameterStatement(NoParameterType.STORE_LONG_2);
                    case 3:
                        return new NoParameterStatement(NoParameterType.STORE_LONG_3);
                    default:
                        return new LocalVariableStatement(LocalVariableType.STORE_LONG, index);
                }
            }

            case REFERENCE:
            case ARRAY: {
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
        }

        throw new IllegalArgumentException("Could not match data type: " + type.getRepresentation());
    }

    @Override
    public List<JasminStatement> declare() {
        List<JasminStatement> statements = new ArrayList<>(value.pushToStack());
        statements.add(store());

        return statements;
    }

    @Override
    public List<JasminStatement> pushToStack() {
        // TODO: Implement rest of the types
        switch (type.getType()) {
            case DOUBLE: {
                switch (index) {
                    case 0:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_DOUBLE_FROM_LOCAL_VARIABLE_0));
                    case 1:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_DOUBLE_FROM_LOCAL_VARIABLE_1));
                    case 2:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_DOUBLE_FROM_LOCAL_VARIABLE_2));
                    case 3:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_DOUBLE_FROM_LOCAL_VARIABLE_3));
                    default:
                        return asList(new LocalVariableStatement(LocalVariableType.LOAD_DOUBLE, index));
                }
            }

            case FLOAT: {
                switch (index) {
                    case 0:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_FLOAT_0));
                    case 1:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_FLOAT_1));
                    case 2:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_FLOAT_2));
                    case 3:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_FLOAT_3));
                    default:
                        return asList(new LocalVariableStatement(LocalVariableType.LOAD_FLOAT, index));
                }
            }

            case INTEGER: {
                switch (index) {
                    case 0:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_INTEGER_0));
                    case 1:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_INTEGER_1));
                    case 2:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_INTEGER_2));
                    case 3:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_INTEGER_3));
                    default:
                        return asList(new LocalVariableStatement(LocalVariableType.LOAD_INTEGER, index));
                }
            }

            case LONG: {
                switch (index) {
                    case 0:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_LONG_0));
                    case 1:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_LONG_1));
                    case 2:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_LONG_2));
                    case 3:
                        return asList(new NoParameterStatement(NoParameterType.LOAD_LONG_3));
                    default:
                        return asList(new LocalVariableStatement(LocalVariableType.LOAD_LONG, index));
                }
            }

            case REFERENCE:
            case ARRAY: {
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
        }

        throw new IllegalArgumentException("Could not match data type: " + type.getRepresentation());
    }

    /**
     * @return The name of the variable
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return The local index of the variable
     */
    @Override
    public int getIndex() {
        return index;
    }

    /**
     * @param index The new local index for the variable
     */
    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return The variables value
     */
    @Override
    public JasminPassable getValue() {
        return value;
    }

    @Override
    public DataType getType() {
        return type;
    }

}
