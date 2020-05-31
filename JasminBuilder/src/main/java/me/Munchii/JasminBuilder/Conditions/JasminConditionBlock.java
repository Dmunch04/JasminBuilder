package me.Munchii.JasminBuilder.Conditions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;

public class JasminConditionBlock {

    private final ConditionBuilder conditionBuilder;
    private final JasminBlock block;

    public JasminConditionBlock(JasminCondition condition, JasminBlock block) {
        this(new ConditionBuilder().addCondition(condition), block);
    }

    public JasminConditionBlock(ConditionBuilder conditionBuilder, JasminBlock block) {
        this.conditionBuilder = conditionBuilder;
        this.block = block;
    }

    public JasminConditionBlock addCondition(JasminCondition condition) {
        conditionBuilder.addCondition(condition);
        return this;
    }

    public ConditionBuilder getBuilder() {
        return conditionBuilder;
    }

    public JasminBlock getBlock() {
        return block;
    }

}
