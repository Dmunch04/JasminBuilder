package me.munchii.Jasmin.instruction;

public enum JasminInstructions {
    // Return from subroutine
    RET("ret", 0),

    // Load reference from local variable
    LOAD_REFERENCE("aload", +1),

    // Store reference into local variable
    STORE_REFERENCE("astore", -1),

    // Load double from local variable
    LOAD_DOUBLE("dload", +1),

    // Store double into local variable
    STORE_DOUBLE("dstore", -1),

    // Load float from local variable
    LOAD_FLOAT("fload", +1),

    // Store float into local variable
    STORE_FLOAT("fstore", -1),

    // Load int from local variable
    LOAD_INTEGER("iload", +1),

    // Store int into local variable
    STORE_INTEGER("istore", -1),

    // Load long from local variable
    LOAD_LONG("lload", +1),

    // Store long into local variable
    STORE_LONG("lstore", -1),

    // Push byte
    BI_PUSH("bipush", +1),

    // Push short
    SI_PUSH("sipush", +1),

    // Increment integer
    INCREMENT("iinc", 0),

    // Branch always
    GOTO("goto", 0),

    // Branch always (wide index)
    GOTO_W("goto_w", 0),

    // Branch if reference comparison succeeds
    IF_REFERENCE_COMPARE_EQUALS("if_acmpeq", -2),
    IF_REFERENCE_COMPARE_NOT_EQUALS("if_acmne", -2),

    // Branch if int comparison succeeds
    IF_INTEGER_COMPARE_EQUALS("if_icmpeq", -2),
    IF_INTEGER_COMPARE_NOT_EQUALS("if_icmpne", -2),
    IF_INTEGER_COMPARE_LESS_THAN("if_icmplt", -2),
    IF_INTEGER_COMPARE_LESS_EQUALS("if_icmple", -2),
    IF_INTEGER_COMPARE_GREATER_THAN("if_icmpgt", -2),
    IF_INTEGER_COMPARE_GREATER_EQUALS("if_icmpge", -2),

    // Branch if int comparison with zero succeeds
    IF_EQUALS("ifeq", -1),
    IF_NOT_EQUALS("ifne", -1),
    IF_LESS_THAN("iflt", -1),
    IF_LESS_EQUALS("ifle", -1),
    IF_GREATER_THAN("ifgt", -1),
    IF_GREATER_EQUALS("ifge", -1),

    // Branch if reference not null
    IF_NON_NULL("ifnonnull", -1),

    // Branch if reference is null
    IF_NULL("ifnull", -1),

    // Jump subroutine
    JUMP_SUBROUTINE("jsr", +1),

    // Jump subroutine (wide index)
    JUMP_SUBROUTINE_W("jsr_w", +1),

    // Create new array of reference
    A_NEW_ARRAY("anewarray", 0),

    // Check whether object is of given type
    CHECK_CAST("checkcast", 0),

    // Determine if object is of given type
    INSTANCE_OF("instanceof", 0),

    // Create new object
    NEW("new", +1),

    // ??
    INVOKE_NON_VIRTUAL("invokenonvirtual", -1),

    // Invoke a class (static) method
    INVOKE_STATIC("invokestatic", 0),

    // Invoke instance method; dispatch based on class
    INVOKE_VIRTUAL("invokevirtual", -1),

    // ??
    INVOKE_SPECIAL("invokespecial", -1),

    // Fetch field from object
    GET_FIELD("getfield", 0),

    // Get static field from class
    GET_STATIC("getstatic", +1),

    // Set field in object
    PUT_FIELD("putfield", -2),

    // Set static field in class
    PUT_STATIC("putstatic", -1),

    // Create new array
    NEW_ARRAY("newarray", 0),

    // create new multi-dimensional array
    // TODO: https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.multianewarray
    MULTI_A_NEW_ARRAY("multianewarray", 0),

    // Push item from run-time constant pool
    LOAD_CONSTANT("ldc", +1),

    // Push item from run-time constant pool (wide index)
    LOAD_CONSTANT_WIDE("ldc_w", +1), // For `long` and `double`

    LOAD_CONSTANT_2("ldc2", +1),

    LOAD_CONSTANT_2_WIDE("ldc2_w", +1),

    // Switch (lookup)
    LOOKUP_SWITCH("lookupswitch", -1),

    // Switch (table)
    TABLE_SWITCH("tableswitch", -1),

