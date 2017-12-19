package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution25;

public class Solution25Test {
	
	@Test
	public void test() {
		assertEquals(0, Solution25.calculate(new ArrayList<String>()));
		assertEquals(0, Solution25.calculate2(new ArrayList<String>()));
	}
	
}
