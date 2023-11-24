package me.munchii.Jasmin.instruction.type;

public enum NoParameterInstructionType {
    // Load reference from array
    LOAD_REFERENCE_FROM_ARRAY("aaload"),

    // Store into reference array
    STORE_INTO_REFERENCE_ARRAY("aastore"),

    // Push null
    PUSH_NULL("aconst_null"),

    // Load reference from local variable
    LOAD_REFERENCE_FROM_LOCAL_VARIABLE_0("aload_0"),
    LOAD_REFERENCE_FROM_LOCAL_VARIABLE_1("aload_1"),
    LOAD_REFERENCE_FROM_LOCAL_VARIABLE_2("aload_2"),
    LOAD_REFERENCE_FROM_LOCAL_VARIABLE_3("aload_3"),

    // Return reference from method
    RETURN_REFERENCE("areturn"),

    // Get length of array
    ARRAY_LENGTH("arraylength"),

    // Store reference into local variable
    STORE_REFERENCE_INTO_LOCAL_VARIABLE_0("astore_0"),
    STORE_REFERENCE_INTO_LOCAL_VARIABLE_1("astore_1"),
    STORE_REFERENCE_INTO_LOCAL_VARIABLE_2("astore_2"),
    STORE_REFERENCE_INTO_LOCAL_VARIABLE_3("astore_3"),

    // Throw exception or error
    THROW_EXCEPTION("athrow"),

    // Load byte or boolean from array
    LOAD_BYTE_BOOLEAN_FROM_ARRAY("baload"),

    // Store into byte or boolean array
    STORE_INTO_BYTE_BOOLEAN_ARRAY("bastore"),

    // For debugging, to implement breakpoints in the code
    BREAKPOINT("breakpoint"),

    // Load char from array
    LOAD_CHAR_FROM_ARRAY("caload"),

    // Store into char array
    STORE_INTO_CHAR_ARRAY("castore"),

    // Convert double to float
    DOUBLE_TO_FLOAT("d2f"),

    // Convert double to int
    DOUBLE_TO_INTEGER("d2i"),

    // Convert double to long
    DOUBLE_TO_LONG("d2l"),

    // Add double
    ADD_DOUBLE("dadd"),

    // Load double from array
    LOAD_DOUBLE_FROM_ARRAY("daload"),

    // Store into double array
    STORE_INTO_DOUBLE_ARRAY("dastore"),

    // Compare double
    COMPARE_DOUBLE_GREATER("dcmpg"),
    COMPARE_DOUBLE_LESS("dcmpl"),

    // Push double
    PUSH_DOUBLE_0("dconst_0"),
    PUSH_DOUBLE_1("dconst_1"),

    // Divide double
    DIVIDE_DOUBLE("ddiv"),

    // Load double from local variable
    LOAD_DOUBLE_FROM_LOCAL_VARIABLE_0("dload_0"),
    LOAD_DOUBLE_FROM_LOCAL_VARIABLE_1("dload_1"),
    LOAD_DOUBLE_FROM_LOCAL_VARIABLE_2("dload_2"),
    LOAD_DOUBLE_FROM_LOCAL_VARIABLE_3("dload_3"),

    // Multiply double
    MULTIPLY_DOUBLE("dmul"),

    // Negate double
    NEGATE_DOUBLE("dneg"),

    // Remainder double
    REMAINDER_DOUBLE("drem"),

    // Return double from method
    RETURN_DOUBLE("dreturn"),

    // Store double into local variable
    STORE_DOUBLE_INTO_LOCAL_VARIABLE_0("dstore_0"),
    STORE_DOUBLE_INTO_LOCAL_VARIABLE_1("dstore_1"),
    STORE_DOUBLE_INTO_LOCAL_VARIABLE_2("dstore_2"),
    STORE_DOUBLE_INTO_LOCAL_VARIABLE_3("dstore_3"),

    // Subtract double
    SUBTRACT_DOUBLE("dsub"),