    // Load reference from array
    LOAD_REFERENCE_FROM_ARRAY("aaload", -1),

    // Store into reference array
    STORE_INTO_REFERENCE_ARRAY("aastore", -3),

    // Push null
    PUSH_NULL("aconst_null", +1),

    // Load reference from local variable
    LOAD_REFERENCE_FROM_LOCAL_VARIABLE_0("aload_0", +1),
    LOAD_REFERENCE_FROM_LOCAL_VARIABLE_1("aload_1", +1),
    LOAD_REFERENCE_FROM_LOCAL_VARIABLE_2("aload_2", +1),
    LOAD_REFERENCE_FROM_LOCAL_VARIABLE_3("aload_3", +1),

    // Return reference from method
    RETURN_REFERENCE("areturn", -1),

    // Get length of array
    ARRAY_LENGTH("arraylength", 0),

    // Store reference into local variable
    STORE_REFERENCE_INTO_LOCAL_VARIABLE_0("astore_0", -1),
    STORE_REFERENCE_INTO_LOCAL_VARIABLE_1("astore_1", -1),
    STORE_REFERENCE_INTO_LOCAL_VARIABLE_2("astore_2", -1),
    STORE_REFERENCE_INTO_LOCAL_VARIABLE_3("astore_3", -1),

    // Throw exception or error
    THROW_EXCEPTION("athrow", 0),

    // Load byte or boolean from array
    LOAD_BYTE_BOOLEAN_FROM_ARRAY("baload", -1),

    // Store into byte or boolean array
    STORE_INTO_BYTE_BOOLEAN_ARRAY("bastore", -3),

    // For debugging, to implement breakpoints in the code
    BREAKPOINT("breakpoint", 0),

    // Load char from array
    LOAD_CHAR_FROM_ARRAY("caload", -1),

    // Store into char array
    STORE_INTO_CHAR_ARRAY("castore", -3),

    // Convert double to float
    DOUBLE_TO_FLOAT("d2f", 0),

    // Convert double to int
    DOUBLE_TO_INTEGER("d2i", 0),

    // Convert double to long
    DOUBLE_TO_LONG("d2l", 0),

    // Add double
    ADD_DOUBLE("dadd", -1),

    // Load double from array
    LOAD_DOUBLE_FROM_ARRAY("daload", -1),

    // Store into double array
    STORE_INTO_DOUBLE_ARRAY("dastore", -3),

    // Compare double
    COMPARE_DOUBLE_GREATER("dcmpg", -1),
    COMPARE_DOUBLE_LESS("dcmpl", -1),

    // Push double
    PUSH_DOUBLE_0("dconst_0", +1),
    PUSH_DOUBLE_1("dconst_1", +1),

    // Divide double
    DIVIDE_DOUBLE("ddiv", -1),

    // Load double from local variable
    LOAD_DOUBLE_FROM_LOCAL_VARIABLE_0("dload_0", +1),
    LOAD_DOUBLE_FROM_LOCAL_VARIABLE_1("dload_1", +1),
    LOAD_DOUBLE_FROM_LOCAL_VARIABLE_2("dload_2", +1),
    LOAD_DOUBLE_FROM_LOCAL_VARIABLE_3("dload_3", +1),

    // Multiply double
    MULTIPLY_DOUBLE("dmul", -1),

    // Negate double
    NEGATE_DOUBLE("dneg", 0),

    // Remainder double
    REMAINDER_DOUBLE("drem", -1),

    // Return double from method
    RETURN_DOUBLE("dreturn", -1),

    // Store double into local variable
    STORE_DOUBLE_INTO_LOCAL_VARIABLE_0("dstore_0", -1),
    STORE_DOUBLE_INTO_LOCAL_VARIABLE_1("dstore_1", -1),
    STORE_DOUBLE_INTO_LOCAL_VARIABLE_2("dstore_2", -1),
    STORE_DOUBLE_INTO_LOCAL_VARIABLE_3("dstore_3", -1),

    // Subtract double
    SUBTRACT_DOUBLE("dsub", -1),

    // Duplicate the top operand stack value
    // TODO: some of these can push more than 1 onto stack. how to handle? https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.dup2_x1
    DUPLICATE_TOP_STACK_VALUE("dup", +1),

    // Duplicate the top operand stack value and insert two values down
    DUPLICATE_TOP_STACK_VALUE_X1("dup_x1", +1),

