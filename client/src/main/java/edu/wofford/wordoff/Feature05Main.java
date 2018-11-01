package edu.wofford.wordoff;

import java.util.*;
import java.util.List;
import java.io.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;

public class Feature05Main extends JFrame implements ActionListener {

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


	public Feature05Main() {
		//set title and name of frame
		setTitle("WordOff");
		setName("WordOff");
		setSize(new Dimension(450, (listOfAnagrams.size() - 1) * 100));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		//set target word to find anagrams of
		//set constarints and make the gui look purty
		target = new JLabel(listOfAnagrams.get(0));
		target.setName("target");
		target.setHorizontalAlignment(JLabel.CENTER);
		target.setBorder(new LineBorder(Color.RED));

		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.gridwidth = 2;
		constraint.gridx = 0;
		constraint.gridy = 0;
		constraint.ipady = 20;
		constraint.ipadx = 325;
		constraint.insets = new Insets(10,0,0,10);
		mainPanel.add(target, constraint);
//dynamically create labels for the appropriate number of anagrams needed
		for(int i = 0; i < listOfAnagrams.size() - 1; i++) {
			JLabel label = new JLabel("");
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setBorder(new LineBorder(Color.BLACK));
			label.setName("anagram" + Integer.toString(i));
			constraint.gridx = 0;
			constraint.gridy = i + 1;
			mainPanel.add(label, constraint);
			dictionary.put(listOfAnagrams.get(i+1),label);
		}
//set the guess textfield
		guess = new JTextField();
		guess.setName("guess");
		constraint.gridwidth = 1;
		constraint.gridx = 0;
		constraint.gridy = listOfAnagrams.size();
		constraint.ipadx = 162;
		mainPanel.add(guess, constraint);
//set the guess button and create actionlistener for button press
		button = new JButton("Guess");
		button.setName("button");
		button.setSize(new Dimension(30, 20));
		//constraint.gridwidth = 1;
		constraint.gridx = 1;
		constraint.gridy = listOfAnagrams.size();
		constraint.ipadx = 20;
		button.addActionListener(this);
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

				//ask garret about keywords vs moodle validity of passing less than 2 into the function
				//if the argument is atleast 1, find a random word with difficulty number of anagrams
				//create a hashmap and make a new window for the word
				if (difficulty >= 1) {
					Anagrams anagrams = new Anagrams("commonwords.txt");
					listOfAnagrams = anagrams.getNumberOfAnagrams(++difficulty);

					if(listOfAnagrams != null) {
						dictionary = new HashMap<>();
						//System.out.println(listOfAnagrams);
						Feature05Main window = new Feature05Main();
						window.setVisible(true);
					}

//catch exceptions if too large, too small, or invalid input
			}
		}
			catch (NumberFormatException e){}
			catch (IndexOutOfBoundsException e){}
		}
	}
}
