package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.helper.Pair;
import hu.fallen.adventofcode.solutions.Solution13;

public class Solution13Test {
    
    @Test
    public void test() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "test13.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        assertEquals(new Pair<Integer, Long>(24, 10l), Solution13.calculate(input));
    }
}
