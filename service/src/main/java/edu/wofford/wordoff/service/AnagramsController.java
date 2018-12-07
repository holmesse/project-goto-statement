package edu.wofford.wordoff.service;

import edu.wofford.wordoff.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import javax.json.*;

/**
* AnagramsController handles the web service implementation of the anagrams 
* guessing game, namely using the web service as a word source and implementing leaderboard functionality.
*/
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

	/**
	* Return given number of results from leaderboard database.
	* @param number The number of results to return.
	* @return A JSON array of the top given number of results from the leaderbaord database.
	*/
	@RequestMapping("/wordoff/leaderboard/top/{number}")
	public String[][] findTopLeaderboardScores(@PathVariable int number) {
		initAnagramsWithSource("all");
		String[][] leaderboard = new String[1][1];
		try{
			AnagramsLeaderboard.createLeaderboardTable();
			leaderboard = AnagramsLeaderboard.selectLeaderboardData(number);
		}
		catch(Exception e){
			System.err.println("An error occured while selecting data");
			System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return leaderboard;
	}

	/**
	* Inserts a new result in the leaderboard database.
	*
	* @param word The word to be inserted in the database.
	* @param difficulty The difficulty of the word.
	* @param secondsleft The number of seconds remaining after all the anagrams of word were entered.
	* @return True if inserted correctly else returns False
	*/
	@RequestMapping("/wordoff/leaderboard/add/{word}/{difficulty}/{secondsleft}")
	public boolean insertNewLeaderboardResult(@PathVariable String word, @PathVariable int difficulty, @PathVariable int secondsleft) {
		initAnagramsWithSource("all");
		boolean recordInserted = false;
		try {
			AnagramsLeaderboard.createLeaderboardTable();
			AnagramsLeaderboard.insertNewResult(word, difficulty, secondsleft);
			recordInserted = true;
		} catch (Exception e) {
			recordInserted = false;
			System.err.println("Leaderboard table was not created due to an error. See stack trace for details.");
			System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
		return recordInserted;
	}
}
