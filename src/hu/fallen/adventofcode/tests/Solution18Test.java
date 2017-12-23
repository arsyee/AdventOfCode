package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution18;

public class Solution18Test {
	
	@Test
	public void test() {
        String mark = "18";
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input"+mark+".txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

		assertEquals(2951, Solution18.calculate(input));
		assertEquals(7366, Solution18.calculate2(input));
	}
	
}
