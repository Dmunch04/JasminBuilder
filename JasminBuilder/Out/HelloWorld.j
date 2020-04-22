.class public HelloWorld
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
	.limit stack 2
	.limit locals 1
	return
	getstatic java/lang/System/out Ljava/io/PrintStream;
	ldc "Hello, World!"
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.end method
