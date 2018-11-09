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

  public void actionPerformed(ActionEvent event) {
    currentTime -= 1;
    timeBox.setText(String.valueOf(currentTime));
    if(currentTime <= 0) {
      countdownTimer.stop();
    }
	}

  public TimerPanel() {
    this(0);
  }

  public TimerPanel(int startTime) {
    this.startTime = startTime;
    this.currentTime = startTime;
    this.countdownTimer = new Timer(1000, null);
    this.countdownTimer.addActionListener(this);

    setLayout(new GridLayout (1, 2, 30, 20));
    JLabel timeRemaining = new JLabel("Time Remaining");
		timeBox = new JLabel(String.valueOf(currentTime));
		timeBox.setName("timer");
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
