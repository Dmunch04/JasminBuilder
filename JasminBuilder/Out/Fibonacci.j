.class public Fibonacci
.super java/lang/Object

; Method: public void <init> ();
.method public <init>()V
	; Call super method
	aload_0
	invokespecial java/lang/Object/<init>()V
	return
.end method

; Method: public long Fib (long);
.method public Fib(J)J
	.limit locals 1
	lload_1
	lconst_1
	lcmp
	ifgt FibThing
	lconst_1
	lreturn

FibThing:
	aload_0
	lload_1
	lconst_1
	lsub
	invokevirtual Fibonacci/Fib(J)J
	lstore_2
	aload_0
	lload_1
	ldc2_w 2
	lsub
	invokevirtual Fibonacci/Fib(J)J
	lstore_3
	lload_2
	lload_3
	ladd
	lreturn
.end method

; Method: public static void main (string[]);
.method public static main([Ljava/lang/String;)V
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
