package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Conditions.JasminConditionBlock;
import me.Munchii.JasminBuilder.Logging.Logger;
import me.Munchii.JasminBuilder.Methods.JasminMethod;

import java.util.Arrays;
import java.util.List;

public class IfInstruction implements JasminInstruction
{

    private List<JasminConditionBlock> IfBlocks;
    private JasminBlock ElseBlock;

    public IfInstruction (JasminConditionBlock... IfBlocks)
    {
        this (null, IfBlocks);
    }

    public IfInstruction (JasminBlock ElseBlock, JasminConditionBlock... IfBlocks)
    {
        this.IfBlocks = Arrays.asList (IfBlocks);
        this.ElseBlock = ElseBlock;
    }

    @Override
    public void Write (JasminMethod Method)
    {
        for (JasminConditionBlock IfBlock : IfBlocks)
        {
            Method.AddStatements (IfBlock.GetBuilder ().Write (IfBlock.GetBlock ().GetLabel ()));
            Method.AddBlock (IfBlock.GetBlock ());
        }

        if (ElseBlock != null)
        {
            Method.AddComment ("Else block (" + ElseBlock.GetLabel () + ")");
            Method.AddStatements (ElseBlock.GetStatements ());
            Method.AddComment ("---");
        }
    }

    @Override
    public void Write (JasminBlock Block)
    {
        for (JasminConditionBlock IfBlock : IfBlocks)
        {
            Block.AddStatements (IfBlock.GetBuilder ().Write (IfBlock.GetBlock ().GetLabel ()));

            //* Ok so basically we cannot write a block inside a block. Which means the user already needs to have written the block
            //* If not then errors will ofc. happen when running it
            // TODO: Can we somehow prevent that the user does this?
            //Block.AddBlock (IfBlock.GetBlock ());
            Logger.Warning ("If block (" + IfBlock.GetBlock ().GetLabel () + ") will not be written! You need to write it yourself");
        }

        if (ElseBlock != null)
        {
            Block.AddComment ("Else block (" + ElseBlock.GetLabel () + ")");
            Block.AddStatements (ElseBlock.GetStatements ());
            Block.AddComment ("---");
        }
    }

}
