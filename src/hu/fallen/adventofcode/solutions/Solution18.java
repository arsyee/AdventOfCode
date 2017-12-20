package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution18 {

    public static void printSolution() {
    	String mark = new Object() {}.getClass().getEnclosingClass().getSimpleName().replace("Solution", "");
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input"+mark+".txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(calculate(input));
        System.out.println(calculate2(input));
    }

	public static long calculate(ArrayList<String> input) {
		Registers registers = new Registers();
		long lastSound = 0;
		int i = 0;
		long x, y;
		while (i < input.size()) {
			final String line = input.get(i);
			System.out.print("Processing line "+i+": "+line+": ");
			String[] tokens = line.split(" ");
			switch (tokens[0]) {
				case "snd":
					lastSound = registers.getValue(tokens[1]);
					System.out.println("value saved: "+lastSound);
					break;
				case "set":
					y = registers.getValue(tokens[2]);
					registers.put(tokens[1].charAt(0), y);
					break;
				case "add":
					x = registers.getValue(tokens[1]);
					y = registers.getValue(tokens[2]);
					registers.put(tokens[1].charAt(0), x + y);
					break;
				case "mul":
					x = registers.getValue(tokens[1]);
					y = registers.getValue(tokens[2]);
					registers.put(tokens[1].charAt(0), x * y);
					break;
				case "mod":
					x = registers.getValue(tokens[1]);
					y = registers.getValue(tokens[2]);
					if (y == 0) {
						System.out.println("division by zero: "+line);
						break;
					}
					registers.put(tokens[1].charAt(0), x % y);
					break;
				case "rcv":
					x = registers.getValue(tokens[1]);
					if (x == 0) {
						System.out.println("rcv failed");
						break;
					}
					if (lastSound != 0) return lastSound;
				case "jgz":
					x = registers.getValue(tokens[1]);
					y = registers.getValue(tokens[2]);
					if (x <= 0) {
						System.out.println("jump failed");
						break;
					}
					i = i + (int)y;
					System.out.println("jumping to "+i);
					continue;
			}
			++i;
		}
		throw new RuntimeException();
	}

    private static class Registers extends HashMap<Character, Long> {
		private static final long serialVersionUID = 1L;
		
		long lastSound = 0;

		@Override
    	public Long put(Character key, Long value) {
    		System.out.println(key + "->" + value);
    		return super.put(key, value);
    	}
		
		public long getValue(String string) {
			try {
				return Long.parseLong(string);
			} catch (Exception e) { }
			try {
				return get(string.charAt(0));
			} catch (Exception e) { }
			return 0;
		}
    }
    
	public static int calculate2(ArrayList<String> input) {
		return 0;
	}

}
