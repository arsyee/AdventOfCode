package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution04 {

    public static void printSolution() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input04.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(Integer.toString(calculate(input)));
        System.out.println(Integer.toString(calculate2(input)));
    }
    
    public static int calculate(List<String> input) {
        int count = 0;
        iteratelines: for (String line : input) {
            String[] texts = line.split(" ");
            for (int i = 0; i < texts.length; ++i) {
                for (int j = i+1; j < texts.length; ++j) {
                    if (texts[i].equals(texts[j])) continue iteratelines;
                }
            }
            count++;
        }
        return count;
    }

    public static int calculate2(List<String> input) {
        int count = 0;
        iteratelines: for (String line : input) {
            String[] texts = line.split(" ");
            for (int i = 0; i < texts.length; ++i) {
                for (int j = i+1; j < texts.length; ++j) {
                    if (sort(texts[i]).equals(sort(texts[j]))) continue iteratelines;
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
