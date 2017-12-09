package hu.fallen.adventofcode.solutions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution6 {
    
    public static void printSolution() {
        int[] input = {2, 8, 8, 5, 4, 2, 3, 1, 5, 5, 1, 2, 15, 13, 5, 14};
        System.out.println(Integer.toString(calculate(input)));
        System.out.println(Integer.toString(calculate(input)));
    }
    
    public static int calculate(int[] input) {
        Set<String> history = new HashSet<String>();
        history.add(string(input));
        int size = 0;
        do {
            size = history.size();
            int index = 0;
            int max = input[index];
            for (int i = 1; i < input.length; ++i) {
                if (input[i] > max) {
                    max = input[i];
                    index = i;
                }
            }
            input[index] = 0;
            while (max > 0) {
                index = (index+1)%input.length;
                input[index]++;
                max--;
            }
            // System.out.println(string(input));
            history.add(string(input));
        } while (size != history.size());
        return size;
    }

    static String string(int[] nums) {
        return String.join(" ", Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new));
    }
}
