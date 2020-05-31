package me.Munchii.JasminBuilder;

import me.Munchii.JasminBuilder.DataTypes.DataType;
import me.Munchii.JasminBuilder.Statements.JasminStatement;

import java.util.List;

/**
 * `JasminPassable` represents any of following: `JasminField`, `JasminValue`, `JasminVariable` or `ExpressionBuilder`
 */
public interface JasminPassable {

    /**
     * @return List of statements for pushing the value onto the stack
     */
    List<JasminStatement> pushToStack();

    /**
     * @return The data type of the passable
     */
    DataType getType();

}
