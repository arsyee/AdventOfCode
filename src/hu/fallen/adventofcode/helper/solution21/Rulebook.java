package hu.fallen.adventofcode.helper.solution21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rulebook {
	private static final boolean DEBUG = false;
	
	private final Map<BoolArray, BoolArray> rules;
	
	public void print() {
		if (!DEBUG) return;
		System.out.println("Rulebook contains following ("+rules.size()+") rules:");
		for (BoolArray key : rules.keySet()) {
			System.out.println(key + " : " + rules.get(key));
		}
	}

	public Rulebook(ArrayList<String> input) {
		H.debug(DEBUG, "Creating Rulebook");
		rules = new HashMap<>();
		int size = 0;
		for (String line : input) {
			H.debug(DEBUG, "Parsing "+line);
			String[] tokens = line.split(" => ");
			BoolArray key = new BoolArray(tokens[0]);
			H.debug(DEBUG, "inserting key: "+key);
			rules.put(key, new BoolArray(tokens[1]));
			if (size == rules.size()) System.out.println("\n=== DUPLICATE RULE FOUND ===\n");
			size = rules.size();
			H.debug(DEBUG, "Rulebook's new length: "+size);
		}
	}

	public BoolArray get(BoolArray key) {
		return rules.get(key);
	}

}
