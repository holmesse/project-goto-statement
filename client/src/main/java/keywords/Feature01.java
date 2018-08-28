package keywords;


import java.io.*;
import java.util.*;

import edu.wofford.wordoff.*;


public class Feature01 {
    private String outputString;

    public void theProgramIsStartedWith(String s) {
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            String[] args = {s};
            Feature01Main.main(args);
            outputString = outContent.toString();
        } finally {
            System.setOut(originalOut);
        }
    }
    
    public String getOutputAsSingleLine() {
        StringBuilder buffer = new StringBuilder();
        Scanner scanner = new Scanner(outputString);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.length() > 0) {
                buffer.append(line + " ");
            }
        }
        return buffer.toString().trim();
    }
    
    public boolean theStringShouldContainExactlyOneOfOptions(String s, String options) {
        List<String> original = Arrays.asList(s.split(" "));
        String[] parts = options.split(" ");
        int count = 0;
        for (String p : parts) {
            if (original.contains(p)) {
                count++;
            }
        }
        return count == 1;
    }
}