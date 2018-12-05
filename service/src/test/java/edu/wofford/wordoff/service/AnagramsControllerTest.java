package edu.wofford.wordoff.service;

import org.junit.*;
import static org.junit.Assert.*;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import edu.wofford.wordoff.*;

import java.util.*;

public class AnagramsControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testGetNumberOfWordsInSourceFile() {
		String sourceToUse = "all";
		String fileName = sourceToUse + "words.txt";
		String uri = "/wordoff/" + sourceToUse + "/numwords";

		try {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);

			String content = mvcResult.getResponse().getContentAsString();

			Anagrams anagrams = new Anagrams(fileName);
			int expectedNumber = anagrams.getNumberOfWords();

			assertEquals(expectedNumber, Integer.parseInt(content));

		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
			System.err.println("Stack trace:\n");
			e.printStackTrace();
			fail("Error occurred.");
		}
	}

	@Test
	public void testIfWordIsInSource() {
		String word = "adder";
		String source = "all";
		String fileName = source + "words.txt";
		String uri = "/wordoff/" + source + "/isword/" + word;

		try {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);

			String content = mvcResult.getResponse().getContentAsString();

			Anagrams anagrams = new Anagrams(fileName);
			boolean wordIsValid = anagrams.isWord(word);

			assertEquals(wordIsValid, Boolean.parseBoolean(content));

		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
			System.err.println("Stack trace:");
			e.printStackTrace();
			fail("Error occurred.");
		}
	}

	@Test
	public void testFindingWordWithSpecifiedLength() {
		int length = 4;
		String source = "all";
		String fileName = source + "words.txt";
		String uri = "/wordoff/" + source + "/wordwithlength/" + length;

		try {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);

			String content = mvcResult.getResponse().getContentAsString();

			Anagrams anagrams = new Anagrams(fileName);

			boolean wordIsValid = anagrams.isWord(content);
			assertTrue(wordIsValid);

			assertEquals(length, content.length());

		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
			System.err.println("Stack trace:");
			e.printStackTrace();
			fail("Error occurred.");
		}
	}

	@Test
	public void testFindingWordWithSpecifiedNumberOfAnagrams() {
		int numberOfAnagrams = 4;
		String source = "all";
		String fileName = source + "words.txt";
		String uri = "/wordoff/" + source + "/wordwithanagrams/" + numberOfAnagrams;

		try {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);

			String content = mvcResult.getResponse().getContentAsString();

			Anagrams anagrams = new Anagrams(fileName);

			boolean wordIsValid = anagrams.isWord(content);
			assertTrue(wordIsValid);

			List<String> anagramsOfReturnedWord = anagrams.getAnagramsOfWord(content);

			assertEquals(numberOfAnagrams, anagramsOfReturnedWord.size());

		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
			System.err.println("Stack trace:");
			e.printStackTrace();
			fail("Error occurred.");
		}
	}

	@Test
	public void testGetAnagramsOfProvidedWord() {
		String word = "adder";
		String source = "all";
		String fileName = source + "words.txt";
		String uri = "/wordoff/" + source + "/anagrams/" + word;

		try {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);

			String content = mvcResult.getResponse().getContentAsString();

			Anagrams anagrams = new Anagrams(fileName);

			List<String> anagramsOfProvidedWord = anagrams.getAnagramsOfWord(word);

			String expectedOutput = "[";

			for (int i = 0; i < anagramsOfProvidedWord.size(); i++) {
				if (i != anagramsOfProvidedWord.size() - 1) {
					expectedOutput += "\"" + anagramsOfProvidedWord.get(i) + "\",";
				} else {
					expectedOutput += "\"" + anagramsOfProvidedWord.get(i) + "\"]";
				}
			}

			assertEquals(expectedOutput, content);

		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
			System.err.println("Stack trace:");
			e.printStackTrace();
			fail("Error occurred.");
		}
	}

	@Test
	public void testGetSubAnagramsOfProvidedWord() {
		String word = "star";
		String source = "all";
		String fileName = source + "words.txt";
		String uri = "/wordoff/" + source + "/subanagrams/" + word;

		try {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);

			String content = mvcResult.getResponse().getContentAsString();

			Anagrams anagrams = new Anagrams(fileName);

			List<String> anagramsOfProvidedWord = anagrams.getSubAnagramsOfWord(word);

			String expectedOutput = "[";

			for (int i = 0; i < anagramsOfProvidedWord.size(); i++) {
				if (anagramsOfProvidedWord.get(i).length() < word.length()) {
					expectedOutput += "\"" + anagramsOfProvidedWord.get(i) + "\",";
				}
			}

			expectedOutput = expectedOutput.substring(0, expectedOutput.length() - 1) + "]";

			assertEquals(expectedOutput, content);

		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
			System.err.println("Stack trace:");
			e.printStackTrace();
			fail("Error occurred.");
		}
	}

	@Test
	public void testGetAllAnagramsOfProvidedWord() {
		String word = "adder";
		String source = "all";
		String fileName = source + "words.txt";
		String uri = "/wordoff/" + source + "/allanagrams/" + word;

		try {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);

			String content = mvcResult.getResponse().getContentAsString();

			Anagrams anagrams = new Anagrams(fileName);

			List<String> anagramsOfProvidedWord = anagrams.getSubAnagramsOfWord(word);

			String expectedOutput = "[";

			for (int i = 0; i < anagramsOfProvidedWord.size(); i++) {
				if (i != anagramsOfProvidedWord.size() - 1) {
					expectedOutput += "\"" + anagramsOfProvidedWord.get(i) + "\",";
				} else {
					expectedOutput += "\"" + anagramsOfProvidedWord.get(i) + "\"]";
				}
			}

			assertEquals(expectedOutput, content);

		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
			System.err.println("Stack trace:");
			e.printStackTrace();
			fail("Error occurred.");
		}
	}

	@Test
	public void testSelectingLeaderboardDataFromServer() {
		String word = "hello";
		int difficulty = 2;
		int seconds_remaining = 18;

		int numberOfRowsToSelect = 1;

		String uri = "/wordoff/leaderboard/add/" + word + "/" + difficulty + "/" + seconds_remaining;

		try {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);

			String content = mvcResult.getResponse().getContentAsString();

			assertEquals("true", content);

			uri = "/wordoff/leaderboard/top/" + numberOfRowsToSelect;

			mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

			status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);

			content = mvcResult.getResponse().getContentAsString();

			String expectedOutput = "[[\"1\",\"" + word + "\",\"" + difficulty + "\",\"" + seconds_remaining + "\"]]";

			assertEquals(expectedOutput, content);

		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
			System.err.println("Stack trace:");
			e.printStackTrace();
			fail("Error occurred.");
		}
	}
/*
	 @Test
	 public void testInsertingBadRecordReturnsFalse() {
	 	String word = "hello";
	 	int difficulty = 5;
	 	int seconds_remaining = 2;

	 	String uri = "/wordoff/leaderboard/add/" + word + "/" + difficulty + "/" + seconds_remaining;

	 	try {
	 		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

	 		int status = mvcResult.getResponse().getStatus();
	 		assertEquals(200, status);

	 		String content = mvcResult.getResponse().getContentAsString();

	 		assertEquals("false", content);
	 	} catch (Exception e) {
	 		System.err.println("ERROR: " + e.toString());
	 		System.err.println("Stack trace:");
	 		e.printStackTrace();
	 		fail("Error occurred.");
	 	}
	}*/
}
