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



/**
* Anagrams GUI
* Implements a GUI for the Anagrams game. Players are given a target
* word with a specified number of anagrams and prompted to enter
* anagrams of that word until all anagrams have been found.
* <br>
* <img src="doc-files/gui-start.png" alt="Example of playing the game.">
* An example of the GUI with no anagrams guessed. The timer will
* continue to decrease until it reaches 0, or until all anagrams
* have been entered. At that point, the game will end in either a
* loss, if not all anagrams were guessed, or a win, if all anagrams
* were discovered in time.
*
* <img src="doc-files/gui-win.png" alt="Example of GUI when game is won">
* An example of the GUI after all words have been guessed.
*/
public class AnagramsGUI extends JFrame implements ActionListener, TimerListener{
	private JTextField guess;
	// dictionary:
	//  Contains the anagram as the key and the JLabel as the value.
	private Map<String, JLabel> dictionary;
	private JLabel target;
	private JButton button;
	private TimerPanel timerPanel;
	private LeaderboardDialog leaderboardDialog;
	private int difficulty;
	private String selectedWord;

	/**
	* Action Performed on Button Click.
	*/
	public void actionPerformed(ActionEvent event) {
			String input = guess.getText().toLowerCase();
			if (dictionary.containsKey(input)) {
				// If guess was correct, set label to input and remove word from dictionary
				dictionary.get(input).setText(input);
				dictionary.remove(input);
			}
			//when the dictionary is empty, all anagrams have been guessed
			if(dictionary.size() == 0){
				//disable JButton if count is higher than the
				target.setBorder(new LineBorder(Color.GREEN));
				button.setEnabled(false);
				guess.setEnabled(false);
				timerPanel.stopTimer();
				showLeaderboadDialog();
			}
			//if guess is incorrect rest the textfield to empty
			guess.setText("");
	}

	/**
	* Implementation of the timerExpired method from the TimerListener interface.
	* When called the method disables the button and text field using
	* {@code disableButtonAndTextField}, then calls the
	* {@code showLeaderboadDialog()} method.
	*/
	public void timerExpired(){
		disableButtonAndTextField();
		showLeaderboadDialog();
	}

	/**
	* Default Constructor
	* Runs the {@code AnagramsGUI(Random random, List<String> listOfAnagrams)}
	* constructor with a new instance of Random with no seed.
	*
	* @param listOfAnagrams A list of anagrams to be used in the generation of the
	* TimerPanel and JLabel creation.
	* @param difficulty The number of anagrams to be found during the game
	*/
	public AnagramsGUI(List<String> listOfAnagrams, int difficulty) {
		this(new Random(), listOfAnagrams, difficulty);
	}


