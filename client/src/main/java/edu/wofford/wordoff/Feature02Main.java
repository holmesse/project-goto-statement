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

	//test

	/**
    * <h2>Feature 02 Main</h2>
    * The main method of Feature 02 checks if the command line 
    * argument {@code args} is a valid input, then uses it to
    * generate a {@code List<String>} stucture of words with that
    * number of anagrams. From that {@code List<String>} stucture
    * an index is randomly selected and the word within is displayed.
    * The method then prompts the user to input the remaining anagrams,
    * using {@code Scanner} to read the user input. The method contiues
    * to prompt the user while possible anagrams remain. When all anagrams
    * have been correctly guessed the method prints out {@code "There are 0 
    * anagrams remaining."}.
    *
    * @param args command line input stored as String array
    */
    public static void main(String[] args) {
    	if (args.length > 0) {

	    	try {
	        	int difficulty = Integer.parseInt(args[0]);

		        if (difficulty >= 2) {

				    	Anagrams anagrams;
    					if (args.length > 1) {
    						long random_seed = Long.parseLong(args[1]);
    						Random random = new Random(random_seed);
    						anagrams = new Anagrams(random);
    					} else {
    						anagrams = new Anagrams();
    					}

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