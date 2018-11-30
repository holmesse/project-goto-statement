package edu.wofford.wordoff;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.Timer;

public class AnagramsGUITest {
  private AnagramsGUI window;
  private Random random;

  @Before
	public void setUp() {
		long seed = Long.parseLong("25");
		random = new Random(seed);
    int difficulty = 1;
    List<String> listOfAnagrams = new ArrayList<>();
    listOfAnagrams.add("peels");
		listOfAnagrams.add("peles");
		listOfAnagrams.add("sleep");
		listOfAnagrams.add("speel");
    window = new AnagramsGUI(random, listOfAnagrams, difficulty);
	}

  @Test
  public void testGetButtonAndTextFieldStateReturnsCorrectValues() {
    JButton button = new JButton();
    JTextField guess = new JTextField();
    assertTrue(window.getButtonAndTextFieldState(button, guess));
    button.setEnabled(false);
    guess.setEnabled(false);
    assertFalse(window.getButtonAndTextFieldState(button, guess));
    button.setEnabled(true);
    assertFalse(window.getButtonAndTextFieldState(button, guess));
    button.setEnabled(false);
    guess.setEnabled(true);
    assertFalse(window.getButtonAndTextFieldState(button, guess));
  }

  @Test
  public void testButtonAndTextFieldAreDisabledByMethod() {
    assertEquals(true, window.getButtonAndTextFieldState(window.getButton(), window.getTextField()));
    window.disableButtonAndTextField();
    assertEquals(false, window.getButtonAndTextFieldState(window.getButton(), window.getTextField()));
  }

  @Test
  public void testStartTimer() {
    assertEquals(30, window.getStartTime());
    window.startTimer();
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch(InterruptedException e) {}
      //doesnt count down by five on this sleep
		assertEquals(26, window.getCurrentTime());
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch(InterruptedException e) {}
      //does count down by five on this sleep
		assertEquals(21, window.getCurrentTime());
  }

  @Test
  public void testTimerExpired() {
    window.startTimer();
    try {
      TimeUnit.SECONDS.sleep(31);
    } catch(InterruptedException e) {}
		assertEquals(0, window.getCurrentTime());
    assertTrue(window.getButtonAndTextFieldState(window.getButton(), window.getTextField()) == false);

  }

}
