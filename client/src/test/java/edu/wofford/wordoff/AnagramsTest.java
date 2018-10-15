package edu.wofford.wordoff;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class AnagramsTest {
	private Anagrams anagrams;

	@Before
	public void setUp() {
		long seed = Long.parseLong("25");
		anagrams = new Anagrams(seed);
	}

	@Test
	public void testConstructsFullWordsMapWithBuildMapOfAnagrams() {
		List<String> listOfAnagrams = new ArrayList<>();
		listOfAnagrams.add("peels");
		listOfAnagrams.add("peles");
		listOfAnagrams.add("sleep");
		listOfAnagrams.add("speel");

		assertEquals(listOfAnagrams, anagrams.getAnagramsOfWord("sleep"));
	}

	@Test
	public void testUsingNonWordProducesNoOutput() {
		assertEquals(null, anagrams.getAnagramsOfWord("epels"));
	}

	@Test
	public void testUsingNumberFifteenReturnsCorrectListOfAnagrams() {
		int numberToTest = 15;

		List<String> listOfAnagrams = new ArrayList<>();

		listOfAnagrams.add("alerts");
		listOfAnagrams.add("alters");
		listOfAnagrams.add("artels");
		listOfAnagrams.add("estral");
		listOfAnagrams.add("laster");
		listOfAnagrams.add("lastre");
		listOfAnagrams.add("rastle");
		listOfAnagrams.add("ratels");
		listOfAnagrams.add("relast");
		listOfAnagrams.add("resalt");
		listOfAnagrams.add("salter");
		listOfAnagrams.add("slater");
		listOfAnagrams.add("staler");
		listOfAnagrams.add("stelar");
		listOfAnagrams.add("talers");

		assertEquals(listOfAnagrams, anagrams.getNumberOfAnagrams(numberToTest));
	}

	@Test
	public void testUsingNumberArgumentReturnsThatManyAnagrams() {
		int numberToTest = 6;

		assertTrue(anagrams.getNumberOfAnagrams(numberToTest).size() == numberToTest);
	}

	@Test
	public void testNumberArgumentReturnsRandomListOfThatManyAnagrams() {
		int numberToTest = 6;

		List<String> listOfAnagrams = new ArrayList<>();
		listOfAnagrams.add("skater");
		listOfAnagrams.add("staker");
		listOfAnagrams.add("strake");
		listOfAnagrams.add("streak");
		listOfAnagrams.add("takers");
		listOfAnagrams.add("tasker");

		assertEquals(listOfAnagrams, anagrams.getNumberOfAnagrams(numberToTest));
	}

	@Test
	public void testInputIsCaseInsensitive() {
		assertEquals(anagrams.getAnagramsOfWord("sleep"), anagrams.getAnagramsOfWord("SleEP"));
		assertEquals(anagrams.getAnagramsOfWord("sleep"), anagrams.getAnagramsOfWord("SLEEP"));
	}
}
