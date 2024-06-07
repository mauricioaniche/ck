# CK

[![Build Status](https://travis-ci.org/mauricioaniche/ck.svg?branch=master)](https://travis-ci.org/mauricioaniche/ck)
[![Code Coverage](https://codecov.io/github/mauricioaniche/ck/coverage.svg)](https://codecov.io/gh/mauricioaniche/ck)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.mauricioaniche/ck/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.mauricioaniche/ck)

CK calculates class-level and method-level code metrics in Java projects by means
of static analysis (i.e. no need for compiled code). Currently, it contains
a large set of metrics, including the famous CK:

- *CBO (Coupling between objects)*: Counts the number of dependencies a class has.
The tools checks for any type used in the entire class (field declaration, method
return types, variable declarations, etc). It ignores dependencies to Java itself
(e.g. java.lang.String).

- *CBO Modified (Coupling between objects)*: Counts the number of dependencies a class has. 
It is very similar to the CKTool's original CBO. However, this metric considers a dependency 
from a class as being both the references the type makes to others and the references that it 
receives from other types.

- *FAN-IN*: Counts the number of input dependencies a class has, i.e, the number of classes 
that reference a particular class. For instance, given a class X, the fan-in of X would be the 
number of classes that call X by referencing it as an attribute, accessing some of its attributes, 
invoking some of its methods, etc.

- *FAN-OUT*: Counts the number of output dependencies a class has, i.e, the number of other classes 
referenced by a particular class. In other words, given a class X, the fan-out of X is the number of 
classes called by X via attributes reference, method invocations, object instances, etc.

- *DIT (Depth Inheritance Tree)*: It counts the number of "fathers" a class has.
All classes have DIT at least 1 (everyone inherits java.lang.Object).
In order to make it happen, classes must exist in the project (i.e. if a class
depends upon X which relies in a jar/dependency file, and X depends upon other
classes, DIT is counted as 2). 

- *NOC (Number of Children)*: It counts the number of immediate subclasses that a 
particular class has. 

- *Number of fields*: Counts the number of fields. Specific numbers for
total number of fields, static, public, private, protected, default, final, and synchronized fields.

- *Number of methods*: Counts the number of methods. Specific numbers for
total number of methods, static, public, abstract, private, protected, default, final, and synchronized methods.
Constructor methods also count here.

- *Number of visible methods*: Counts the number of visible methods. A method is visible if it is not private.

- *NOSI (Number of static invocations)*: Counts the number of invocations
to static methods. It can only count the ones that can be resolved by the
JDT.

- *RFC (Response for a Class)*: Counts the number of unique method
invocations in a class. As invocations are resolved via static analysis,
this implementation fails when a method has overloads with same number of parameters,
but different types.

- *WMC (Weight Method Class)* or *McCabe's complexity*. It counts the number
of branch instructions in a class.

- *LOC (Lines of code)*: It counts the lines of count, ignoring
empty lines and comments (i.e., it's Source Lines of Code, or SLOC).
The number of lines here might be a bit different from the original file, as we use JDT's internal representation of the source code to calculate it.

- *LCOM (Lack of Cohesion of Methods)*: Calculates LCOM metric. This is the very first
version of metric, which is not reliable. LCOM-HS can be better (hopefully, you will
send us a pull request).

- *LCOM&ast; (Lack of Cohesion of Methods)*: This metric is a modified version of the current version 
of LCOM implemented in CK Tool. LCOM&ast; is a normalized metric that computes the lack of cohesion of 
class within a range of 0 to 1. Then, the closer to 1 the value of LCOM&ast; in a class, the less the cohesion 
degree of this respective class. The closer to 0 the value of LCOM&ast; in a class, the most the cohesion of 
this respective class. This implementation follows the third version of LCOM&ast; defined in [1].
	- *Reference:* [1] Henderson-Sellers, Brian, Larry L. Constantine and Ian M. Graham. “Coupling and cohesion 
	(towards a valid metrics suite for object-oriented analysis and design).” Object Oriented Systems 3 (1996): 
	143-158. 

- *TCC (Tight Class Cohesion)*: Measures the cohesion of a class with a value range from 0 to 1. TCC measures the cohesion of a class via direct connections between visible methods, two methods or their invocation trees access the same class variable. 

- *LCC (Loose Class Cohesion)*: Similar to TCC but it further includes the number of indirect connections between visible classes for the cohesion calculation. Thus, the constraint LCC >= TCC holds always. 

- *Quantity of returns*: The number of `return` instructions.

- *Quantity of loops*: The number of loops (i.e., for, while, do while, enhanced for).

- *Quantity of comparisons*: The number of comparisons (i.e., == and !=). Note: != is only
available in 0.4.2+.

- *Quantity of try/catches*: The number of try/catches

- *Quantity of parenthesized expressions*: The number of expressions inside parenthesis.

- *String literals*: The number of string literals (e.g., `"John Doe"`). Repeated strings count as many times as they appear. 

- *Quantity of Number*: The number of numbers (i.e., int, long, double, float) literals.

- *Quantity of Math Operations*: The number of math operations (times, divide, remainder, plus, minus, left shit, right shift).

- *Quantity of Variables*: Number of declared variables.

- *Max nested blocks*: The highest number of blocks nested together.

- *Quantity of Anonymous classes, inner classes, and lambda expressions*: The name says it all.
Note that whenever an anonymous class or an inner class is declared, it becomes an
"entire new class", e.g., CK generates A.B and A.B$C, C being an inner class inside A.B.
However, lambda expressions are not considered classes, and thus, are part of the
class/method they are embedded into. A class or a method only has the number of inner classes
that are declared at its level, e.g., an inner class that is declared inside a method M2,
that is inside an anonymous
class A, that is declared inside a method M, that finally is declared inside a class C,
will not count in class C, but only in method M2 (first-level method it is embodied),
and anonymous class A (first-level class it is embodied).

- *Number of unique words*: Number of unique words in the source code. At method level, it only uses the method body as input. At class level,
it uses the entire body of the class as metrics.
The algorithm basically counts the number of words in a method/class, after removing Java keywords. Names are split based on camel case and underline (e.g., longName_likeThis becomes four words).
See `WordCounter` class for details on the implementation.

- *Number of Log Statements*: Number of log statements in the source code. The counting uses REGEX compatible with SLF4J and Log4J API calls.
See `NumberOfLogStatements.java` and the test examples (`NumberOfLogStatementsTest` and `fixtures/logs`) for more info.

- *Has Javadoc*: Boolean indicating whether a method has javadoc. (Only at method-level for now) 

- *modifiers*: public/abstract/private/protected/native modifiers of classes/methods. Can be decoded using `org.eclipse.jdt.core.dom.Modifier`.  

- *Usage of each variable*: How often each variable was used inside each method.

- *Usage of each field*: How often each local field was used inside each method, local field are fields within a class (subclasses are not included). Also indirect local field usages are detected, indirect local field usages include all usages of fields within the local invocation tree of a class e.g. A invokes B and B uses field a, then a is indirectly used by A.

- *Method invocations*: All directly invoked methods, variations are local invocations and indirect local invocations.
      

Note: CK separates classes, inner classes, and anonymous classes. LOC is the only metric that is not completely isolated from the others, e.g., if A has a declaration of an inner class B, then LOC(A) = LOC(class A) + LOC(inner class B).

## Project Structure

CK is a Java code metrics collection tool, streamlined into a simple structure that revolves around three primary packages:

- **ck:** Contains the core classes that drive the metrics collection process.
- **ck.metrics:** Hosts the metric definitions and implementations.
- **ck.utils:** Utilities supporting the metrics collection process.

For brevity, within this documentation, package prefixes such as `com.github.mauricioaniche.ck` are omitted.

## Architecture

### Core Components

- **CK:** The backbone class of the tool, `CK` manages the orchestration of the entire metrics collection process. It initializes metric finders, handles file partitioning based on available memory, sets up AST parsers with appropriate environment settings, and manages execution flow across different directories and JAR dependencies. It dynamically adjusts its behavior based on user inputs like `useJars`, `maxAtOnce`, and `variablesAndFields` to optimize the processing of Java files for metrics collection.
- **Runner:** The entry point of the application, housed in the `ck` package. This class processes command-line arguments to configure and launch the metrics collection process. It handles user input for the project path, JAR inclusion, file partitioning, metrics detailing, and output directory setup. `Runner` orchestrates the overall execution by initializing and utilizing the `CK` class and handling result output through `ResultWriter`.
- **MetricsExecutor:** This class extends `FileASTRequestor`, a component of the Eclipse JDT (Java Development Tools) core. It plays a pivotal role in the CK framework by orchestrating the metrics collection process. The `MetricsExecutor` coordinates the creation of the Abstract Syntax Tree (AST) for Java source files, which is essential for analyzing and extracting code metrics.

### Metrics Identification

- **MetricsFinder:** This utility class, located in `ck.utils`, plays a crucial role in the dynamic identification and instantiation of metric collector classes within the CK framework. It targets classes that implement the `ClassLevelMetric` and `MethodLevelMetric` interfaces from the `metrics` package.

  The `MetricsFinder` utilizes the `Reflections` library to scan and load metric collector classes at runtime, which enables the CK system to be extensible and adaptable to new metrics without requiring modifications to the core architecture. This feature is particularly useful for integrating custom metrics into the analysis process seamlessly.

### Metrics Collection

- **CKVisitor:** An integral component of the CK framework, `CKVisitor` extends the `ASTVisitor` class provided by the Eclipse JDT (Java Development Tools) library, enabling detailed analysis and metric collection directly from Java source code's Abstract Syntax Tree (AST).

  The visitor is designed to traverse various nodes of the AST, such as types and methods, and apply specific actions at each node. It effectively manages a stack-based hierarchy of classes and methods, allowing metrics to be calculated and collected in the context of the current node's scope.

- **CKASTVisitor:** Implemented by metric classes in `ck.metrics`, allowing each metric to handle specific AST nodes of interest, such as method invocations and class instance creations.
- **ClassLevelMetric** and **MethodLevelMetric:** Interfaces defining methods for collecting class-level and method-level metrics, respectively.

### Results Notification and Storage

- **MetricsExecutor:** After collecting metrics, it uses a Notifier design pattern to broadcast the results using the `CKNotifier` interface.
- **Anonymous class in Runner.main:** Fills the `CKClassResult` and `CKMethodResult` with the collected data.
- **ResultWriter:** Utilizes the collected data to generate and store results in .CSV format.

## Design Patterns

CK framework incorporates a number of well-established design patterns to enhance modularity, extendibility, and maintainability of its codebase. These patterns enable the framework to efficiently handle complex operations such as traversing abstract syntax trees (AST), collecting metrics, and notifying results. Below are the key design patterns utilized:

- **Visitor Pattern:** The `CKVisitor` and `CKASTVisitor` interfaces implement the Visitor pattern, which is pivotal in handling operations on various AST nodes without altering the classes of the elements on which it operates. This pattern is especially beneficial in scenarios where a component needs to perform distinct and unrelated operations across a class hierarchy of AST nodes. It simplifies code by externalizing operational logic into visitor objects, facilitating easy addition of new operations without modifying existing node classes. This separation of concerns leads to a more maintainable and extensible codebase, where AST node structures and operations on them are decoupled.

- **Notifier Pattern:** CK adopts the Notifier pattern through the use of `CKNotifier`, which acts as a central mechanism to broadcast the results of the metrics collection to all registered observers. This pattern is crucial for creating a loosely coupled architecture where the subject (metric computation process) is independent of its observers (results processors or report generators). This enables CK to notify multiple components about the completion of metric calculations without coupling to specific components, which enhances flexibility and scalability of the system.

- **Factory Pattern:** The instantiation of metric collectors is managed by `MetricsFinder`, which embodies the Factory pattern. This pattern is utilized to encapsulate the logic of instantiating specific metric collector classes based on runtime decisions. The Factory pattern simplifies the process of adding new types of metric collectors without disturbing the existing code, providing a plug-and-play architecture where new metrics can be introduced seamlessly. It also aids in maintaining separation of concerns, as the process of creating metric objects is isolated from the core logic of metric collection.

By leveraging these design patterns, CK efficiently manages complexity and ensures that the framework remains robust, adaptable, and easy to extend as new requirements and metric types emerge.

## How to use the standalone version

You need at least Java 8 to be able to compile and run this tool.

To use the _latest version_ (which you should), clone the project and generate a JAR. A simple
`mvn clean compile package` generates the single JAR file for you (see your _target_ folder).

Then, just run:

```
java -jar ck-x.x.x-SNAPSHOT-jar-with-dependencies.jar \
	<project dir> \
	<use jars:true|false> \
	<max files per partition, 0=automatic selection> \
	<variables and fields metrics? True|False> \
	<output dir> \
	[ignored directories...] \
	<verbose flag for large metric outputs? true|false>
```

`Project dir` refers to the directory where CK can find all the source code to be parsed.
Ck will recursively look for .java files. CK can use the dependencies of the project
as to improve its precision. The `use jars` parameters tells CK to look for any .jar files
in the directory and use them to better resolve types. `Max files per partition` tells JDT the size
of the batch to process. Let us decide that for you and start with 0; if problems happen (i.e., 
out of memory) you think of tuning it. `Variables and field metrics` indicates to CK whether
you want metrics at variable- and field-levels too. They are highly fine-grained and produce a lot of output;
you should skip it if you only need metrics at class or method level. Aditionally, `output dir` refer to the 
directory where CK will export the csv file with metrics from the analyzed project.
Optionally, you can specify any number ignored directories, separated by spaces (for example, `build/`).
By default, `.git` and all other hidden folders are ignored. Finally, the `verbose flag` tells CK if it must process metrics tagged as large outputs. If you are not interested in the detailed output of the metrics, you can set it to false.

The tool will generate three csv files: class, method, and variable levels.


## How to integrate it in my Java app

Learn by example. See `Runner.java` class.

## Maven

See the most recent version of the library in the badge at the beginning of this README, or at https://mvnrepository.com/artifact/com.github.mauricioaniche/ck.

Use the following snippet in your pom.xml. Update X.Y.Z with the most recent version of the tool (check mvnrepository.com or the badge at the beginning of this README file):

```
<!-- https://mvnrepository.com/artifact/com.github.mauricioaniche/ck -->
<dependency>
    <groupId>com.github.mauricioaniche</groupId>
    <artifactId>ck</artifactId>
    <version>X.Y.Z</version>
</dependency>
```

You also may use the CK maven plugin, developed by @jazzmuesli, which automatically runs CK in your project. Very useful to developers: https://github.com/jazzmuesli/ck-mvn-plugin.


## Supporting a new version of Java

This tool uses Eclipse's JDT Core library under the hood for AST
construction. Currently the compliance version is set to Java 11.

Need support for a newer language version? The process of adding it is
very straightforward, considering contributing a PR:

1. Add a failing unit test case showcasing at least one of the syntax
features present in the new version you want to provide support.
2. Update the Eclipse JDT Core dependency in the `pom.xml` file. You may
use a repository browser like
[MVN Repository](https://mvnrepository.com/artifact/org.eclipse.jdt/org.eclipse.jdt.core)
to ease this process.
3. Also in the `pom.xml` file, update the `source` and `target`
properties of the Maven Compiler plugin accordingly.
4. Adjust the following lines in `CK.java`:
    ```
    [...]
    ASTParser parser = ASTParser.newParser(AST.JLS11);
    [...]
    JavaCore.setComplianceOptions(JavaCore.VERSION_11, options);
    [...]
    ```
5. Check if the failing unit test case you added in the first step is
now green. Then submit a PR.


## Why is it called CK?

Because the tool was born to just calculate the CK classLevelMetrics, but it grew beyond
my expectations... Life is funny!

## How to cite?

Please, use the following bibtex entry:

```
@manual{aniche-ck,
  title={Java code metrics calculator (CK)},
  author={Maurício Aniche},
  year={2015},
  note={Available in https://github.com/mauricioaniche/ck/}
}
```

## How to Contribute

Just submit a PR! :)

## License

This software is licensed under the [Apache 2.0 License](LICENSE).
