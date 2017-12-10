package hu.fallen.adventofcode.solutions;

public class Solution10 {

    public static void printSolution() {
        System.out.println(calculate(256, 63,144,180,149,1,255,167,84,125,65,188,0,2,254,229,24));
    }

    public static int calculate(int length, int... input) {
        int[] string = new int[length];
        for (int i = 0; i < length; ++i) {
            string[i] = i;
        }
        
        int position = 0;
        int skip = 0;
        
        print(position, string);
        for (int i = 0; i < input.length; ++i) {
            for (int j = 0; j < input[i]/2; ++j) {
                int x = (position+j)%length;
                int y = (position+input[i]-1-j)%length;
                // In general, result of modulo could be negative, if dividend is negative!
                // In our case dividend is always non-negative, because
                // either input[i] >= j+1 or input[i]/2 == 0, so there is no 'j' to enter the loop
                int temp = string[x];
                string[x] = string[y];
                string[y] = temp;
            }
            position = (position+input[i]+skip)%length;
            skip++;
            print(position, string);
        }
        
        return string[0]*string[1];
    }

    private static void print(int position, int[] string) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length; ++i) {
            if (i > 0) result.append(" ");
            if (i == position) result.append("[");
            result.append(string[i]);
            if (i == position) result.append("]");
        }
        System.out.println(result.toString());
    }
   
}
