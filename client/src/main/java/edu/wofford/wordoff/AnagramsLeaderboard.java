package edu.wofford.wordoff;

import java.sql.*;
import java.util.*;

/**
* This class sets up the results.db SQLite database that is used for keeping track
* of game results and high scores.
*/
public class AnagramsLeaderboard {

   private static String pathToDatabase = "jdbc:sqlite:results.db";

   /**
   * Create Leaderboard.
   * This method creates a new leaderboard table if one
   * does not already exist. If the leaderboard table
   * exists, nothing happens.
   *
   * @throws ClassNotFoundException When sqlite JDBC cannot be found.
   * @throws SQLException If an error occurs when creating the leaderboard table.
   */
   public static void createLeaderboardTable() throws ClassNotFoundException, SQLException {
      Connection conn = null;
      Statement stmt = null;
      //Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(pathToDatabase);

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
   }

   /**
   * Insert new result into the leaderboard.
   * Inserts a new record into the leaderboard table with the
   * specified values of word, difficulty, and seconds_left.
   *
   * @param word The word played.
   * @param difficulty The difficulty of the game.
   * @param seconds_left The time remaining after winning.
   *
   * @throws ClassNotFoundException When sqlite JDBC cannot be found.
   * @throws SQLException If an error occurs when inserting data
   * into the leaderboard table.
   */
   public static void insertNewResult(String word, int difficulty, int seconds_left) throws ClassNotFoundException, SQLException {
      Connection conn = null;
      Statement stmt = null;

      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(pathToDatabase);

      stmt = conn.createStatement();
      String insertSQL = String.format("INSERT OR IGNORE INTO leaderboard (word, difficulty, seconds_left) VALUES ('%s', %d, %d);", word, difficulty, seconds_left);

      stmt.executeUpdate(insertSQL);
      stmt.close();
      conn.close();
   }

   /**
   * Get the top results.
   * Gets the top 5 results from the leaderboard and returns
   * them as a 2-dimensional String array. Columns are, in order:
   * rank, word, difficulty, seconds_left
   * @return {@code String[][]} Two-dimensional string array
   * representing the results of the query.
   *
   * @throws ClassNotFoundException When sqlite JDBC cannot be found.
   * @throws SQLException If an error occurs when inserting data
   * into the leaderboard table.
   */
   public static String[][] selectLeaderboardData() throws ClassNotFoundException, SQLException{
     return selectLeaderboardData(5);
   }

   /**
   * Get a select number of results from the leaderboard.
   * Gets the top n results from the leaderboard and returns
   * them as a 2-dimensional String array. Columns are, in order:
   * rank, word, difficulty, seconds_left
   * @param numberOfScores int representing number of score results to be returned, defaults to 5
   * @return {@code String[][]} Two-dimensional string array
   * representing the results of the query.
   *
   * @throws ClassNotFoundException When sqlite JDBC cannot be found.
   * @throws SQLException If an error occurs when inserting data
   * into the leaderboard table.
   */
   public static String[][] selectLeaderboardData(int numberOfScores) throws ClassNotFoundException, SQLException {
      Connection conn = null;
      Statement stmt = null;

      String[][] results = new String[numberOfScores][4];
      String[][] leaderboardData = new String[0][0];

      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(pathToDatabase);

      stmt = conn.createStatement();
      String insertSQL = "SELECT [word], [difficulty], [seconds_left] FROM leaderboard ORDER BY (([difficulty] * 10 - [seconds_left]) / [difficulty]) LIMIT " + numberOfScores + " ;";

      ResultSet rs = stmt.executeQuery(insertSQL);

      int rank = 1;
      while (rs.next()) {
         String[] row = {String.valueOf(rank), rs.getString("word"), String.valueOf(rs.getInt("difficulty")), String.valueOf(rs.getInt("seconds_left"))};
         results[rank - 1] = row;
         rank++;
      }

      int rowCounter = 0;
      for (int i = 0; i < numberOfScores; i++) {
         if (results[i][0] != null) {
            rowCounter++;
         }
      }

      leaderboardData = new String[rowCounter][4];
      for (int j = 0; j < numberOfScores; j++) {
         if (results[j][0] != null) {
            leaderboardData[j] = results[j];
         }
      }

      rs.close();
      stmt.close();
      conn.close();
      return leaderboardData;
   }

   /**
   * Checks if leaderboard table is empty.
   * Determines whether the leaderboard table has rows or not.
   *
   * @return {@code boolean} true if table is empty, false otherwise.
   *
   * @throws ClassNotFoundException When sqlite JDBC cannot be found.
   * @throws SQLException If an error occurs when checking if the table
   * is empty.
   */
   public static boolean leaderboardIsEmpty() throws ClassNotFoundException, SQLException {
      Connection conn = null;
      Statement stmt = null;

      boolean isEmpty = false;

      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(pathToDatabase);

      stmt = conn.createStatement();
      String insertSQL = "SELECT COUNT(*) AS 'count' FROM leaderboard;";

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
      return isEmpty;
   }

   /**
   * Clears leaderboard of all data.
   * Deletes all rows from the leaderboard table.
   *
   * @throws ClassNotFoundException When sqlite JDBC cannot be found.
   * @throws SQLException If an error occurs when clearing the data
   * from the leaderboard.
   */
   public static void clearLeaderboardData() throws ClassNotFoundException, SQLException {
      Connection conn = null;
      Statement stmt = null;

      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection(pathToDatabase);

      stmt = conn.createStatement();
      String deleteCmd = "DELETE FROM leaderboard;";

      stmt.executeUpdate(deleteCmd);

      stmt.close();
      conn.close();
   }

}
