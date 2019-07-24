# Beverage Controller Example

![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)  
  
Christopher D. Canfield  
BU MET CS 665 Group 5

![Beverage Controller UI Example](doc/images/gui-execution-example.png "Beverage Controller GUI")

## Overview



## Design

### Assumptions

### Design


### UML


### Design Patterns

Observer  
Builder  
Command  
Composite  

### Notes & Comments

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


