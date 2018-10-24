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
*/
public class Anagrams {

	private Map<String, List<String>> anagrams;
	private List<String> wordList;
	private Random randomGenerator;

	/**
	* Default Constructor.
	* The default constructor calls the {@link Anagrams(Random randomInstance)}
	* constructor with a new instance of {@code Random()}.
	*/
	public Anagrams() {
		this(new Random());
	}

	/**
	* Constructor with Random Instance.
	* This constructor initilizes {@code anagrams} to a HashMap
	* of types {@code <String, List<String>>} and {@code wordList} is
	* set as an empty {@code ArrayList<>}. The {@code randomGenerator}
	* is initialized, and the {@link buildMapOfAnagrams} method is called.
	*
	* It is mainly used for testing purposes or when a random
	* number seed has already been provided.
	* 
	* @param randomInstance An instance of {@code Random()} to use for random
	* generation.
	*/
	public Anagrams(Random randomInstance) {
		anagrams = new HashMap<String, List<String>>();
		wordList = new ArrayList<>();
		randomGenerator = randomInstance;
		buildMapOfAnagrams();
	}

	/**
	* Create anagrams data structure.
	* This method reads the words from the allwords.txt file and
	* hashes them into {@code anagrams}.
	*/
	private void buildMapOfAnagrams() {
		InputStream stream = getClass().getClassLoader().getResourceAsStream("allwords.txt");
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
		if (wordList.contains(word)) {
			String sortedWord = sortWord(word);

			List<String> anagramsOfWord = anagrams.get(sortedWord);

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

	public List<String> getSubAnagramsOfWord(String word) {
		List<String> subsetsOfWord = getSubsetsOfWord(word);
		List<String> subAnagramsOfWord = new ArrayList<>();

		for (int i = 0; i < subsetsOfWord.size(); i++) {
			String subsetWord = subsetsOfWord.get(i);
			List<String> anagramsOfSubset = getAnagramsOfWord(subsetWord);

			if (anagramsOfSubset.size() > 0) {
				subAnagramsOfWord.addAll(getAnagramsOfWord(subsetsOfWord.get(i)));
			}
		}

		return subAnagramsOfWord;
	}

	private List<String> getSubsetsOfWord(String word) {
		List<String> listOfSubsets = new ArrayList<>();
		char[] letters = word.toCharArray();

		double powerSetSize = Math.pow(2, letters.length);
		SortedSet<String> powerSet = new TreeSet<>();

		for (int counter = 0; counter < powerSetSize; counter++) {
			String sortedString = "";
			for (int j = 0; j < letters.length; j++) {
				if ((counter & (1 << j)) > 0) {
					sortedString += letters[j];
					sortedString = sortWord(sortedString);
				}
			}
			powerSet.add(sortedString);
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