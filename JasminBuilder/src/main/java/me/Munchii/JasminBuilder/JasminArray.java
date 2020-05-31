package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.DataTypes.ArrayType;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Logging.Exceptions.AbortException;
import me.Munchii.JasminBuilder.Logging.Logger;
import me.Munchii.JasminBuilder.Logging.Message;
import me.Munchii.JasminBuilder.Statements.*;
import me.Munchii.JasminBuilder.Types.LocalVariableType;
import me.Munchii.JasminBuilder.Types.NoParameterType;
import me.Munchii.JasminBuilder.Types.ObjectType;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class JasminArray implements Variable, JasminPassable {

    private final String name;
    private int index;

    private final JasminPassable[] elements;
    private final DataType type;
    private final int size;
    private final int dimensions;
    private int indexPointer;
    private final ArrayType arrayType;

    private final JasminPassable lengthValue;

    public JasminArray(String name, DataType type, int size) {
        this(name, type, size, 1);
    }

    public JasminArray(String name, DataType type, int size, int dimensions) {
        this(name, type, size, dimensions, new JasminPassable[size]);
    }

    public JasminArray(String name, DataType type, int size, JasminPassable[] elements) {
        this(name, type, size, 1, elements);
    }

    public JasminArray(String name, DataType type, int size, int dimensions, JasminPassable[] elements) {
        this.name = name;
        this.index = -1;

        this.elements = elements;
        this.type = type;
        this.size = size;
        this.dimensions = dimensions;
        this.indexPointer = 0;
        this.arrayType = new ArrayType(type, dimensions);

        this.lengthValue = new JasminPassable() {
            @Override
            public List<JasminStatement> pushToStack() {
                // TODO: Should it be maybe long or double instead?
                return asList(Helper.pushValueToStack(new JasminValue(size, DataType.INTEGER)));
            }

            @Override
            public DataType getType() {
                return type;
            }
        };
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
        List<JasminStatement> statements = new ArrayList<>();

        if (dimensions == 1) {
            statements.add(Helper.pushValueToStack(new JasminValue(size, DataType.INTEGER)));
            statements.add(new ObjectStatement(ObjectType.A_NEW_ARRAY, type.getRepresentation()));
        } else {
            for (int dimension = 0; dimension < dimensions; dimension++) {
                // TODO: Allow for multiple sizes, ex. `String[x][y]` instead of `String[x][x]`
                statements.add(Helper.pushValueToStack(new JasminValue(size, DataType.INTEGER)));
            }

            statements.add(new MultiANewArrayStatement(type, dimensions));
        }

        statements.add(store());

        for (int index = 0; index < size; index++) {
            JasminPassable element = elements[index];
            if (element != null) {
                statements.addAll(pushToStack());
                statements.add(Helper.pushValueToStack(new JasminValue(index, DataType.INTEGER)));
                statements.addAll(element.pushToStack());
                statements.add(storeElement());
            }
        }

        return statements;
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

    //* Simple wrapper for `GetElement` method to avoid stack overflow error, since it can't access this classes `PushToStack()`
    private List<JasminStatement> load() {
        return pushToStack();
    }

    public JasminStatement storeElement() {
        switch (type.getType()) {
            case BOOLEAN:
            case BYTE:
                return new NoParameterStatement(NoParameterType.STORE_INTO_BYTE_BOOLEAN_ARRAY);

            case CHAR:
                return new NoParameterStatement(NoParameterType.STORE_INTO_CHAR_ARRAY);

            case DOUBLE:
                return new NoParameterStatement(NoParameterType.STORE_INTO_DOUBLE_ARRAY);

            case FLOAT:
                return new NoParameterStatement(NoParameterType.STORE_INTO_FLOAT_ARRAY);

            case INTEGER:
                return new NoParameterStatement(NoParameterType.STORE_INTO_INTEGER_ARRAY);

            case LONG:
                return new NoParameterStatement(NoParameterType.STORE_INTO_LONG_ARRAY);

            case SHORT:
                return new NoParameterStatement(NoParameterType.STORE_INTO_SHORT_ARRAY);

            case ARRAY:
            case REFERENCE:
                return new NoParameterStatement(NoParameterType.STORE_INTO_REFERENCE_ARRAY);
        }

        Logger.error(String.format(Message.COULD_NOT_MATCH_TYPE, Helper.getDataTypeName(type)));
        throw new AbortException();
    }

    public JasminStatement loadElement() {
        switch (type.getType()) {
            case BOOLEAN:
            case BYTE:
                return new NoParameterStatement(NoParameterType.LOAD_BYTE_BOOLEAN_FROM_ARRAY);

            case CHAR:
                return new NoParameterStatement(NoParameterType.LOAD_CHAR_FROM_ARRAY);

            case DOUBLE:
                return new NoParameterStatement(NoParameterType.LOAD_DOUBLE_FROM_ARRAY);

            case FLOAT:
                return new NoParameterStatement(NoParameterType.LOAD_FLOAT_FROM_ARRAY);

            case INTEGER:
                return new NoParameterStatement(NoParameterType.LOAD_INTEGER_FROM_ARRAY);

            case LONG:
                return new NoParameterStatement(NoParameterType.LOAD_LONG_FROM_ARRAY);

            case SHORT:
                return new NoParameterStatement(NoParameterType.LOAD_SHORT_FROM_ARRAY);

            case ARRAY:
            case REFERENCE:
                return new NoParameterStatement(NoParameterType.LOAD_REFERENCE_FROM_ARRAY);
        }

        Logger.error(String.format(Message.COULD_NOT_MATCH_TYPE, Helper.getDataTypeName(type)));
        throw new AbortException();
    }

    public JasminArray addElement(JasminPassable element) {
        return addElement(indexPointer, element);
    }

    public JasminArray addElement(int index, JasminPassable element) {
        checkType(element.getType());
        checkIndex(index);

        elements[index] = element;
        indexPointer = index + 1;

        return this;
    }

    public JasminPassable getElement(int index) {
        checkIndex(index);

        // TODO: We can maybe make this more efficient by having the `JasminPassable` values in a list?
        // ^^ This can maybe be done when reworking the array to allow for multi dimensions
        return new JasminPassable() {
            @Override
            public List<JasminStatement> pushToStack() {
                List<JasminStatement> statements = new ArrayList<>();
                statements.addAll(load());
                statements.add(Helper.pushValueToStack(new JasminValue(index, DataType.INTEGER)));
                statements.add(loadElement());

                return statements;
            }

            @Override
            public DataType getType() {
                return type;
            }
        };
    }

    private void checkType(DataType targetType) {
        if (!targetType.compare(type)) {
            Logger.error(String.format(Message.VALUE_MUST_BE_SAME_TYPE, Helper.getDataTypeName(type)));
            System.exit(0x1);
        }
    }

    private void checkIndex(int index) {
        if (index >= size) {
            Logger.error(String.format(Message.INDEX_OUT_OF_RANGE, index));
            System.exit(0x1);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public JasminPassable getValue() {
        // TODO: Is this really the best way lol?
        return elements.length > 0 ? elements[0] : new JasminValue(0, DataType.INTEGER);
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public DataType getType() {
        return arrayType;
    }

    public void setIndexPointer(int position) {
        this.indexPointer = position;
    }

    public int getIndexPointer() {
        return indexPointer;
    }

    public void incrementIndexPointer(int amount) {
        this.indexPointer += amount;
    }

    public void decrementIndexPointer(int amount) {
        this.indexPointer -= amount;
    }

    public JasminPassable getLengthValue() {
        return lengthValue;
    }

}
