# SMARTEDHUB
Maven project generated with com.a9ski:quick-start archetype


# Development guide
1. Install pre-commit (https://pre-commit.com/)
2. Install the pre-commit hook by executing `pre-commit install` inside project directory
3. Run against all files in the project: `pre-commit run --all-files`

# Building
```
mvn clean install
```
produces a jar file with MANIFEST containing main class and classpath.
All dependencies are copied to target/libs directory.

# Runing
```
mvn exec:java
```

or

```
cd target
java -jar xxxx.jar
```
