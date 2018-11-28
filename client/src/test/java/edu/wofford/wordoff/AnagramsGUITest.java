package edu.wofford.wordoff;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

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
  public void testButtonAndTextFieldAreDisabledByMethod() {
    assertEquals(true, window.getButtonAndTextFieldState());
    window.disableButtonAndTextField();
    assertEquals(false, window.getButtonAndTextFieldState());
  }

  @Test
  public void testLeaderboardDialogDisplaysWhenCalled() {
    window.showLeaderboadDialog();
    //check if showing
  }

}
