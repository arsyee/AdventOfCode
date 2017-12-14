package hu.fallen.adventofcode.solutions;

public class Solution10 {

    public static boolean debug = true;
    
    public static void printSolution() {
        System.out.println(calculate(256, 63,144,180,149,1,255,167,84,125,65,188,0,2,254,229,24));
        System.out.println(calculate2(256, "63,144,180,149,1,255,167,84,125,65,188,0,2,254,229,24", 17, 31, 73, 47, 23));
    }

    static int position = 0;
    static int skip = 0;
    
    public static int calculate(int length, int... input) {
        int[] string = initString(length);
        
        boolean lastDebug = debug;
        debug = false;
        position = 0;
        skip = 0;
        hashRound(string, input);
        debug = lastDebug;
        
        return string[0]*string[1];
    }

    public static String calculate2(int length, String inputString, int... inputExtra) {
        int[] input = convertInput(inputString, inputExtra);
        int[] string = initString(length);
        position = 0;
        skip = 0;
        for (int i = 0; i < 64; ++i) {
            hashRound(string, input);
        }
        int[] dense = reduce(string, 16);
        String hexDense = convertHex(dense);
        return hexDense;
    }
    
    private static String convertHex(int[] dense) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < dense.length; ++i) {
            if (dense[i] < 16) result.append("0");
            result.append(Integer.toHexString(dense[i]));
        }
        return result.toString();
    }

    private static int[] reduce(int[] string, int groupSize) {
        int[] result = new int[string.length/groupSize]; 
        for (int i = 0; i < result.length; ++i) {
            for (int j = 0; j < groupSize; ++j) {
                result[i] ^= string[i*groupSize+j];
            }
        }
        return result;
    }

    private static void hashRound(int[] string, final int[] input) {
        int length = string.length;
        
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
    }

    private static void print(int position, int[] string) {
        if (!debug) return;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < string.length; ++i) {
            if (i > 0) result.append(" ");
            if (i == position) result.append("[");
            result.append(string[i]);
            if (i == position) result.append("]");
        }
        System.out.println(result.toString());
    }
   
    private static int[] convertInput(String inputString, int... inputExtra) {
        char[] inputChars = inputString.toCharArray();
        int[] input = new int[inputChars.length+inputExtra.length];
        if (debug) System.out.print("input: ");
        for (int i = 0; i < inputChars.length; ++i) {
            input[i] = inputChars[i];
            if (debug) System.out.print(input[i]+" ");
        }
        for (int i = 0; i < inputExtra.length; ++i) {
            input[i+inputChars.length] = inputExtra[i];
            if (debug) System.out.print(input[i+inputChars.length]+" ");
        }
        if (debug) System.out.println();
        return input;
    }

    private static int[] initString(int length) {
        int[] string = new int[length];
        for (int i = 0; i < length; ++i) {
            string[i] = i;
        }
        return string;
    }

	public static String knot(String string) {
		return calculate2(256, string, 17, 31, 73, 47, 23);
	}

}
