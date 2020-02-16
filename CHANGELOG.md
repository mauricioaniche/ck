# CK changelog

## 0.5.0

* Passing the number of files in a JDT partition via (optional) parameter. We, however,
suggest you to only change this number if you have a good reason, as the default
value is calculated based on the amount of free memory available.
* Variables and field metrics can now be disabled via a parameter. This produces
way less output.
* New metric: Number of log statements in a method.
* Better documentation on "Number of Methods" metrics: Constructor methods are counted here.
* Enhancements on "max nested block depth" and "word count".
* Bug fixes related to sub-classes, e.g., subclasses inside enums, and some classes had
-1 as number of sub-classes.
* Add Pitest as test dependency of the project.
* Refactorings: metrics now implement CKASTVisitor instead of JDT's ASTVisitor directly,
variable and field metrics are now indicated by means of an interface and not an annotation.
* SubClass was renamed to innerClass. Subclass was definitely the wrong name.
* Lots of new tests.

## 0.4.4

* Compiling CK with JDK 8 compatibility (instead of Java 11)

## 0.4.3

* FIX: Lambda expressions declared in field initialization used to crash
CK. In this version, lambda expressions are considered part of the class
and the method it is embedded; in other words, lambda expressions, differently
from anonymous classes, are not considered "classes".

* FIX/FEATURE: The CBO metric now tries to capture as much as coupling possible
even when the binding fails. Moreover, it also fixes a small NPE that was occurring
when one of the resolveBinding failed.

## 0.4.2

* FEATURE: The 'number of comparisons' operator now also counts !=.
Before that, it would only count ==.

* FIX: If a method binding fails, CK was throwing an exception in its endVisit.

* FEATURE: CK can make use of jar files in the project folder to better
resolve bindings.

* FEATURE: CK now first tries to use the resolved binding; if the binding fails,
it then uses the information available at the class only (in 0.4.x versions before
this one, if binding failed, CK would not process that class/method).

* FEATURE: When collecting java files, CK always ignores .git directories.

## 0.4.1

* FEATURE/FIX: Add support for static initializers as methods. In 0.4.0, an exception
was happening whenever a class had an initializer. The method appears
as "(initializer)"

## 0.4.0

* FEATURE: Better support for classes. sub-classes, and anonymous classes.
* FEATURE: Support for Java 11
* FEATURE: Metrics at method and variable levels.

(I was not doing a changelog before that)
