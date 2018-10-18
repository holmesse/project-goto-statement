package edu.wofford.wordoff;
import java.util.*;

/**
* <h1>Feature 01</h1>
* This program implements an application that
* takes either a string or integer from the command line
* and prints out either all the anagrams of the string
* or a randomly chosen word with a number of anagrams equal to the passed integer
*/

public class Feature01Main {
    /**
    * <h2>Feature 01 Main</h2>
    * This is the main method that creates a new hashmap called anagrams
    * and calls proper functions if command line argument is string or integer
    * @param args the command line argument given: either string or integer
    */
    public static void main(String[] args) {
      Anagrams anagrams = new Anagrams();
      try{
        int numOfAnagrams = Integer.parseInt(args[0]);
        List<String> anagramsOfWord = anagrams.getNumberOfAnagrams(numOfAnagrams);
        if(anagramsOfWord != null) {
          for(int i = 0; i < anagramsOfWord.size(); i++){
            System.out.println(anagramsOfWord.get(i));
          }
        }
      }
      catch(NumberFormatException e){
          String word = args[0];
          List<String> anagrams_of_word = anagrams.getAnagramsOfWord(word);
          if(anagrams_of_word != null){
            for(int i = 0; i < anagrams_of_word.size(); i++){
              System.out.println(anagrams_of_word.get(i));
            }
          }
      }
    }
}
