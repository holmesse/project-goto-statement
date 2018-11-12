package keywords;


import java.io.*;
import java.util.*;

import edu.wofford.wordoff.*;


public class Feature06 {
    public List<String> getGuessesForWord(String word) {
        Anagrams anagram_list;
        anagram_list = new Anagrams("commonwords.txt");
        List<String> anagrams = anagram_list.getAnagramsOfWord(word);
        anagrams.remove(word);
        return anagrams;
    }
}
