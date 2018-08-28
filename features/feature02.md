# Create a program that allows the user to enter all of the anagrams of a given word.

The program should take an integer as a single command-line argument, 
representing the number of anagrams the chosen starting word should have
(a "difficulty" of sorts). The program then randomly chooses a word with that
number of anagrams and presents one randomly to the user, along with the number
of anagrams remaining to be found. It then prompts the user for each anagram
(case insensitive) and reduces the remaining count each time until the count
is 0, at which point the program ends. If a given user response is not an
anagram of the chosen word or has already been chosen, the program simply
reprompts without changing the count.

The following cases for the command-line argument result in the program ending
immediately without producing output:
    
* the argument supplied is not an integer
* the integer argument is not provided or is less than 2
* there is no word in the word bank with a number of anagrams of the supplied size

Your program should pull words from the file "allwords.txt" that has been
provided to you. It contains 370,099 English words and abbreviations, any of
which should be considered a valid word for anagram purposes.


## Use Cases

Assume that the main program is called Feature02.

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


Call: `java Feature02 6`

Example Interaction:

    The word is "risen".
    There are 5 anagrams remaining: hello
    There are 5 anagrams remaining: RiNSe
    There are 4 anagrams remaining: risen
    There are 4 anagrams remaining: reins
    There are 3 anagrams remaining: resin
    There are 2 anagrams remaining: siren
    There are 1 anagrams remaining: SEriN
    There are 0 anagrams remaining.

