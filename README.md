# CK

[![Build Status](https://travis-ci.org/mauricioaniche/ck.svg?branch=master)](https://travis-ci.org/mauricioaniche/ck)
[![Code Coverage](https://codecov.io/github/mauricioaniche/ck/coverage.svg)](https://codecov.io/gh/mauricioaniche/ck)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.mauricioaniche/ck/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.mauricioaniche/ck)

CK calculates class-level and metric-level code metrics in Java projects by means
of static analysis (i.e. no need for compiled code). Currently, it contains
a large set of metrics, including the famous CK:

- *CBO (Coupling between objects)*: Counts the number of dependencies a class has.
The tools checks for any type used in the entire class (field declaration, method
return types, variable declarations, etc). It ignores dependencies to Java itself
(e.g. java.lang.String).

- *DIT (Depth Inheritance Tree)*: It counts the number of "fathers" a class has.
All classes have DIT at least 1 (everyone inherits java.lang.Object).
In order to make it happen, classes must exist in the project (i.e. if a class
depends upon X which relies in a jar/dependency file, and X depends upon other
classes, DIT is counted as 2). 

- *Number of fields*: Counts the number of fields. Specific numbers for
total number of fields, static, public, private, protected, default, final, and synchronized fields.

- *Number of methods*: Counts the number of methods. Specific numbers for
total number of methods, static, public, abstract, private, protected, default, final, and synchronized methods.
Constructor methods also count here.

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

- *Quantity of Anonymous classes, inner classes, and lambda expressions*. The name says it all.
Note that whenever an anonymous class or an inner class is declared, it becomes an
"entire new class", e.g., CK generates A.B and A.B$C, C being an inner class inside A.B.
However, lambda expressions are not considered classes, and thus, are part of the
class/method they are embedded into. A class or a method only has the number of inner classes
that are declared at its level, e.g., an inner class that is declared inside a method M2,
that is inside an anonymous
class A, that is declared inside a method M, that finally is declared inside a class C,
will not count in class C, but only in method M2 (first-level method it is embodied),
and anonymous class A (first-level class it is embodied).

- *Number of unique words*. Number of unique words in the source code. At method level, it only uses the method body as input. At class level,
it uses the entire body of the class as metrics.
The algorithm basically counts the number of words in a method/class, after removing Java keywords. Names are split based on camel case and underline (e.g., longName_likeThis becomes four words).
See `WordCounter` class for details on the implementation.

- *Number of Log Statements*. Number of log statements in the source code. The counting is based on the following regex:
`line.matches(".*\\.(info|warn|debug|error)\\(.*") || line.matches(".*log(ger)?\\..*");`.
See `NumberOfLogStatements.java` for more info.

- *modifiers*. public/abstract/private/protected/native modifiers of classes/methods. Can be decoded using `org.eclipse.jdt.core.dom.Modifier`.  

- *Usage of each variable*. How much each variable was used inside each method.

- *Usage of each field*. How much each field was used inside each method.

      
(In a previous version, it calculated NOC (Number of Children), but it doesn't do it anymore,
as it requires too much memory.)

Note: CK separates classes, inner classes, and anonymous classes. LOC is the only metric that is not completely isolated from the others, e.g., if A has a declaration of an inner class B, then LOC(A) = LOC(class A) + LOC(inner class B).

## How to use the standalone version

You need at least Java 8 to be able to compile and run this tool.

To use the _latest version_ (which you should), clone the project and generate a JAR. A simple
`mvn clean compile assembly:single` generates the single JAR file for you (see your _target_ folder).

Then, just run:
```
java -jar ck-x.x.x-SNAPSHOT-jar-with-dependencies.jar <project dir> <use jars:true|false> <max files per partition, 0=automatic selection> <variables and fields metrics? True|False>
```

`Project dir` refers to the directory where CK can find all the source code to be parsed.
Ck will recursively look for .java files. CK can use the dependencies of the project
as to improve its precision. The `use jars` parameters tells CK to look for any .jar files
in the directory and use them to better resolve types. `Max files per partition` tells JDT the size
of the batch to process. Let us decide that for you and start with 0; if problems happen (i.e., 
out of memory) you think of tuning it. Finally, `variables and field metrics` indicates to CK whether
you want metrics at variable- and field-levels too. They are highly fine-grained and produce a lot of output;
you should skip it if you only need metrics at class or method level.

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
@manual{
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
