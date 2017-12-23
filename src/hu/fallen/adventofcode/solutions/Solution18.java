package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import hu.fallen.adventofcode.helper.solution18.Machine;
import hu.fallen.adventofcode.helper.solution18.Registers;

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
		int instructionPointer = 0;
		long x, y;
		while (instructionPointer < input.size()) {
			final String line = input.get(instructionPointer);
			// System.out.print("Processing line "+instructionPointer+": "+line+": ");
			String[] tokens = line.split(" ");
			switch (tokens[0]) {
				case "snd":
					lastSound = registers.getValue(tokens[1]);
					// System.out.println("value saved: "+lastSound);
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
						// System.out.println("division by zero: "+line);
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
						// System.out.println("jump failed");
						break;
					}
					instructionPointer = instructionPointer + (int)y;
					// System.out.println("jumping to "+instructionPointer);
					continue;
			}
			++instructionPointer;
		}
		throw new RuntimeException();
	}
	
	public static long calculate2(ArrayList<String> input) {
		Machine machine0 = new Machine(input, 0l);
		Machine machine1 = new Machine(input, 1l);
		Long message = null;
		while (true) {
			message = machine0.out.poll();
			if (message != null) machine1.in.add(message);
			message = machine1.out.poll();
			if (message != null) machine0.in.add(message);
			machine0.execute();
			machine1.execute();
			if (machine0.locked && machine1.locked) return machine1.out.counter;
		}
	}
    
}
