package edu.wofford.wordoff;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class AnagramsTest {
	private Anagrams anagrams;

	@Before
	public void setUp() {
		anagrams = new Anagrams();
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

	// @Test
	// public void testDefaultConstructorBuildsHashMap() {
	// 	Map<String, List<String>> testMap = new HashMap();

	// 	assertEquals(testMap, anagrams.getHashMapOfAllWords());
	// }

	// @Test
	// public void testConstructAnagramsArrayFromInputWord() {
	// 	anagrams = new Anagrams("original");
	// 	List<String> testList = new ArrayList<>();
	// 	testList.add("original");

	// 	assertEquals(testList, anagrams.getAnagrams());
	// }

	// @Test
	// public void testConstructAnagarmsWithIntArgument() {
	// 	anagrams = new Anagrams(3);
	// 	assertTrue(anagrams.getAnagrams().size() == 3);

	// 	anagrams = new Anagrams(5);
	// 	assertTrue(anagrams.getAnagrams().size() == 5);
	// }

	// @Test
	// public void testFindAnagramsOfAWord() {
	// 	String wordToTest = "sleep";
	// 	List<String> anagramsToTestAgainst = new ArrayList<>();
	// 	anagramsToTestAgainst.add("peels");
	// 	anagramsToTestAgainst.add("peles");
	// 	anagramsToTestAgainst.add("sleep");
	// 	anagramsToTestAgainst.add("speel");
	// 	anagrams = new Anagrams("sleep");

	// 	assertEquals(anagramsToTestAgainst, anagrams.getAnagrams());

	// }
}