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
	.limit stack 50
	.limit locals 51
	lload_1
	lconst_1
	lcmp
	; jump(IF_GREATER_THAN):FibThing
	ifgt FibThing
	; Else block (ELSE:443308702)
	lconst_1
	lreturn
	; ---
FibThing:
	aload_0
	lload_1
	lconst_1
	lsub
	invokevirtual Fibonacci/Fib(J)J
	aload_0
	lload_1
	ldc2_w 2
	lsub
	invokevirtual Fibonacci/Fib(J)J
	ladd
	lreturn
	return
.end method

; Method: public static void main (string[]);
.method public static main([Ljava/lang/String;)V
	.limit stack 52
	.limit locals 51
	new Fibonacci
	dup
	invokespecial Fibonacci/<init>()V
	astore_1
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload_1
	ldc2_w 45
	invokevirtual Fibonacci/Fib(J)J
	invokevirtual java/io/PrintStream/println(J)V
	return
.end method