    // Duplicate the top operand stack value and insert two or three values down
    DUPLICATE_TOP_STACK_VALUE_X2("dup_x2", +1),

    // Duplicate the top one or two operand stack values
    DUPLICATE_TOP_ONE_TWO_STACK_VALUES("dup2", +1),

    // Duplicate the top one or two operand stack values and insert two or three values down
    DUPLICATE_TOP_ONE_TWO_STACK_VALUES_X1("dup2_x1", +1),

    // Duplicate the top one or two operand stack values and insert two, three, or four values down
    DUPLICATE_TOP_ONE_TWO_STACK_VALUES_X2("dup2_x2", +1),

    // Convert float to double
    FLOAT_TO_DOUBLE("f2d", 0),

    // Convert float to int
    FLOAT_TO_INTEGER("f2i", 0),

    // Convert float to long
    FLOAT_TO_LONG("f2l", 0),

    // Add float
    ADD_FLOAT("fadd", -1),

    // Load float from array
    LOAD_FLOAT_FROM_ARRAY("faload", -1),

    // Store into float array
    STORE_INTO_FLOAT_ARRAY("fastore", -3),

    // Compare float
    COMPARE_FLOAT_GREATER("fcmpg", -1),
    COMPARE_FLOAT_LESS("fcmpl", -1),

    // Push float constant onto stack (0.0, 1.0, 2.0)
    FLOAT_CONSTANT_0("fconst_0", +1),
    FLOAT_CONSTANT_1("fconst_1", +1),
    FLOAT_CONSTANT_2("fconst_2", +1),

    // Divide float
    DIVIDE_FLOAT("fdiv", -1),

    // Load float from local variable
    LOAD_FLOAT_0("fload_0", +1),
    LOAD_FLOAT_1("fload_1", +1),
    LOAD_FLOAT_2("fload_2", +1),
    LOAD_FLOAT_3("fload_3", +1),

    // Multiply float
    MULTIPLY_FLOAT("fmul", -1),

    // Negate float
    NEGATE_FLOAT("fneg", 0),

    // Remainder float (modulo - %)
    REMAINDER_FLOAT("frem", -1),

    // Return float
    RETURN_FLOAT("freturn", -1),

    // Store float into local variable
    STORE_FLOAT_0("fstore_0", -1),
    STORE_FLOAT_1("fstore_1", -1),
    STORE_FLOAT_2("fstore_2", -1),
    STORE_FLOAT_3("fstore_3", -1),

    // Subtract float
    SUBTRACT_FLOAT("fsub", -1),

    // TODO: i2b? https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.i2b
    // as well as i2c. maybe jasmin is outdated? maybe we should change jasmin version (look into jasminxt)
    // and i2s
    // nvm. jasmin has those, but with other instruction names (int2byte instead i2b), but still look into newer jasmin version

    // Convert integer to double
    INTEGER_TO_DOUBLE("i2d", 0),

    // Convert int to float
    INTEGER_TO_FLOAT("i2f", 0),

    // Convert int to long
    INTEGER_TO_LONG("i2l", 0),

    // Add int
    ADD_INTEGER("iadd", -1),

    // Load int from array
    LOAD_INTEGER_FROM_ARRAY("iaload", -1),

    // Boolean 'and' int
    AND_INTEGER("iand", -1),

    // Store into int array
    STORE_INTO_INTEGER_ARRAY("iastore", -3),

    // Push int constant onto stack (-1, 0, 1, 2, 3, 4, 5)
    INTEGER_CONSTANT_NEGATIVE_1("iconst_m1", +1),
    INTEGER_CONSTANT_0("iconst_0", +1),
    INTEGER_CONSTANT_1("iconst_1", +1),
    INTEGER_CONSTANT_2("iconst_2", +1),
    INTEGER_CONSTANT_3("iconst_3", +1),
    INTEGER_CONSTANT_4("iconst_4", +1),
    INTEGER_CONSTANT_5("iconst_5", +1),

    // Divide int
    DIVIDE_INTEGER("idiv", -1),

    // Load int from local variable
    LOAD_INTEGER_0("iload_0", +1),
    LOAD_INTEGER_1("iload_1", +1),
    LOAD_INTEGER_2("iload_2", +1),
    LOAD_INTEGER_3("iload_3", +1),

    // Multiply int
    MULTIPLY_INTEGER("imul", -1),

