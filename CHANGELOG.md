# CK changelog

## 0.4.2 (under development)

- The 'number of comparisons' operator now also counts !=. Before that,
it would only count ==.
- FIX: If a method binding fails, CK was throwing an exception in its
endVisit.

## 0.4.1

- Add support for static initializers as methods. In 0.4.0, an exception
was happening whenever a class had an initializer. The method appears
as "(initializer)"

## 0.4.0

- Better support for classes. sub-classes, and anonymous classes.

## 0.3.x

- Support for Java 11
- Metrics at method and variable levels.