package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Solution2 {

    public static void printSolution() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(Integer.toString(calculate(input)));
        System.out.println(Integer.toString(calculate2(input)));
    }
    
    public static int calculate(List<String> input) {
        int sum = 0;
        for (String line : input) {
            String[] texts = line.split(" ");
            if (texts.length == 0) continue;
            int max = Integer.parseInt(texts[0]);
            int min = max;
            for (String text : texts) {
                int val = Integer.parseInt(text);
                if (val > max) max = val;
                if (val < min) min = val;
            }
            sum += max - min;
        }
        return sum;
    }

    public static int calculate2(List<String> input) {
        int sum = 0;
        iteratelines: for (String line : input) {
            String[] texts = line.split(" ");
            if (texts.length == 0) continue;
            int[] nums = new int[texts.length];
            for (int i = 0; i < texts.length; ++i) {
                nums[i] = Integer.parseInt(texts[i]);
            }
            for (int i = 0; i < nums.length; ++i) {
                for (int j = 0; j < nums.length; ++j) {
                    if (i != j && nums[j] != 0 && nums[i] % nums[j] == 0) {
                        sum += nums[i] / nums[j];
                        continue iteratelines;
                    }
                }
            }
        }
        return sum;
    }

}
