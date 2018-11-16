package keywords;


import java.io.*;
import java.util.*;

import edu.wofford.wordoff.*;


public class Feature07 {
	private AnagramsLeaderboard leaderboard;

    public void clearLeaderboardDatabase() {
    	leaderboard = new AnagramsLeaderboard();
    	leaderboard.clearLeaderboardData();
    }

    public void addToLeaderboardDatabase(String word, int difficulty, int seconds_left) {
    	leaderboard = new AnagramsLeaderboard();
    	leaderboard.insertNewResult(word, difficulty, seconds_left);
    }

    public List<String> getGuessesForWord(String word) {
        Anagrams anagram_list;
        anagram_list = new Anagrams("commonwords.txt");
        List<String> anagrams = anagram_list.getAnagramsOfWord(word);
        anagrams.remove(word);
        return anagrams;
    }
}
