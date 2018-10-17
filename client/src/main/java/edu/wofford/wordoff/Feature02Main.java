package edu.wofford.wordoff;

import java.util.Scanner;
import java.util.*;
import java.io.*;

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