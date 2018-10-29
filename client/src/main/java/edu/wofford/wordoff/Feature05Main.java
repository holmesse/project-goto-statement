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
	private static Map<String, JLabel> dictionary;
	private Integer count;
	private JLabel target;
	private JButton button;

	public void actionPerformed(ActionEvent event) {
			String input = guess.getText();
			if (dictionary.containsKey(input)) {
				dictionary.get(input).setText(input);
				count++;
			}
			if(count >= dictionary.size()){
				//disable JButton
				target.setBorder(new LineBorder(Color.GREEN));
				button.setEnabled(false);
				guess.setEnabled(false);
			}
			guess.setText("");
	}


	public Feature05Main() {
		setTitle("Word Off");
		setSize(new Dimension(450, (listOfAnagrams.size() - 1) * 100));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		count = 0;
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();

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

		for(int i = 0; i < listOfAnagrams.size() - 1; i++) {
			JLabel label = new JLabel("??");
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setBorder(new LineBorder(Color.BLACK));
			label.setName("anagram" + Integer.toString(i));
			constraint.gridx = 0;
			constraint.gridy = i + 1;
			mainPanel.add(label, constraint);
			dictionary.put(listOfAnagrams.get(i+1),label);
		}

		guess = new JTextField();
		constraint.gridwidth = 1;
		constraint.gridx = 0;
		constraint.gridy = listOfAnagrams.size();
		constraint.ipadx = 162;
		mainPanel.add(guess, constraint);

		button = new JButton("Guess");
		button.setSize(new Dimension(30, 20));
		//constraint.gridwidth = 1;
		constraint.gridx = 1;
		constraint.gridy = listOfAnagrams.size();
		constraint.ipadx = 20;
		button.addActionListener(this);
		mainPanel.add(button, constraint);


		add(mainPanel);
}


	public static void main(String[] args) {
		if (args.length > 0) {

			try {
				int difficulty = Integer.parseInt(args[0]);

				if (difficulty >= 2) {
					Anagrams anagrams = new Anagrams();
					listOfAnagrams = anagrams.getNumberOfAnagrams(difficulty);

					if(listOfAnagrams != null) {
						dictionary = new HashMap<>();
						System.out.println(listOfAnagrams);
						Feature05Main window = new Feature05Main();
						window.setVisible(true);
					}


			}
		}
			catch (NumberFormatException e){}
			catch (IndexOutOfBoundsException e){}
		}
	}
}
