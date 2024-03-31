# Makefile for managing the build process of a Java project using Maven

# Default rule
default: build

# Rule for building the project
build:
	mvn compile

# Rule for cleaning and packaging the project without running tests
package:
	clear
	mvn clean package -DskipTests

# Rule for running tests
test:
	mvn test

# Rule for cleaning the project
clean:
	mvn clean

# Rule for generating documentation
doc:
	mvn javadoc:javadoc

# Rule for installing the project's artifacts into the local repository
install:
	mvn install

.PHONY: default build package test clean doc install
