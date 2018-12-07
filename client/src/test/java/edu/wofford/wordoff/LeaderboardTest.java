package edu.wofford.wordoff;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class LeaderboardTest {
  private AnagramsLeaderboard database;
  private LeaderboardDialog leaderboard;
  private JFrame parentFrame;
  private String selectWord;
  private int difficulty;
  private int time;

  @Before
  public void setup() {
    parentFrame = new JFrame();
    parentFrame.setVisible(true);
    database = new AnagramsLeaderboard();
    try {
      database.insertNewResult("test", 4, 10);
    } catch (Exception e) {
      System.err.println("Leaderboard table was not created due to an error. See stack trace for details.");
      System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
      e.printStackTrace();
    }

  }

  @Test
  public void testIfLeaderboardIsVisible() {
    selectWord = "emits";
    difficulty = 4;
    time = 5;
    leaderboard = new LeaderboardDialog(parentFrame, selectWord, difficulty, time);
    assertTrue(leaderboard != null);
  }
}
