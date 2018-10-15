package edu.wofford.wordoff;
import java.util.*;

public class Feature01Main {
    public static void main(String[] args) {
      //do if int passed
      //handle null pointer exception
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
