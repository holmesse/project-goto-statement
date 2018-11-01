package edu.wofford.wordoff;

import java.util.*;
import java.util.List;
import java.io.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
* Anagrams GUI
* Implements a GUI for the Anagrams game.
*/
public class AnagramsGUI extends JFrame implements ActionListener {

	private static List<String> listOfAnagrams;
	private JTextField guess;
	// dictionary:
	//  Contains the anagram as the key and the JLabel as the value.
	private static Map<String, JLabel> dictionary;
	private JLabel target;
	private JButton button;

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


	public AnagramsGUI(Random random) {
		//set title and name of frame
		setTitle("WordOff");
		setName("WordOff");
		setSize(new Dimension(415, 550));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		//set target word to find anagrams of
		//set constraints and make the gui look purty
		
		int randomIndex = random.nextInt(listOfAnagrams.size());
		String selectedWord = listOfAnagrams.get(randomIndex);
		listOfAnagrams.remove(randomIndex);
		
		target = new JLabel(selectedWord);
		target.setName("target");
		target.setHorizontalAlignment(JLabel.CENTER);
		target.setBorder(new LineBorder(Color.RED));

		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.anchor = GridBagConstraints.CENTER;
		constraint.gridwidth = 2;
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.ipady = 20;
		constraint.ipadx = 315;
		constraint.insets = new Insets(25,20,0,20);
		Dimension preferredSize = new Dimension(0,15);
		mainPanel.add(target, constraint);
		
		//dynamically create labels for the appropriate number of anagrams needed
		for(int i = 0; i < listOfAnagrams.size(); i++) {
			JLabel label = new JLabel("");
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setBorder(new LineBorder(Color.BLACK));
			label.setName("anagram" + Integer.toString(i));
			label.setPreferredSize(preferredSize);
			label.setMinimumSize(preferredSize);
			constraint.gridx = 0;
			constraint.gridy = i + 1;
			mainPanel.add(label, constraint);
			dictionary.put(listOfAnagrams.get(i),label);
		}
		//set the guess textfield
		guess = new JTextField();
		guess.setName("guess");
		constraint.insets = new Insets(60,20,40,20);
		constraint.fill = GridBagConstraints.NONE;
		constraint.gridwidth = 1;
		constraint.gridx = 0;
		constraint.gridy = listOfAnagrams.size() + 1;
		constraint.ipadx = 162;
		constraint.anchor = GridBagConstraints.LAST_LINE_START;
		mainPanel.add(guess, constraint);
		guess.requestFocus(true);
		
		//set the guess button and create actionlistener for button press
		button = new JButton("Guess");
		button.setName("button");
		button.setSize(new Dimension(30, 20));
		constraint.gridx = 1;
		constraint.gridy = listOfAnagrams.size() + 1;
		constraint.ipadx = 20;
		constraint.anchor = GridBagConstraints.LAST_LINE_END;
		button.addActionListener(this);
		guess.addActionListener(this);
		mainPanel.add(button, constraint);


		add(mainPanel);
		pack();
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