    // Negate int
    NEGATE_INTEGER("ineg", 0),

    // Convert int to byte
    INTEGER_TO_BYTE("int2byte", 0),

    // Convert int to char
    INTEGER_TO_CHAR("int2char", 0),

    // Convert int to short
    INTEGER_TO_SHORT("int2short", 0),

    // Boolean 'or' int
    OR_INTEGER("ior", -1),

    // Remainder int (modulo - %)
    REMAINDER_INTEGER("irem", -1),

    // Return int
    RETURN_INTEGER("ireturn", -1),

    // Shift int
    SHIFT_LEFT_INTEGER("ishl", -1),
    SHIFT_RIGHT_INTEGER("ishr", -1),

    // Store int into local variable
    STORE_INTEGER_0("istore_0", -1),
    STORE_INTEGER_1("istore_1", -1),
    STORE_INTEGER_2("istore_2", -1),
    STORE_INTEGER_3("istore_3", -1),

    // Subtract int
    SUBTRACT_INTEGER("isub", -1),

    // Logical shift right int
    LOGICAL_SHIFT_RIGHT_INTEGER("iushr", -1),

    // Boolean 'xor' int
    XOR_INTEGER("ixor", -1),

    // Convert long to double
    LONG_TO_DOUBLE("l2d", 0),

    // Convert long to float
    LONG_TO_FLOAT("l2f", 0),

    // Convert long to int
    LONG_TO_INTEGER("l2i", 0),

    // Add long
    ADD_LONG("ladd", -1),

    // Load long from array
    LOAD_LONG_FROM_ARRAY("laload", -1),

    // Boolean 'and' long
    AND_LONG("land", -1),

    // Store into long array
    STORE_INTO_LONG_ARRAY("lastore", -3),

    // Compare long (a > b = 1 | a == b = 0 | a < b = -1)
    COMPARE_LONG("lcmp", -1),

    // Push long constant onto stack (9, 10)
    LONG_CONSTANT_0("lconst_0", +1),
    LONG_CONSTANT_1("lconst_1", +1),

    // Divide long
    DIVIDE_LONG("ldiv", -1),

    // Load float from local variable
    LOAD_LONG_0("lload_0", +1),
    LOAD_LONG_1("lload_1", +1),
    LOAD_LONG_2("lload_2", +1),
    LOAD_LONG_3("lload_3", 01),

    // Multiply long
    MULTIPLY_LONG("lmul", -1),

    // Negate long
    NEGATE_LONG("lneg", 0),

    // Boolean 'or' long
    OR_LONG("lor", -1),

    // Remainder long (modulo - %)
    REMAINDER_LONG("lrem", -1),

    // Return long
    RETURN_LONG("lreturn", -1),

    // Shift long
    SHIFT_LEFT_LONG("lshl", -1),
    SHIFT_RIGHT_LONG("lshr", -1),

    // Store long into local variable
    STORE_LONG_0("lstore_0", -1),
    STORE_LONG_1("lstore_1", -1),
    STORE_LONG_2("lstore_2", -1),
    STORE_LONG_3("lstore_3", -1),

    // Subtract long
    SUBTRACT_LONG("lsub", -1),

    // Logical shift right long
    LOGICAL_SHIFT_RIGHT_LONG("lushr", -1),

    // Boolean 'xor' long
    XOR_LONG("lxor", -1),

    // Enter monitor for object
    ENTER_MONITOR("monitorenter", -1),

    // Exit monitor for object
    EXIT_MONITOR("monitorexit", -1),

    // Do nothing
    NOP("nop", 0),

    // Pop the top operand stack value
    POP("pop", -1),

    // Pop the top one or two operand stack values
    // TODO: this also has an option to only sub 1 from stack, so how do we handle?
    POP2("pop2", -2),

    // Return void
    RETURN("return", 0),

    // Load short from array
    LOAD_SHORT_FROM_ARRAY("saload", -1),

    // Store into short array
    STORE_INTO_SHORT_ARRAY("sastore", -3),

    // Swap the top two operand stack values
    SWAP("swap", 0);

    private final String representation;
    private final int stackChange;

    JasminInstructions(String representation, int stackChange) {
        this.representation = representation;
        this.stackChange = stackChange;
    }

    public String getRepresentation() {
        return representation;
    }

    public int getStackChange() {
        return stackChange;
    }
}