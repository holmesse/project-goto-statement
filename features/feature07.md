# Use a database to add a leaderboard to the existing graphical interface.

The program should take an integer as a single command-line argument, 
representing the number of anagrams the chosen starting word should have
(the "difficulty" for this game). All behavior of the main program and GUI
should be the same as before.

However, when the game ends (either due to the user's guessing all anagrams
[win] or running out of time [loss]), the program should pop up a *modeless*
dialog box containing the top 5 best scores that have been achieved so far,
where scores should be sorted in ascending order according to the following
metric:

    score = (difficulty * 10 - time_remaining) / difficulty

This score essentially captures the amount of time on average needed to solve
each anagram, and lower values of the score are considered "better".

The results of each game won should be stored in an SQLite database that can
then be queried to produce the leaderboard. The SQLite database file should be
stored in the same directory where the program JAR file exists. If the database
file does not exist at the beginning of program execution, an empty database
should be created that can then be filled on this and subsequent runs.

The leaderboard dialog box should look similar to the following, with the name
(using `setName`) of the `JTable` set to "leaderboard" and the title of the
dialog box set to "Leaderboard". This example assumes that the database already
has enough data to fill all 5 leaderboard slots.


    +---------------------------------------------------+
    |   Leaderboard                         | o | - | X |
    +---------------------------------------------------+
    |                                                   |
    |  -----------------------------------------------  |
    | | Rank |  Word  |  Difficulty  |  Seconds Left  | |
    |  -----------------------------------------------  |
    | |    1 | hello  |            3 |             18 | |
    |  -----------------------------------------------  |
    | |    2 | world  |            4 |             20 | |
    |  -----------------------------------------------  |
    | |    3 | apple  |            2 |              8 | |
    |  -----------------------------------------------  |
    | |    4 | cat    |            2 |              6 | |
    |  -----------------------------------------------  |
    | |    5 | silly  |            6 |             12 | |
    |  -----------------------------------------------  |
    |                                                   |
    +---------------------------------------------------+


The database should be updated on any "win" condition, but only if the current
winning word is not already in the database with the given difficulty or if
it exists but the seconds remaining are larger than what is already in the
database. (Treat the primary key as a composite of the word and the difficulty.)

At the end of the game (win or loss but after database update), if there are no
entries in the database, then no leaderboard dialog box should be displayed.


