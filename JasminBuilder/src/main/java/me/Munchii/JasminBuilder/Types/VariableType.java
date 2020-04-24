package me.Munchii.JasminBuilder.Types;

public enum VariableType
{

    Declare, // Same as `Store`
    Store, // Same as `Declare`
    Load, // Load variable (this is a very special case)

    // NOTE: this can be risky as the lib WON'T do ANY type checking. Use with caution!
    Stack, // No value needed, as this will use the top stack item as value

}
