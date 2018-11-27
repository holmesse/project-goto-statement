package edu.wofford.wordoff;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.List;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;

public class TimerPanelTest {
	private TimerPanel timer;
  private AnagramsGUI window;


	@Before
	public void setUp() {
		List<String> listOfAnagrams = new ArrayList<>();
		listOfAnagrams.add("peels");
		listOfAnagrams.add("peles");
		listOfAnagrams.add("sleep");
		listOfAnagrams.add("speel");
		int difficulty = 3;
  	window = new AnagramsGUI(listOfAnagrams, difficulty);
		timer = new TimerPanel();
	}

  @Test
  public void testGetStartTimeReturnsCorrectInt() {
    int numberToTest = 5;
    timer = new TimerPanel(numberToTest);
    assertEquals(numberToTest, timer.getStartTime());
  }

  @Test
  public void testGetCurrentTimeReturnsCorrectInt() {
    int numberToTest = 7;
    timer = new TimerPanel(7);
    assertEquals(numberToTest, timer.getCurrentTime());
  }

  @Test
  public void testSetStartTimeCorrectlyChanges() {
    timer = new TimerPanel();
    assertEquals(0, timer.getStartTime());
    timer.setStartTime(10);
    assertEquals(10, timer.getStartTime());
  }

  @Test
  public void testTimerResetsToStartTime() {
    timer = new TimerPanel(5);
    assertEquals(5, timer.getCurrentTime());
    timer.startTimer();
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch(InterruptedException e) {}
		assertFalse(timer.getCurrentTime() == 5);

    timer.stopTimer();
    timer.resetTimer();
    assertEquals(5, timer.getCurrentTime());
  }

}
