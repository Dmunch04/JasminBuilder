package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.Logging.Exceptions.AbortException;
import me.Munchii.JasminBuilder.Logging.Logger;
import me.Munchii.JasminBuilder.Statements.BranchStatement;
import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Statements.NoParameterStatement;
import me.Munchii.JasminBuilder.Types.BranchType;
import me.Munchii.JasminBuilder.Types.ConditionType;
import me.Munchii.JasminBuilder.Types.NoParameterType;

import java.util.ArrayList;
import java.util.List;

public class ConditionBuilder
{

    private final List<JasminCondition> Conditions;

    public ConditionBuilder ()
    {
        this.Conditions = new ArrayList<JasminCondition> ();
    }

    public List<JasminStatement> Write (String BlockLabel)
    {
        List<JasminStatement> Statements = new ArrayList<JasminStatement> ();

        for (JasminCondition Condition : Conditions)
        {
            Statements.addAll (Condition.GetValue1 ().PushToStack ());
            Statements.addAll (Condition.GetValue2 ().PushToStack ());

            ConditionType Type = Condition.GetType ();

            switch (Condition.GetValueType ().GetType ())
            {
                // TODO: If one if the values is `false` it should just use `ifne` (etc) and not push the value
                case Boolean: {
                    switch (Type)
                    {
                        case Equals: Statements.add (new BranchStatement (BranchType.IfIntegerCompareEquals, BlockLabel)); continue;
                        case NotEquals: Statements.add (new BranchStatement (BranchType.IfIntegerCompareNotEquals, BlockLabel)); continue;
                        default: {
                            Logger.Error ("Cannot perform condition check: '" + Helper.ConditionTypeToRepresentation (Type) + "' on boolean!");
                            throw new AbortException ();
                        }
                    }
                }

                case Double: {
                    switch (Type)
                    {
                        case Equals: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareDoubleLess));
                            Statements.add (new BranchStatement (BranchType.IfEquals, BlockLabel));
                            continue;
                        }

                        case NotEquals: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareDoubleLess));
                            Statements.add (new BranchStatement (BranchType.IfNotEquals, BlockLabel));
                            continue;
                        }

                        case LessThan: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareDoubleLess));
                            Statements.add (new BranchStatement (BranchType.IfLessThan, BlockLabel));
                            continue;
                        }

                        case LessThanEquals: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareDoubleLess));
                            Statements.add (new BranchStatement (BranchType.IfLessEquals, BlockLabel));
                            continue;
                        }

                        case GreaterThan: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareDoubleGreater));
                            Statements.add (new BranchStatement (BranchType.IfGreaterThan, BlockLabel));
                            continue;
                        }

                        case GreaterThanEquals: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareDoubleGreater));
                            Statements.add (new BranchStatement (BranchType.IfGreaterEquals, BlockLabel));
                            continue;
                        }
                    }
                }

                case Float: {
                    switch (Type)
                    {
                        case Equals: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareFloatLess));
                            Statements.add (new BranchStatement (BranchType.IfEquals, BlockLabel));
                            continue;
                        }

                        case NotEquals: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareFloatLess));
                            Statements.add (new BranchStatement (BranchType.IfNotEquals, BlockLabel));
                            continue;
                        }

                        case LessThan: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareFloatLess));
                            Statements.add (new BranchStatement (BranchType.IfLessThan, BlockLabel));
                            continue;
                        }

                        case LessThanEquals: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareFloatLess));
                            Statements.add (new BranchStatement (BranchType.IfLessEquals, BlockLabel));
                            continue;
                        }

                        case GreaterThan: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareFloatGreater));
                            Statements.add (new BranchStatement (BranchType.IfGreaterThan, BlockLabel));
                            continue;
                        }

                        case GreaterThanEquals: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareFloatGreater));
                            Statements.add (new BranchStatement (BranchType.IfGreaterEquals, BlockLabel));
                            continue;
                        }
                    }
                }

                //? TODO: Should byte be here too? In my testing it acted like ints so I guess
                case Byte:
                case Char:
                case Short:
                case Integer: {
                    switch (Type)
                    {
                        case Equals: Statements.add (new BranchStatement (BranchType.IfIntegerCompareEquals, BlockLabel)); continue;
                        case NotEquals: Statements.add (new BranchStatement (BranchType.IfIntegerCompareNotEquals, BlockLabel)); continue;
                        case LessThan: Statements.add (new BranchStatement (BranchType.IfIntegerCompareLessThan, BlockLabel)); continue;
                        case LessThanEquals: Statements.add (new BranchStatement (BranchType.IfIntegerCompareLessEquals, BlockLabel)); continue;
                        case GreaterThan: Statements.add (new BranchStatement (BranchType.IfIntegerCompareGreaterThan, BlockLabel)); continue;
                        case GreaterThanEquals: Statements.add (new BranchStatement (BranchType.IfIntegerCompareGreaterEquals, BlockLabel)); continue;
                    }
                }

                case Long: {
                    switch (Type)
                    {
                        case Equals: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareLong));
                            Statements.add (new BranchStatement (BranchType.IfEquals, BlockLabel));
                            continue;
                        }

                        case NotEquals: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareLong));
                            Statements.add (new BranchStatement (BranchType.IfNotEquals, BlockLabel));
                            continue;
                        }

                        case LessThan: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareLong));
                            Statements.add (new BranchStatement (BranchType.IfLessThan, BlockLabel));
                            continue;
                        }

                        case LessThanEquals: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareLong));
                            Statements.add (new BranchStatement (BranchType.IfLessEquals, BlockLabel));
                            continue;
                        }

                        case GreaterThan: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareLong));
                            Statements.add (new BranchStatement (BranchType.IfGreaterThan, BlockLabel));
                            continue;
                        }

                        case GreaterThanEquals: {
                            Statements.add (new NoParameterStatement (NoParameterType.CompareLong));
                            Statements.add (new BranchStatement (BranchType.IfGreaterEquals, BlockLabel));
                            continue;
                        }
                    }
                }

                case Void: {
                    switch (Type)
                    {
                        case Equals: Statements.add (new BranchStatement (BranchType.IfNonNull, BlockLabel)); continue;
                        case NotEquals: Statements.add (new BranchStatement (BranchType.IfNull, BlockLabel)); continue;
                        default: {
                            Logger.Error ("Cannot perform condition check: '" + Helper.ConditionTypeToRepresentation (Type) + "' on null!");
                            throw new AbortException ();
                        }
                    }
                }

                case Array:
                case Reference: {
                    switch (Type)
                    {
                        case Equals: Statements.add (new BranchStatement (BranchType.IfReferenceCompareEquals, BlockLabel)); continue;
                        case NotEquals: Statements.add (new BranchStatement (BranchType.IfReferenceCompareNotEquals, BlockLabel)); continue;
                        default: {
                            Logger.Error ("Cannot perform condition check: '" + Helper.ConditionTypeToRepresentation (Type) + "' on reference!");
                            throw new AbortException ();
                        }
                    }
                }
            }
        }

        return Statements;
    }

    public ConditionBuilder AddCondition (JasminCondition Condition)
    {
        this.Conditions.add (Condition);
        return this;
    }

}
