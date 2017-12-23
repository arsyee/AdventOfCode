package hu.fallen.adventofcode.helper.solution18;

import java.util.HashMap;

public class Registers extends HashMap<Character, Long> {
	private static final long serialVersionUID = 1L;
	
	long lastSound = 0;

	@Override
	public Long put(Character key, Long value) {
		// System.out.println(key + "->" + value);
		return super.put(key, value);
	}
	
	public long getValue(String string) {
		try {
			return Long.parseLong(string);
		} catch (Exception e) { }
		try {
			return get(string.charAt(0));
		} catch (Exception e) { }
		return 0;
	}
}