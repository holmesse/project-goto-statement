package edu.wofford.wordoff;

import java.sql.*;

/**
* This class sets up the results.db SQLite database that is used for keeping track
* of game results and high scores.
*/
public class SQLiteJDBC {

   public static void main( String args[] ) {
      
   }

   public void createLeaderboardTable() {
      Connection conn = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:client/build/libs/results.db");
         System.out.println("Opened database successfully");

         stmt = conn.createStatement();
         String createLeaderboardSQL = "CREATE TABLE leaderboard (" +
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

   public void insertNewResult(String word, int difficulty, int seconds_left) {
      Connection conn = null;
      Statement stmt = null;

      try {
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:client/build/libs/results.db");
         System.out.println("Opened database successfully");

         stmt = conn.createStatement();
         String insertSQL = String.format("INSERT INTO leaderboard (word, difficulty, seconds_left) VALUES (%s, %d, %d);", word, difficulty, seconds_left);

         stmt.executeUpdate(insertSQL);
         stmt.close();
         conn.close();  
      } catch (Exception e) {
         System.err.println(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }
      System.out.println("Insert executed successfully");
   }

   public String[][] selectLeaderboardData() {
      Connection conn = null;
      Statement stmt = null;

      String[][] results = new String[5][4];

      try {
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:client/build/libs/results.db");
         System.out.println("Opened database successfully");

         stmt = conn.createStatement();
         String insertSQL = "SELECT [word] AS 'Word', [difficulty] AS 'Difficulty', [seconds_left] AS 'Seconds Left' FROM leaderboard ORDER BY (([difficulty] * 10 - [seconds_left]) / [difficulty]) LIMIT 5;";

         ResultSet rs = stmt.executeQuery(insertSQL);

         int rank = 1;
         while (rs.next()) {
            String[] row = {String.valueOf(rank), rs.getString("word"), String.valueOf(rs.getInt("difficulty")), String.valueOf(rs.getInt("seconds_left"))};
            results[rank - 1] = row;
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