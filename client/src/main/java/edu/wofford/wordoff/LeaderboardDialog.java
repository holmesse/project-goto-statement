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

import java.net.*;

import java.io.*;

/**
* LeaderboardDialog
* Implements JDialog to generate a modeless dialog box that displays the top scores
* of the anagrams guessing game.
*
* The following is an example of how to intialize a {@code LeaderboardDialog} object using
* the {@code LeaderboardDialog} constructor.
* <pre>{@code
* //Constructor with parent JFrame, the selected word used in the game, its difficulty, and the remaining time.
* JFrame parentFrame = new JFrame();
* String word = "test";
* int difficultyLevel = 4;
* int timeLeft = 5;
* LeaderboardDialog leaderboard = new LeaderboardDialog(parentFrame, word, difficultyLevel, timeLeft);
* }</pre>
*/
public class LeaderboardDialog extends JDialog{
    private JDialog leaderboardDialog;
   // private AnagramsLeaderboard leaderboardData;

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

        if (time > 0) {
            try {

                URL url = new URL("http://localhost:8080/wordoff/leaderboard/add/" + selectedWord + "/" + difficulty + "/" + time);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int status = conn.getResponseCode();
                if (status == 200) {
                    StringBuffer content = new StringBuffer();
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            content.append(inputLine);
                        }
                        in.close();
                    } catch (Exception e) {
                        System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
                        e.printStackTrace();
                    }

                    String response = content.toString();
                    System.out.println(response);

                    if (!response.equals("true")) {
                        throw new Exception("Result not successfully inserted into database.");
                    }
                }
            } catch (Exception e) {
                System.err.println("Data was not successfully inserted into leaderboard."
                    + "See stack trace for details.");
                e.printStackTrace();
            }
        }

        boolean tableIsEmpty = true;
        try {
            tableIsEmpty = AnagramsLeaderboard.leaderboardIsEmpty();
        } catch (Exception e) {
            System.err.println("Exception raised when checking to see if the leaderboard was empty. See stack trace for details.");
            System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
        }

        if (!tableIsEmpty) {
            leaderboardDialog = new JDialog(parent);
            leaderboardDialog.add(Box.createRigidArea(new Dimension(500, 300)));

            try {
                String[][] dataModel = new String[0][0];

                URL url = new URL("http://localhost:8080/wordoff/leaderboard/top/5");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int status = conn.getResponseCode();
                if (status == 200) {

                    try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {

                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            dataModel = convertStringToStringArray(inputLine);
                        }
                        in.close();

                    } catch (Exception e) {
                        System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
                        e.printStackTrace();
                    }

                    String[] columnNames = {"Rank", "Word", "Difficulty", "Seconds Left"};

                    JTable leaderboard = new JTable(dataModel, columnNames);
                    leaderboard.setRowHeight(30);
                    leaderboardDialog.add(new JScrollPane(leaderboard));

                    leaderboard.setName("leaderboard");
                    leaderboardDialog.setTitle("Leaderboard");
                    leaderboardDialog.pack();
                    leaderboardDialog.setVisible(true);
                }
            } catch (Exception e) {
                System.err.println("An exception was raised when selecting data. See stack trace for details.");
                System.err.println("ERROR: " + e.getClass().getName() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    /**
    * Private helper method to return a given string as a two dimensional String array
    *
    * @param arrayAsString A String to be converted to a String[][].
    * @return A String[][] version of the given String.
    */
    private String[][] convertStringToStringArray(String arrayAsString) {
        String modifiedString = arrayAsString.substring(2, arrayAsString.length() - 2);

        String[] tempArray = modifiedString.split("\\],\\[");

        String[][] retArray = new String[tempArray.length][4];

        for (int i = 0; i < tempArray.length; i++) {
            String noQuotesString = tempArray[i].replace("\"", "");
            retArray[i] = noQuotesString.split(",");
        }

        return retArray;
    }

}
