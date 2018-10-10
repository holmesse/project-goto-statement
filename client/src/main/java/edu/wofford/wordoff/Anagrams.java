package edu.wofford;

import java.util.*;

public class Anagrams {
	private List<String> anagrams;

	public Anagrams() {
		anagrams = new ArrayList<>();
	}

	public Anagrams(String originalWord) {
		anagrams = new ArrayList<>();
		//anagrams.add(originalWord);
		findAnagramsOfWord(originalWord);
	}

	public Anagrams(int numberOfAnagrams) {
		anagrams = new ArrayList<>();
		for (int i = 0; i < numberOfAnagrams; i++) {
			anagrams.add("");
		}
	}

	public List<String> getAnagrams() {
		return anagrams;
	}

	private List<String> findAnagramsOfWord(String word) {
		
	}
}