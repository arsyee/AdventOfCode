package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.helper.solution21.BoolArray;
import hu.fallen.adventofcode.solutions.Solution21;

public class Solution21Test {
	
	@Test
	public void test() {
		// assertEquals(0, Solution21.calculate(new ArrayList<String>()));
		assertEquals(0, Solution21.calculate2(new ArrayList<String>()));
	}
	
	@Test
	public void testInput() {
        ArrayList<String> input = new ArrayList<String>();
        input.add("../.# => ##./#../...");
        input.add(".#./..#/### => #..#/..../..../#..#");
        assertEquals(12, Solution21.calculate(input, 2));
	}

	@Test
	public void testBoolArray() {
		BoolArray a = new BoolArray("##/..");
		BoolArray b = new BoolArray("##/..");
		BoolArray c = new BoolArray("#./#.");
		BoolArray d = new BoolArray(".#/#.");
		assertEquals(a, b);
		assertEquals(a, c);
		assertEquals(b, c);
		assertNotEquals(a, d);
	}
	
	@Test
	public void testUnique2() {
		List<BoolArray> uniqueList = new ArrayList<BoolArray>();
		uniqueList.add(new BoolArray("../.."));
		uniqueList.add(new BoolArray("#./.."));
		uniqueList.add(new BoolArray("##/.."));
		uniqueList.add(new BoolArray(".#/#."));
		uniqueList.add(new BoolArray("##/#."));
		uniqueList.add(new BoolArray("##/##"));
		
		for (int i = 0; i < uniqueList.size(); ++i) {
			for (int j = 0; j < uniqueList.size(); ++j) {
				if (i == j) continue;
				assertNotEquals(uniqueList.get(i), uniqueList.get(j));
			}
		}
	}
	
	@Test
	public void testUnique3_problem() {
		List<BoolArray> uniqueList = new ArrayList<BoolArray>();

		uniqueList.add(new BoolArray(".#./#../..."));
		uniqueList.add(new BoolArray(".../#.#/..."));
		
		for (int i = 0; i < uniqueList.size(); ++i) {
			for (int j = 0; j < uniqueList.size(); ++j) {
				if (i == j) continue;
				assertNotEquals(uniqueList.get(i), uniqueList.get(j));
			}
		}
		
	}
	
