package hu.fallen.adventofcode.solutions;

import java.util.ArrayList;
import java.util.List;

public class Solution17 {

    private static final int YEAR = 2017;
	private static int BIGYEAR = 50*1000*1000;

	public static void printSolution() {
        System.out.println(calculate(3));
        System.out.println(calculate(316)); // 51 is wrong
        System.out.println(calculate2(316));
    }

	public static int calculate(int input) {
		List<Integer> buffer = new ArrayList<Integer>();
		buffer.add(0);
		int position = 0;
		for (int n = 0; n < YEAR; ++n) {
			// print(buffer, position);
			position = (position + input) % buffer.size();
			buffer.add(position+1, buffer.size());
			position++;
		}
		print(buffer, position);
		return buffer.get((position+1)%buffer.size());
	}

	public static int calculate2(int input) {
		MyBuffer buffer = new MyBuffer();
		// same algorithm as calculate!!!
		buffer.add(0);
		int position = 0;
		for (int n = 0; n < BIGYEAR; ++n) {
			// print(buffer, position);
			position = (position + input) % buffer.size();
			buffer.add(position+1, buffer.size());
			position++;
		}
		print(buffer, position);
		return buffer.getNextElement();
	}

	// only store relevant information about the list
	private static class MyBuffer {
		private int size = 0;
		private int position0 = 0;
		private int nextElement = 0;
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(size);
			return sb.toString();
		}
		public int getNextElement() {
			return nextElement;
		}
		public int get(int i) {
			if (size == 0 || i < 0) throw new IndexOutOfBoundsException();
			if (i == position0) return 0;
			if (i == position0+1) return nextElement;
			throw new IllegalArgumentException();
		}
		public void add(int i, int value) {
			if (i < position0) position0++;
			if (i-1 == position0) nextElement = value;
			size++;
		}
		public int size() {
			return size;
		}
		public void add(int i) {
			if (i != 0) throw new IndexOutOfBoundsException();
			if (size != 0) throw new IndexOutOfBoundsException();
			size++;
			// position0 is now valid
			// nextElement is not really valid, but we can live with it
		}
	}
	
	private static void print(MyBuffer buffer, int position) {
		System.out.println(buffer.toString()+"@"+position);
	}
	
	private static void print(List<Integer> buffer, int position) {
		for (int i = 0; i < buffer.size(); ++i) {
			System.out.print(i == position ? "(" : " ");
			System.out.print(buffer.get(i));
			System.out.print(i == position ? ")" : " ");
			System.out.print(" ");
		}
		System.out.println();
	}	
	
}
