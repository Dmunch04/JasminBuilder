.class public Fibonacci
.super java/lang/Object

.method public <init>()V
	; Call super method
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

.method public Fib(I)I
	.limit locals 1
	iload_0
	ireturn
.end method

.method public static main([Ljava/lang/String;)V
	.limit stack 2
	.limit locals 1
	return
	new Fibonacci
	dup
	invokespecial Fibonacci/<init>()V
	astore_1
	aload_1
	invokevirtual Fibonacci/Fib(I)I
	istore_0
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iconst_0
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.end method
