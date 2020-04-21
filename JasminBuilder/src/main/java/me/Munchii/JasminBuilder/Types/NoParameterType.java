package me.Munchii.JasminBuilder.Types;

public enum NoParameterType
{

    // Load reference from array
    LoadReferenceFromArray ("aaload"),

    // Store into reference array
    StoreIntoReferenceArray ("aastore"),

    // Push null
    PushNull ("aconst_null"),

    // Load reference from local variable
    LoadReferenceFromLocalVariable0 ("aload_0"),
    LoadReferenceFromLocalVariable1 ("aload_1"),
    LoadReferenceFromLocalVariable2 ("aload_2"),
    LoadReferenceFromLocalVariable3 ("aload_3"),

    // Return reference from method
    ReturnReference ("areturn"),

    // Get length of array
    ArrayLength ("arraylength"),

    // Store reference into local variable
    StoreReferenceIntoLocalVariable0 ("astore_0"),
    StoreReferenceIntoLocalVariable1 ("astore_1"),
    StoreReferenceIntoLocalVariable2 ("astore_2"),
    StoreReferenceIntoLocalVariable3 ("astore_3"),

    // Throw exception or error
    ThrowException ("athrow"),

    // Load byte or boolean from array
    LoadByteBooleanFromArray ("baload"),

    // Store into byte or boolean array
    StoreIntoByteBooleanArray ("bastore"),

    // For debugging, to implement breakpoints in the code
    Breakpoint ("breakpoint"),

    // Load char from array
    LoadCharFromArray ("caload"),

    // Store into char array
    StoreIntoCharArray ("castore"),

    // Convert double to float
    DoubleToFloat ("d2f"),

    // Convert double to int
    DoubleToInteger ("d2i"),

    // Convert double to long
    DoubleToLong ("d2l"),

    // Add double
    AddDouble ("dadd"),

    // Load double from array
    LoadDoubleFromArray ("daload"),

    // Store into double array
    StoreIntoDoubleArray ("dastore"),

    // Compare double
    CompareDoubleGreater ("dcmpg"),
    CompareDoubleLess ("dcmpl"),

    // Push double
    PushDouble0 ("dconst_0"),
    PushDouble1 ("dconst_1"),

    // Divide double
    DivideDouble ("ddiv"),

    // Load double from local variable
    LoadDoubleFromLocalVariable0 ("dload_0"),
    LoadDoubleFromLocalVariable1 ("dload_1"),
    LoadDoubleFromLocalVariable2 ("dload_2"),
    LoadDoubleFromLocalVariable3 ("dload_3"),

    // Multiply double
    MultiplyDouble ("dmul"),

    // Negate double
    NegateDouble ("dneg"),

    // Remainder double
    RemainderDouble ("drem"),

    // Return double from method
    ReturnDouble ("dreturn"),

    // Store double into local variable
    StoreDoubleIntoLocalVariable0 ("dstore_0"),
    StoreDoubleIntoLocalVariable1 ("dstore_1"),
    StoreDoubleIntoLocalVariable2 ("dstore_2"),
    StoreDoubleIntoLocalVariable3 ("dstore_3"),

    // Subtract double
    SubtractDouble ("dsub"),

    // Duplicate the top operand stack value
    DuplicateTopStackValue ("dup"),

    // Duplicate the top operand stack value and insert two values down
    DuplicateTopStackValueX1 ("dup_x1"),

    // Duplicate the top operand stack value and insert two or three values down
    DuplicateTopStackValueX2 ("dup_x2"),

    // Duplicate the top one or two operand stack values
    DuplicateTopOneTwoStackValues ("dup2"),

    // Duplicate the top one or two operand stack values and insert two or three values down
    DuplicateTopOneTwoStackValuesX1 ("dup2_x1"),

    // Duplicate the top one or two operand stack values and insert two, three, or four values down
    DuplicateTopOneTwoStackValuesX2 ("dup2_x2"),

    // Convert float to double
    FloatToDouble ("f2d"),

    // Convert float to int
    FloatToInteger ("f2i"),

    // Convert float to long
    FloatToLong ("f2l"),

    // Add float
    AddFloat ("fadd"),

    // Load float from array
    LoadFloatFromArray ("faload"),

    // Store into float array
    StoreIntoFloatArray ("fastore"),

    // Compare float
    CompareFloatGreater ("fcmpg"),
    CompareFloatLess ("fcmpl"),

    // Push float constant onto stack (0.0, 1.0, 2.0)
    FloatConstant0 ("fconst_0"),
    FloatConstant1 ("fconst_1"),
    FloatConstant2 ("fconst_2"),

    // Divide float
    DivideFloat ("fdiv"),

    // Load float from local variable
    LoadFloat0 ("fload_0"),
    LoadFloat1 ("fload_1"),
    LoadFloat2 ("fload_2"),
    LoadFloat3 ("fload_3"),

    // Multiply float
    MultiplyFloat ("fmul"),

    // Negate float
    NegateFloat ("fneg"),

