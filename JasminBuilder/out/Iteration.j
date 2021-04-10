.class public Iteration
.super java/lang/Object

; Method: public void <init> ();
.method public <init>()V
	; Call super method
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

; Method: public static void main (string[]);
.method public static main([Ljava/lang/String;)V
	.limit locals 100
	.limit stack 100
	new Iteration
	dup
	invokespecial Iteration/<init>()V
	astore_1
	aload_1
	invokevirtual Iteration/testWhile()V
	return
.end method

; Method: void testWhile ();
.method testWhile()V
	.limit locals 100
	.limit stack 100
	iconst_5
	istore_1
	iload_1
	iconst_0
	; jump(IF_INTEGER_COMPARE_GREATER_THAN):whileBlock
	if_icmpgt whileBlock
whileBlock:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "While Iteration No."
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload_1
	invokevirtual java/io/PrintStream/println(I)V
	iload_1
	iconst_1
	isub
	istore_1
	iload_1
	iconst_0
	; jump(IF_INTEGER_COMPARE_GREATER_THAN):whileBlock
	if_icmpgt whileBlock
	return
.end method
