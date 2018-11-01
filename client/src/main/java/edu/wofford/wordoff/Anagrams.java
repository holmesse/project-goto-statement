package edu.wofford.wordoff;

import java.util.*;
import java.io.*;
import java.util.Scanner;

/**
* <h1>Anagrams</h1>
* The Anagrams class uses a {@code Map<String, List<String>>} structure
* to hash words in the words file according to the characters of each word
* sorted alphabetically. This will result in a mapping of a string of
* sorted letters to a list of words from the words file that are arrangements
* of those letters, resulting in all words in one list being anagrams
* of the others.
* <br>
* The {@link #getAnagramsOfWord(String) getAnagramsOfWord} function runs in
* constant time, and the object builds in {@code O(n)} time, with {@code n} being
* the number of words in the text file provided in the
* {@link #Anagrams(String) Anagrams(fileName)} constructor.
*/
public class Anagrams {

	private Map<String, List<String>> anagrams;
	private List<String> wordList;
	private Random randomGenerator;

	/**
	* Default Constructor.
	* The default constructor calls the {@link #Anagrams(String) Anagrams(fileName)}
	* constructor with the file "allwords.txt".
	*/
	public Anagrams() {
		this("allwords.txt");
	}

	/**
	* Constructor with Word File.
	* This constructor takes a file name and calls the
	* {@link #Anagrams(Random, String) Anagrams(randomInstance, fileName)} constructor
	* with a new instance of {@code Random()} and the specified
	* file name.
	*
	* @param fileName The name of the file containing valid words.
	*/
	public Anagrams(String fileName) {
		this(new Random(), fileName);
	}

	/**
	* Constructor with Random Instance.
	* This constructor takes an instance of {@code Random()} and calls the
	* {@link #Anagrams(Random, String) Anagrams(randomInstance, fileName)} constructor
	* with the specified instance of {@code Random()} and the file
	* "allwords.txt".
	*
	* It is mainly used for testing purposes or when a random
	* number seed has already been provided.
	*
	* @param randomInstance An instance of {@code Random()} to use for
	* random generation.
	*/
	public Anagrams(Random randomInstance) {
		this(randomInstance, "allwords.txt");
	}

	/**
	* Constructor with Random Instance and Word File.
	* This constructor initilizes {@code anagrams} to a HashMap
	* of types {@code <String, List<String>>} and {@code wordList} is
	* set as an empty {@code ArrayList<>}. The {@code randomGenerator}
	* is initialized, and the {@link #buildMapOfAnagrams(String) buildMapOfAnagrams} method is called.
	*
	*
	* @param randomInstance An instance of {@code Random()} to use for random
	* generation.
	* @param fileName The name of the file containing valid words.
	*/
	public Anagrams(Random randomInstance, String fileName) {
		anagrams = new HashMap<String, List<String>>();
		wordList = new ArrayList<>();
		randomGenerator = randomInstance;
		buildMapOfAnagrams(fileName);
	}

	/**
	* Create anagrams data structure.
	* This method reads the words from the allwords.txt file and
	* hashes them into {@code anagrams}.
	*
	* @param fileName The name of the file to draw words from.
	*/
	private void buildMapOfAnagrams(String fileName) {
		InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
		Scanner scanner = new Scanner(stream);

		while (scanner.hasNext()) {
			String word = scanner.next().toLowerCase();
			wordList.add(word);
			String sortedWord = sortWord(word);

			List<String> anagramsOfSortedLetters = anagrams.getOrDefault(sortedWord, new ArrayList<>());
			anagramsOfSortedLetters.add(word);
			anagrams.put(sortedWord, anagramsOfSortedLetters);
		}
		scanner.close();
	}

	/**
	* Find all anagrams of word.
	* This method returns a list of all anagrams of the given word.
	*
	* @param word The word to find anagrams of.
	* @return {@code List<String>} list of all possible anagrams
		of the provided word.
	*/
	public List<String> getAnagramsOfWord(String word) {
		word = word.toLowerCase();
		String sortedWord = sortWord(word);
		List<String> anagramsOfWord = anagrams.getOrDefault(sortedWord, new ArrayList<>());
		if (anagramsOfWord.contains(word)) {
			return anagramsOfWord;
		} else {
			return new ArrayList<>();
		}
	}

