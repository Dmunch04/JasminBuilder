.class public Fibonacci
.super java/lang/Object

; Method: public void <init> ();
.method public <init>()V
	; Call super method
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

; Method: public int Fib (int);
.method public Fib(I)I
	.limit locals 1
	iload_1
	iconst_1
	if_icmpgt FibThing
	iload_1
	ireturn

FibThing:
	aload_0
	iload_1
	iconst_1
	isub
	invokevirtual Fibonacci/Fib(I)I
	istore_2
	aload_0
	iload_1
	iconst_2
	isub
	invokevirtual Fibonacci/Fib(I)I
	istore_3
	iload_2
	iload_3
	iadd
	ireturn
.end method

; Method: static public void main (string[]);
.method static public main([Ljava/lang/String;)V
	.limit stack 2
	.limit locals 1
	new Fibonacci
	dup
	invokespecial Fibonacci/<init>()V
	astore_1
	aload_1
	invokevirtual Fibonacci/Fib(I)I
	istore_0
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iconst_0
	invokevirtual java/io/PrintStream/println(I)V
	return
.end method
