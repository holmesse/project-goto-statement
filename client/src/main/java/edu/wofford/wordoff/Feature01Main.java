package edu.wofford.wordoff;
import java.util.*;

/**
*This Feature01 program implements an application that
*takes either a string or integer from the command line
*and prints out either all the anagrams of the String
*or a randomly chosen word with a number of anagrams equal to the passed integer
*@author David Slusher and Lindsey Goan
*@version 1.0
*@since 10/15/18
*/

public class Feature01Main {
    /**
    *This is the main method that creates a new hashmap called anagrams
    *and calls proper functions if command line argument is string or integer
    *@param args the command line argument given: either string or integer
    *@return nothing
    *@exception NumberFormatException if command line argument is a string and cannot be parsed as an integer
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