	/**
	* Find anagram with specified number.
	* Returns a random list of anagrams with the length specified.
	*
	* @param numberOfAnagrams The length of the list of anagrams to return.
	* @return {@code List<String>} List of words that are anagrams of each other
		with the length specified.
	*/
	public List<String> getNumberOfAnagrams(int numberOfAnagrams) {
		List<List <String>> listOfAnagramsWithRightNumber = new ArrayList<>();

		Set <Map.Entry<String, List<String>>> anagramsSet = anagrams.entrySet();
		for (Map.Entry<String, List<String>> mapEntry : anagramsSet) {
			List<String> val = mapEntry.getValue();
			if (val.size() == numberOfAnagrams) {
				listOfAnagramsWithRightNumber.add(val);
			}
		}

		if (listOfAnagramsWithRightNumber.size() > 0) {
			int randomIndex = randomGenerator.nextInt(listOfAnagramsWithRightNumber.size());
			return listOfAnagramsWithRightNumber.get(randomIndex);
		}
		else {
			return new ArrayList<>();
		}
	}

	/**
	* Find anagrams of all sub-words of the given word.
	* Gets the power set of all the letters in the word and then loops
	* through them to find all anagrams of each subset of letters.
	*
	* @param word The word to find all anagrams and sub-anagrams of.
	* @return {@code List<String>} Anagrams of all subsets of letters
		of the specified word.
	*/
	public List<String> getSubAnagramsOfWord(String word) {
		if (wordList.contains(word)) {
			List<String> subsetsOfWord = getSubsetsOfWord(word);
			List<String> subAnagramsOfWord = new ArrayList<>();

			for (int i = 0; i < subsetsOfWord.size(); i++) {
				String subsetWord = subsetsOfWord.get(i);
				List<String> anagramsOfSubset = getAnagramsOfWord(subsetWord);

				if (anagramsOfSubset.size() > 0) {
					subAnagramsOfWord.addAll(anagramsOfSubset);
				}
			}

			subAnagramsOfWord.sort(null);

			return subAnagramsOfWord;
		} else {
			return new ArrayList<>();
		}
	}

	/**
	* Find a word with the given length.
	* Creates a {@code List<List<String>>} that contains all words of
	* the given length and their anagrams. A random {@code List<String>}
	* is chosen and from the selected {@code List<String>} a random word
	* is selected and returned. If no words are found with the given length an
	* empty {@code String} is returned.
	*
	* @param lengthToFind The length of the word
	* @return A randomly chosen {@code String} from the generated
		{@code List<String>}
	*/
	public String getWordWithLength(int lengthToFind) {
		List<List<String>> listOfWordsWithRightLength = new ArrayList<>();

		Set <Map.Entry<String, List<String>>> anagramsSet = anagrams.entrySet();
		for (Map.Entry<String, List<String>> mapEntry : anagramsSet) {
			String key = mapEntry.getKey();
			if (key.length() == lengthToFind) {
				List<String> val = mapEntry.getValue();
				listOfWordsWithRightLength.add(val);
			}
		}

		if (listOfWordsWithRightLength.size() > 0) {
			int randomIndex = randomGenerator.nextInt(listOfWordsWithRightLength.size());
			List<String> wordList = listOfWordsWithRightLength.get(randomIndex);

			// Returns a random word from the list that was randomly chosen above.
			return wordList.get(randomGenerator.nextInt(wordList.size()));
		}
		else {
			return "";
		}
	}

	/**
	* Finds power set of the letters of a word.
	*
	* @param word The word to get the powerset of.
	* @return {@code List<String>} of the power set of the letters
		of the supplied word.
	*/
	private List<String> getSubsetsOfWord(String word) {
		List<String> listOfSubsets = new ArrayList<>();
		char[] letters = word.toCharArray();

		// Power set size is 2^n, where n is the length of the word.
		double powerSetSize = Math.pow(2, letters.length);
		Set<String> powerSet = new TreeSet<>();

		// Loop through the letters of the word and the size of the powerset.
		for (int counter = 0; counter < powerSetSize; counter++) {
			String subword = "";
			for (int j = 0; j < letters.length; j++) {
				// Power sets can be found by viewing each subset as a bit string of
				//  the original set, where an element is included in that subset
				//  if there is a 1 in that position in the bit string.
				// For more info & code, see https://www.geeksforgeeks.org/power-set/
				if ((counter & (1 << j)) > 0) {
					subword += letters[j];
				}
			}
			powerSet.add(subword);
		}
		listOfSubsets.addAll(powerSet);

		return listOfSubsets;
	}

	/**
	* Sort letters alphabetically.
	* This helper function sorts the letters of a word alphabetically
	* and returns the result.
	*
	* @param word The word to sort alphabetically.
	* @return {@code String} Letters of {@code word} sorted alphabetically.
	*/
	private String sortWord(String word) {
		char[] charsToSort = word.toCharArray();
		Arrays.sort(charsToSort);
		String sortedWord = new String(charsToSort);
		return sortedWord;
	}

}
