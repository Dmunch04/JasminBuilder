# TODO

| Message | Status | File |
| --- | --- | --- |
| Maybe rename this + it's statement (`IntegerPushStatement`) + everything related to this | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Types/IntegerPushType.java:3` |
| Should it be maybe long or double instead? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminArray.java:59` |
| Allow for multiple sizes, ex. `String[x][y]` instead of `String[x][x]` | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminArray.java:95` |
| We can maybe make this more efficient by having the `JasminPassable` values in a list? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminArray.java:221` |
| Is this really the best way lol? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminArray.java:262` |
| Implement more types? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/ExpressionBuilder.java:28` |
| Implement more types? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/ExpressionBuilder.java:53` |
| Implement more types | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/ExpressionBuilder.java:78` |
| Fix that primitive types just gets displayed as their representation | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:95` |
| Should we introduce a `Null` type? If so, Couldn't it get confused with `Void` in some cases? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:156` |
| Is this the correct way? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:167` |
| Should this be allowed? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Utils/Helper.java:173` |
| Implement rest of the types | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminVariable.java:51` |
| Implement rest of the types | `N/A` | `src/main/java/me/Munchii/JasminBuilder/JasminVariable.java:144` |
| Hmm | `N/A` | `src/main/java/me/Munchii/JasminBuilder/References/ClassInstance.java:94` |
| Hmm | `N/A` | `src/main/java/me/Munchii/JasminBuilder/DataTypes/ReferenceType.java:17` |
| Should `Null` type be added? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/DataTypes/ValueType.java:5` |
| Document these | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Logging/Message.java:5` |
| How can we stop/prevent the user from chaining on the `JasminMethod` constructor method? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:22` |
| Make instructions for control-flow, loops, field init, etc. | `if/else statements done` | `src/main/java/me/Munchii/JasminBuilder/Main.java:23` |
| Add multi dimensional support for arrays | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:24` |
| Maybe rework how the error system works? Should it even abort/exit when error? And how would the user toggle that | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:26` |
| Make it nice to chain them with `and` (&&) and `or` (||) | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Main.java:34` |
| Can we somehow prevent that the user does this? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Instructions/IfInstruction.java:46` |
| Find a way to represent value (`JasminPassable`) | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Instructions/InitFieldInstruction.java:40` |
| Well doesn't work well with scopes ay | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Methods/JasminMethod.java:106` |
| Is this really the best way? I guess but eh | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Methods/JasminMethod.java:300` |
| Surely there must be a better way, than using the `Hook` method | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Fields/JasminField.java:20` |
| How would we make field spec without knowing the class? | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Fields/JasminField.java:69` |
| If one if the values is `false` it should just use `ifne` (etc) and not push the value | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Conditions/ConditionBuilder.java:40` |
| Should byte be here too? In my testing it acted like ints so I guess | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Conditions/ConditionBuilder.java:136` |
| Hmm. Same as above but well works | `N/A` | `src/main/java/me/Munchii/JasminBuilder/Conditions/JasminCondition.java:38` |
| Why does the helloworld test take 42-50ms, while fib takes around 2-3ms? | `N/A` | `src/test/java/me/Munchii/JasminBuilder/Testing/Test.java:19` |

> Total items: `33`

> Last generated at: `31/05/20 - 11:48:34`