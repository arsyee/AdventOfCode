package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution22;

public class Solution22Test {
	
	@Test
	public void test() {
		ArrayList<String> input = new ArrayList<String>();
		input.add("..#");
        input.add("#..");
        input.add("...");
        assertEquals(41, Solution22.calculate(input, 70));
        assertEquals(5587, Solution22.calculate(input, 10000));
		assertEquals(26, Solution22.calculate2(input, 100));
        assertEquals(2511944, Solution22.calculate2(input, 10000000));
	}
	
}
