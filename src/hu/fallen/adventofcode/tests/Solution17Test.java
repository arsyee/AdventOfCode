package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution17;

public class Solution17Test {
	
	@Test
	public void test() {
		assertEquals(180, Solution17.calculate(316));
		assertEquals(13326437, Solution17.calculate2(316));
	}
	
}
