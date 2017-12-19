package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution21;

public class Solution21Test {
	
	@Test
	public void test() {
		assertEquals(0, Solution21.calculate(new ArrayList<String>()));
		assertEquals(0, Solution21.calculate2(new ArrayList<String>()));
	}
	
}
