# Create a program that produces a set of anagrams and sub-anagrams based on command-line input.

The program should take a single command-line string argument. It should then
print out all of the anagrams and sub-anagrams (anagrams made of a subset of
the letters) of that string (irrespective of case and including the original
string) in alphabetical order. If no string is provided (no command-line
argument) or if the initial string provided is not an actual word, the program
should immediately end without producing any output.

Your program should pull words from the file "allwords.txt" that has been
provided to you. It contains 370,099 English words and abbreviations, any of
which should be considered a valid word for anagram purposes.


## Use Cases

Assume that the main program is called Feature03.

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


Call: `java Feature03 notaword`

Output:

    <no output>


Call: `java Feature03`

Output:

    <no output>

