# CK changelog

## 0.4.3 (under development)

* FIX: Lambda expressions declared in field initialization used to crash
CK. In this version, lambda expressions are considered part of the class
and the method it is embedded; in other words, lambda expressions, differently
from anonymous classes, are not considered "classes".

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

## 0.3.x

* FEATURE: Support for Java 11
* FEATURE: Metrics at method and variable levels.

(I was not doing a changelog before that)