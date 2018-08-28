# Create a web service that allows a client to get word and anagram information.

The web service should allow the following queries. In all cases, `source` can
either be `all` or `common`, depending on whether the `allwords.txt` 
or `commonwords.txt` is used.


`<root>/wordoff/[source]/numwords`
: Returns an integer representing the number of words in `source`
Example: `<root>/wordoff/all/numwords`

`<root>/wordoff/[source]/isword/{word}`
: Returns a boolean representing whether `word` is a word in `source`
Example: `<root>/wordoff/common/isword/rats`

`<root>/wordoff/[source]/wordwithlength/{length}`
: Returns a string representing a random word in `source` with a length of `length`
Example: `<root>/wordoff/all/wordwithlength/5`

`<root>/wordoff/[source]/wordwithanagrams/{numanagrams}`
: Returns a string representing a random word in `source` with a number of full anagrams equal to `numanagrams`
Example: `<root>/wordoff/common/wordwithanagrams/3`

`<root>/wordoff/[source]/anagrams/{word}`
: Returns a JSON array of the full anagrams of `word` in `source`
Example: `<root>/wordoff/all/anagrams/pets`

`<root>/wordoff/[source]/subanagrams/{word}`
: Returns a JSON array of the subanagrams of `word` in `source`
Example: `<root>/wordoff/common/subanagrams/parts`

`<root>/wordoff/[source]/allanagrams/{word}`
: Returns a JSON array of all anagrams and subanagrams of `word` in `source`
Example: `<root>/wordoff/all/allanagrams/star`
