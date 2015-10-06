# CK

Simple CK metrics calculator for Java files. No need for bytecode. 

Read more about them here: http://www.aivosto.com/project/help/pm-oo-ck.html.

# How to use it

```
java -jar ck.jar /dir/to/your/project/src > output.csv
```

# How to Contribute

```
git clone https://github.com/mauricioaniche/ck.git
cd src/test/resources
echo "/my/dir/to/src/test/project" < project-dir.txt
```

Then, you can:

* compile : `mvn clean compile`
* test    : `mvn test`
* eclipse : `mvn eclipse:eclipse`
* build   : `mvn clean compile assembly:single`


# License

This software is licensed under the Apache 2.0 License.
