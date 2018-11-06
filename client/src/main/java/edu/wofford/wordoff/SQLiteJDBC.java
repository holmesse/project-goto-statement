package edu.wofford.wordoff;

import java.sql.*;

/**
* This class sets up the results.db SQLite database that is used for keeping track
* of game results and high scores.
*/
public class SQLiteJDBC {

   public static void main( String args[] ) {
      Connection conn = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         conn = DriverManager.getConnection("jdbc:sqlite:client/build/libs/results.db");
         System.out.println("Opened database successfully");

         stmt = conn.createStatement();
         String createLeaderboardSQL = "CREATE TABLE leaderboard (" +
                        " id INT PRIMARY KEY NOT NULL," +
                        " word TEXT NOT NULL, " + 
                        " difficulty INT NOT NULL, " + 
                        " seconds_left INT NOT NULL, " + 
                        " submitDateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"; 
         stmt.executeUpdate(createLeaderboardSQL);
         stmt.close();
         conn.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Table created successfully");
   }
}