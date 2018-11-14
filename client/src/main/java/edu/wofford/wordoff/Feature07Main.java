package edu.wofford.wordoff;

public class Feature07Main {

	public static void main(String[] args) {
		AnagramsLeaderboard leaderboard = new AnagramsLeaderboard();

		leaderboard.createLeaderboardTable();

		leaderboard.insertNewResult("apple", 2, 8);
		leaderboard.insertNewResult("hello", 3, 18);
		leaderboard.insertNewResult("cat", 2, 6);
		leaderboard.insertNewResult("silly", 6, 12);
		leaderboard.insertNewResult("world", 4, 20);

		String[][] results = leaderboard.selectLeaderboardData();
		for (String[] row : results) {
			for (String item : row) {
				System.out.print(item + ", ");
			}
			System.out.println();
		}
	}
	
}
