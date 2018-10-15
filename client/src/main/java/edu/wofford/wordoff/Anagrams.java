package edu.wofford.wordoff;

import java.util.*;
import java.io.*;
import java.util.Scanner;

public class Anagrams {
	private Map<String, List<String>> anagrams;

	public Anagrams() {
		anagrams = new HashMap<String, List<String>>();
		buildMapOfAnagrams();
	}

	private void buildMapOfAnagrams() {
		InputStream stream = getClass().getClassLoader().getResourceAsStream("allwords.txt");
		try {
			Scanner scanner = new Scanner(stream);

			while (scanner.hasNext()) {
				String word = scanner.next().toLowerCase();
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
		String sortedWord = sortWord(word);
		System.out.println(sortedWord);

		List<String> anagramsOfWord = anagrams.get(sortedWord);

		return anagramsOfWord;
	}

	private String sortWord(String word) {
		char[] charsToSort = word.toCharArray();
		Arrays.sort(charsToSort);
		String sortedWord = new String(charsToSort);
		return sortedWord;
	}

}