.class public HelloWorld
.super java/lang/Object

; Method: static public void main (string[]);
.method static public main([Ljava/lang/String;)V
	.limit stack 2
	.limit locals 1
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "Hello, World!"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	return
.end method
