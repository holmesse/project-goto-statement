package edu.wofford.wordoff;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TimerPanelTest {
	private TimerPanel timer;

	@Before
	public void setUp() {
		timer = new TimerPanel();
	}

  @Test
  public void testGetStartTimeReturnsCorrectInt() {
    int numberToTest = 5;
    timer = new TimerPanel(5);
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
  public void testTimerCountsDownToZero() {
    timer = new TimerPanel(5);
    assertEquals(5, timer.getCurrentTime());
    timer.startTimer();
    try {
      //Thread.sleep(5000);
      TimeUnit.SECONDS.sleep(6);
    } catch(InterruptedException e) {}

    assertEquals(0, timer.getCurrentTime());
  }

  @Test
  public void testTimerResetsToStartTime() {
    timer = new TimerPanel(5);
    assertEquals(5, timer.getCurrentTime());
    timer.startTimer();
    try {
      //Thread.sleep(5000);
      TimeUnit.SECONDS.sleep(3);
    } catch(InterruptedException e) {}

    timer.stopTimer();
    timer.resetTimer();
    assertEquals(5, timer.getCurrentTime());

  }
}
