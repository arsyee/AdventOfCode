package hu.fallen.adventofcode.helper.solution18;

import java.util.ArrayList;

public class Machine {
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