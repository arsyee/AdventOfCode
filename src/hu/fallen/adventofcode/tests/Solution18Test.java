package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution18;

public class Solution18Test {
	
	@Test
	public void test() {
		assertEquals(0, Solution18.calculate(new ArrayList<String>()));
		assertEquals(0, Solution18.calculate2(new ArrayList<String>()));
	}
	
}