	/**
	* Constructor with Random Instance
	* Builds the GUI for the anagrams game.
	*
	* @param random An instance of Random to use for word generation.
	* @param listOfAnagrams A list of anagrams to be used in the generation of the
	* TimerPanel and JLabel creation.
	* @param difficulty The number of anagrams to be found during the game
	*/
	public AnagramsGUI(Random random, List<String> listOfAnagrams, int difficulty) {
		//set title and name of frame
		setTitle("WordOff");
		setName("WordOff");
		setSize(new Dimension(415, 550));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1, 0, 20));
		mainPanel.setBorder(new EmptyBorder(20, 30, 10, 30));
		//set target word to find anagrams of
		//set constraints and make the gui look purty

		int randomIndex = random.nextInt(listOfAnagrams.size());
		selectedWord = listOfAnagrams.get(randomIndex);
		this.difficulty = difficulty;
		listOfAnagrams.remove(randomIndex);

		target = new JLabel(selectedWord);
		target.setName("target");
		target.setHorizontalAlignment(JLabel.CENTER);
		target.setBorder(new LineBorder(Color.RED));
		Dimension preferredSize = new Dimension(350, 40);
		mainPanel.add(target);
		dictionary = new HashMap<>();
		//dynamically create labels for the appropriate number of anagrams needed
		for(int i = 0; i < listOfAnagrams.size(); i++) {
			JLabel label = new JLabel("");
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setBorder(new LineBorder(Color.BLACK));
			label.setName("anagram" + Integer.toString(i));
			label.setPreferredSize(preferredSize);
			mainPanel.add(label);
			dictionary.put(listOfAnagrams.get(i),label);
		}

		JPanel guessPanel = new JPanel();
		guessPanel.setLayout(new GridLayout (1, 2, 30, 20));
		guessPanel.setBorder(new EmptyBorder(40, 30, 30, 30));

		//set the guess textfield
		guess = new JTextField();
		guess.setName("guess");
		guess.setPreferredSize(new Dimension(20, 30));


		guessPanel.add(guess);
		guess.requestFocus(true);

		//set the guess button and create actionlistener for button press
		button = new JButton("Guess");
		button.setName("button");
		button.setPreferredSize(new Dimension(10, 40));
		button.addActionListener(this);
		guess.addActionListener(this);
		guessPanel.add(button);

		int totalTime = 10 * listOfAnagrams.size();
		timerPanel = new TimerPanel(totalTime);
		timerPanel.addTimerListener(this);
		add(timerPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(guessPanel, BorderLayout.SOUTH);
		pack();
	}

/**
* Starts the timerPanel countdown timer
*/
	public void startTimer() {
		timerPanel.startTimer();
	}

/**
* Returns the {@code JButton} object of the AnagramsGUI.
* @return The {@code JButton} object intialized by the AnagramsGUI constructor
*/
	public JButton getButton(){
		return this.button;
	}

/**
* Returns the {@code JTextField} object of the AnagramsGUI.
* @return The {@code JTextField} object intialized by the AnagramsGUI constructor
*/
	public JTextField getTextField(){
		return this.guess;
	}

/**
* Disables the JButton and JTextField in the GUI
*/
	public void disableButtonAndTextField() {
		button.setEnabled(false);
		guess.setEnabled(false);
	}

/**
* Returns the current state of the given {@code JButton} and {@code JTextField}
* objects.
* @param jbutton JButton object to check to see if it is enabled
* @param jtextfield JTextField object to check to see if it is enabled
* @return returns true if both are enabled, returns false otherwise
*/
	public boolean getButtonAndTextFieldState(JButton jbutton, JTextField jtextfield) {
		return jbutton.isEnabled() && jtextfield.isEnabled();
	}

/**
* Creates a new instance of the {@code LeaderboardDialog} to display the leaderboard.
*/
	public void showLeaderboadDialog() {
		leaderboardDialog = new LeaderboardDialog(this, selectedWord, difficulty, timerPanel.getCurrentTime());
	}

	public static void main(String[] args) {
	//check argument was passed
		if (args.length > 0) {
			//try to parse argument as an integer
			try {
				int difficulty = Integer.parseInt(args[0]);

				//if the argument is atleast 1, find a random word with difficulty number of anagrams
				//create a hashmap and make a new window for the word
				if (difficulty >= 1) {
					Anagrams anagrams;
					Random random;
					if (args.length > 1) {
						long randomSeed = Long.parseLong(args[1]);
						random = new Random(randomSeed);
						anagrams = new Anagrams(random, "commonwords.txt");
					} else {
						anagrams = new Anagrams("commonwords.txt");
						random = new Random();
					}
					List<String> listOfAnagrams = anagrams.getNumberOfAnagrams(difficulty + 1);

					if(listOfAnagrams != null && listOfAnagrams.size() != 0) {
						AnagramsGUI window = new AnagramsGUI(random, listOfAnagrams, difficulty);
						window.setVisible(true);
						window.startTimer();
					}

			//catch exceptions if too large, too small, or invalid input
			}
		}
			catch (NumberFormatException | IndexOutOfBoundsException e){}
		}
	}
}
