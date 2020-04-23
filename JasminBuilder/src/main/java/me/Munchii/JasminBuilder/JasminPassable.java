package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.Statements.JasminStatement;
import me.Munchii.JasminBuilder.Types.DataType;

import java.util.List;

public interface JasminPassable
{

    List<JasminStatement> PushToStack ();
    DataType GetType ();

}
