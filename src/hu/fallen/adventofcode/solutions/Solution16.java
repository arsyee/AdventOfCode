package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Solution16 {

    public static void printSolution() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input16.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(calculate(input.get(0)));
        long start = System.nanoTime();
        System.out.println(calculateRepeat(input.get(0), 1000));
        long end = System.nanoTime();
        System.out.println((end-start));
    }

	public static String calculate(String string) {
		return calculate(string, 16);
	}

	public static String calculate(String string, int length) {
		return calculateRepeat(string, length, 1);
	}

	public static String calculateRepeat(String string, int repetition) {
		return calculateRepeat(string, 16, repetition);
	}

	public static String calculateRepeat(String string, int length, int repetition) {
		// System.out.println("calculateRepeat entered: "+string+", "+length+", "+repetition);
		char[] position = prepare(length);
		List<Command> commands = buildCommandList(string.split(","));
		for (int r = 0; r < repetition; ++r) {
			for (Command command : commands) {
				command.execute(position);
			}
			// System.out.println("  Position after "+(r+1)+" repetitions: "+new String(position));
		}
		return new String(position);
	}

	public static void calculateRaw(char[] position, String string, int length) {
		List<Command> commands = buildCommandList(string.split(","));
		for (Command command : commands) {
			command.execute(position);
		}
	}

	public static List<Command> buildCommandList(String[] commandsStr) {
		List<Command> commands = new ArrayList<Command>();
		for (String command : commandsStr) {
			if (command.equals("")) continue;
			switch (command.charAt(0)) {
				case 's':
					int X = Integer.parseInt(command.substring(1));
					commands.add(new SpinCommand(X));
					break;
				case 'x':
					String[] swaps = command.substring(1).split("/");
					int A = Integer.parseInt(swaps[0]);
					int B = Integer.parseInt(swaps[1]);
					commands.add(new SwapCommand(A, B));
					break;
				case 'p':
					char a = command.charAt(1);
					char b = command.charAt(3);
					commands.add(new PartnerCommand(a, b));
					break;
			}
		}
		return commands;
	}

	private static abstract class Command {
		abstract void execute(char[] position);
	}
	
	private static class SpinCommand extends Command {
		private final int X;
		SpinCommand(int X) {
			this.X = X;
		}
		@Override
		void execute(char[] position) {
			spin(position, X);
		}
	}
	
	private static class SwapCommand extends Command {
		private final int A, B;
		SwapCommand(int A, int B) {
			this.A = A;
			this.B = B;
		}
		@Override
		void execute(char[] position) {
			swap(position, A, B);			
		}
	}
	
	private static class PartnerCommand extends Command {
		private final char a, b;
		PartnerCommand(char a, char b) {
			this.a = a;
			this.b = b;
		}
		@Override
		void execute(char[] position) {
			partner(position, a, b);			
		}
	}
	
	private static void spin(char[] position, int x) {
		int len = position.length;
		char[] newPosition = new char[len];
		for (int i = 0; i < len; ++i) {
			newPosition[i] = position[(i-x+len)%len];
		}
		for (int i = 0; i < len; ++i) {
			position[i] = newPosition[i];
		}
	}

	private static void swap(char[] position, int A, int B) {
		char temp = position[A];
		position[A] = position[B];
		position[B] = temp;
	}
	
	private static void partner(char[] position, char a, char b) {
		swap(position, find(position, a), find(position, b));
	}
	
	private static int find(char[] position, char a) {
		for (int i = 0;  i < position.length; ++i) {
			if (position[i] == a) return i;
		}
		return -1;
	}
	
	public static char[] prepare(int length) {
		char[] position = new char[length];
		for (char i = 0; i < length; ++i) {
			position[i] = (char) ('a'+i);
		}
		return position;
	}
    
}
