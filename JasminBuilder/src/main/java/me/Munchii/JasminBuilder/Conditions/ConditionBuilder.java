package me.Munchii.JasminBuilder.Conditions;

import me.Munchii.JasminBuilder.Logging.Exceptions.AbortException;
import me.Munchii.JasminBuilder.Logging.Logger;
import me.Munchii.JasminBuilder.Statements.BranchStatement;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Types.BranchType;
import me.Munchii.JasminBuilder.Types.ConditionType;
import me.Munchii.JasminBuilder.Types.NoParameterType;
import me.Munchii.JasminBuilder.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class ConditionBuilder {

    private final List<JasminCondition> conditions;

    public ConditionBuilder() {
        this.conditions = new ArrayList<>();
    }

    public ConditionBuilder(JasminCondition... conditions) {
        this.conditions = asList(conditions);
    }

    public List<JasminStatement> write(String label) {
        List<JasminStatement> statements = new ArrayList<>();

        for (JasminCondition condition : conditions) {
            statements.addAll(condition.getFirstValue().pushToStack());
            statements.addAll(condition.getLastValue().pushToStack());

            ConditionType type = condition.getType();

            switch (condition.getValueType().getType()) {
                // TODO: If one if the values is `false` it should just use `ifne` (etc) and not push the value
                case BOOLEAN: {
                    switch (type) {
                        case EQUALS:
                            statements.add(new BranchStatement(BranchType.IF_INTEGER_COMPARE_EQUALS, label));
                            continue;
                        case NOT_EQUALS:
                            statements.add(new BranchStatement(BranchType.IF_INTEGER_COMPARE_NOT_EQUALS, label));
                            continue;
                        default: {
                            Logger.error("Cannot perform condition check: '" + Helper.conditionTypeToRepresentation(type) + "' on boolean!");
                            throw new AbortException();
                        }
                    }
                }

                case DOUBLE: {
                    switch (type) {
                        case EQUALS: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_DOUBLE_LESS));
                            statements.add(new BranchStatement(BranchType.IF_EQUALS, label));
                            continue;
                        }

                        case NOT_EQUALS: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_DOUBLE_LESS));
                            statements.add(new BranchStatement(BranchType.IF_NOT_EQUALS, label));
                            continue;
                        }

                        case LESS_THAN: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_DOUBLE_LESS));
                            statements.add(new BranchStatement(BranchType.IF_LESS_THAN, label));
                            continue;
                        }

                        case LESS_THAN_EQUALS: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_DOUBLE_LESS));
                            statements.add(new BranchStatement(BranchType.IF_LESS_EQUALS, label));
                            continue;
                        }

                        case GREATER_THAN: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_DOUBLE_GREATER));
                            statements.add(new BranchStatement(BranchType.IF_GREATER_THAN, label));
                            continue;
                        }

                        case GREATER_THAN_EQUALS: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_DOUBLE_GREATER));
                            statements.add(new BranchStatement(BranchType.IF_GREATER_EQUALS, label));
                            continue;
                        }
                    }
                }

                case FLOAT: {
                    switch (type) {
                        case EQUALS: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_FLOAT_LESS));
                            statements.add(new BranchStatement(BranchType.IF_EQUALS, label));
                            continue;
                        }

                        case NOT_EQUALS: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_FLOAT_LESS));
                            statements.add(new BranchStatement(BranchType.IF_NOT_EQUALS, label));
                            continue;
                        }

                        case LESS_THAN: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_FLOAT_LESS));
                            statements.add(new BranchStatement(BranchType.IF_LESS_THAN, label));
                            continue;
                        }

                        case LESS_THAN_EQUALS: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_FLOAT_LESS));
                            statements.add(new BranchStatement(BranchType.IF_LESS_EQUALS, label));
                            continue;
                        }

                        case GREATER_THAN: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_FLOAT_GREATER));
                            statements.add(new BranchStatement(BranchType.IF_GREATER_THAN, label));
                            continue;
                        }

                        case GREATER_THAN_EQUALS: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_FLOAT_GREATER));
                            statements.add(new BranchStatement(BranchType.IF_GREATER_EQUALS, label));
                            continue;
                        }
                    }
                }

                //? TODO: Should byte be here too? In my testing it acted like ints so I guess
                case BYTE:
                case CHAR:
                case SHORT:
                case INTEGER: {
                    switch (type) {
                        case EQUALS:
                            statements.add(new BranchStatement(BranchType.IF_INTEGER_COMPARE_EQUALS, label));
                            continue;
                        case NOT_EQUALS:
                            statements.add(new BranchStatement(BranchType.IF_INTEGER_COMPARE_NOT_EQUALS, label));
                            continue;
                        case LESS_THAN:
                            statements.add(new BranchStatement(BranchType.IF_INTEGER_COMPARE_LESS_THAN, label));
                            continue;
                        case LESS_THAN_EQUALS:
                            statements.add(new BranchStatement(BranchType.IF_INTEGER_COMPARE_LESS_EQUALS, label));
                            continue;
                        case GREATER_THAN:
                            statements.add(new BranchStatement(BranchType.IF_INTEGER_COMPARE_GREATER_THAN, label));
                            continue;
                        case GREATER_THAN_EQUALS:
                            statements.add(new BranchStatement(BranchType.IF_INTEGER_COMPARE_GREATER_EQUALS, label));
                            continue;
                    }
                }

                case LONG: {
                    switch (type) {
                        case EQUALS: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_LONG));
                            statements.add(new BranchStatement(BranchType.IF_EQUALS, label));
                            continue;
                        }

                        case NOT_EQUALS: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_LONG));
                            statements.add(new BranchStatement(BranchType.IF_NOT_EQUALS, label));
                            continue;
                        }

                        case LESS_THAN: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_LONG));
                            statements.add(new BranchStatement(BranchType.IF_LESS_THAN, label));
                            continue;
                        }

                        case LESS_THAN_EQUALS: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_LONG));
                            statements.add(new BranchStatement(BranchType.IF_LESS_EQUALS, label));
                            continue;
                        }

                        case GREATER_THAN: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_LONG));
                            statements.add(new BranchStatement(BranchType.IF_GREATER_THAN, label));
                            continue;
                        }

                        case GREATER_THAN_EQUALS: {
                            statements.add(new NoParameterStatement(NoParameterType.COMPARE_LONG));
                            statements.add(new BranchStatement(BranchType.IF_GREATER_EQUALS, label));
                            continue;
                        }
                    }
                }

                case VOID: {
                    switch (type) {
                        case EQUALS:
                            statements.add(new BranchStatement(BranchType.IF_NON_NULL, label));
                            continue;
                        case NOT_EQUALS:
                            statements.add(new BranchStatement(BranchType.IF_NULL, label));
                            continue;
                        default: {
                            Logger.error("Cannot perform condition check: '" + Helper.conditionTypeToRepresentation(type) + "' on null!");
                            throw new AbortException();
                        }
                    }
                }

                case ARRAY:
                case REFERENCE: {
                    switch (type) {
                        case EQUALS:
                            statements.add(new BranchStatement(BranchType.IF_REFERENCE_COMPARE_EQUALS, label));
                            continue;
                        case NOT_EQUALS:
                            statements.add(new BranchStatement(BranchType.IF_REFERENCE_COMPARE_NOT_EQUALS, label));
                            continue;
                        default: {
                            Logger.error("Cannot perform condition check: '" + Helper.conditionTypeToRepresentation(type) + "' on reference!");
                            throw new AbortException();
                        }
                    }
                }
            }
        }

        return statements;
    }

    public ConditionBuilder addCondition(JasminCondition condition) {
        this.conditions.add(condition);
        return this;
    }

}
