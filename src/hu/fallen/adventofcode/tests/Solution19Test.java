package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution19;

public class Solution19Test {
	
	@Test
	public void test() {
		assertEquals(0, Solution19.calculate(new ArrayList<String>()));
		assertEquals(0, Solution19.calculate2(new ArrayList<String>()));
	}
	
}
