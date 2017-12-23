package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import hu.fallen.adventofcode.helper.solution18.Machine;

public class Solution23 {

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

	public static int calculate(ArrayList<String> input) {
	    Machine23 machine = new Machine23(input);
	    machine.setDebug(true);
	    while (!machine.locked) {
	        machine.execute();
	    }
		return machine.getMulCount();
	}

	public static int calculate2(ArrayList<String> input) {
		return 0;
	}

	static class Machine23 extends Machine {

	    private int mulCount = 0;
	    
        public Machine23(ArrayList<String> input) {
            super(input, 0);
        }
	    
        public int getMulCount() {
            return mulCount;
        }

        @Override
        protected void mul(String[] tokens) {
            mulCount++;
            super.mul(tokens);
        }
	}
	
}
