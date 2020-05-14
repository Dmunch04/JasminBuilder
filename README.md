## JasminBuilder
A builder lib for Java for the Jasmin JVM

<br>

## Example Usage
Here's an example how to use the builder:
```java
// TODO: Actually do the correct imports lol
import me.Munchii.JasminBuilder.*;

import static java.util.Arrays.asList;

class Fibonacci {
    public static void main(String... args) {
	// create file named "Fibonacci.j"
        JasminFile file = new JasminFile("/", "Fibonacci");
	// create public class called "Main"
	JasminClass class = new JasminClass("Main", ClassAccessSpec.Public);
	
	// create default constructor method
	Class.AddMethod(MethodCreator.CreateConstructorMethod());

	// name, return type, access spec(s), arg type(s)
	JasminMethod fibMethod = new JasminMethod("fib", DataType.Long, asList(MethodAccessSpec.Public), DataType.Long);
	JasminBlock ifBlock = new JasminBlock("FIB");
	JasminVariable thisInstance = fibMethod.GetVariable("this");
	// long res1 = fib(n - 1)
	JasminVariable res1 = new JasminVariable("res1", new ClassMethodCall(thisInstance, class, fibMethod, new ExpressionBuilder(Method.GetVariable("arg1")).Add(new JasminValue(1, DataType.Long))));
	fibMethod.DeclareVariable(res1);
	// long res2 = fib(n - 2)
	JasminVariable res2 = new JasminVariable("res2", new ClassMethodCall(thisInstance, class, fibMethod, new ExpressionBuilder(Method.GetVariable("arg1")).Add(new JasminValue(2, DataType.Long))));
	fibMethod.DeclareVariable(res2);
	// long res = res1 + res2
	JasminVariable res = new JasminVariable("res", new ExpressionBuilder(res1).Add(res2));
	// return res
	fibMethod.AddReturnStatement(res);
	
	JasminBlock elseBlock = new JasminBlock("ELSE")
		// return 1
		.AddNoParameterStatement(NoParameterType.LongConstant1)
                .AddNoParameterStatement(NoParameterType.ReturnLong);

	// if(arg1 > 1)
	JasminCondition cond = JasminConditions.GreaterThan(
		fibMethod.GetVariable("arg1"),
		new JasminValue(1, DataType.Long)
	);
	fibMethod.AddInstruction(new IfInstruction(
		// else block
		elseBlock,

		// if condition block(s)
		new JasminConditionBlock(cond, ifBlock)
	))

	JasminMethod mainMethod = MethodCreator.CreateMainMethod();
	// create new class instance
	JasminVariable main = new JasminVariable("main", new ClassInstance(class));
	mainMethod.DeclareVariable(main);
	// long result = fib(35)
	JasminVariable result = new JasminVariable("result", new ClassMethodCall(main, class, fibMethod, new JasminValue(35, DataType.Long)));
	mainMethod.DeclareVariable(result);
	// System.out.println(result)
	mainMethod.AddInstruction(new PrintInstruction(result));

	// add the methods to the class
	class.AddMethod(mainMethod);
	class.AddMethod(fibMethod);

	// add the class to the file and write the file
	file.AddClass(class);
	file.Write();
    }
}
```

<br>

## Changelog
### 1.1.0
Core features finished and everything is stable. Variables, class, methods, fields, etc.
> **Note:** Method declaration and building should from now on be __SEPARATED__! Else you can't access variables like `this`, `arg1`, `arg2`, .. `argN`

### 1.0.0
Implement core features

<br>

## Contributing
Feel free to fork and create PRs. I'll gladly accept all the help I can get!

<br>

## Licensing
This project is licensed under the MIT license
