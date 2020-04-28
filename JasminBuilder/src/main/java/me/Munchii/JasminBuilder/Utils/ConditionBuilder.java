package me.Munchii.JasminBuilder.Utils;

import me.Munchii.JasminBuilder.Statements.JasminStatement;

import java.util.ArrayList;
import java.util.List;

public class ConditionBuilder
{

    private String BlockLabel;
    private List<JasminCondition> Conditions;

    public ConditionBuilder (String BlockLabel)
    {
        this.BlockLabel = BlockLabel;
        this.Conditions = new ArrayList<JasminCondition> ();
    }

    // TODO: Make this and accept as many types as possible
    public List<JasminStatement> PushToStack ()
    {
        return null;
    }

    public ConditionBuilder AddCondition (JasminCondition Condition)
    {
        this.Conditions.add (Condition);
        return this;
    }

}
