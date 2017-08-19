# COSC410 Project

## Argument Parser
The entire goal of this project is to create a command-line argument parsing library. 
The feature specifications are provided in separate files in the `features` directory.



## Building the Project
After you have cloned the repository, you should be able to navigate
to the directory containing the `gradle.build` file. There, you can
build the project by running the command

`gradlew build`

Then, you can run the unit test coverage report.

`gradlew jacocoTestReport`

You can even do multiple things in one statement:

`gradlew build jacocoTestReport`

When you want to get rid of all of the temporary files (like compiled class files and such), you can say

`gradlew clean`

If you want to do a full build and reporting from a clean project, you can just string it all together:

`gradlew clean build jacocoTestReport`



## Structure
The directory structure that is assumed by Gradle (though it can be changed if you want) is as follows:

    project root     (root directory of project)
               |
                - build.gradle    (contains the instructions for the build tasks)
               |
                - demos           (root directory of any demo programs)
               |
                - features        (directory containing all the features and robot tests)
               |
                - src             (root directory of the source code; main and tests)
                    |
                     - main       (root directory of normal source code)
                    |     |
                    |      - java (all packages go here)
                    |           |
                    |            - edu    
                    |           |    |
                    |           |     - wofford (source code goes here)
                    |           |
                    |            - keywords (Robot Framework keyword code goes here)
                    |
                     - test       (root directory of test code, both unit and acceptance)
                          |
                           - acceptancetest    (all Robot Framework test files go here)
                          |
                           - java (all packages go here)
                                |
                                 - edu    
                                     |
                                      - wofford (unit test code goes here)

After you run `gradlew build`, a new `build` directory will automatically be created. This will contain all of the generated files (compiled class files, JAR files, reports, etc.). The most important things here are as follows:

`build/reports/tests/index.html`
: This file holds the results of all of the unit tests.

`build/reports/jacoco/test/html/index.html`
: This file holds the unit test code coverage results from Jacoco.

`build/robot-results/report.html`
: This file holds the Robot Framework test results.

`build/robot-results/debug.log`
: This file holds all of the debug information from the Robot Framework tests.

`build/libs/argparse-<version>.jar`
: This file (where *version* is specified in the jar settings of `gradle.build`) is the fully bundled code for the project. 
  This is not an executable JAR file; you can't run it like a program. Instead, this JAR file is how you would distribute
  your library to paying customers. You would give them the JAR file, and they would make sure it was on their CLASSPATH
  when they compile and run their programs. For instance,
  
  `javac -cp .:my/jars/path/argparse-<version>.jar MyClientProgram.java`  (the colon should be a semicolon on Windows)
  
  The CLASSPATH can also be set globally by modifying the environment variable.
  

## Creating Demo Programs
To compile and run any demo program, you should do so against the built JAR file in the `build/libs` directory.

`javac -cp .:../build/libs/argparse-<version>.jar MyDemoProgram1.java`
`java -cp .:../build/libs/argparse-<version>.jar MyDemoProgram`