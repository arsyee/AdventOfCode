package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution24;

public class Solution24Test {
	
	@Test
	public void test() {
		assertEquals(0, Solution24.calculate(new ArrayList<String>()));
		assertEquals(0, Solution24.calculate2(new ArrayList<String>()));
	}
	
}
