package hu.fallen.adventofcode.helper.solution21;

// only pure functions here

public class H {

	public static int coord(int i, int j, int size) {
		return i * size + j;
	}

	public static void debug(boolean DEBUG, String message) {
		if (DEBUG) System.out.println(message);
	}
}
