package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution20;

public class Solution20Test {
	
	@Test
	public void test() {
	    ArrayList<String> input = new ArrayList<String>();
	    input.add("p=< 3,0,0>, v=< 2,0,0>, a=<-1,0,0>");
	    input.add("p=< 4,0,0>, v=< 0,0,0>, a=<-2,0,0>");
	    
		assertEquals(0, Solution20.calculate(input));
	}
	
    @Test
    public void test2() {
        ArrayList<String> input = new ArrayList<String>();
        input.add("p=<-6,0,0>, v=< 3,0,0>, a=< 0,0,0>");
        input.add("p=<-4,0,0>, v=< 2,0,0>, a=< 0,0,0>");
        input.add("p=<-2,0,0>, v=< 1,0,0>, a=< 0,0,0>");
        input.add("p=< 3,0,0>, v=<-1,0,0>, a=< 0,0,0>");
        
        assertEquals(1, Solution20.calculate2(input));
    }
}
