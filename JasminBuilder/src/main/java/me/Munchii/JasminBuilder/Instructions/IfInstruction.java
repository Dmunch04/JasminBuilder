package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Methods.JasminMethod;
import me.Munchii.JasminBuilder.Utils.ConditionBuilder;

public class IfInstruction implements JasminInstruction
{

    private ConditionBuilder Builder;
    private JasminBlock IfBlock;
    private JasminBlock ElseBlock;

    public IfInstruction (ConditionBuilder Builder, JasminBlock IfBlock)
    {
        this (Builder, IfBlock, null);
    }

    public IfInstruction (ConditionBuilder Builder, JasminBlock IfBlock, JasminBlock ElseBlock)
    {
        this.Builder = Builder;
        this.IfBlock = IfBlock;
        this.ElseBlock = ElseBlock;
    }

    @Override
    public void Write (JasminMethod Method)
    {
        Method.AddStatements (Builder.PushToStack ());
        Method.AddBlock (IfBlock);

        if (ElseBlock != null)
            Method.AddBlock (ElseBlock);
    }

    @Override
    public void Write (JasminBlock Block)
    {
        // TODO: Make a better logging system
        // TODO: Explain this better lol
        System.out.println ("WARNING: Blocks can't contain blocks! Make sure to write blocks to method");
        Block.AddStatements (Builder.PushToStack ());
    }

}
