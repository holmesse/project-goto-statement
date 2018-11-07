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
*/
public class AnagramsGUI extends JFrame implements ActionListener {

	private static List<String> listOfAnagrams;
	private  JTextField guess;
	// dictionary:
	//  Contains the anagram as the key and the JLabel as the value.
	private static Map<String, JLabel> dictionary;
	private JLabel target;
	private JButton button;
	private JPanel timerPanel;
	

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
			}
			//if guess is incorrect rest the textfield to empty
			guess.setText("");
	}

	/**
	* Default Constructor
	* Runs the {@link #AnagramsGUI(Random) AnagramsGUI(random)}
	* constructor with a new instance of Random with no seed.
	*/
	public AnagramsGUI() {
		this(new Random());
	}


	/**
	* Constructor with Random Instance
	* Builds the GUI for the anagrams game.
	*
	* @param random An instance of Random to use for word generation.
	*/
	public AnagramsGUI(Random random) {
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
		String selectedWord = listOfAnagrams.get(randomIndex);
		listOfAnagrams.remove(randomIndex);

		target = new JLabel(selectedWord);
		target.setName("target");
		target.setHorizontalAlignment(JLabel.CENTER);
		target.setBorder(new LineBorder(Color.RED));
		Dimension preferredSize = new Dimension(350, 40);
		mainPanel.add(target);

		//dynamically create labels for the appropriate number of anagrams needed
		for(int i = 0; i < listOfAnagrams.size(); i++) {
			JLabel label = new JLabel("");
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setBorder(new LineBorder(Color.BLACK));
			label.setName("anagram" + Integer.toString(i));
			label.setPreferredSize(preferredSize);
			//label.setMinimumSize(preferredSize);
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
	/*
		if (timer.equals("1")) {
			JPanel timerPanel = new JPanel();
			timerPanel.setLayout(new GridLayout (1, 2, 30, 20));
			JLabel timeRemaining = new JLabel("Time Remaining");
			timerPanel.add(timeRemaining);
			add(timerPanel, BorderLayout.NORTH);
		}
		*/
		timerPanel = new JPanel();
		add(timerPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(guessPanel, BorderLayout.SOUTH);
		pack();
}
	/**
	* Adds {@code Timer} implementation to a generated GUI window.
	*/
	/*public static void timerSetup() {
		int totalTime = 10 * listOfAnagrams.size();
		timerPanel.setLayout(new GridLayout (1, 2, 30, 20));
		JLabel timeRemaining = new JLabel("Time Remaining");
		JLabel timer = new JLabel(String.valueOf(totalTime));
		timer.setName("timer");
		timerPanel.add(timeRemaining);
		timerPanel.add(timer);

		ActionListener countDown = new ActionListener(){
			public void actionPerformed(ActionEvent e){

			  totalTime -= 1;
				timer.setText(String.valueOf(totalTime));
				if(totalTime <= 0)
        {
        	countdownTimer.stop();
					button.setEnabled(false);
					guess.setEnabled(false);
        }
			}
		};
		countdownTimer = new Timer(1000, countDown);

	}*/

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
					listOfAnagrams = anagrams.getNumberOfAnagrams(++difficulty);

					if(listOfAnagrams != null && listOfAnagrams.size() != 0) {
						dictionary = new HashMap<>();
						//System.out.println(listOfAnagrams);
						AnagramsGUI window = new AnagramsGUI(random);
						window.setVisible(true);
					}

			//catch exceptions if too large, too small, or invalid input
			}
		}
			catch (NumberFormatException | IndexOutOfBoundsException e){}
		}
	}
}
