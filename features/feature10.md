# Modify the current web service and GUI program to make the leaderboard functionality a part of the service.

The Leaderboard component of the existing program should be moved to the web
service, allowing the following requests.

`<root>/wordoff/leaderboard/top/{number}`
: Returns a JSON array of dictionaries containing the top `number` of results in the leaderboard
Example: `<root>/wordoff/leaderboard/top/5`

`<root>/wordoff/leaderboard/add/{word}/{difficulty}/{secondsleft}`
: Adds the specified (`word`, `difficulty`, `secondsleft`) entry to the leaderboard and returns a boolean representing whether it was added
Example: `<root>/wordoff/leaderboard/add/star/3/17`


The program should take an integer as a single command-line argument, 
representing the number of anagrams the chosen starting word should have
(the "difficulty" for this game). The difficulty is then sent to a server
that responds with the target word and its anagrams. Any interaction with the
leaderboard should also happen through the web service. All other behavior of
the main program and GUI should be the same as before.