	@Test
	public void testUnique3() {
		List<BoolArray> uniqueList = new ArrayList<BoolArray>();
		uniqueList.add(new BoolArray(".../.../..."));
		uniqueList.add(new BoolArray("#../.../..."));
		uniqueList.add(new BoolArray(".#./.../..."));
		uniqueList.add(new BoolArray("##./.../..."));
		uniqueList.add(new BoolArray("#.#/.../..."));
		uniqueList.add(new BoolArray("###/.../..."));
		uniqueList.add(new BoolArray(".#./#../..."));
		uniqueList.add(new BoolArray("##./#../..."));
		uniqueList.add(new BoolArray("..#/#../..."));
		uniqueList.add(new BoolArray("#.#/#../..."));
		uniqueList.add(new BoolArray(".##/#../..."));
		uniqueList.add(new BoolArray("###/#../..."));
		uniqueList.add(new BoolArray(".../.#./..."));
		uniqueList.add(new BoolArray("#../.#./..."));
		uniqueList.add(new BoolArray(".#./.#./..."));
		uniqueList.add(new BoolArray("##./.#./..."));
		uniqueList.add(new BoolArray("#.#/.#./..."));
		uniqueList.add(new BoolArray("###/.#./..."));
		uniqueList.add(new BoolArray(".#./##./..."));
		uniqueList.add(new BoolArray("##./##./..."));
		uniqueList.add(new BoolArray("..#/##./..."));
		uniqueList.add(new BoolArray("#.#/##./..."));
		uniqueList.add(new BoolArray(".##/##./..."));
		uniqueList.add(new BoolArray("###/##./..."));
		uniqueList.add(new BoolArray(".../#.#/..."));
		uniqueList.add(new BoolArray("#../#.#/..."));
		uniqueList.add(new BoolArray(".#./#.#/..."));
		uniqueList.add(new BoolArray("##./#.#/..."));
		uniqueList.add(new BoolArray("#.#/#.#/..."));
		uniqueList.add(new BoolArray("###/#.#/..."));
		uniqueList.add(new BoolArray(".../###/..."));
		uniqueList.add(new BoolArray("#../###/..."));
		uniqueList.add(new BoolArray(".#./###/..."));
		uniqueList.add(new BoolArray("##./###/..."));
		uniqueList.add(new BoolArray("#.#/###/..."));
		uniqueList.add(new BoolArray("###/###/..."));
		uniqueList.add(new BoolArray("..#/.../#.."));
		uniqueList.add(new BoolArray("#.#/.../#.."));
		uniqueList.add(new BoolArray(".##/.../#.."));
		uniqueList.add(new BoolArray("###/.../#.."));
		uniqueList.add(new BoolArray(".##/#../#.."));
		uniqueList.add(new BoolArray("###/#../#.."));
		uniqueList.add(new BoolArray("..#/.#./#.."));
		uniqueList.add(new BoolArray("#.#/.#./#.."));
		uniqueList.add(new BoolArray(".##/.#./#.."));
		uniqueList.add(new BoolArray("###/.#./#.."));
		uniqueList.add(new BoolArray(".##/##./#.."));
		uniqueList.add(new BoolArray("###/##./#.."));
		uniqueList.add(new BoolArray("#../..#/#.."));
		uniqueList.add(new BoolArray(".#./..#/#.."));
		uniqueList.add(new BoolArray("##./..#/#.."));
		uniqueList.add(new BoolArray("#.#/..#/#.."));
		uniqueList.add(new BoolArray(".##/..#/#.."));
		uniqueList.add(new BoolArray("###/..#/#.."));
		uniqueList.add(new BoolArray("#../#.#/#.."));
		uniqueList.add(new BoolArray(".#./#.#/#.."));
		uniqueList.add(new BoolArray("##./#.#/#.."));
		uniqueList.add(new BoolArray("..#/#.#/#.."));
		uniqueList.add(new BoolArray("#.#/#.#/#.."));
		uniqueList.add(new BoolArray(".##/#.#/#.."));
		uniqueList.add(new BoolArray("###/#.#/#.."));
		uniqueList.add(new BoolArray("#../.##/#.."));
		uniqueList.add(new BoolArray(".#./.##/#.."));
		uniqueList.add(new BoolArray("##./.##/#.."));
		uniqueList.add(new BoolArray("#.#/.##/#.."));
		uniqueList.add(new BoolArray(".##/.##/#.."));
		uniqueList.add(new BoolArray("###/.##/#.."));
		uniqueList.add(new BoolArray("#../###/#.."));
		uniqueList.add(new BoolArray(".#./###/#.."));
		uniqueList.add(new BoolArray("##./###/#.."));
		uniqueList.add(new BoolArray("..#/###/#.."));
		uniqueList.add(new BoolArray("#.#/###/#.."));
		uniqueList.add(new BoolArray(".##/###/#.."));
		uniqueList.add(new BoolArray("###/###/#.."));
		uniqueList.add(new BoolArray(".#./#.#/.#."));
		uniqueList.add(new BoolArray("##./#.#/.#."));
		uniqueList.add(new BoolArray("#.#/#.#/.#."));
		uniqueList.add(new BoolArray("###/#.#/.#."));
		uniqueList.add(new BoolArray(".#./###/.#."));
		uniqueList.add(new BoolArray("##./###/.#."));
		uniqueList.add(new BoolArray("#.#/###/.#."));
		uniqueList.add(new BoolArray("###/###/.#."));
		uniqueList.add(new BoolArray("#.#/..#/##."));
		uniqueList.add(new BoolArray("###/..#/##."));
		uniqueList.add(new BoolArray(".##/#.#/##."));
		uniqueList.add(new BoolArray("###/#.#/##."));
		uniqueList.add(new BoolArray("#.#/.##/##."));
		uniqueList.add(new BoolArray("###/.##/##."));
		uniqueList.add(new BoolArray(".##/###/##."));
		uniqueList.add(new BoolArray("###/###/##."));
		uniqueList.add(new BoolArray("#.#/.../#.#"));
		uniqueList.add(new BoolArray("###/.../#.#"));
		uniqueList.add(new BoolArray("###/#../#.#"));
		uniqueList.add(new BoolArray("#.#/.#./#.#"));
		uniqueList.add(new BoolArray("###/.#./#.#"));
		uniqueList.add(new BoolArray("###/##./#.#"));
		uniqueList.add(new BoolArray("#.#/#.#/#.#"));
		uniqueList.add(new BoolArray("###/#.#/#.#"));
		uniqueList.add(new BoolArray("#.#/###/#.#"));
		uniqueList.add(new BoolArray("###/###/#.#"));
		uniqueList.add(new BoolArray("###/#.#/###"));
		uniqueList.add(new BoolArray("###/###/###"));
		
		for (int i = 0; i < uniqueList.size(); ++i) {
			for (int j = 0; j < uniqueList.size(); ++j) {
				if (i == j) continue;
				assertNotEquals(i + " == " + j, uniqueList.get(i), uniqueList.get(j));
			}
		}
	}
	
}
