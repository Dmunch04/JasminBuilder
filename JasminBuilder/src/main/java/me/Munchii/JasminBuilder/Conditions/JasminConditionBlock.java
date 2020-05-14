package me.Munchii.JasminBuilder.Conditions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;

public class JasminConditionBlock
{

    private ConditionBuilder Builder;
    private JasminBlock Block;

    public JasminConditionBlock (JasminCondition Condition, JasminBlock Block)
    {
        this (new ConditionBuilder ().AddCondition (Condition), Block);
    }

    public JasminConditionBlock (ConditionBuilder Builder, JasminBlock Block)
    {
        this.Builder = Builder;
        this.Block = Block;
    }

    public JasminConditionBlock AddCondition (JasminCondition Condition)
    {
        Builder.AddCondition (Condition);
        return this;
    }

    public ConditionBuilder GetBuilder ()
    {
        return Builder;
    }

    public JasminBlock GetBlock ()
    {
        return Block;
    }

}