    // Duplicate the top operand stack value
    DUPLICATE_TOP_STACK_VALUE("dup"),

    // Duplicate the top operand stack value and insert two values down
    DUPLICATE_TOP_STACK_VALUE_X1("dup_x1"),

    // Duplicate the top operand stack value and insert two or three values down
    DUPLICATE_TOP_STACK_VALUE_X2("dup_x2"),

    // Duplicate the top one or two operand stack values
    DUPLICATE_TOP_ONE_TWO_STACK_VALUES("dup2"),

    // Duplicate the top one or two operand stack values and insert two or three values down
    DUPLICATE_TOP_ONE_TWO_STACK_VALUES_X1("dup2_x1"),

    // Duplicate the top one or two operand stack values and insert two, three, or four values down
    DUPLICATE_TOP_ONE_TWO_STACK_VALUES_X2("dup2_x2"),

    // Convert float to double
    FLOAT_TO_DOUBLE("f2d"),

    // Convert float to int
    FLOAT_TO_INTEGER("f2i"),

    // Convert float to long
    FLOAT_TO_LONG("f2l"),

    // Add float
    ADD_FLOAT("fadd"),

    // Load float from array
    LOAD_FLOAT_FROM_ARRAY("faload"),

    // Store into float array
    STORE_INTO_FLOAT_ARRAY("fastore"),

    // Compare float
    COMPARE_FLOAT_GREATER("fcmpg"),
    COMPARE_FLOAT_LESS("fcmpl"),

    // Push float constant onto stack (0.0, 1.0, 2.0)
    FLOAT_CONSTANT_0("fconst_0"),
    FLOAT_CONSTANT_1("fconst_1"),
    FLOAT_CONSTANT_2("fconst_2"),

    // Divide float
    DIVIDE_FLOAT("fdiv"),

    // Load float from local variable
    LOAD_FLOAT_0("fload_0"),
    LOAD_FLOAT_1("fload_1"),
    LOAD_FLOAT_2("fload_2"),
    LOAD_FLOAT_3("fload_3"),

    // Multiply float
    MULTIPLY_FLOAT("fmul"),

    // Negate float
    NEGATE_FLOAT("fneg"),

    // Remainder float (modulo - %)
    REMAINDER_FLOAT("frem"),

    // Return float
    RETURN_FLOAT("freturn"),

    // Store float into local variable
    STORE_FLOAT_0("fstore_0"),
    STORE_FLOAT_1("fstore_1"),
    STORE_FLOAT_2("fstore_2"),
    STORE_FLOAT_3("fstore_3"),

    // Subtract float
    SUBTRACT_FLOAT("fsub"),

    // Convert integer to double
    INTEGER_TO_DOUBLE("i2d"),

    // Convert int to float
    INTEGER_TO_FLOAT("i2f"),

    // Convert int to long
    INTEGER_TO_LONG("i2l"),

    // Add int
    ADD_INTEGER("iadd"),

    // Load int from array
    LOAD_INTEGER_FROM_ARRAY("iaload"),

    // Boolean 'and' int
    AND_INTEGER("iand"),

    // Store into int array
    STORE_INTO_INTEGER_ARRAY("iastore"),

    // Push int constant onto stack (-1, 0, 1, 2, 3, 4, 5)
    INTEGER_CONSTANT_NEGATIVE_1("iconst_m1"),
    INTEGER_CONSTANT_0("iconst_0"),
    INTEGER_CONSTANT_1("iconst_1"),
    INTEGER_CONSTANT_2("iconst_2"),
    INTEGER_CONSTANT_3("iconst_3"),
    INTEGER_CONSTANT_4("iconst_4"),
    INTEGER_CONSTANT_5("iconst_5"),

    // Divide int
    DIVIDE_INTEGER("idiv"),

    // Load int from local variable
    LOAD_INTEGER_0("iload_0"),
    LOAD_INTEGER_1("iload_1"),
    LOAD_INTEGER_2("iload_2"),
    LOAD_INTEGER_3("iload_3"),

