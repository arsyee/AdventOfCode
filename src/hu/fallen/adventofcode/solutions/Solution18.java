package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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

	private static class Machine {
		Registers registers;
		ArrayList<String> input;
		int instructionPointer = 0;
		
		public MessageQueue out;
		public MessageQueue in;
		
		public boolean locked = false;
		
		public Machine(ArrayList<String> input, long id) {
			registers = new Registers();
			registers.put('p', id);
			out = new MessageQueue();
			in = new MessageQueue();
			this.input = input;
		}
		
		public void execute() {
			if (instructionPointer >= input.size()) {
				locked = true;
				return;
			}
			final String line = input.get(instructionPointer);
			long x, y;

			// System.out.print("Processing line "+instructionPointer+": "+line+": ");
			String[] tokens = line.split(" ");
			if (locked && !"rcv".equals(tokens[0])) throw new RuntimeException();
			switch (tokens[0]) {
				case "snd":
					out.add(registers.getValue(tokens[1]));
					// System.out.println("value sent: "+lastSound);
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
					Long message = in.poll();
					if (message == null) {
						locked = true;
						return; // so the instructionPointer doesn't get increased
					}
					locked = false;
					registers.put(tokens[1].charAt(0), message);
					break;
				case "jgz":
					x = registers.getValue(tokens[1]);
					y = registers.getValue(tokens[2]);
					if (x <= 0) {
						// System.out.println("jump failed");
						break;
					}
					instructionPointer = instructionPointer + (int)y;
					// System.out.println("jumping to "+instructionPointer);
					return;
			}
			++instructionPointer;
		}
	}
	
	static class MessageQueue extends LinkedList<Long> {
		private static final long serialVersionUID = 1L;
		public long counter = 0;
		@Override
		public boolean add(Long e) {
			counter++;
			return super.add(e);
		}
	}
	
    private static class Registers extends HashMap<Character, Long> {
		private static final long serialVersionUID = 1L;
		
		long lastSound = 0;

		@Override
    	public Long put(Character key, Long value) {
    		// System.out.println(key + "->" + value);
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
    
}
