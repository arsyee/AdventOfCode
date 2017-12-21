package hu.fallen.adventofcode.helper.solution21;

public class BoolArray {
	
	private static boolean DEBUG = false;

	public final boolean[] list;
	public final int size;
	private int hashcode = 0;
	
	public BoolArray(String descriptor) {
		descriptor = descriptor.replaceAll("/", "");
		size = (int) Math.sqrt(descriptor.length());
		if (size * size != descriptor.length()) throw new RuntimeException();
		list = new boolean[size * size];
		for (int i = 0; i < descriptor.length(); ++i) {
			if (descriptor.charAt(i) == '#') {
				list[i] = true;
				++hashcode;
			}
		}
		H.debug(DEBUG, "Created BA: "+toString());
	}
	
	@Override
	public int hashCode() {
		return hashcode;
	}
	
	@Override
	public boolean equals(Object obj) {
		H.debug(DEBUG, "equals() entered");
		if (obj == null) return false;
		if (!(obj instanceof BoolArray)) return false;
		BoolArray a = (BoolArray) obj;
		if (list.length != a.list.length) return false;
		return elementsInSameClass(a.list);
	}
	
	private boolean elementsInSameClass(boolean[] original) {
		boolean[] copy = new boolean[original.length];
		for (int i = 0; i < list.length; ++i) copy[i] = original[i];

		if (elementsEqual(copy)) return true;
		for (int i = 0; i < 3; ++i) {
			rotate(copy, size );
			if (elementsEqual(copy)) return true;
		}
		
		flip(copy, size);

		if (elementsEqual(copy)) return true;
		for (int i = 0; i < 3; ++i) {
			rotate(copy, size );
			if (elementsEqual(copy)) return true;
		}
		
		return false;
	}

	private static void rotate(boolean[] copy, int size) {
		boolean copytmp;
		if (size == 2) {
			copytmp = copy[0];
			copy[0] = copy[1];
			copy[1] = copy[3];
			copy[3] = copy[2];
			copy[2] = copytmp;
			return;
		}
		if (size == 3) {
			copytmp = copy[0];
			copy[0] = copy[2];
			copy[2] = copy[8];
			copy[8] = copy[6];
			copy[6] = copytmp;
			copytmp = copy[1];
			copy[1] = copy[5];
			copy[5] = copy[7];
			copy[7] = copy[3];
			copy[3] = copytmp;
			return;
		}
		throw new RuntimeException();
	}

	private static void flip(boolean[] copy, int size) {
		boolean temp;
		for (int i = 0; i <= size/2; ++i) {
			for (int j = 0; j < size; ++j) {
				temp = copy[H.coord(i, j, size)];
				copy[H.coord(i, j, size)] = copy[H.coord(size-i-1, j, size)];
				copy[H.coord(size-i-1, j, size)] = temp;
			}
		}
	}

	public boolean elementsEqual(boolean[] other) {
		print("Comparing to ", other, size);
		for (int i = 0; i < list.length; ++i) if (list[i] != other[i]) return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.length; ++i) sb.append(list[i]?"#":".");
		return sb.toString();
	}
	
	private static void print(String message, boolean[] image, int size) {
		if (message != null) H.debug(DEBUG, message);
		StringBuilder sb = new StringBuilder();
		if (size * size != image.length) throw new RuntimeException();
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				sb.append(image[H.coord(i, j, size)]?'#':'.');
			}
			sb.append('\n');
		}
		H.debug(DEBUG, sb.toString());
	}

}
