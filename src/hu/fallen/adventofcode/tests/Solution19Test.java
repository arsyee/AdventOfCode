package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.helper.Pair;
import hu.fallen.adventofcode.solutions.Solution19;

public class Solution19Test {
	
	@Test
	public void test() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "test19.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        assertEquals(new Pair<>("ABCDEF", 38), Solution19.calculate(input));
	}
	
}
