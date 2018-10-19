package edu.wofford.wordoff;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		if (args.length > 0) {
			if (args[0].equals("feature01")) {
				Feature01Main.main(Arrays.copyOfRange(args, 1, args.length));
			} else if (args[0].equals("feature02")) {
				Feature02Main.main(Arrays.copyOfRange(args, 1, args.length));
			}
		} else {
			System.out.println("Running Feature 01 with input \"sleep\".");
			System.out.println("OUTPUT:");
			Feature01Main.main(new String[]{"sleep"});

			System.out.print("\n");

			// System.out.println("Running Feature 01 with input \"epels\".");
			// System.out.println("OUTPUT:");
			// Feature01Main.main(new String[]{"epels"});

			// System.out.print("\n");

			System.out.println("Running Feature 01 with input \"6\".");
			System.out.println("OUTPUT:");
			Feature01Main.main(new String[]{"6"});

			System.out.print("\n");
			
			System.out.println("Running again with same input to show randomness.");
			System.out.println("OUTPUT:");
			Feature01Main.main(new String[]{"6"});

			System.out.print("\n");

			// System.out.println("Running Feature 01 with input \"0\".");
			// System.out.println("OUTPUT:");
			// Feature01Main.main(new String[]{"0"});

			// System.out.print("\n");

			System.out.println("Running Feature 02 with input \"6\" and random seed \"25\".");
			System.out.println("OUTPUT:");
			Feature02Main.main(new String[]{"6", "25"});
		}
	}
}
