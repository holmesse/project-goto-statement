package edu.wofford.wordoff;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class AnagramsLeaderboardTest {
	private AnagramsLeaderboard leaderboard;

	@Before
	public void setUp() {
		leaderboard = new AnagramsLeaderboard();
		try {
			leaderboard.createLeaderboardTable();
			leaderboard.clearLeaderboardData();
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
			System.err.println("Stack trace:\n");
			e.printStackTrace();
			fail("Error occurred during setUp.");
		}
	}

	@Test
	public void testEmptyDatabaseCreatedSuccessfully() {
		try {
			Boolean databaseIsEmpty = leaderboard.leaderboardIsEmpty();
			assertTrue(databaseIsEmpty);
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
			System.err.println("Stack trace:\n");
			e.printStackTrace();
			fail("Error occurred while checking if database is empty.");
		}
	}

	@Test
	public void testSelectingDataFromEmptyDatabaseReturnsEmptyArray() {
		try {
			String[][] results = leaderboard.selectLeaderboardData();
			assertArrayEquals(new String[0][0], results);
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
			System.err.println("Stack trace:\n");
			e.printStackTrace();
			fail("Error occurred.");
		}
	}

	@Test
	public void testInsertingDataIntoDatabase() {
		try {
			leaderboard.insertNewResult("hello", 3, 18);
			leaderboard.insertNewResult("world", 4, 20);
			leaderboard.insertNewResult("apple", 2, 8);
			leaderboard.insertNewResult("cat", 2, 6);

			String[][] results = leaderboard.selectLeaderboardData();

			String[][] expectedResults = {{"1", "hello", "3", "18"}, {"2", "world", "4", "20"}, {"3", "apple", "2", "8"}, {"4", "cat", "2", "6"}};

			assertArrayEquals(expectedResults, results);
			assertTrue(!leaderboard.leaderboardIsEmpty());
		} catch (Exception e) {
			System.err.println("ERROR: " + e.toString());
			System.err.println("Stack trace:\n");
			e.printStackTrace();
			fail("Error occurred.");
		}
	}
}
