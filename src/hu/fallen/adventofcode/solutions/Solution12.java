package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import hu.fallen.adventofcode.helper.Pair;

public class Solution12 {

	static boolean debug = false;
	
    public static void printSolution() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input12.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(calculate(input));
    }

	public static Pair<Integer, Integer> calculate(List<String> input) {
		HashMap<Integer, HashSet<Integer>> sections = parse(input);
		int size = 0;
		eliminate: do {
			print(sections);
			size = sections.size();
			for (Entry<Integer, HashSet<Integer>> section : sections.entrySet()) {
				for (Integer value : section.getValue()) {
					if (!section.getKey().equals(value) && sections.keySet().contains(value)) {
						System.out.println("moving "+value+" to "+section.getKey());
						section.getValue().addAll(sections.get(value));
						sections.remove(value);
						continue eliminate;
					}
				}
			}
		} while (size > sections.size());
		return new Pair<Integer, Integer>(sections.get(new Integer(0)).size(), sections.size());
	}

	private static HashMap<Integer, HashSet<Integer>> parse(List<String> lines) {
		HashMap<Integer, HashSet<Integer>> result = new HashMap<Integer, HashSet<Integer>>();
		for (String line : lines) {
			String[] setDef = line.split(" <-> ");
			Integer key = Integer.parseInt(setDef[0]);
			String[] valueSetString = setDef[1].split(", ");
			HashSet<Integer> values = new HashSet<Integer>();
			for (String valueString : valueSetString) {
				Integer value = Integer.parseInt(valueString);
				values.add(value);
			}
			result.put(key, values);
		}
		return result;
	}

	private static void print(HashMap<Integer, HashSet<Integer>> sections) {
		if (!debug) return;
		System.out.println("print entered:");
		for (Entry<Integer, HashSet<Integer>> section : sections.entrySet()) {
			System.out.print(section.getKey()+" <-> " );
			for (Integer i : section.getValue()) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
		
	}

}
