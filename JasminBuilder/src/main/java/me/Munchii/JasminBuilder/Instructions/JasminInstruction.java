package me.Munchii.JasminBuilder.Instructions;

import me.Munchii.JasminBuilder.Blocks.JasminBlock;
import me.Munchii.JasminBuilder.Methods.JasminMethod;

public interface JasminInstruction
{

    void Write (JasminMethod Method);
    void Write (JasminBlock Block);

}
