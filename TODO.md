# TODO

| Message | Status | File |
| --- | --- | --- |
| Maybe rename this + it's statement (`IntegerPushStatement`) + everything related to this | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Types/IntegerPushType.java:3` |
| Allow for multiple sizes, ex. `String[x][y]` instead of `String[x][x]` | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminArray.java:86` |
| Is this really the best way lol? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminArray.java:252` |
| Implement more types? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/ExpressionBuilder.java:40` |
| Implement more types? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/ExpressionBuilder.java:59` |
| Implement more types | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/ExpressionBuilder.java:78` |
| Make this and accept as many types as possible | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/ConditionBuilder.java:20` |
| Fix that primitive types just gets displayed as their representation | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:96` |
| Is this the correct way? Probably not, but maybe? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:113` |
| Should we introduce a `Null` type? If so, Couldn't it get confused with `Void` in some cases? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:152` |
| Is this the correct way? I suppose it is. Actually Idk | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:161` |
| Get the type? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:168` |
| Is this the correct way?? I suppose it is | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:171` |
| Implement rest of the types | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminVariable.java:55` |
| Implement rest of the types | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminVariable.java:131` |
| Document these | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Logging/Message.java:6` |
| Make this | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Statements/ReturnStatement.java:6` |
| Find a way to push and store all different value types | `mostly done` | `src/main/java/me/Munchii/JasminBuilder/Main.java:22` |
| Find a way to represent arrays and push items into the array and load them from array | `done?` | `src/main/java/me/Munchii/JasminBuilder/Main.java:23` |
| How can we stop/prevent the user from chaining on the `JasminMethod` constructor method? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:24` |
| How should conditions work? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:25` |
| Make instructions for control-flow, loops, field init, etc. | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:26` |
| Fix tests to use new variable system | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:28` |
| This here will NOT work because blocks are evaluated after the methods main block, so the id would just be -1 | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:50` |
| Find a good fix | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:51` |
| Make a better logging system | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Instructions/IfInstruction.java:39` |
| Explain this better lol | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Instructions/IfInstruction.java:40` |
| Find a way to represent value (`JasminPassable`) | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Instructions/InitFieldInstruction.java:47` |
| Add support for `ldc2` & `ldc2_w` | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Methods/MethodHelper.java:97` |
| Well doesn't work well with scopes ay | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Methods/JasminMethod.java:117` |
| Is this really the best way? I guess but eh | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Methods/JasminMethod.java:297` |
| Surely there must be a better way, than using the `Hook` method | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Fields/JasminField.java:21` |
| How would we make field spec without knowing the class? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Fields/JasminField.java:74` |
| Why does the helloworld test take 42-50ms, while fib takes around 2-3ms? | `N/A` | `src/test/java/me/Munchii/JasminBuilder/Testing/Test.java:21` |
| Compare arg (if arg == 0 or arg == 1, return arg) | `N/A` | `src/test/java/me/Munchii/JasminBuilder/Testing/Tests/FibonacciTest.java:37` |
| Call Fib(arg-1) + Fib(arg-2) | `N/A` | `src/test/java/me/Munchii/JasminBuilder/Testing/Tests/FibonacciTest.java:44` |
| Use `ExpressionBuilder` here instead, but how? Perhaps make a MethodCall instruction? That way we should also be able to make it a variable? | `N/A` | `src/test/java/me/Munchii/JasminBuilder/Testing/Tests/FibonacciTest.java:47` |
| Fix this when local variables have been implemented and print instruction can take in one as argument | `N/A` | `src/test/java/me/Munchii/JasminBuilder/Testing/Tests/FibonacciTest.java:89` |

> Last generated at: `05/05/20 - 12:45:51`