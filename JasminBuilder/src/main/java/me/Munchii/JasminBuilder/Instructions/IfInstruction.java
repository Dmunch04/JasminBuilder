package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Conditions.JasminConditionBlock;
import me.Munchii.JasminBuilder.Logging.Logger;
import me.Munchii.JasminBuilder.Methods.JasminMethod;

import java.util.Arrays;
import java.util.List;

public class IfInstruction implements JasminInstruction {

    private final List<JasminConditionBlock> ifBlocks;
    private final JasminBlock elseBlock;

    public IfInstruction(JasminConditionBlock... ifBlocks) {
        this(null, ifBlocks);
    }

    public IfInstruction(JasminBlock elseBlock, JasminConditionBlock... ifBlocks) {
        this.ifBlocks = Arrays.asList(ifBlocks);
        this.elseBlock = elseBlock;
    }

    @Override
    public void write(JasminMethod method) {
        for (JasminConditionBlock ifBlock : ifBlocks) {
            method.addStatements(ifBlock.getBuilder().write(ifBlock.getBlock().getLabel()));
            method.addBlock(ifBlock.getBlock());
        }

        if (elseBlock != null) {
            method.addComment("Else block (" + elseBlock.getLabel() + ")");
            method.addStatements(elseBlock.getStatements());
            method.addComment("---");
        }
    }

    @Override
    public void write(JasminBlock block) {
        for (JasminConditionBlock ifBlock : ifBlocks) {
            block.addStatements(ifBlock.getBuilder().write(ifBlock.getBlock().getLabel()));

            //* Ok so basically we cannot write a block inside a block. Which means the user already needs to have written the block
            //* If not then errors will ofc. happen when running it
            // TODO: Can we somehow prevent that the user does this?
            //Block.AddBlock (ifBlock.GetBlock ());
            Logger.warning("If block (" + ifBlock.getBlock().getLabel() + ") will not be written! You need to write it yourself");
        }

        if (elseBlock != null) {
            block.addComment("Else block (" + elseBlock.getLabel() + ")");
            block.addStatements(elseBlock.getStatements());
            block.addComment("---");
        }
    }

}
