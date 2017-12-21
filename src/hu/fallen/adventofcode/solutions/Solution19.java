package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import hu.fallen.adventofcode.helper.Pair;
import hu.fallen.adventofcode.helper.Pair.IntPair;

public class Solution19 {
	
	private static final boolean DEBUG = true;

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
    }

    public enum Dir {U, D, L, R};

    static Dir dir = null;
    static IntPair pos = null;
    static char[][] map;
    static int steps = 0;
    
	public static Pair<String, Integer> calculate(ArrayList<String> input) {
		parseMap(input);
		print();
		
		StringBuilder route = new StringBuilder();
		dir = Dir.D;
		pos = findEntrance();
		
		do {
			switch (value()) {
			case '|':
			case '-':
				step();
				break;
			case '+':
				turn();
				break;
			default:
				route.append(value());
				step();
				break;
			}
		} while (pos != null);
		
		return new Pair<>(route.toString(), steps);
	}

	private static void turn() {
		steps++;
		int i = ((Integer)pos.get(0)).intValue();
		int j = ((Integer)pos.get(1)).intValue();
		switch (dir) {
		case U:
		case D:
			pos = getNewpos(i, j-1);
			dir = Dir.L;
			if (pos != null) break;
			pos = getNewpos(i, j+1);
			dir = Dir.R;
			break;
		case L:
		case R:
			pos = getNewpos(i-1, j);
			dir = Dir.U;
			if (pos != null) break;
			pos = getNewpos(i+1, j);
			dir = Dir.D;
			break;
		}
		debug("Turned to "+pos);
	}

	private static void step() {
		steps++;
		int i = ((Integer)pos.get(0)).intValue();
		int j = ((Integer)pos.get(1)).intValue();
		switch (dir) {
		case U:
			--i;
			break;
		case D:
			++i;
			break;
		case L:
			--j;
			break;
		case R:
			++j;
			break;
		}
		pos = getNewpos(i, j);
		debug("Stepped to "+pos);
	}

	public static IntPair getNewpos(int i, int j) {
		IntPair newpos = new IntPair(i, j);
		try {
			if (value(newpos) == ' ') { // route ends
				return null;
			} else {
				return newpos;
			}
		} catch (Exception e) { // we left the map
			return null;
		}
	}
	
	private static char value() {
		return value(pos);
	}	
	
	private static char value(IntPair pos) {
		int i = ((Integer)pos.get(0)).intValue();
		int j = ((Integer)pos.get(1)).intValue();
		return map[i][j];
	}

	private static IntPair findEntrance() {
		int x = -1;
		for (int j = 0; j < map[0].length; ++j) {
			if (map[0][j] == ' ') continue;
			if (map[0][j] == '|' && x == -1) {
				x = j;
				continue;
			}
			throw new RuntimeException();
		}
		if (x == -1) throw new RuntimeException();
		debug("Found entrance: 0,"+x);
		return new IntPair(0, x);
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[i].length; ++j) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		debug(sb.toString());
	}

	private static void parseMap(ArrayList<String> input) {
		map = new char[input.size()][];
		for (int i = 0; i < input.size(); ++i) {
			map[i] = new char[input.get(i).length()];
			for (int j = 0; j < input.get(i).length(); ++j) {
				map[i][j] = input.get(i).charAt(j);
			}
		}
	}

	private static void debug(String string) {
		if (DEBUG) System.out.println(string);
	}

}
