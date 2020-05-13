# TODO

| Message | Status | File |
| --- | --- | --- |
| Maybe rename this + it's statement (`IntegerPushStatement`) + everything related to this | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Types/IntegerPushType.java:3` |
| Should it be maybe long or double instead? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminArray.java:65` |
| Allow for multiple sizes, ex. `String[x][y]` instead of `String[x][x]` | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminArray.java:105` |
| We can maybe make this more efficient by having the `JasminPassable` values in a list? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminArray.java:222` |
| Is this really the best way lol? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminArray.java:271` |
| Implement more types? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/ExpressionBuilder.java:40` |
| Implement more types? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/ExpressionBuilder.java:59` |
| Implement more types | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/ExpressionBuilder.java:78` |
| If one if the values is `false` it should just use `ifne` (etc) and not push the value | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/ConditionBuilder.java:38` |
| Should byte be here too? In my testing it acted like ints so I guess | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/ConditionBuilder.java:133` |
| Hmm. Same as above but well works | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/JasminCondition.java:46` |
| Fix that primitive types just gets displayed as their representation | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:98` |
| Should we introduce a `Null` type? If so, Couldn't it get confused with `Void` in some cases? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:159` |
| Is this the correct way? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:170` |
| Should this be allowed? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:176` |
| Implement rest of the types | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminVariable.java:55` |
| Implement rest of the types | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminVariable.java:132` |
| Should `Null` type be added? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/DataTypes/ValueType.java:6` |
| Document these | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Logging/Message.java:6` |
| Make this | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Statements/ReturnStatement.java:6` |
| Find a way to push and store all different value types | `mostly done` | `src/main/java/me/Munchii/JasminBuilder/Main.java:22` |
| How can we stop/prevent the user from chaining on the `JasminMethod` constructor method? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:23` |
| How should conditions work? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:24` |
| Make instructions for control-flow, loops, field init, etc. | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:25` |
| Add multi dimensional support for arrays | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:26` |
| Fix tests to use new variable system | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:28` |
| Maybe rework how the error system works? Should it even abort/exit when error? And how would the user toggle that | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:30` |
| Make it nice to chain them with `and` (&&) and `or` (||) | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:41` |
| This here will NOT work because blocks are evaluated after the methods main block, so the id would just be -1 | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:68` |
| Find a good fix | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:69` |
| Make a better logging system | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Instructions/IfInstruction.java:39` |
| Explain this better lol | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Instructions/IfInstruction.java:40` |
| Find a way to represent value (`JasminPassable`) | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Instructions/InitFieldInstruction.java:47` |
| Add support for `ldc2` & `ldc2_w` | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Methods/MethodHelper.java:97` |
| Well doesn't work well with scopes ay | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Methods/JasminMethod.java:116` |
| Is this really the best way? I guess but eh | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Methods/JasminMethod.java:296` |
| Surely there must be a better way, than using the `Hook` method | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Fields/JasminField.java:21` |
| How would we make field spec without knowing the class? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Fields/JasminField.java:74` |
| Why does the helloworld test take 42-50ms, while fib takes around 2-3ms? | `N/A` | `src/test/java/me/Munchii/JasminBuilder/Testing/Test.java:21` |
| Compare arg (if arg == 0 or arg == 1, return arg) | `N/A` | `src/test/java/me/Munchii/JasminBuilder/Testing/Tests/FibonacciTest.java:33` |
| Call Fib(arg-1) + Fib(arg-2) | `N/A` | `src/test/java/me/Munchii/JasminBuilder/Testing/Tests/FibonacciTest.java:40` |
| Use `ExpressionBuilder` here instead, but how? Perhaps make a MethodCall instruction? That way we should also be able to make it a variable? | `N/A` | `src/test/java/me/Munchii/JasminBuilder/Testing/Tests/FibonacciTest.java:43` |
| Fix this when local variables have been implemented and print instruction can take in one as argument | `N/A` | `src/test/java/me/Munchii/JasminBuilder/Testing/Tests/FibonacciTest.java:85` |

> Total items: `43`

> Last generated at: `13/05/20 - 21:47:50`