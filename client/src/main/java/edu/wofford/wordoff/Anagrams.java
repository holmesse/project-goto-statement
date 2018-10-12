package edu.wofford;

import java.util.*;

public class Anagrams {
	private Map<String, List<String>> anagrams;

	public Anagrams() {
		anagrams = new HashMap<String, List<String>>();
	}

	//public Anagrams(String originalWord) {

		//findAnagramsOfWord(originalWord);
	//}

	public Map<String, List<String>> getHashMapOfAllWords() {
		return anagrams;
	}
/*
	public Anagrams(int numberOfAnagrams) {
		anagrams = new ArrayList<>();
		for (int i = 0; i < numberOfAnagrams; i++) {
			anagrams.put("");
		}
	}

	public Map<String, List<Strings>> getHashMapOfAllWords() {
		return anagrams;
	}

	private List<String> findAnagramsOfWord(String word) {

	}*/
}