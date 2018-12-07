# project-goto-statement
project-goto-statement is a program that will help you learn anagrams for words.

## Features
This program has 10 features total and all of the features utilize one of two textfiles
`allwords.txt` or `commonwords.txt`.

### Feature01
Feature01 takes a single command-line argument of either a string or an integer.
- If the command-line argument is a string, the program will return all of the anagrams for the given string, provided the string is a valid word.
- If the command-line argument is an integer, the program randomly selects a word with a number of anagrams equal to the provided integer.

#### Example
Assume that the main program is called `Feature01`.

Call: `java Feature01 slEEp`

Output:

    peels
    peles
    sleep
    speel

### Feature02
Feature02 takes a single command-line argument of an integer. The program randomly selects a word with a number of anagrams equal to the provided integer. It displays that word along with the number of anagrams remaining, and prompts the user to input an anagram of that word. If the user input is a correct anagram of the target word, then the number of anagrams remaining decreases. When all anagrams have been entered, the program terminates.
#### Example
Assume that the main program is called `Feature02`.

Call: `java Feature02 6`

Example Interaction:

    The word is "risen".
    There are 5 anagrams remaining: resin
    There are 4 anagrams remaining: seRiN
    There are 3 anagrams remaining: rinse
    There are 2 anagrams remaining: SIREN
    There are 1 anagrams remaining: reins
    There are 0 anagrams remaining.

Note that the user response is after the colon on each line.

### Feature03
Taking a single command-line argument string, the program returns all the anagrams and
subanagrams of the given string in alphabetical order.
#### Example
Assume that the main program is called `Feature03`.

Call: `java Feature03 star`

Output:

    a
    ar
    ars
    art
    arts
    as
    ast
    astr
    at
    r
    ra
    ras
    rat
    rats
    rs
    rt
    s
    sa
    sar
    sart
    sat
    sr
    st
    sta
    star
    str
    stra
    t
    ta
    tar
    tars
    tas
    tr
    tra
    trs
    ts
    tsar

### Feature04
Taking a single command-line argument, a string or an integer, the program will
either randomly choose a word with the number of anagrams equal to the integer
or the given string as well as the number of remaining anagrams and subanagrams. It
the prompts the user to enter an anagram or subanagram of the starting word and if
the input is an anagram or subanagram, the appropriate count is reduced.
#### Example
Assume that the main program is called `Feature04`.

Call: `java Feature04 4`

Example Interaction:

    The word is "hope".
    There are 0 [len 4], 2 [len 3], 4 [len 2], and 0 [len 1] anagrams remaining: he
    There are 0 [len 4], 2 [len 3], 3 [len 2], and 0 [len 1] anagrams remaining: hop
    There are 0 [len 4], 1 [len 3], 3 [len 2], and 0 [len 1] anagrams remaining: oh
    There are 0 [len 4], 1 [len 3], 2 [len 2], and 0 [len 1] anagrams remaining: eh
    There are 0 [len 4], 1 [len 3], 1 [len 2], and 0 [len 1] anagrams remaining: HO
    There are 0 [len 4], 1 [len 3], 0 [len 2], and 0 [len 1] anagrams remaining: hoe
    There are 0 anagrams remaining.

Note that the user response is after the colon on each line.

### Feature05
This feature turns `Feature02` into a GUI where the input is typed into a field and a button
labeled `Guess` is clicked. If the input is an anagram, the word appears in alphabetical order
on the GUI. Once all anagrams are entered, the box containing the target word is highlighted
in green.
#### Example
##### Starting a game.
![image](client/src/main/java/edu/wofford/wordoff/doc-files/gui-start.png?raw=true "Starting a game")
##### Winning a game.
![image](client/src/main/java/edu/wofford/wordoff/doc-files/gui-win.png?raw=true "Winning game")

### Feature06
This feature adds a timer to the GUI constructed from `Feature05` with an initial time of
10 seconds per anagram, e.g. - six anagrams means 60 second timer. As soo as the program loads,
the timer counts down. If the user gets all the anagrams before the timer ends, the timer stops and displays the remaining time. Conversely, if the timer ends before the user has entered all the
anagrams, the 'Guess' button and textfield is disabled, preventing the user from inputing anything.
#### Example
##### Winning a game.
![image](client/src/main/java/edu/wofford/wordoff/doc-files/win.png?raw=true "Winning game")
##### Losing a game.
![image](client/src/main/java/edu/wofford/wordoff/doc-files/loss.png?raw=true "Starting a game")
### Feature07
This feature adds a database to `Feature06` containing the top 5 scores recorded. Using the
formula `score = (difficulty * 10 - time_remaining) / difficulty`, the program determines if
the user placed anywhere on the leaderboard and updates the database accordingly, but only if the current
winning word is not already in the database with the given difficulty or if
it exists but the seconds remaining are larger than what is already in the
database.
#### Example
##### Leaderboard
![image](client/src/main/java/edu/wofford/wordoff/doc-files/leaderboard.png?raw=true "leaderboard")

