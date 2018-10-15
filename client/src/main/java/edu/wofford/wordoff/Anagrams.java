package edu.wofford.wordoff;

import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Anagrams {

	private Map<String, List<String>> anagrams;
	private List<String> wordList;
	private Random randomGenerator;

	public Anagrams() {
		anagrams = new HashMap<String, List<String>>();
		wordList = new ArrayList<>();
		randomGenerator = new Random();
		buildMapOfAnagrams();
	}

	public Anagrams(long seedForRandomGenerator) {
		anagrams = new HashMap<String, List<String>>();
		wordList = new ArrayList<>();
		randomGenerator = new Random(seedForRandomGenerator);
		buildMapOfAnagrams();
	}

	private void buildMapOfAnagrams() {
		InputStream stream = getClass().getClassLoader().getResourceAsStream("allwords.txt");
		try {
			Scanner scanner = new Scanner(stream);

			while (scanner.hasNext()) {
				String word = scanner.next().toLowerCase();
				wordList.add(word);
				String sortedWord = sortWord(word);

				List<String> anagramsOfSortedLetters = anagrams.getOrDefault(sortedWord, new ArrayList<>());
				anagramsOfSortedLetters.add(word);
				anagrams.put(sortedWord, anagramsOfSortedLetters);
			}
		}
		catch (Exception e) {
			System.out.println("Something went wrong. Maybe the file was not found.");
		}
	}

	public Map<String, List<String>> getAllAnagramsOfAllWords() {
		return anagrams;
	}

	public List<String> getAnagramsOfWord(String word) {
		word = word.toLowerCase();
		if (wordList.contains(word)) {
			String sortedWord = sortWord(word);

			List<String> anagramsOfWord = anagrams.get(sortedWord);

			return anagramsOfWord;
		} else {
			return null;
		}
	}

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
			return null;
		}
	}

	private String sortWord(String word) {
		char[] charsToSort = word.toCharArray();
		Arrays.sort(charsToSort);
		String sortedWord = new String(charsToSort);
		return sortedWord;
	}

}