package edu.wofford.wordoff;

import java.sql.*;

/**
* This class sets up the results.db SQLite database that is used for keeping track
* of game results and high scores.
*/
public class AnagramsLeaderboard {

   public static void main( String args[] ) {
   }

   /**
   * Create Leaderboard.
   * This method creates a new leaderboard table if one
   * does not already exist. If the leaderboard table
   * exists, nothing happens.
   */
   public void createLeaderboardTable() {
      Connection conn = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:client/build/libs/results.db");

         stmt = conn.createStatement();
         String createLeaderboardSQL = "CREATE TABLE IF NOT EXISTS leaderboard (" +
                        " word TEXT NOT NULL, " + 
                        " difficulty INT NOT NULL, " + 
                        " seconds_left INT NOT NULL, " + 
                        " submitDateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                        " PRIMARY KEY (word, difficulty));"; 
         stmt.executeUpdate(createLeaderboardSQL);
         stmt.close();
         conn.close();
      } catch (Exception e) {
         System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
   }

   /**
   * Insert new result into the leaderboard.
   * Inserts a new record into the leaderboard table with the
   * specified values of word, difficulty, and seconds_left.
   *
   * @param word The word played.
   * @param difficulty The difficulty of the game.
   * @param seconds_left The time remaining after winning.
   */
   public void insertNewResult(String word, int difficulty, int seconds_left) {
      Connection conn = null;
      Statement stmt = null;

      try {
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:client/build/libs/results.db");

         stmt = conn.createStatement();
         String insertSQL = String.format("INSERT OR IGNORE INTO leaderboard (word, difficulty, seconds_left) VALUES ('%s', %d, %d);", word, difficulty, seconds_left);

         stmt.executeUpdate(insertSQL);
         stmt.close();
         conn.close();  
      } catch (SQLException e) {
         System.err.println("ERROR: Error code (" + e.getErrorCode() + "): " + e.getMessage());
         System.exit(0);
      } catch (Exception e) {
         System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
   }

   /**
   * Get results from the leaderboard.
   * Gets the top 5 results from the leaderboard and returns
   * them as a 2-dimensional String array. Columns are, in order:
   * rank, word, difficulty, seconds_left
   *
   * @return {@code String[][]} Two-dimensional string array
   * representing the results of the query.
   */
   public String[][] selectLeaderboardData() {
      Connection conn = null;
      Statement stmt = null;

      String[][] results = new String[5][4];

      try {
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:client/build/libs/results.db");

         stmt = conn.createStatement();
         String insertSQL = "SELECT [word], [difficulty], [seconds_left] FROM leaderboard ORDER BY (([difficulty] * 10 - [seconds_left]) / [difficulty]) LIMIT 5;";

         ResultSet rs = stmt.executeQuery(insertSQL);

         int rank = 1;
         while (rs.next()) {
            String[] row = {String.valueOf(rank), rs.getString("word"), String.valueOf(rs.getInt("difficulty")), String.valueOf(rs.getInt("seconds_left"))};
            results[rank - 1] = row;
            rank++;
         }

         rs.close();
         stmt.close();
         conn.close();  
      } catch (Exception e) {
         System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      return results;
   }

   /**
   * Checks if leaderboard table is empty.
   * Determines whether the leaderboard table has rows or not.
   *
   * @return {@code boolean} true if table is empty, false otherwise.
   */
   public boolean leaderboardIsEmpty() {
      Connection conn = null;
      Statement stmt = null;

      boolean isEmpty = false;

      try {
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:client/build/libs/results.db");

         stmt = conn.createStatement();
         String insertSQL = "SELECT COUNT(*) AS 'count' FROM leaderboard";

         ResultSet rs = stmt.executeQuery(insertSQL);

         while (rs.next()) {
            if (rs.getInt("count") == 0) {
               isEmpty = true;
            } else {
               isEmpty = false;
            }
         }

         rs.close();
         stmt.close();
         conn.close();  
      } catch (Exception e) {
         System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      return isEmpty;
   }

   /**
   * Clears leaderboard of all data.
   * Deletes all rows from the leaderboard table.
   */
   public void clearLeaderboardData() {
      Connection conn = null;
      Statement stmt = null;

      try {
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:client/build/libs/results.db");

         stmt = conn.createStatement();
         String deleteCmd = "DELETE FROM leaderboard;";

         stmt.executeUpdate(deleteCmd);

         stmt.close();
         conn.close();  
      } catch (Exception e) {
         System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
   }

}