package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class Solution04 {

    public static void printSolution() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input04.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(Integer.toString(calculate(input, (a, b) -> a.equals(b))));
        System.out.println(Integer.toString(calculate(input, (a, b) -> sort(a).equals(sort(b)))));
    }
    
    public static int calculate(List<String> input, BiFunction<String, String, Boolean> compare) {
        int count = 0;
        iteratelines: for (String line : input) {
            String[] texts = line.split(" ");
            for (int i = 0; i < texts.length; ++i) {
                for (int j = i+1; j < texts.length; ++j) {
                    if (compare.apply(texts[i], texts[j])) continue iteratelines;
                }
            }
            count++;
        }
        return count;
    }

    static String sort(String word) {
        char[] temp = word.toCharArray();
        Arrays.sort(temp);
        return new String(temp);
    }
    
}
