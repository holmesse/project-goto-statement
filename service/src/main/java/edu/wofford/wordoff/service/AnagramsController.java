package edu.wofford.wordoff.service;

import edu.wofford.wordoff.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import javax.json.*;

@RestController
public class AnagramsController {

	private Anagrams anagrams;

	private void initAnagramsWithSource(String source) {
		anagrams = new Anagrams(source + "words.txt");
	}

	/**
	* Finds the number of words listed in the specified source.
	*
	* @param source "all" or "common". The source that contains
	* the words that are considered valid for anagram purposes.
	* @return {@code int} Number of total words in the provided
	* word source.
	*/
	@RequestMapping("/wordoff/{source}/numwords")
	public int getNumberOfWordsInSource(@PathVariable String source) {
		initAnagramsWithSource(source);
		return anagrams.getNumberOfWords();
	}

	/**
	* Checks if the provided word is valid in the given source.
	*
	* @param source "all" or "common". The source that contains
	* the words that are considered valid for anagram purposes.
	* @param word The word to check for validity.
	* @return {@code boolean} True if word is valid, false otherwise.
	*/
	@RequestMapping("/wordoff/{source}/isword/{word}")
	public boolean wordIsInSource(@PathVariable String source, @PathVariable String word) {
		initAnagramsWithSource(source);
		return anagrams.isWord(word);
	}

	/**
	* Finds a random word with the specified length.
	*
	* @param source "all" or "common". The source that contains
	* the words that are considered valid for anagram purposes.
	* @param length The length of a word to find.
	* @return {@code String} A word with the provided length.
	*/
	@RequestMapping("/wordoff/{source}/wordwithlength/{length}")
	public String findWordWithLength(@PathVariable String source, @PathVariable int length) {
		initAnagramsWithSource(source);
		return anagrams.getWordWithLength(length);
	}

	/**
	* Finds a random word that has the provided number of anagrams.
	*
	* @param source "all" or "common". The source that contains
	* the words that are considered valid for anagram purposes.
	* @param numanagrams The number of anagrams the returned word
	* will have.
	* @return {@code String} A word that has the provided number
	* of anagrams.
	*/
	@RequestMapping("/wordoff/{source}/wordwithanagrams/{numanagrams}")
	public String findWordWithAnagrams(@PathVariable String source, @PathVariable int numanagrams) {
		initAnagramsWithSource(source);
		return anagrams.getWordWithNumberOfAnagrams(numanagrams);
	}

	/**
	* Finds all anagrams of the given word.
	*
	* @param source "all" or "common". The source that contains
	* the words that are considered valid for anagram purposes.
	* @param word The word to find anagrams of.
	* @return {@code List<String>} A JSON array of anagrams of word.
	*/
	@RequestMapping("/wordoff/{source}/anagrams/{word}")
	public List<String> findAnagramsOfWord(@PathVariable String source, @PathVariable String word) {
		initAnagramsWithSource(source);
		return anagrams.getAnagramsOfWord(word);
	}

	/**
	* Finds all sub-anagrams of the given word.
	*
	* @param source "all" or "common". The source that contains
	* the words that are considered valid for anagram purposes.
	* @param word The word to find sub-anagrams of.
	* @return {@code List<String>} A JSON array of sub-anagrams of word.
	*/
	@RequestMapping("/wordoff/{source}/subanagrams/{word}")
	public List<String> findSubAnagramsOfWord(@PathVariable String source, @PathVariable String word) {
		// Acceptance tests say that this should not include the full anagrams of the word.
		// If that changes, simply replace this code with the code in findAllAnagramsOfWord.
		initAnagramsWithSource(source);
		List<String> subAnagrams = anagrams.getSubAnagramsOfWord(word);
		for (int i = 0; i < subAnagrams.size(); i++) {
			if (subAnagrams.get(i).length() == word.length()) {
				subAnagrams.remove(i);
			}
		}
		return subAnagrams;
	}

	/**
	* Finds all anagrams of the given word.
	*
	* @param source "all" or "common". The source that contains
	* the words that are considered valid for anagram purposes.
	* @param word The word to find full and sub anagrams of.
	* @return {@code List<String>} A JSON array of all anagrams of word.
	*/
	@RequestMapping("/wordoff/{source}/allanagrams/{word}")
	public List<String> findAllAnagramsOfWord(@PathVariable String source, @PathVariable String word) {
		initAnagramsWithSource(source);
		return anagrams.getSubAnagramsOfWord(word);
	}


	@RequestMapping("/wordoff/leaderboard/top/{number}")
	public List<String> findTopLeaderboardScores(@PathVariable int number) {
		initAnagramsWithSource("all");
		String[][] leaderboard = AnagramsLeaderboard.selectLeaderboardData(number);
		JsonArray scores = Json.createArrayBuilder();
		for(int i = 0; i < number; i++){
			Json obj = Json.createObjectBuilder();
			obj.add("rank", leaderboard[i][0]);
			obj.add("word", leaderboard[i][1]);
			obj.add("difficulty", leaderboard[i][2]);
			obj.add("seconds remaining", leaderboard[i][3]);
			scores.add(obj);
		}
		score.build();
	}
}
