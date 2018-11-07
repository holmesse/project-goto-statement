package edu.wofford.wordoff.service;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnagramsController {

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
		String fileName;
		if (source.equals("all")) {
			fileName = "allwords.txt";
		} else if (source.equals("common")) {
			fileName = "commonwords.txt";
		} else {
			fileName = "";
			System.err.println("Unrecognized source.");
			System.exit(0);
		}
		Anagrams anagrams = new Anagrams(fileName);
		return anagrams.getNumberOfWords();
	}
}