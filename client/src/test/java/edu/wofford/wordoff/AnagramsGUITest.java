package edu.wofford.wordoff;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

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

}
