# Create a GUI program that allows the user to enter all of the anagrams of a given word before a timer ends.

The program should take an integer as a single command-line argument, 
representing the number of anagrams the chosen starting word should have
(the "difficulty" for this game).

The program determines the number of anagrams of the starting word remaining
to be found. Then, the program opens a GUI window for user input. The window
should look similar to the following, with the name (using `setName`) of each
component exactly as specified. This example assumes that the target word has
5 additional anagrams to be guessed.


    +--------------------------------------------------+
    |   WordOff                            | o | - | X |
    +--------------------------------------------------+
    |                                                  |
    |    -------------------      -----------------    |
    |   |  Time Remaining:  |    |  JLabel: timer  |   |
    |    -------------------      -----------------    |
    |                                                  |
    |                                                  |
    |    ------------------------------------------    |
    |   |              JLabel: target              |   |
    |    ------------------------------------------    |
    |                                                  |
    |    ------------------------------------------    |
    |   |             JLabel: anagram0             |   |
    |    ------------------------------------------    |
    |                                                  |
    |    ------------------------------------------    |
    |   |             JLabel: anagram1             |   |
    |    ------------------------------------------    |
    |                                                  |
    |    ------------------------------------------    |
    |   |             JLabel: anagram2             |   |
    |    ------------------------------------------    |
    |                                                  |
    |    ------------------------------------------    |
    |   |             JLabel: anagram3             |   |
    |    ------------------------------------------    |
    |                                                  |
    |    ------------------------------------------    |
    |   |             JLabel: anagram4             |   |
    |    ------------------------------------------    |
    |                                                  |
    |                                                  |
    |                                                  |
    |    ---------------------         ------------    |
    |   |  JTextField: guess  |       |  JButton:  |   |
    |    ---------------------        |   button   |   |
    |                                  ------------    |
    |                                                  |
    |                                                  |
    +--------------------------------------------------+


The initial time on the timer should be equal to 10 seconds for each anagram to
be found. (So 6 anagrams would have a timer of 1 minute.) As soon as the
program loads, the timer should begin to count down to 0. If it reaches 0 before
the user enters all of the anagrams, the `JTextField` and `JButton` should be
disabled (thus preventing any further user input). Similarly, if the user enters
all of the anagrams before the timer reaches 0, the timer should stop and
continue to display the final time remaining.

The user should be able to enter a guess into the `JTextField` and then click
the `JButton`. The program should then determine whether the guess matches any
outstanding anagram and, if so, fill in the appropriate label. The labels should
match the anagrams in alphabetical order from top to bottom. This means, for
example, if the target word were "stop", then the labels from top to bottom
should be "opts", "post", "pots", "spot", and "tops". After each button press,
the `JTextField` should be cleared in order to enter a new guess.

Initially, the border of the target word label should be red. When all of the
anagrams have been entered, the border should be set to green and both the 
`JTextField` and `JButton` should be disabled.

The following cases for the command-line argument result in the program ending
immediately without showing a window:

* the argument supplied is not an integer
* the integer argument is not provided or is less than 2
* there is no word in the word bank with a number of anagrams of the supplied size

Your program should pull words from the file "commonwords.txt" that has been
provided to you. It contains 58,110 English words, any of which should be 
considered a valid word for anagram purposes.