### Feature08
Creates a web service to get word and anagram information. The web service supports
the following queries:
`<root>/wordoff/[source]/numwords` Returns an integer representing the number of words in `source`

`<root>/wordoff/[source]/isword/{word}` Returns a boolean representing whether `word` is a word in `source`

`<root>/wordoff/[source]/wordwithlength/{length}` Returns a string representing a random word in `source` with a length of `length`

`<root>/wordoff/[source]/wordwithanagrams/{numanagrams}` Returns a string representing a random word in `source` with a number of full anagrams equal to `numanagrams`

`<root>/wordoff/[source]/anagrams/{word}`Returns a JSON array of the full anagrams of `word` in `source`

`<root>/wordoff/[source]/subanagrams/{word}` Returns a JSON array of the subanagrams of `word` in `source`

`<root>/wordoff/[source]/allanagrams/{word}` Returns a JSON array of all anagrams and subanagrams of `word` in `source`

Note: `[source]` can be either `all` or `common` for `allwords.txt` or `commonwords.txt` respectively.

#### Example
##### Web Service in Action
![image](client/src/main/java/edu/wofford/wordoff/doc-files/web_service.png?raw=true "leaderboard")

### Feature09
The program should take an integer as a single command-line argument,
representing the number of anagrams the chosen starting word should have
(the "difficulty" for this game). The difficulty is then sent to a server
that responds with the target word and its anagrams. All behavior of the main
program and GUI should be the same as before.

### Feature10
Moves the leaderboard feature to the webservice and supports the following queries

`<root>/wordoff/leaderboard/top/{number}`
: Returns a JSON array of dictionaries containing the top `number` of results in the leaderboard
Example: `<root>/wordoff/leaderboard/top/5`

`<root>/wordoff/leaderboard/add/{word}/{difficulty}/{secondsleft}`
: Adds the specified (`word`, `difficulty`, `secondsleft`) entry to the leaderboard and returns a boolean representing whether it was added
Example: `<root>/wordoff/leaderboard/add/star/3/17`
s

# COSC 410 Project
The following is the README provided by Dr. Garret at project start.
# Word-Off
This repository will allow you to build your first professional Java
project, including unit and acceptance tests, using Gradle as the
build tool.

## Building the Project
After you have cloned the repository, you should be able to navigate
to the directory containing the `gradle.build` file. There, you can
build the project by running the command

`gradlew build`

Then, you can run the unit test coverage report.

`gradlew jacocoTestReport`

Then, you can run the acceptance tests.

`gradlew runrobot`

You can even do multiple things in one statement:

`gradlew build jacocoTestReport runrobot`

When you want to get rid of all of the temporary files (like compiled class files and such), you can say

`gradlew clean`

If you want to do a full build and reporting from a clean project, you can just string it all together:

`gradlew clean build jacocoTestReport runrobot`

If you want to create the generated documentation (based on your Javadoc comments), you can say

`gradlew javadoc`

And if you want to run the application you have created, you can say

`gradlew run`



## Structure
The directory structure that is assumed by Gradle (though it can be changed if you want) is as follows:

    project root  (root directory of project or subproject)
               |
                - build.gradle  (contains the instructions for the build tasks)
               |
                - src  (root directory of the source code; acceptance, main, test)
                    |
                     - acceptance  (root directory of Robot Framework acceptance tests)
                    |
                     - main  (root directory of normal source code)
                    |     |
                    |      - java  (all packages go here)
                    |           |
                    |            - edu    
                    |           |    |
                    |           |     - wofford  (source code goes here)
                    |           |
                    |            - keywords  (sorce code for Robot Framework custom keywords goes here)
                    |
                     - test  (root directory of unit test code)
                          |
                           - java  (all packages go here)
                                |
                                 - edu    
                                     |
                                      - wofford  (unit test code goes here)

After you run `gradlew build`, a new `build` directory will automatically be created.
This will contain all of the generated files (compiled class files, JAR files, reports,
etc.). The most important things here are as follows:

`build/reports/tests/index.html`
: This file holds the results of all of the unit tests.

`build/libs/<name>.jar`
: This file (where *name* is specified in the jar settings of `gradle.build`,
  is the fully bundled code for the project. You can run this by saying
  `java -jar build/libs/<name>.jar`
  from the project root.

After you run `gradlew runrobot`, a `reports/robot` directory will be
created in the `build` directory. This will contain the reports for the
acceptance tests.

`build/reports/robot/report.html`
: This file holds the Robot Framework acceptance test results.

After you run `gradlew jacocoTestReport`, a `reports/jacoco/test/html` directory
will be created in the `build` directory. This will contain the reports for the
Jacoco code coverage.

`build/reports/jacoco/test/html/index.html`
: This file holds the unit test code coverage results from JaCoCo.

After you run `gradlew javadoc`, a `docs` directory will be created in the project
root. This will contain all of the generated Javadoc documentation for your
source files.  

`docs/javadoc/index.html`
: This file is the index to the generated documentation.
