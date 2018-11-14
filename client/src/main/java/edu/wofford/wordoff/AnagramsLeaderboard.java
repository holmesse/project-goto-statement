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
         System.out.println("Opened database successfully");

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
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      System.out.println("Table created successfully");
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
         System.out.println(String.format("Inserting %s", word));

         stmt = conn.createStatement();
         String insertSQL = String.format("INSERT INTO leaderboard (word, difficulty, seconds_left) VALUES ('%s', %d, %d);", word, difficulty, seconds_left);

         stmt.executeUpdate(insertSQL);
         stmt.close();
         conn.close();  
      } catch (SQLException e) {
         if (e.getErrorCode() != 19) { //19 is the error code for the UNIQUE constraint violation, which we want to ignore.
            System.err.println("Error code (" + e.getErrorCode() + "): " + e.getMessage());
            System.exit(0);
         }
      } catch (Exception e) {
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      System.out.println("Insert executed successfully");
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
         System.out.println("Selecting data");

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
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      System.out.println("Query executed successfully");
      return results;
   }
}