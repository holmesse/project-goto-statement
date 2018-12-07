package keywords;


import java.io.*;
import java.util.*;

import edu.wofford.wordoff.*;


public class Feature07 {
	private AnagramsLeaderboard leaderboard;

    public void clearLeaderboardDatabase() {
    	leaderboard = new AnagramsLeaderboard();
        try {
            leaderboard.clearLeaderboardData();
        } catch (Exception e) {
            System.err.println("Leaderboard table was not cleared due to an error. See stack trace for details.");
            System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addToLeaderboardDatabase(String word, int difficulty, int seconds_left) {
    	leaderboard = new AnagramsLeaderboard();
        try {
            leaderboard.insertNewResult(word, difficulty, seconds_left);
        } catch (Exception e) {
            System.err.println("Leaderboard table was not created due to an error. See stack trace for details.");
            System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<String> getGuessesForWord(String word) {
        Anagrams anagram_list;
        anagram_list = new Anagrams("commonwords.txt");
        List<String> anagrams = anagram_list.getAnagramsOfWord(word);
        anagrams.remove(word);
        return anagrams;
    }
}
