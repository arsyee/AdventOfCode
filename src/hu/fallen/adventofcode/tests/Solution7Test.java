package hu.fallen.adventofcode.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import org.junit.Test;

import hu.fallen.adventofcode.solutions.Solution7;

public class Solution7Test {
    
    @Test
    public void test() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "test7.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        assertEquals("tknk", Solution7.calculate(input));
        assertEquals(60, Solution7.calculate2(input));
    }

}
