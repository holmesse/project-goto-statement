package edu.wofford.wordoff;

import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Feature04Main {
    public static void main(String[] args) {
    	if (args.length > 0) {

	    	try {
	        	int difficulty = Integer.parseInt(args[0]);

		        if (difficulty >= 3) {

			    	Anagrams anagrams;
					if (args.length > 1) {
						long random_seed = Long.parseLong(args[1]);
						Random randomInstance = new Random(random_seed);
						anagrams = new Anagrams(randomInstance, "commonwords.txt");
					} else {
						anagrams = new Anagrams("commonwords.txt");
					}
					
			        String chosenWord = anagrams.getWordWithLength(difficulty);

			        if (chosenWord != "") {

			        	System.out.printf("The word is \"%s\".%n", chosenWord);

				        List<String> subAnagramsOfWord = anagrams.getSubAnagramsOfWord(chosenWord);
				        subAnagramsOfWord.remove(chosenWord);

					    Scanner reader = new Scanner(System.in);

				        while (subAnagramsOfWord.size() > 0) {
				        	Map<Integer, List<String>> lengthBuckets = new HashMap<Integer, List<String>>();
					        for (int i = 0; i < subAnagramsOfWord.size(); i++) {
					        	String subAnagram = subAnagramsOfWord.get(i);

					        	List<String> subAnagramsWithSize = lengthBuckets.getOrDefault(subAnagram.length(), new ArrayList<>());
					        	subAnagramsWithSize.add(subAnagram);
					        	lengthBuckets.put(subAnagram.length(), subAnagramsWithSize);
					        }

					        System.out.print("There are ");

					        for(int len = difficulty; len > 1; len--) {
					        	int anagramsRemainingWithLength = lengthBuckets.getOrDefault(len, new ArrayList<>()).size();
					        	if (anagramsRemainingWithLength > 0) {
					        		System.out.printf("%s [len %s], ", anagramsRemainingWithLength, len);
					        	} else {
					        		System.out.printf("0 [len %s], ", len);
					        	}
					        }

					        int anagramsRemainingWithLength = lengthBuckets.getOrDefault(1, new ArrayList<>()).size();
				        	if (anagramsRemainingWithLength > 0) {
				        		System.out.printf("%s [len 1] anagrams remaining: ", anagramsRemainingWithLength);
				        	} else {
				        		System.out.print("0 [len 1] anagrams remaining: ");
				        	}

					        String guess = reader.next().toLowerCase();
					        if (subAnagramsOfWord.contains(guess)) {
					        	subAnagramsOfWord.remove(guess);
					        }
				    	}
				        System.out.println("There are 0 anagrams remaining.");
			        	reader.close();
		    		}
			    }

	    	}
	    	catch (NumberFormatException e) {
	    		// Input is string.
	    		Anagrams anagrams;
				if (args.length > 1) {
					long random_seed = Long.parseLong(args[1]);
					Random randomInstance = new Random(random_seed);
					anagrams = new Anagrams(randomInstance, "commonwords.txt");
				} else {
					anagrams = new Anagrams("commonwords.txt");
				}
				
		        String chosenWord = args[0];

		        if (chosenWord != "") {

			        List<String> subAnagramsOfWord = anagrams.getSubAnagramsOfWord(chosenWord);
			        subAnagramsOfWord.remove(chosenWord);

			        if (subAnagramsOfWord.size() > 0) {

			        	System.out.printf("The word is \"%s\".%n", chosenWord);

					    Scanner reader = new Scanner(System.in);

				        while (subAnagramsOfWord.size() > 0) {
				        	Map<Integer, List<String>> lengthBuckets = new HashMap<Integer, List<String>>();
					        for (int i = 0; i < subAnagramsOfWord.size(); i++) {
					        	String subAnagram = subAnagramsOfWord.get(i);

					        	List<String> subAnagramsWithSize = lengthBuckets.getOrDefault(subAnagram.length(), new ArrayList<>());
					        	subAnagramsWithSize.add(subAnagram);
					        	lengthBuckets.put(subAnagram.length(), subAnagramsWithSize);
					        }

					        System.out.print("There are ");

					        for(int len = chosenWord.length(); len > 1; len--) {
					        	int anagramsRemainingWithLength = lengthBuckets.getOrDefault(len, new ArrayList<>()).size();
					        	if (anagramsRemainingWithLength > 0) {
					        		System.out.printf("%s [len %s], ", anagramsRemainingWithLength, len);
					        	} else {
					        		System.out.printf("0 [len %s], ", len);
					        	}
					        }

					        int anagramsRemainingWithLength = lengthBuckets.getOrDefault(1, new ArrayList<>()).size();
				        	if (anagramsRemainingWithLength > 0) {
				        		System.out.printf("%s [len 1] anagrams remaining: ", anagramsRemainingWithLength);
				        	} else {
				        		System.out.print("0 [len 1] anagrams remaining: ");
				        	}

					        String guess = reader.next().toLowerCase();
					        if (subAnagramsOfWord.contains(guess)) {
					        	subAnagramsOfWord.remove(guess);
					        }
				        	System.out.println("There are 0 anagrams remaining.");
				        }
		        		reader.close();
			    	}
			    }
	    	}
    	}
    }
}
