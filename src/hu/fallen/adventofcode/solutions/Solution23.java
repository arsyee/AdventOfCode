package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.math.BigInteger;
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
        //System.out.println(calculate2m(input));
        System.out.println(calculate3());
    }

	public static int calculate(ArrayList<String> input) {
	    Machine23 machine = new Machine23(input);
	    while (!machine.locked) {
	        machine.execute();
	    }
		return machine.getMulCount();
	}

    public static int calculate2m(ArrayList<String> input) {
        Machine23_2 machine = new Machine23_2(input);
        machine.getRegisters().put('a', 1l);
        while (!machine.locked) {
            machine.execute();
        }
        return machine.getMulCount();
    }

	// 914 is too low
	public static long calculate3() {
	    int counter = 0;
	    final int original = 57 * 100 + 100000;

        for (int n = 0; n <= 1000; ++n) {
            // boolean increment = false;
            int number = original + 17 * n;
            if (!BigInteger.valueOf(number).isProbablePrime(100000)) counter++;
            /*
	        for (int i = 2; i < number; ++i) {
	            for (int j = 2; j < number; ++j) {
	                if (i * j == number) increment = true; // count numbers, which are not primes
	            }
	        }
	        if (increment) ++counter;
	        */
	    }
	    
	    return counter;
	}

    public static long calculate2() {
        int a = 0; int b = 0; int c = 0; int d = 0; int e = 0; int f = 0; int g = 0; int h = 0;
        a = 1;
        // init
        b = 57;
        c = b;
        if (a != 0) { // if not debug, init b and c with some huge values
            b = b * 100 + 100000;
            c = b + 17000;
        }
        // loop
        do { // sub 9
            f = 1;
            d = 2;
            do { // sub 11
                e = 2;
                do { // sub 12
                    g = d * e - b;
                    if (g != 0) {
                        f = 0;
                    }
                    e++;
                    g = e - b;
                } while (g != 0); // jumps if g is not 0
                d++;
                g = d - b;
            } while (g != 0); // jumps if g is not 0
            if (f == 0) ++h;
            g = b - c;
            if (g == 0) break;
            b += 17;
        } while (true); // always jumps
        return h;
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
	
	static class Machine23_2 extends Machine23 {
        public Machine23_2(ArrayList<String> input) {
            super(input);
        }
        
        @Override
        public void execute() {
            if (instructionPointer == 15) {
                System.out.println(registers.getValue("b")+" = "+registers.getValue("d")+"*"+registers.getValue("e"));
            }
            super.execute();
        }
	}
}