    // Remainder float (modulo - %)
    RemainderFloat ("frem"),

    // Return float
    ReturnFloat ("freturn"),

    // Store float into local variable
    StoreFloat0 ("fstore_0"),
    StoreFloat1 ("fstore_1"),
    StoreFloat2 ("fstore_2"),
    StoreFloat3 ("fstore_3"),

    // Subtract float
    SubtractFloat ("fsub"),

    // Convert integer to double
    IntegerToDouble ("i2d"),

    // Convert int to float
    IntegerToFloat ("i2f"),

    // Convert int to long
    IntegerToLong ("i2l"),

    // Add int
    AddInteger ("iadd"),

    // Load int from array
    LoadIntegerFromArray ("iaload"),

    // Boolean 'and' int
    AndInteger ("iand"),

    // Store into int array
    StoreIntoIntegerArray ("iastore"),

    // Push int constant onto stack (-1, 0, 1, 2, 3, 4, 5)
    IntegerConstantNegative1 ("iconst_m1"),
    IntegerConstant0 ("iconst_0"),
    IntegerConstant1 ("iconst_1"),
    IntegerConstant2 ("iconst_2"),
    IntegerConstant3 ("iconst_3"),
    IntegerConstant4 ("iconst_4"),
    IntegerConstant5 ("iconst_5"),

    // Divide int
    DivideInteger ("idiv"),

    // Load int from local variable
    LoadInteger0 ("iload_0"),
    LoadInteger1 ("iload_1"),
    LoadInteger2 ("iload_2"),
    LoadInteger3 ("iload_3"),

    // Multiply int
    MultiplyInteger ("imul"),

    // Negate int
    NegateInteger ("ineg"),

    // Convert int to byte
    IntegerToByte ("int2byte"),

    // Convert int to char
    IntegerToChar ("int2char"),

    // Convert int to short
    IntegerToShort ("int2short"),

    // Boolean 'or' int
    OrInteger ("ior"),

    // Remainder int (modulo - %)
    RemainderInteger ("irem"),

    // Return int
    ReturnInteger ("ireturn"),

    // Shift int
    ShiftLeftInteger ("ishl"),
    ShiftRightInteger ("ishr"),

    // Store int into local variable
    StoreInteger0 ("istore_0"),
    StoreInteger1 ("istore_1"),
    StoreInteger2 ("istore_2"),
    StoreInteger3 ("istore_3"),

    // Subtract int
    SubtractInteger ("isub"),

    // Logical shift right int
    LogicalShiftRightInteger ("iushr"),

    // Boolean 'xor' int
    XORInteger ("ixor"),

    // Convert long to double
    LongToDouble ("l2d"),

    // Convert long to float
    LongToFloat ("l2f"),

    // Convert long to int
    LongToInteger ("l2i"),

    // Add long
    AddLong ("ladd"),

    // Load long from array
    LoadLongFromArray ("laload"),

    // Boolean 'and' long
    AndLong ("land"),

    // Store into long array
    StoreIntoLongArray ("lastore"),

    // Compare long (a > b = 1 | a == b = 0 | a < b = -1)
    CompareLong ("lcmp"),

    // Push long constant onto stack (9, 10)
    LongConstant0 ("lconst_0"),
    LongConstant1 ("lconst_1"),

    // Divide long
    DivideLong ("ldiv"),

    // Load float from local variable
    LoadLong0 ("lload_0"),
    LoadLong1 ("lload_1"),
    LoadLong2 ("lload_2"),
    LoadLong3 ("lload_3"),

    // Multiply long
    MultiplyLong ("lmul"),

    // Negate long
    NegateLong ("lneg"),

    // Boolean 'or' long
    OrLong ("lor"),

    // Remainder long (modulo - %)
    RemainderLong ("lrem"),

    // Return long
    ReturnLong ("lreturn"),

    // Shift long
    ShiftLeftLong ("lshl"),
    ShiftRightLong ("lshr"),

    // Store long into local variable
    StoreLong0 ("lstore_0"),
    StoreLong1 ("lstore_1"),
    StoreLong2 ("lstore_2"),
    StoreLong3 ("lstore_3"),

    // Subtract long
    SubtractLong ("lsub"),

    // Logical shift right long
    LogicalShiftRightLong ("lushr"),

    // Boolean 'xor' long
    XORLong ("lxor"),

    // Enter monitor for object
    EnterMonitor ("monitorenter"),

    // Exit monitor for object
    ExitMonitor ("monitorexit"),

    // Do nothing
    Nop ("nop"),

    // Pop the top operand stack value
    Pop ("pop"),

    // Pop the top one or two operand stack values
    Pop2 ("pop2"),

    // Return void
    Return ("return"),

    // Load short from array
    LoadShortFromArray ("saload"),

    // Store into short array
    StoreIntoShortArray ("sastore"),

    // Swap the top two operand stack values
    Swap ("swap");

    private String Representation;

    private NoParameterType (String Representation)
    {
        this.Representation = Representation;
    }

    public String GetRepresentation ()
    {
        return Representation;
    }

}
