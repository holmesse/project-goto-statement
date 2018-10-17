package edu.wofford.wordoff;

import java.util.Scanner;
import java.util.*;
import java.io.*;

/**
* <h1>Feature 02</h1>
* This feature accepts an integer from the command line and
* presents the user with a word that has a number of anagrams
* equal to the number provided.
* <br />
* The user then is prompted to guess anagrams of the word using
* the command line. If the guess is correct, the program prompts
* the user that there are x number of anagrams remaining,
* and the user continues to guess until there are no more
* anagrams remaining.
*/
public class Feature02Main {
    public static void main(String[] args) {
    	if (args.length > 0) {

	    	try {
	        	int difficulty = Integer.parseInt(args[0]);

		        if (difficulty >= 2) {

				    	Anagrams anagrams = new Anagrams();
				        List<String> listOfAnagrams = anagrams.getNumberOfAnagrams(difficulty);

				        if (listOfAnagrams != null) {

				        Random random = new Random();

				        int randomIndex = random.nextInt(listOfAnagrams.size());

				        String selectedWord = listOfAnagrams.get(randomIndex);
				        listOfAnagrams.remove(randomIndex);
				        System.out.printf("The word is \"%s\".\n", selectedWord);

				        Scanner reader = new Scanner(System.in);

				        int anagramsRemaining = listOfAnagrams.size();
				        while (anagramsRemaining > 0) {
				        	System.out.printf("There are %s anagrams remaining: ", anagramsRemaining);
				        	String guess = reader.next().toLowerCase();
				        	if (listOfAnagrams.contains(guess)) {
				        		anagramsRemaining -= 1;
				        		listOfAnagrams.remove(guess);
				        	}
				        }
				        System.out.println("There are 0 anagrams remaining.");
				        reader.close();
			    	}
			    }

	    	}
	    	catch (NumberFormatException e) {

	    	}
    	}
    }
}