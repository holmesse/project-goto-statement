package edu.wofford.wordoff;

import java.awt.Dialog.ModalityType;
import java.awt.Dimension;

import javax.swing.Box;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.table.AbstractTableModel;

public class LeaderboardDialog extends JDialog{
  private JDialog leaderboardDialog;
  private AnagramsLeaderboard leaderboardData;

/**
* Generates an new modeless dialog box with a {@code JTable} that shows the
* score leaderboard for the anagrams getName
*
* @param parent A JFrame that is used to create a new {@code JDialog} box.
* @param selectedWord The word used in the game to be added to the leaderboard.
* @param difficulty The number of anagams needed to be found.
* @param time The remaining time after all anagrams were found.
*/
  public LeaderboardDialog(JFrame parent, String selectedWord, int difficulty, int time) {
    leaderboardData = new AnagramsLeaderboard();

    leaderboardData.createLeaderboardTable();
    if(!leaderboardData.leaderboardIsEmpty()) {
      leaderboardDialog = new JDialog(parent);
      leaderboardDialog.add(Box.createRigidArea(new Dimension(500, 300)));

      if(time > 0) {
        leaderboardData.insertNewResult(selectedWord, difficulty, time);
      }

      String[][] dataModel = leaderboardData.selectLeaderboardData();

      String[] columnNames = {"Rank", "Word", "Difficulty", "Seconds Left"};

      JTable leaderboard = new JTable(dataModel, columnNames);
      leaderboard.setRowHeight(30);
      leaderboardDialog.add(new JScrollPane(leaderboard));

      leaderboard.setName("leaderboard");
      leaderboardDialog.setTitle("Leaderboard");
      leaderboardDialog.pack();
      leaderboardDialog.setVisible(true);
    }

  }

}
