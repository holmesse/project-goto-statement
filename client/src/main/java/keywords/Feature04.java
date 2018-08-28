package keywords;


import java.io.*;
import java.util.*;

import edu.wofford.wordoff.*;


public class Feature04 {
    private String clarg;
    private String outputString;

    public void theProgramIsStartedWith(String s) {
        clarg = s;
    }
    
    public void theUserGuessesAllSubanagramsOfTheTargetWord(String anagrams) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        try {
            anagrams = anagrams.replace(" ", "\n");
            System.setIn(new ByteArrayInputStream(anagrams.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            String[] args = {clarg};
            Feature04Main.main(args);
            outputString = outContent.toString();
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
    
    public boolean didTheProgramEndSuccessfully() {
        if (clarg == null || clarg.length() == 0) {
            return outputString.length() == 0;
        } else {
            try {
                int n = Integer.parseInt(clarg);
                if (n < 3) {
                    return outputString.length() == 0;
                } else {
                    return outputString.contains("0 anagrams remaining");
                }
            } catch (NumberFormatException e) {
                return outputString.contains("0 anagrams remaining");
            }
        }
    }
}
