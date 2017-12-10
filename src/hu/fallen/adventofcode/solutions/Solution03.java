package hu.fallen.adventofcode.solutions;

public class Solution03 {

    public static void printSolution() {
        System.out.println(Integer.toString(calculate(265149)));
        System.out.println(Integer.toString(findNext(265149)));
    }
    
    static class MyArray {
        int[][] memory = {{1}};
        int x, y = 0;
        int position = 1;
        int result = 1;
        
        int getNext() {
            if (oddSquare(position)) reSize();
            System.out.println("Position: "+x+", "+y);
            if (y+1 < memory.length && memory[x][y+1] == 0) {
                fill(x, y+1);
            } else if (x > 0 && memory[x-1][y] == 0) {
                fill(x-1, y);
            } else if (y > 0 && memory[x][y-1] == 0) {
                fill(x, y-1);
            } else if (x+1 < memory.length && memory[x+1][y] == 0) {
                fill(x+1, y);
            } else {
                System.out.println("ERROR!");
            }
            print();
            return memory[x][y];
        }

        void fill(int i, int j) {
            System.out.println("Filling "+i+", "+j);
            position++;
            if (i+1 < memory.length) {
                                         memory[i][j] += memory[i+1][j];
                if (j+1 < memory.length) memory[i][j] += memory[i+1][j+1];
                if (j > 0)               memory[i][j] += memory[i+1][j-1];
            }
            if (i > 0) {
                                         memory[i][j] += memory[i-1][j];
                if (j+1 < memory.length) memory[i][j] += memory[i-1][j+1];
                if (j > 0)               memory[i][j] += memory[i-1][j-1];
            }
            if (j+1 < memory.length)     memory[i][j] += memory[i][j+1];
            if (j > 0)                   memory[i][j] += memory[i][j-1];
            x = i;
            y = j;
        }
        
        void reSize() {
            int[][] newMemory = new int[memory.length+2][memory.length+2];
            for (int i = 0; i < memory.length; ++i) {
                for (int j = 0; j < memory.length; ++j) {
                    newMemory[i+1][j+1] = memory[i][j];
                }
            }
            memory = newMemory;
            x++; y++;
            print();
        }
        
        void print() {
            System.out.print("*");
            for (int i = 0; i < memory.length; ++i) {
                System.out.print("--------");
            }
            System.out.println("*");
            for (int i = 0; i < memory.length; ++i) {
                System.out.print("|");
                for (int j = 0; j < memory.length; ++j) {
                    System.out.print(pad(memory[i][j]));
                }
                System.out.println("|");
            }
            System.out.print("*");
            for (int i = 0; i < memory.length; ++i) {
                System.out.print("--------");
            }
            System.out.println("*");            
        }

        static String pad(int num) {
            StringBuilder builder = new StringBuilder(8);
            builder.append(num);
            int length = 6-builder.toString().length();
            for (int i = 0; i <= length; ++i) builder.insert(0, " ");
            builder.append(" ");
            return builder.toString();
        }
        
        static boolean oddSquare(int i) {
            int root = (int) Math.sqrt(i);
            return root % 2 != 0 && root*root == i;
        }
    }
    
    private static int findNext(int i) {
        MyArray A = new MyArray();
        int value = 1;
        while (value < i) value = A.getNext();
        return value;
    }

    public static int calculate(int input) {
        // System.out.println("calculate entered: " + input);        
        int length = (int) Math.sqrt(input);
        length += (length % 2 == 0) ? 1 : (length*length == input ? 0 : 2);
        
        int lastCorner = length * length;
        int distCorner = length == 1 ? 0 : (lastCorner - input) % (length - 1);
        if (distCorner > length / 2) distCorner = length - distCorner - 1;
        return length - 1 - distCorner;
        
        /*
        int dist = length - 1;
        System.out.println("  setting dist to: " + dist);
        int[] corners = new int[4];
        corners[3] = length * length;
        for (int i = 2; i >= 0; --i) {
            corners[i] = corners[i+1] - length + 1;
            if (input > corners[i] || i == 0) {
                int distToCorner = Math.abs(input - corners[i]);
                if (distToCorner > length / 2) {
                    distToCorner = length - distToCorner - 1;
                }
                // System.out.println("  substracting "+distToCorner+
                //        " because corners["+i+"]="+corners[i]);
                if (distToSomeCorner != distToCorner) System.out.println("error");
                dist -= distToCorner;
                break;
            }
        }
        return dist;
        */
    }

    /*
     * Layer 1  :            1   ->    1
     * Layer 2  :            2   ->    9
     * Layer 3  :           10   ->   25
     * Layer 4  :           26   ->   49
     * Layer 5  :           50   ->   81
     * Layer n-1:   (2n-2)^2+1   ->   (2n-1)^2
     * Layer n  :   (2n-2)^2+1   ->   (2n-1)^2
     * Layer n  :  (2n-1)^2-4n   ->   (2n-1)^2
     * */
    
    public static int layer(int input) {
        int root = (int) Math.sqrt(input);
        int length = root;
        if (isOdd(root)) {
            if (root*root != input) {
                length+=2;
            }
        } else {
            length++;
        }
        return length / 2 + 1;
    }

    public static int layerSize(int layer) {
        if (layer < 1) return 0;
        if (layer == 1) return 1;
        return 8 * (layer - 1);
    }

    public static boolean isOdd(int i) {
        return i % 2 != 0;
    }
    
}
