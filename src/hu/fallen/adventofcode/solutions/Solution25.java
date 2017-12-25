package hu.fallen.adventofcode.solutions;

public class Solution25 {

    public static void printSolution() {
        /*
    	String mark = new Object() {}.getClass().getEnclosingClass().getSimpleName().replace("Solution", "");
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input"+mark+".txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        */
        long start = System.currentTimeMillis();
        System.out.println(calculate());
        System.out.println("Calculated in "+(System.currentTimeMillis() - start)+"ms");
        System.out.println(calculate2());
    }

    private static final int INPUT_CYCLES = 12173597;
    private static final int CYCLES = INPUT_CYCLES; // 1000*1000;
    private static final int ORIGINAL_SIZE = 32;

    enum State { A, B, C, D, E, F }
    private static final int left = -1;
    private static final int right = 1;
    private static final boolean V0 = false;
    private static final boolean V1 = true;
    
	public static int calculate() {
	    boolean[] tape = new boolean[ORIGINAL_SIZE];
	    int position = ORIGINAL_SIZE / 2;
	    State state = State.A;
	    for (int i = 0; i < CYCLES; ++i) {
	        switch (state) {
            case A:
                if (tape[position] == V0) {
                    tape[position] = V1;
                    position += right;
                    state = State.B;
                } else {
                    tape[position] = V0;
                    position += left;
                    state = State.C;
                }
                break;
            case B:
                if (tape[position] == V0) {
                    tape[position] = V1;
                    position += left;
                    state = State.A;
                } else {
                    tape[position] = V1;
                    position += right;
                    state = State.D;
                }
                break;
            case C:
                if (tape[position] == V0) {
                    tape[position] = V1;
                    position += right;
                    state = State.A;
                } else {
                    tape[position] = V0;
                    position += left;
                    state = State.E;
                }
                break;
            case D:
                if (tape[position] == V0) {
                    tape[position] = V1;
                    position += right;
                    state = State.A;
                } else {
                    tape[position] = V0;
                    position += right;
                    state = State.B;
                }
                break;
            case E:
                if (tape[position] == V0) {
                    tape[position] = V1;
                    position += left;
                    state = State.F;
                } else {
                    tape[position] = V1;
                    position += left;
                    state = State.C;
                }
                break;
            case F:
                if (tape[position] == V0) {
                    tape[position] = V1;
                    position += right;
                    state = State.D;
                } else {
                    tape[position] = V1;
                    position += right;
                    state = State.A;
                }
                break;
	        }
	        if (position < 0 || position >= tape.length) {
	            boolean[] newtape = new boolean[tape.length * 2];
	            for (int p = 0; p < tape.length; ++p) {
	                newtape[newtape.length / 4 + p] = tape[p];
	            }
	            tape = newtape;
	            position = newtape.length / 4 + position;
	        }
	        // debug(tape, position);
	    }
		return calculateChecksum(tape);
	}

	private static int calculateChecksum(boolean[] tape) {
        int checksum = 0;
        for (int i = 0; i < tape.length; ++i) if (tape[i]) ++checksum;
        return checksum;
    }

    private static void debug(boolean[] tape, int p) {
	    for (int i = 0; i < 64 - tape.length / 2; ++i) {
	        System.out.print(" ");
	    }
        for (int i = 0; i < tape.length; ++i) {
            System.out.print(tape[i] ? (i == p ? "1" : "|") : (i == p ? "0" : "."));
        }
        System.out.println();
    }

    public static int calculate2(/*ArrayList<String> input*/) {
		return 0;
	}

}