    // Multiply int
    MULTIPLY_INTEGER("imul"),

    // Negate int
    NEGATE_INTEGER("ineg"),

    // Convert int to byte
    INTEGER_TO_BYTE("int2byte"),

    // Convert int to char
    INTEGER_TO_CHAR("int2char"),

    // Convert int to short
    INTEGER_TO_SHORT("int2short"),

    // Boolean 'or' int
    OR_INTEGER("ior"),

    // Remainder int (modulo - %)
    REMAINDER_INTEGER("irem"),

    // Return int
    RETURN_INTEGER("ireturn"),

    // Shift int
    SHIFT_LEFT_INTEGER("ishl"),
    SHIFT_RIGHT_INTEGER("ishr"),

    // Store int into local variable
    STORE_INTEGER_0("istore_0"),
    STORE_INTEGER_1("istore_1"),
    STORE_INTEGER_2("istore_2"),
    STORE_INTEGER_3("istore_3"),

    // Subtract int
    SUBTRACT_INTEGER("isub"),

    // Logical shift right int
    LOGICAL_SHIFT_RIGHT_INTEGER("iushr"),

    // Boolean 'xor' int
    XOR_INTEGER("ixor"),

    // Convert long to double
    LONG_TO_DOUBLE("l2d"),

    // Convert long to float
    LONG_TO_FLOAT("l2f"),

    // Convert long to int
    LONG_TO_INTEGER("l2i"),

    // Add long
    ADD_LONG("ladd"),

    // Load long from array
    LOAD_LONG_FROM_ARRAY("laload"),

    // Boolean 'and' long
    AND_LONG("land"),

    // Store into long array
    STORE_INTO_LONG_ARRAY("lastore"),

    // Compare long (a > b = 1 | a == b = 0 | a < b = -1)
    COMPARE_LONG("lcmp"),

    // Push long constant onto stack (9, 10)
    LONG_CONSTANT_0("lconst_0"),
    LONG_CONSTANT_1("lconst_1"),

    // Divide long
    DIVIDE_LONG("ldiv"),

    // Load float from local variable
    LOAD_LONG_0("lload_0"),
    LOAD_LONG_1("lload_1"),
    LOAD_LONG_2("lload_2"),
    LOAD_LONG_3("lload_3"),

    // Multiply long
    MULTIPLY_LONG("lmul"),

    // Negate long
    NEGATE_LONG("lneg"),

    // Boolean 'or' long
    OR_LONG("lor"),

    // Remainder long (modulo - %)
    REMAINDER_LONG("lrem"),

    // Return long
    RETURN_LONG("lreturn"),

    // Shift long
    SHIFT_LEFT_LONG("lshl"),
    SHIFT_RIGHT_LONG("lshr"),

    // Store long into local variable
    STORE_LONG_0("lstore_0"),
    STORE_LONG_1("lstore_1"),
    STORE_LONG_2("lstore_2"),
    STORE_LONG_3("lstore_3"),

    // Subtract long
    SUBTRACT_LONG("lsub"),

    // Logical shift right long
    LOGICAL_SHIFT_RIGHT_LONG("lushr"),

    // Boolean 'xor' long
    XOR_LONG("lxor"),

    // Enter monitor for object
    ENTER_MONITOR("monitorenter"),

    // Exit monitor for object
    EXIT_MONITOR("monitorexit"),

    // Do nothing
    NOP("nop"),

    // Pop the top operand stack value
    POP("pop"),

    // Pop the top one or two operand stack values
    POP2("pop2"),

    // Return void
    RETURN("return"),

    // Load short from array
    LOAD_SHORT_FROM_ARRAY("saload"),

    // Store into short array
    STORE_INTO_SHORT_ARRAY("sastore"),

    // Swap the top two operand stack values
    SWAP("swap");

    private final String representation;

    NoParameterInstructionType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
