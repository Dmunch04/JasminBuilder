package me.Munchii.JasminBuilder.Methods;

import me.Munchii.JasminBuilder.*;
import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Instructions.JasminInstruction;
import me.Munchii.JasminBuilder.References.VariableReference;
import me.Munchii.JasminBuilder.Statements.*;
import me.Munchii.JasminBuilder.Types.*;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class JasminMethod implements Builder {

    private final List<MethodAccessSpec> accessSpecs;
    private final String methodName;
    private final DataType methodReturnType;
    private List<DataType> paramTypes;
    private final List<JasminStatement> statements;
    private final List<JasminBlock> blocks;

    private int stack;
    private int locals;
    private boolean didReturn;

    private final Map<String, Variable> variables;
    private int variableIndex;

    private JasminScope scope;

    public JasminMethod(String methodName, DataType methodReturnType, MethodAccessSpec... accessSpecs) {
        this(methodName, methodReturnType, asList(accessSpecs), null);
    }

    public JasminMethod(String methodName, DataType methodReturnType, List<MethodAccessSpec> accessSpecs, DataType... paramTypes) {
        this.accessSpecs = accessSpecs;
        this.methodName = methodName;
        this.methodReturnType = methodReturnType;

        if (paramTypes != null)
            this.paramTypes = asList(paramTypes);
        else
            this.paramTypes = new ArrayList<>();

        this.statements = new ArrayList<>();
        this.blocks = new ArrayList<>();

        this.stack = 0;
        this.locals = paramTypes != null ? paramTypes.length : 0;
        this.didReturn = false;

        this.variables = new HashMap<>();
        this.variableIndex = 0;

        this.scope = new JasminScope();

        if (!accessSpecs.contains(MethodAccessSpec.STATIC)) {
            variables.put("this", new JasminVariable("this", variableIndex, new JasminValue(null, DataType.EMPTY_REFERENCE)));
            variableIndex++;
        }

        if (paramTypes != null) {
            for (int i = 0; i < paramTypes.length; i++) {
                String argName = "arg" + (i + 1);
                variables.put(argName, new JasminVariable(argName, variableIndex, new JasminValue(null, paramTypes[i])));
                variableIndex++;
            }
        }
    }

    @Override
    public String toOutputString() {
        StringBuilder builder = new StringBuilder();
        builder.append(".method").append(" ");
        accessSpecs.forEach(Spec -> builder.append(Spec.getRepresentation()).append(" "));
        builder.append(Helper.makeMethodSpec(methodName, methodReturnType, paramTypes)).append("\n");

        if (stack != 0)
            builder.append("\t").append(".limit stack").append(" ").append(stack).append("\n");
        if (locals != 0)
            builder.append("\t").append(".limit locals").append(" ").append(locals).append("\n");

        for (JasminStatement statement : scope.getStatements()) {
            builder.append("\t").append(statement.toOutputString()).append("\n");
        }

        JasminScope outerScope = scope;
        for (JasminBlock block : blocks) {
            scope = new JasminScope();
            builder.append(block.getLabel()).append(":\n");
            block.write(this);

            for (JasminStatement statement : scope.getStatements()) {
                builder.append("\t").append(statement.toOutputString()).append("\n");
            }
        }
        scope = outerScope;

        // If the user haven't added a return statement, return void
        // TODO: Well doesn't work well with scopes ay
        if (!didReturn)
            builder.append("\t").append("return").append("\n");

        builder.append(".end method");

        return builder.toString();
    }

    // ========================
    // User methods
    // ========================

    public JasminMethod addInstruction(JasminInstruction instruction) {
        instruction.write(this);
        return this;
    }

    public JasminMethod addStackLimit(int amount) {
        stack += amount;
        return this;
    }

    public JasminMethod addLocalsLimit(int amount) {
        locals += amount;
        return this;
    }

    public JasminMethod addComment(String s) {
        return addStatement(new CommentStatement(s));
    }

    public JasminMethod addMethodInvocationStatement(MethodInvocationType type, String methodName, DataType methodReturnType, DataType... args) {
        return addStatement(new MethodInvocationStatement(type, methodName, methodReturnType, Helper.dataTypeArrayToList(args)));
    }

    public JasminMethod addFieldManipulationStatement(FieldManipulationType type, String fieldSpec, DataType fieldType) {
        return addStatement(new FieldManipulationStatement(type, fieldSpec, fieldType.getRepresentation()));
    }

    public JasminMethod addLoadConstantStatement(LoadConstantType type, JasminValue value) {
        return addStatement(new LoadConstantStatement(type, value));
    }

    public JasminMethod addLocalVariableStatement(LocalVariableType type, int i) {
        return addStatement(new LocalVariableStatement(type, i));
    }

    public JasminMethod addBranchStatement(BranchType type, String s) {
        return addStatement(new BranchStatement(type, s));
    }

    public JasminMethod addObjectStatement(ObjectType type, String s) {
        return addStatement(new ObjectStatement(type, s));
    }

    public JasminMethod addNoParameterStatement(NoParameterType type) {
        // If the user defines their own return statement, we don't need to add our own void return
        if (
                type == NoParameterType.RETURN ||
                type == NoParameterType.RETURN_INTEGER ||
                type == NoParameterType.RETURN_DOUBLE ||
                type == NoParameterType.RETURN_FLOAT ||
                type == NoParameterType.RETURN_LONG ||
                type == NoParameterType.RETURN_REFERENCE
        )
            didReturn = true;

        return addStatement(new NoParameterStatement(type));
    }

    public JasminMethod addSwitchStatement(SwitchType type) {
        return addStatement(new SwitchStatement(type));
    }

    public JasminMethod addIntegerPushStatement(IntegerPushType type, int value) {
        return addStatement(new IntegerPushStatement(type, value));
    }

    public JasminMethod addStatement(JasminStatement statement) {
        if (statement instanceof ReturnStatement) this.didReturn = true;

        if (checkStatement(statement))
            scope.addStatement(statement);

        return this;
    }

    public JasminMethod addStatements(List<JasminStatement> statements) {
        if (statements.stream().anyMatch(Statement -> Statement instanceof ReturnStatement)) this.didReturn = true;

        for (JasminStatement statement : statements)
            addStatement(statement);

        return this;
    }

    public JasminMethod addBlock(JasminBlock block) {
        blocks.add(block);
        return this;
    }

    /**
     * @param variable The target variable which will be initialized with it's value
     * @return Returns the updated method
     */
    public JasminMethod declareVariable(Variable variable) {
        if (variable.getIndex() == -1)
            variable.setIndex(variableIndex++);

        addStatements(variable.declare());

        variables.put(variable.getName(), variable);

        return this;
    }

    public JasminMethod storeVariable(Variable variable, JasminPassable value) {
        if (variable instanceof JasminArray) {
            addStatements(variable.pushToStack());
            addStatement(Helper.pushValueToStack(new JasminValue(((JasminArray) variable).getIndexPointer(), DataType.INTEGER)));

            ((JasminArray) variable).addElement(value);
        }

        addStatements(value.pushToStack());

        if (variable instanceof JasminArray) addStatement(((JasminArray) variable).storeElement());
        else addStatement(variable.store());

        return this;
    }

    public JasminMethod loadVariable(VariableReference reference) {
        if (!variables.containsKey(reference.name))
            throw new IllegalArgumentException("No variable with name exists: " + reference.name);

        addStatements(variables.get(reference.name).pushToStack());

        return this;
    }

    public JasminMethod addValue(JasminPassable value) {
        if (value instanceof VariableReference) {
            return loadVariable((VariableReference) value);
        }

        addStatements(value.pushToStack());
        return this;
    }

    public JasminMethod returnValue(JasminPassable value) {
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
    // Helpers
    // ========================

    // TODO: Is this really the best way? I guess but eh
    private boolean checkStatement(JasminStatement statement) {
        if (statement instanceof VariableStatement) {
            VariableStatement variable = (VariableStatement) statement;
            switch (variable.getType()) {
                case DECLARE:
                    declareVariable(variable.getVariable());
                case STORE:
                    storeVariable(variable.getVariable(), variable.getValue());
                case LOAD:
                    loadVariable(variable.getReference());
            }
            return false;
        } else if (statement instanceof LimitStatement) {
            LimitStatement limit = (LimitStatement) statement;
            switch (limit.getType()) {
                case STACK:
                    addStackLimit(limit.getAmount());
                case LOCALS:
                    addLocalsLimit(limit.getAmount());
            }
            return false;
        }

        return true;
    }

    // ========================
    // Getters & Setters
    // ========================

    public List<MethodAccessSpec> getAccessSpecs() {
        return accessSpecs;
    }

    public JasminMethod addAccessSpec(MethodAccessSpec accessSpec) {
        if (!this.accessSpecs.contains(accessSpec))
            this.accessSpecs.add(accessSpec);

        return this;
    }

    public JasminMethod removeAccessSpec(MethodAccessSpec accessSpec) {
        this.accessSpecs.remove(accessSpec);
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public DataType getMethodReturnType() {
        return methodReturnType;
    }

    public List<DataType> getParamTypes() {
        return paramTypes;
    }

    public List<JasminStatement> getStatements() {
        return statements;
    }

    public List<JasminBlock> getBlocks() {
        return blocks;
    }

    public void setParamTypes(List<DataType> paramTypes) {
        this.paramTypes = paramTypes;
    }

    public void addParamType(DataType paramType) {
        paramTypes.add(paramType);
    }

    public Map<String, Variable> getVariables() {
        return variables;
    }

    public Variable getVariable(String name) {
        if (!variables.containsKey(name))
            throw new IllegalArgumentException("No variable with name exists: " + name);

        return variables.get(name);
    }

    public Variable getVariable(VariableReference reference) {
        return getVariable(reference.name);
    }

}
