package edu.wofford.wordoff;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class AnagramsTest {
	private Anagrams anagrams;

	@Before
	public void setUp() {
		long seed = Long.parseLong("25");
		Random random = new Random(seed);
		anagrams = new Anagrams(random);
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
		assertEquals(new ArrayList<>(), anagrams.getAnagramsOfWord("epels"));
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

	@Test
	public void testNoListOfAnagramsExistsWithSpecifiedNumber() {
		int numberToTest = 20;
		assertEquals(new ArrayList<>(), anagrams.getNumberOfAnagrams(numberToTest));
	}

	@Test
	public void testAnagramsConstructorWithNoRandomSeed() {
		anagrams = new Anagrams();
		assertEquals(new ArrayList<>(), anagrams.getAnagramsOfWord("epels"));
	}

	@Test
	public void testGetSubAnagramsOfTwoLetterWord() {
		List<String> listOfSubAnagrams = new ArrayList<>();
		listOfSubAnagrams.add("a");
		listOfSubAnagrams.add("ax");
		listOfSubAnagrams.add("x");
		assertEquals(listOfSubAnagrams, anagrams.getSubAnagramsOfWord("ax"));
	}

	@Test
	public void testGetSubAnagramsOfLongerWord() {
		List<String> listOfSubAnagrams = new ArrayList<>();
		listOfSubAnagrams = new ArrayList<>();
		listOfSubAnagrams.add("a");
		listOfSubAnagrams.add("ar");
		listOfSubAnagrams.add("ars");
		listOfSubAnagrams.add("art");
		listOfSubAnagrams.add("arts");
		listOfSubAnagrams.add("as");
		listOfSubAnagrams.add("ast");
		listOfSubAnagrams.add("astr");
		listOfSubAnagrams.add("at");
		listOfSubAnagrams.add("r");
		listOfSubAnagrams.add("ra");
		listOfSubAnagrams.add("ras");
		listOfSubAnagrams.add("rat");
		listOfSubAnagrams.add("rats");
		listOfSubAnagrams.add("rs");
		listOfSubAnagrams.add("rt");
		listOfSubAnagrams.add("s");
		listOfSubAnagrams.add("sa");
		listOfSubAnagrams.add("sar");
		listOfSubAnagrams.add("sart");
		listOfSubAnagrams.add("sat");
		listOfSubAnagrams.add("sr");
		listOfSubAnagrams.add("st");
		listOfSubAnagrams.add("sta");
		listOfSubAnagrams.add("star");
		listOfSubAnagrams.add("str");
		listOfSubAnagrams.add("stra");
		listOfSubAnagrams.add("t");
		listOfSubAnagrams.add("ta");
		listOfSubAnagrams.add("tar");
		listOfSubAnagrams.add("tars");
		listOfSubAnagrams.add("tas");
		listOfSubAnagrams.add("tr");
		listOfSubAnagrams.add("tra");
		listOfSubAnagrams.add("trs");
		listOfSubAnagrams.add("ts");
		listOfSubAnagrams.add("tsar");
		assertEquals(listOfSubAnagrams, anagrams.getSubAnagramsOfWord("star"));
	}

	@Test
	public void testGettingSubAnagramsOfNonWordReturnsEmptyList() {
		String nonWordToTest = "notaword";
		assertEquals(new ArrayList<>(), anagrams.getSubAnagramsOfWord(nonWordToTest));
	}

	@Test
	public void testPassingEmptyStringToSubAnagramsReturnsEmptyList() {
		String emptyString = "";
		assertEquals(new ArrayList<>(), anagrams.getSubAnagramsOfWord(emptyString));
	}

	@Test
	public void testFindingWordWithSpecifiedCharacterLength() {
		int lengthToFind = 6;
		assertTrue(anagrams.getWordWithLength(lengthToFind).length() == lengthToFind);
	}

	@Test
	public void testNoWordReturnedWhenLengthIsNotFound() {
		int lengthToFind = 180;
		assertEquals("", anagrams.getWordWithLength(lengthToFind));
	}

	@Test
	public void testSizeOfAllWordsIsCorrect() {
		int sizeOfAllWordsTxtFile = 370099;
		assertEquals(sizeOfAllWordsTxtFile, anagrams.getNumberOfWords());
	}

}
