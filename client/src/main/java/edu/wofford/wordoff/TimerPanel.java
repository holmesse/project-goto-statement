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

  public TimerPanel(){
    this(0);
  }

  public TimerPanel(int startTime){
    startTime = startTime;
    currentTime = startTime;
    countdownTimer = new Timer(1000, null);
    countdownTimer.addActionListener(this);
  }
  public void actionPerformed(ActionEvent event) {

	}
}
