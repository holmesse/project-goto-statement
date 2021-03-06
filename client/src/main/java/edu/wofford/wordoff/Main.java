package edu.wofford.wordoff;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		if (args.length > 0) {
			if (args[0].equals("feature01")) {
				Feature01Main.main(Arrays.copyOfRange(args, 1, args.length));
			} else if (args[0].equals("feature02")) {
				Feature02Main.main(Arrays.copyOfRange(args, 1, args.length));
			} else if (args[0].equals("feature03")) {
				Feature03Main.main(Arrays.copyOfRange(args, 1, args.length));
			} else if (args[0].equals("feature04")) {
				Feature04Main.main(Arrays.copyOfRange(args, 1, args.length));
			} else if (args[0].equals("feature05")) {
				Feature05Main.main(Arrays.copyOfRange(args, 1, args.length));
		 	} else if (args[0].equals("feature06")) {
				Feature06Main.main(Arrays.copyOfRange(args, 1, args.length));
			} else if (args[0].equals("feature07")) {
		 		Feature07Main.main(Arrays.copyOfRange(args, 1, args.length));
		 	} else if (args[0].equals("feature08")) {
		 		Feature08Main.main(Arrays.copyOfRange(args, 1, args.length));
		 	}
		} else {
			System.out.println("Running Feature 01 with input \"sleep\".");
			System.out.println("OUTPUT:");
			Feature01Main.main(new String[]{"sleep"});

			System.out.println("");

			System.out.println("Running Feature 01 with input \"6\".");
			System.out.println("OUTPUT:");
			Feature01Main.main(new String[]{"6"});

			System.out.println("");

			System.out.println("Running again with same input to show randomness.");
			System.out.println("OUTPUT:");
			Feature01Main.main(new String[]{"6"});

			System.out.println("");

			// System.out.println("Running Feature 02 with input \"6\" and random seed \"25\".");
			// System.out.println("[skater, staker, strake, streak, takers, tasker]");
			// System.out.println("OUTPUT:");
			// Feature02Main.main(new String[]{"6", "25"});

			System.out.println("Skipping Feature 02 because of scanner conflict.");

			System.out.println("");

			System.out.println("Running Feature 03 with input \"tell\".");
			System.out.println("OUTPUT:");
			Feature03Main.main(new String[]{"tell"});

			System.out.println("");

			System.out.println("Runnning Feature 04 with input \"4\" and random seed \"25\".");
			System.out.println("[age, gad, eg]");
			System.out.println("OUTPUT:");
			Feature04Main.main(new String[]{"4", "25"});

			System.out.println("");

			System.out.println("Running Feature 05 with difficulty \"4\" and random seed \"25\".");
			System.out.println("[items, emits, mites, times]");
			Feature05Main.main(new String[]{"4", "25"});

			System.out.println("");

			System.out.println("Running Feature 06 with difficulty \"1\" and random seed \"25\".");
			Feature06Main.main(new String[]{"1", "25"});
		}
	}
}
