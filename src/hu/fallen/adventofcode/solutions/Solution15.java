package hu.fallen.adventofcode.solutions;

import java.util.function.Function;

public class Solution15 {

	public static final int BITS_16 = 65536;
	
	public static final int FACTOR_A = 16807;
	public static final long CRITERIA_A = 4;
	public static final int FACTOR_B = 48271;
	public static final long CRITERIA_B = 8;
	
    public static final long SAMPLES_VALID = 5l*1000l*1000l;
	public static final long SAMPLES = 40l*1000l*1000l;
	
	static boolean debug = true;
    
    public static void printSolution() {
        System.out.println(Solution15.calculate(873, 583));
        System.out.println(Solution15.calculate2(873, 583));
    }

    public static long calculate(long prevA, long prevB) {
    	return judge(new Generator(FACTOR_A, prevA, CRITERIA_A), new Generator(FACTOR_B, prevB, CRITERIA_B), SAMPLES, a -> a.getNext());
    }

    public static long calculate2(long prevA, long prevB) {
    	return judge(new Generator(FACTOR_A, prevA, CRITERIA_A), new Generator(FACTOR_B, prevB, CRITERIA_B), SAMPLES_VALID, a -> a.getNextValid());
    }

    public static long judge(Generator A, Generator B, long samples, Function<Generator, Long> function) {
    	long count = 0;
    	for (long i = 0l; i < samples; ++i) {
    		if (function.apply(A)%BITS_16 == function.apply(B)%BITS_16) ++count;
    	}
    	return count;
    }
    
    public static class Generator {
    	private static final int GROUP = 2147483647;
    	
		long factor;
    	long previous;
    	long criteria;
    	
    	public Generator(long factor, long previous, long criteria) {
    		this.factor = factor;
    		this.previous = previous;
    		this.criteria = criteria;
    	}
    	
    	public long getNext() {
    		previous = previous * factor % GROUP;
    		return previous;
    	}
    	
    	public long getNextValid() {
    		while (getNext() % criteria != 0);
    		return previous;
    	}
    }
    
}
