# CK

[![Build Status](https://travis-ci.org/mauricioaniche/ck.svg?branch=master)](https://travis-ci.org/mauricioaniche/ck)

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
empty lines.

- *LCOM (Lack of Cohesion of Methods)*: Calculates LCOM metric. This is the very first
version of metric, which is not reliable. LCOM-HS can be better (hopefully, you will
send us a pull request). 

- *Quantity of returns*: The number of `return` instructions.

- *Quantity of loops*: The number of loops (i.e., for, while, do while, enhanced for).

- *Quantity of comparisons*: The number of comparisons (i.e., ==).

- *Quantity of try/catches*: The number of try/catches

- *Quantity of parenthesized expressions*: The number of expressions inside parenthesis.

- *String literals*: The number of string literals (e.g., `"John Doe"`). Repeated strings count as many times as they appear. 

- *Quantity of Number*: The number of numbers (i.e., int, long, double, float) literals.

- *Quantity of Math Operations*: The number of math operations (times, divide, remainder, plus, minus, left shit, right shift).

- *Quantity of Variables*: Number of declared variables.

- *Max nested blocks*: The highest number of blocks nested together.

- *Quantity of Anonymous classes, subclasses, and lambda expressions*. The name says it all.

- *Number of unique words*. Number of unique words in the source code. See `WordCounter` class for details on the
implementation.  

- *Usage of each variable*. How much each variable was used inside each method.

- *Usage of each field*. How much each field was used inside each method.
			
(In a previous version, it calculated NOC (Number of Children), but it doesn't do it anymore,
as it requires too much memory.)

# Java Syntax Support

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

# How to use the standalone version

You need at least Java 11 to be able to compile and run this tool.

To use the _latest version_ (which you should), clone the project and generate a JAR. A simple
`mvn clean compile assembly:single` generates the single JAR file for you (see your _target_ folder).

>_PS. In case you face `ERROR - Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:compile ...` change maven-compiler-plugin to the Java version that you have._

Then, just run:
```
java -jar ck-x.x.x-SNAPSHOT-jar-with-dependencies.jar <project dir>
```

The tool will generate three csv files (one for classLevelMetrics at class level, method level, and variable level).


# How to integrate it in my Java app

Learn by example. See `Runner.java` class. In a nutshell:

```
new CK().calculate(path, result -> {
    // process each 'result' here
}
```

Add it to your POM.xml: https://mvnrepository.com/artifact/com.github.mauricioaniche/ck

# Why is it called CK?

Because the tool was born to just calculate the CK classLevelMetrics, but it grew beyond
my expectations... Life is funny!
  
# How to Contribute

```
git clone https://github.com/mauricioaniche/ck.git
```

Then, you can:

* compile : `mvn clean compile`
* test    : `mvn test`

Note that you can optionally use the bundled Maven Wrapper to
automatically download Maven for you, e.g: `./mvnw clean compile`.

# License

This software is licensed under the [Apache 2.0 License](LICENSE).
