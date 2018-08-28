# Create a program that allows the user to enter all of the anagrams and subanagrams of a given word.

The program should take a single command-line argument that determines the
behavior.

If the argument is an integer, it represents the *character length* the chosen
starting word should have (a modified version of the previous "difficulty").
The program then randomly chooses a word with that size, finds its true
anagrams, and presents one randomly to the user as the starting word.

If the argument is any other string, it represents the starting word.

The program determines the number of anagrams *and subanagrams* of the starting
word remaining to be found (where subanagrams are grouped by character length).
These counts do not include the starting word, since the user already knows it.
The program then prompts the user for each (sub)anagram (case insensitive) and 
reduces the remaining count each time until the count for all groups is 0, at
which point the program ends. If a given user response is not a (sub)anagram of
the chosen word or has already been chosen, the program simply reprompts without
changing the counts.

The following cases for the command-line argument result in the program ending
immediately without producing output:
    
* the integer argument is not provided or is less than 3
* there is no word in the word bank with a length of the supplied size
* the supplied starting word is not a word in the word bank

Your program should pull words from the file "commonwords.txt" that has been
provided to you. It contains 58,110 English words, any of which should be 
considered a valid word for (sub)anagram purposes.


## Use Cases

Assume that the main program is called Feature04.

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


Call: `java Feature04 cable`

Example Interaction:

    The word is "cable".
    There are 0 [len 5], 4 [len 4], 8 [len 3], 1 [len 2], and 0 [len 1] anagrams remaining: wrong
    There are 0 [len 5], 4 [len 4], 8 [len 3], 1 [len 2], and 0 [len 1] anagrams remaining: aBLe
    There are 0 [len 5], 3 [len 4], 8 [len 3], 1 [len 2], and 0 [len 1] anagrams remaining: ale
    There are 0 [len 5], 3 [len 4], 7 [len 3], 1 [len 2], and 0 [len 1] anagrams remaining: bale
    There are 0 [len 5], 2 [len 4], 7 [len 3], 1 [len 2], and 0 [len 1] anagrams remaining: be
    There are 0 [len 5], 2 [len 4], 7 [len 3], 0 [len 2], and 0 [len 1] anagrams remaining: bel
    There are 0 [len 5], 2 [len 4], 6 [len 3], 0 [len 2], and 0 [len 1] anagrams remaining: cab
    There are 0 [len 5], 2 [len 4], 5 [len 3], 0 [len 2], and 0 [len 1] anagrams remaining: lab
    There are 0 [len 5], 2 [len 4], 4 [len 3], 0 [len 2], and 0 [len 1] anagrams remaining: abe
    There are 0 [len 5], 2 [len 4], 3 [len 3], 0 [len 2], and 0 [len 1] anagrams remaining: abel
    There are 0 [len 5], 1 [len 4], 3 [len 3], 0 [len 2], and 0 [len 1] anagrams remaining: alb
    There are 0 [len 5], 1 [len 4], 2 [len 3], 0 [len 2], and 0 [len 1] anagrams remaining: lab
    There are 0 [len 5], 1 [len 4], 2 [len 3], 0 [len 2], and 0 [len 1] anagrams remaining: lace
    There are 0 [len 5], 0 [len 4], 2 [len 3], 0 [len 2], and 0 [len 1] anagrams remaining: ace
    There are 0 [len 5], 0 [len 4], 1 [len 3], 0 [len 2], and 0 [len 1] anagrams remaining: lea
    There are 0 anagrams remaining.


