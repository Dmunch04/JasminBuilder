package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Conditions.JasminConditionBlock;
import me.Munchii.JasminBuilder.Logging.Logger;
import me.Munchii.JasminBuilder.Methods.JasminMethod;

public class WhileInstruction implements JasminInstruction {

    private final JasminConditionBlock conditionBlock;

    public WhileInstruction(JasminConditionBlock condBlock) {
        this.conditionBlock = condBlock;
    }

    @Override
    public void write(JasminMethod method) {
        method.addStatements(conditionBlock.getBuilder().write(conditionBlock.getBlock().getLabel()));
        method.addBlock(conditionBlock.getBlock().addStatements(
                conditionBlock.getBuilder().write(conditionBlock.getBlock().getLabel())
        ));
    }

    @Override
    public void write(JasminBlock block) {
        block.addStatements(conditionBlock.getBuilder().write(conditionBlock.getBlock().getLabel()));
        Logger.warning("While block (" + conditionBlock.getBlock().getLabel() + ") will not be written! You need to write it yourself");
    }

}
