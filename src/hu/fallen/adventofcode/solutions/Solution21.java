package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;

import hu.fallen.adventofcode.helper.solution21.BoolArray;
import hu.fallen.adventofcode.helper.solution21.Rulebook;
import hu.fallen.adventofcode.helper.solution21.H;

public class Solution21 {
	
	private static boolean DEBUG = false;

    public static void printSolution() {
    	String mark = new Object() {}.getClass().getEnclosingClass().getSimpleName().replace("Solution", "");
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input"+mark+".txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(calculate(input, 5));
        System.out.println(calculate(input, 18));
    }

    static boolean[] image;
    static int size;
    
	public static int calculate(ArrayList<String> input, int countermax) {
		Rulebook rulebook = new Rulebook(input);
		size = 3;
		image = new boolean[] {false, true, false, false, false, true, true, true, true};
		rulebook.print();
		for (int counter = 0; counter < countermax; ++counter) {
			print("After iteration "+counter);
			int slice = size % 2 == 0 ? 2 : 3;
			
			int newsize = size / slice * (slice + 1);
			boolean[] newimage = new boolean[newsize * newsize];
			for (int i = 0; i < size / slice; ++i) {
				for (int j = 0; j < size / slice; ++j) {
					BoolArray key = sample(slice, i, j);
					H.debug(DEBUG, "Key: "+key.toString());
					BoolArray value = rulebook.get(key);
					H.debug(DEBUG, value.toString());
					copy(value, i, j, newsize, newimage);
				}
			}
			size = newsize;
			image = newimage;
		}
		print("After iteration "+countermax);
		return count();
	}
	
	private static int count() {
		int count = 0;
		for (int i = 0; i < image.length; ++i) if (image[i]) ++count;
		return count;
	}

	private static void print(String message) {
		print(message, image, size);
	}
	
	private static void print(String message, boolean[] image, int size) {
		if (!DEBUG) return;
		if (message != null) System.out.println(message);
		if (size * size != image.length) throw new RuntimeException();
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				System.out.print(img(i, j)?'#':'.');
			}
			System.out.println();
		}
	}

	private static BoolArray sample(int sampleSize, int x, int y) {
		StringBuilder rule = new StringBuilder();
		H.debug(DEBUG, "sample("+sampleSize+","+x+","+y+") - "+size);
		for (int i = 0; i < sampleSize; ++i) {
			for (int j = 0; j < sampleSize; ++j) {
				rule.append(image[H.coord(sampleSize * x + i, sampleSize * y + j, size)] ? "#" : ".");
			}
		}
		return new BoolArray(rule.toString());
	}

	private static void copy(BoolArray value, int x, int y, int newsize, boolean[] newimage) {
		int sampleSize = value.size;
		H.debug(DEBUG, "copy(\""+value.toString()+"\","+x+","+y+","+newsize+",["+newimage.length+"]) - "+sampleSize);
		for (int i = 0; i < sampleSize; ++i) {
			for (int j = 0; j < sampleSize; ++j) {
				boolean b = value.list[H.coord(i, j, sampleSize)];
				newimage[H.coord(sampleSize * x + i, sampleSize * y + j, newsize)] = b;
			}
		}
	}
	
	private static boolean img(int i, int j) {
		return image[i * size + j];
	}
	
	public static int calculate2(ArrayList<String> input) {
		return 0;
	}
	
}
