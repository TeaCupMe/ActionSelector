# Action selector by TeaCupMe
This is a simple ActionSelector to automate parsing user input and get rid of
repeating switch-case statements.

Tired of writing code like this?
```java
import java.util.Scanner;
public class App() {
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int input = scanner.nextInt();
    switch (input) {
        case (0):
            method1();
            break;
        case (1):
            method2();
            break;
        case (2):
            method3();
            break;
        }
    }
}
```
Sure, it's short and somewhat readable. 

But what about handling incorrect input?

Or about keeping track of numbers in `case()` statements?

Use ActionSelector to automate it:

```java
import space.crtech.utils.UserAction;
import space.crtech.utils.UserActionSelector;

import java.util.ArrayList;
import java.util.Scanner;

public class App() {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        ArrayList<UserAction> actions = new ArrayList<>();

        actions.add(new UserAction("make thing 1") {
            @Override
            public void act() {
                method1();
            }
        });

        actions.add(new UserAction("make thing 2") {
            @Override
            public void act() {
                method2();
            }
        });
        
        actions.add(new UserAction("make thing 3") {
            @Override
            public void act() {
                method3();
            }
        });
        
        UserActionSelector.setOutputStream(System.out);
        UserActionSelector.run(actions, scanner::nextInt);
    }
}
```
There are more lines, but most things are automated.

`UserActionSelector.run(...)` keeps track of action indexes, 
keeps asking for input until it is valid (number in range `[0, n-1]` - 
where `n` is the number of actions), and finally runs the method, associated with selected action