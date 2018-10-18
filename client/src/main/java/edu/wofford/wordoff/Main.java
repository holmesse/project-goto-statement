package edu.wofford.wordoff;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		if (args[0].equals("feature01")) {
			Feature01Main.main(Arrays.copyOfRange(args, 1, args.length));
		} else if (args[0].equals("feature02")) {
			Feature02Main.main(Arrays.copyOfRange(args, 1, args.length));
		}
	}
}
