package edu.wofford.wordoff;

import java.util.*;

public class Feature03Main {
    public static void main(String[] args) {
    	if (args.length > 0) {
	    	Anagrams anagrams;
	    	if (args.length > 1) {
	    		String fileName = args[1];
	    		anagrams = new Anagrams(fileName);
	    	} else {
	    		anagrams = new Anagrams();
	    	}
	    	String word = args[0];
	    	List<String> anagrams_of_word = anagrams.getSubAnagramsOfWord(word);
	    	if(anagrams_of_word != null){
	        	for(int i = 0; i < anagrams_of_word.size(); i++){
	          		System.out.println(anagrams_of_word.get(i));
	        	}
	    	}
	    }
    }
}