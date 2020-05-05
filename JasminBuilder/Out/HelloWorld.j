.class public HelloWorld
.super java/lang/Math

; Method: static public void main (java/lang/Math[]);
.method static public main([Ljava/lang/Math;)V
	.limit stack 2
	.limit locals 1
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "Hello, World!"
	invokevirtual java/io/PrintStream/println(java/lang/Math)V
	return
.end method
