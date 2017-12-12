package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Solution12 {

	static boolean debug = false;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		List<String> input = Arrays.asList(("0 <-> 2\n"+
					   					    "1 <-> 1\n"+
					   					    "2 <-> 0, 3, 4\n"+
					   					    "3 <-> 2, 4\n"+
					   					    "4 <-> 2, 3, 6\n"+
					   					    "5 <-> 6\n"+
					   					    "6 <-> 4, 5").split("\n"));
		input = Files.readAllLines(FileSystems.getDefault().getPath("res", "input.txt"), StandardCharsets.UTF_8);
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
		System.out.println(sections.get(new Integer(0)).size());
		System.out.println(sections.size());
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
