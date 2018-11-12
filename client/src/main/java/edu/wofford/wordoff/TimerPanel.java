package edu.wofford.wordoff;

import java.util.*;
import java.util.List;
import java.io.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;

public class TimerPanel extends JPanel implements ActionListener {
  private Timer countdownTimer;
  private int currentTime;
  private int startTime;
  private JPanel timerPanel;
  private JLabel timeBox;
  private JButton button;
  private JTextField guess;

  public void actionPerformed(ActionEvent event) {
    currentTime -= 1;
    timeBox.setText(String.format("%02d:%02d", currentTime / 60, currentTime % 60));
    if(currentTime <= 0) {
      countdownTimer.stop();
      button.setEnabled(false);
      guess.setEnabled(false);
    }
	}

  public TimerPanel(JButton button, JTextField guess) {
    this(0, button, guess);
  }

  public TimerPanel(int startTime, JButton button, JTextField guess) {
    this.startTime = startTime;
    this.currentTime = startTime;
    this.countdownTimer = new Timer(1000, null);
    this.countdownTimer.addActionListener(this);
    this.button = button;
    this.guess = guess;

    setLayout(new GridLayout (1, 2, 30, 20));
    setBorder(new EmptyBorder(20, 30, 20, 30));
    JLabel timeRemaining = new JLabel("Time Remaining");
    timeRemaining.setBorder(new LineBorder(Color.GRAY));
    timeRemaining.setHorizontalAlignment(JLabel.CENTER);
    timeRemaining.setPreferredSize(new Dimension(20, 40));

		timeBox = new JLabel(String.format("%02d:%02d", currentTime / 60, currentTime % 60));
		timeBox.setName("timer");
    timeBox.setBorder(new LineBorder(Color.GRAY));
    timeBox.setHorizontalAlignment(JLabel.CENTER);
    timeBox.setPreferredSize(new Dimension(20, 40));
		add(timeRemaining);
		add(timeBox);
  }

  public int getStartTime() {
    return startTime;
  }

  public int getCurrentTime() {
    return currentTime;
  }

  public void setStartTime(int newTime) {
    this.startTime = newTime;
  }

  public void resetTimer() {
    this.startTime = startTime;
    this.currentTime = startTime;
  }

  public void startTimer() {
    countdownTimer.start();
  }

  public void stopTimer() {
    countdownTimer.stop();
  }

}
