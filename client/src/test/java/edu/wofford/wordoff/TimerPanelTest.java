package edu.wofford.wordoff;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;

public class TimerPanelTest {
	private TimerPanel timer;
  private JButton button;
  private JTextField guess;

	@Before
	public void setUp() {
    button = new JButton();
    guess = new JTextField();
		timer = new TimerPanel(button, guess);
	}

  @Test
  public void testGetStartTimeReturnsCorrectInt() {
    int numberToTest = 5;
    timer = new TimerPanel(5, button, guess);
    assertEquals(numberToTest, timer.getStartTime());
  }

  @Test
  public void testGetCurrentTimeReturnsCorrectInt() {
    int numberToTest = 7;
    timer = new TimerPanel(7, button, guess);
    assertEquals(numberToTest, timer.getCurrentTime());
  }

  @Test
  public void testSetStartTimeCorrectlyChanges() {
    timer = new TimerPanel(button, guess);
    assertEquals(0, timer.getStartTime());
    timer.setStartTime(10);
    assertEquals(10, timer.getStartTime());
  }

  @Test
  public void testTimerCountsDownToZero() {
    timer = new TimerPanel(5, button, guess);
    assertEquals(5, timer.getCurrentTime());
    timer.startTimer();
    try {
      TimeUnit.SECONDS.sleep(6);
    } catch(InterruptedException e) {}

    assertEquals(0, timer.getCurrentTime());
  }

  @Test
  public void testTimerResetsToStartTime() {
    timer = new TimerPanel(5, button, guess);
    assertEquals(5, timer.getCurrentTime());
    timer.startTimer();
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch(InterruptedException e) {}

    timer.stopTimer();
    timer.resetTimer();
    assertEquals(5, timer.getCurrentTime());
  }

}
