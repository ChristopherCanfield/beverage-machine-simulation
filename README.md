# Beverage Controller Example

![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)  
  
Christopher D. Canfield  
BU MET CS 665 Group 5

![Beverage Controller UI Example](docs/images/gui-execution-example.png "Beverage Controller GUI")

1. [Overview](#overview)
2. [Compiling & Running](#compiling-&-running)
    1. [How to compile](#how-to-compile)
    2. [How to run the unit tests](#how-to-run-the-unit-tests)
    3. [How to run](#how-to-run)
    4. [Example runs](#example-runs)
3. [Design](#design)
    1. [Assumptions](#assumptions)
    2. [UML](#uml)
    3. [Design Patterns](#design-patterns)
    4. [Notes](#notes)


## Overview

We have a seen a number of excellent, small-scale examples so far in this course. That is a great way to learn the patterns, because small-scale examples allow us to more easily focus on the design, and not get lost in irrelevant details.

However, most useful programs are not small. Additionally, patterns are rarely used alone: typically, a medium-scale or larger program will use many of the design patterns that we have discussed, or will discuss in the later modules. A larger program also presents more opportunities to show the design principles that we have discussed, such as ensuring that our design is flexible, understandable, robust & reliable, and so forth. (See the module 1 section titled "Goals of Software Design" for more design goals that we work to achieve in our software.)

For those reasons, I decided to take our assignment 1 specification, and create a program that is at a larger scale than what we have seen so far. I have incorporated a number of [design patterns](#Design-Patterns) that we have seen, or will soon see, as well as a few that we will only briefly touch on.

The program has approximately 2,000 lines of code (lines of code is a notoriously bad metric, but it can be used to indicate scale), is heavily documented (aside from the GuiApp graphical UI implementation, which needs more documentation), and has nearly 100% test coverage in the core packages: `edu.bu.met.cs665.bev.controller` and `edu.bu.met.cs665.bev.hardware`.
 

## Compiling & Running

Instructions on compiling and running the program using Apache Maven: 

### How to compile

```bash
mvn clean compile
```

### How to run the unit tests

```bash
mvn clean test
```

### How to run

Running the automated command line version:

```bash
mvn compile exec:java -Dexec.mainClass="edu.bu.met.cs665.Main" -Dlog4j.configuration="file:log4j.properties"
```

Running the GUI version:

```bash
mvn compile exec:java -Dexec.mainClass="edu.bu.met.cs665.gui.GuiApp" -Dlog4j.configuration="file:log4j.properties"
```

Esc exits the GUI version of the application.

### Example Runs

**Command Line**

```console
$ mvn compile exec:java -Dexec.mainClass="edu.bu.met.cs665.Main" -Dlog4j.configuration="file:log4j.properties"
[INFO] Scanning for projects...
[INFO]
[INFO] -----------< edu.bu.cs665:Beverage-Controller-CS665-Group-5 >-----------
[INFO] Building Beverage-Controller-CS665-Group-5 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ Beverage-Controller-CS665-Group-5 ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 17 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ Beverage-Controller-CS665-Group-5 ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- exec-maven-plugin:1.3:java (default-cli) @ Beverage-Controller-CS665-Group-5 ---
Main [INFO]: Main: Welcome to the BeverageController tests.
Main [INFO]: Main: Constructing the HardwareInterface
Main [INFO]: Main: Constructing BeverageController
Main [INFO]: Main: Subscribing to BeverageController's events.
Main [INFO]:
Main [INFO]: Main: Constructing new order.
Main [INFO]: Main: BeverageController's current state is READY
Main [INFO]: Main: Submitting order to BeverageController: Latte Macchiato
Main [INFO]: BeverageController received an order: Latte Macchiato
Main [INFO]: BeverageController's state changed: MAKING_DRINK
Main [INFO]:
Main [INFO]: Main: Constructing new order.
Main [INFO]: Main: BeverageController's current state is MAKING_DRINK
Main [INFO]: Main: Submitting order to BeverageController: Green Tea with Milk, Milk
Main [INFO]: BeverageController received an order: Green Tea with Milk, Milk
Main [INFO]: BeverageController's state changed: MAKING_DRINK
Main [INFO]:
Main [INFO]: Main: Constructing second order.
Main [INFO]: Main: BeverageController's current state is MAKING_DRINK
Main [INFO]: Main: Submitting order to BeverageController: Americano with Milk, Sugar
Main [INFO]: BeverageController received an order: Americano with Milk, Sugar
Main [INFO]: BeverageController's state changed: MAKING_DRINK
Main [INFO]:
Main [INFO]: Main: Constructing second order.
Main [INFO]: Main: BeverageController's current state is MAKING_DRINK
Main [INFO]: Main: Submitting order to BeverageController: Yellow Tea with Milk, Milk, Milk, Milk
Main [INFO]: BeverageController received an order: Yellow Tea with Milk, Milk, Milk, Milk
Main [INFO]: BeverageController's state changed: MAKING_DRINK
Main [WARN]: BeverageController received too many condiments in order.
        Message: Too many condiments in order. Milk: 4; Sugar: 0. Adjusting order to 3 milk & 0 sugar.
Main [INFO]:
Main [INFO]: Main: Waiting for remaining drinks to be made.
Main [INFO]: BeverageController reports that the drink was made at 2019-07-25T16:41:27.711429900Z. Hardware command was C:LM:205.
Main [INFO]: BeverageController reports that the drink was made at 2019-07-25T16:41:27.719427Z. Hardware command was T:GT:175:M:M.
Main [INFO]: BeverageController reports that the drink was made at 2019-07-25T16:41:27.719427Z. Hardware command was C:A:210:M:S.
Main [INFO]: BeverageController reports that the drink was made at 2019-07-25T16:41:27.722427600Z. Hardware command was T:YT:170:M:M:M.
Main [INFO]: BeverageController's state changed: READY
Main [INFO]: Main: All drinks made. Shutting down.
Main [INFO]: Main: Exiting BeverageController tests.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.599 s
[INFO] Finished at: 2019-07-25T12:41:27-04:00
[INFO] ------------------------------------------------------------------------

```

**GUI**

![GUI Example: Latte](docs/images/gui-example-latte.png "GUI Example: Latte")

```console
$ mvn compile exec:java -Dexec.mainClass="edu.bu.met.cs665.gui.GuiApp" -Dlog4j.configuration="file:log4j.properties"
[INFO] Scanning for projects...
[INFO]
[INFO] -----------< edu.bu.cs665:Beverage-Controller-CS665-Group-5 >-----------
[INFO] Building Beverage-Controller-CS665-Group-5 1.0-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ Beverage-Controller-CS665-Group-5 ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 17 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ Beverage-Controller-CS665-Group-5 ---
[INFO] Nothing to compile - all classes are up to date
[INFO]
[INFO] --- exec-maven-plugin:1.3:java (default-cli) @ Beverage-Controller-CS665-Group-5 ---
GuiApp [INFO]: GuiApp: Brew button clicked. Submitting order: Latte Macchiato with Milk, Sugar, Sugar, Sugar
GuiApp [INFO]: Beverage Controller reports that is has received an order: Latte Macchiato with Milk, Sugar, Sugar, Sugar
GuiApp [INFO]: Beverage Controller reports that its state has changed to MAKING_DRINK
GuiApp [INFO]: Beverage Controller reports that beverage is ready at 2019-07-25T16:44:12.428760200Z. Order hardware command was C:LM:205:M:S:S:S
GuiApp [INFO]: Beverage Controller reports that its state has changed to READY

```


## Design

### Assumptions

Most of the assumptions were derived from the requirements document.  

* The beverage vending machine can initially brew six hot drinks, including three types of coffee and three types of tea: Americano, Espresso, Latte Macchiato, Black Tea, Gree Tea, and Yellow Tea.
* A drink order can contain up to three milks and three sugars. If the drink order sent to the controller exceeds these maximums -- such as if the user interface doesn't correctly limit them -- the controller will automatically update the order and notify any observers.
* New beverage and condiment types may be added in the future. These may be added directly in code (in this design, they would extend Beverage, CoffeeBeverage, TeaBeverage, or Condiment), or they could be loaded from a data source, such as a text file, a database, or from a web service (in that case, they would be instantiated using ParameterizedBeverage or ParameterizedCondiment).
* The controller may be used with multiple user interfaces, such as touch screen, phone app, and command line UIs.
* We would like the ability to use the same user interfaces with other controllers, such as a controller that makes only cold drinks.
* After processing the request, the controller sends the recipe to the hardware, which is represented here as the "HardwareInterface" (called "Interface" because it interfaces directly with the hardware, not because it is implemented as a Java interface in the system). The recipe is converted into a hardware instruction, which is what the hardware ultimately uses to create the drink.
* The controller won't block the user interface: user interfaces can continue to submit orders as the current orders are being processed. Orders are completed in first-in-first-out order.


### UML

**Core Classes, Interfaces, and Relationships**

![UML Class Diagram: Overview](docs/images/class-diagram-overview.png "UML Class Diagram: Overview")

___
**Beverages & Condiments**

![UML Class Diagram: Beverages & Condiments](docs/images/class-diagram-beverages-condiments.png "UML Class Diagram: Beverages & Condiments") 

### Design Patterns

**Observer**
> Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. [GoF, *Design Patterns*, p. 293]

* `BeverageControllerObserver <--> BeverageController`: Any class that implements the BeverageControllerObserver interface can subscribe to events that are issued by a BeverageController. Any BeverageControllerObserver can subscribe to any BeverageController (which currently only includes one concrete class: the HotBeverageController). In this implementation, the Main and GuiApp classes implement the BeverageControllerObserver interface, and subscribe to the HotBeverageController's events. Future classes that could benefit from this could include a console UI and a logging class.  
This implementation uses the push model. That somewhat increases coupling, but at the benefit of providing more detailed information to the observers.
* `GuiApp <--> JFrame`: In addition to implementing the BeverageController interface, GuiApp implements the MouseListener, KeyListener and MouseMotionListener observer interfaces, which allows it to receive events from a Swing JFrame. 
  
**Builder**  
> Separate the construction of a complex object from its representation so that the same construction process can create different representations. [GoF, *Design Patterns*, p. 97]

* `Spinner`: The spinner has a left and right arrow on either side, and in the middle contains an image representing the current value. It is a generic type, and can be used to represent any Java type, including Integers and Beverages. Its flexibility makes its creation somewhat complex, however. For that reason, the Builder pattern was used to make the creation process clearer. Example, from the GuiApp class:

```java
beverageSpinner = new Spinner.Builder<Beverage>()
    .setResourceManager(resourceManager)
    .setUpButtonRect(new Rectangle(312, 235, 31, 34))
    .setDownButtonRect(new Rectangle(178, 235, 31, 34))
    .setItemPosition(new Point(163, 238))
    .addItem(new AmericanoBeverage(), ImageId.TEXT_AMERICANO)
    .addItem(new EspressoBeverage(), ImageId.TEXT_ESPRESSO)
    .addItem(new LatteMacchiatoBeverage(), ImageId.TEXT_LATTE_MACCHIATO)
    .addItem(new BlackTeaBeverage(), ImageId.TEXT_BLACK_TEA)
    .addItem(new GreenTeaBeverage(), ImageId.TEXT_GREEN_TEA)
    .addItem(new YellowTeaBeverage(), ImageId.TEXT_YELLOW_TEA)
    .build();
```

* `Recipe`: Recipe uses the Builder pattern in order to introduce named parameters, and to fully separate its creation from its representation. It's useful since Recipe is immutable, like many of the classes in this program, and so it provides additional flexibility during the creation process that may be more difficult when using only constructors.  

**Prototype**
> Specify the kinds of objects to create using a prototypical instance, and create new objects by copying this prototype. [GoF, *Design Patterns*, p. 117]

`createCondimentBuilderPrototype` in `GuiApp`: GuiApp contains two spinners that are very similar: one for milk condiments, and one for sugar condiments. Rather than duplicating much of their creation, GuiApp creates a prototypical representation using the `createCondimentBuilderPrototype` method, clones the `Spinner.Builder` prototypical instance that is returned, and then modifies each clone to be appropriate for either the milk or sugar beverage spinner. Compare this with the code for the beverageSpinner, above: by creating a prototypical instance first, and then cloning it, we've significantly reduced the amount of duplicate code that these two spinners would have had.

```java
    // Create a condimentBuilder prototype.
    Spinner.Builder<Integer> condimentBuilder = createCondimentBuilderPrototype(resourceManager);

    // Create the milk spinner.
    milkSpinner = condimentBuilder.clone()
        .setUpButtonRect(new Rectangle(229, 276, 31, 34))
        .setDownButtonRect(new Rectangle(178, 276, 31, 34))
        .setItemPosition(new Point(202, 275))
        .build();

    // Create the sugar spinner.
    sugarSpinner = condimentBuilder.clone()
        .setUpButtonRect(new Rectangle(229, 313, 31, 34))
        .setDownButtonRect(new Rectangle(178, 313, 31, 34))
        .setItemPosition(new Point(202, 310))
        .build();
```

* In Java, the Prototype pattern is typically implemented by implementing the Cloneable interface, and overriding the clone() method, as I did for Spinner.Builder. Be careful: clone() can be difficult to implement properly, if you're not familiar with it. See Joshua Bloch, *Effective Java, Third Edition*, p. 58: "Item 13: Override `clone` judiciously."


**Composite**
> Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly. [GoF, *Design Patterns*, p 168]

* `GuiApp`: GuiApp extends the Swing `Component` class. Like many GUI frameworks, Swing components are organized into a [scene graph](https://en.wikipedia.org/wiki/Scene_graph), which forms a tree of components.


### Notes

**Test Coverage**

As of July 25, 2019:  
* edu.bu.met.cs665.bev.controller: 99.1%
* edu.bu.met.cs665.bev.hardware: 99.1%

Main and the GUI are not currently covered by automated tests.

**Why wasn't Java Beans style naming used for getters (getX/getY)?**  

getX(), getY() is perfectly fine. But it's not required. For an explanation, I'll refer you to Joshua Bloch, *Effective Java: Third Edition*, p. 291: 
> Methods that return a non-boolean function or attribute of the object on which they're invoked are usually named with a noun, a noun phrase, or a verb phrase beginning with the verb get, for example, `size`, `hashCode`, or `getTime`. There is a vocal contingent that claims that only the third form (beginning with get) is acceptable, but there is little basis for this claim. The first two forms usually lead to more readable code ...

**Other patterns that could be used**

The orders that are submitted to BeverageController could more fully implement the Command pattern, which would make it easier to allow the cancellation of submitted orders. 

Individual non-parameterized beverages and condiments could use the Singleton pattern, since these are immutable and so are effectively clones of each other. That would potentially very slightly improve efficiency, at the cost of more complexity, so I'm not sure that would be a worthwhile change.

**Flaws in the design and implementation**

Beverage should probably be an interface. The Swing code is probably not ideal, since I don't have much experience with Swing (RIP, JavaFX), and the GUI was a secondary concern. Unlike the core program, which has unit tests that are approaching 100% code coverage according to EclEmma, the GUI has no automated tests, and has only been manually tested. There are probably a few design inconsistencies that should be reconciled. These are issues to address in a future release.

