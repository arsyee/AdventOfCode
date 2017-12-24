package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.helper.Pair.IntPair;
import hu.fallen.adventofcode.solutions.Solution24;

public class Solution24Test {
	
	@Test
	public void test() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "test24.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
		assertEquals(31, Solution24.calculate(input));
		assertEquals(new IntPair(4, 19), Solution24.calculate2(input));
	}
	
    @Test
    public void test_1() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "test24_1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        assertEquals(new IntPair(5, 24), Solution24.calculate2(input));
    }
    
}
