# Create a program that produces a list of anagrams based on command-line input.

The program should take a single command-line argument, either a string or an
integer. If the argument is string, the program should print all of the anagrams
of that string (irrespective of case and including the original string) in 
alphabetical order. If the argument is an integer, the program should randomly
choose one word from among all words that have that number of anagrams and then
print out all anagrams of that word in alphabetical order.

Your program should pull words from the file "allwords.txt" that has been
provided to you. It contains 370,099 English words and abbreviations, any of
which should be considered a valid word for anagram purposes.


## Use Cases

Assume that the main program is called Feature01.

Call: `java Feature01 slEEp`

Output:

    peels
    peles
    sleep
    speel


Call: `java Feature01 epels`
    
Output:

    <no output>

Call: `java Feature01 15`

Output:

    alerts
    alters
    artels
    estral
    laster
    lastre
    rastle
    ratels
    relast
    resalt
    salter
    slater
    staler
    stelar
    talers

Note that there is only one set of words with 15 anagrams.

Call: `java Feature01 0`

Output:

    <no output>

