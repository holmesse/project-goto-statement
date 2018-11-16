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

public class ModelessDialog extends JDialog{
  private JDialog modelessDialog;
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
  public ModelessDialog(JFrame parent, String selectedWord, int difficulty, int time) {
    leaderboardData = new AnagramsLeaderboard();

    if(!leaderboardData.leaderboardIsEmpty()) {
      modelessDialog = new JDialog(parent);
      modelessDialog.add(Box.createRigidArea(new Dimension(500, 300)));



      System.out.println("Creating table...");
      leaderboardData.createLeaderboardTable();
      System.out.println("Inserting new result...");
      if(time > 0) {
        leaderboardData.insertNewResult(selectedWord, difficulty, time);
      }

      System.out.println("Selecting data...");
      String[][] dataModel = leaderboardData.selectLeaderboardData();

      String[] columnNames = {"Rank", "Word", "Difficulty", "Seconds Left"};

      JTable leaderboard = new JTable(dataModel, columnNames);
      leaderboard.setRowHeight(30);
      modelessDialog.add(new JScrollPane(leaderboard));

      leaderboard.setName("leaderboard");
      modelessDialog.setTitle("Leaderboard");
      //modelessDialog.add(leaderboard);
      modelessDialog.pack();
      modelessDialog.setVisible(true);
    }

  }

}
