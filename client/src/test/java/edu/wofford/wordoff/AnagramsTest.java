package edu.wofford;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class AnagramsTest {
	private Anagrams anagrams;

	@Before
	public void setup() {
		anagrams = new Anagrams();
	}

	@Test
	public void testDefaultAnagramsIsEmptyList() {
		List<String> testEmptyList = new ArrayList<>();

		assertEquals(testEmptyList, anagrams.getAnagrams());
	}

	@Test
	public void testConstructAnagramsArrayFromInputWord() {
		anagrams = new Anagrams("original");
		List<String> testList = new ArrayList<>();
		testList.add("original");

		assertEquals(testList, anagrams.getAnagrams());
	}

	@Test
	public void testConstructAnagarmsWithIntArgument() {
		anagrams = new Anagrams(3);
		assertTrue(anagrams.getAnagrams().size() == 3);

		anagrams = new Anagrams(5);
		assertTrue(anagrams.getAnagrams().size() == 5);
	}

	@Test
	public void testFindAnagramsOfAWord() {
		String wordToTest = "sleep";
		List<String> anagramsToTestAgainst = new ArrayList<>();
		anagramsToTestAgainst.add("peels");
		anagramsToTestAgainst.add("peles");
		anagramsToTestAgainst.add("sleep");
		anagramsToTestAgainst.add("speel");
		anagrams = new Anagrams("sleep");

		assertEquals(anagramsToTestAgainst, anagrams.getAnagrams());

	}
}