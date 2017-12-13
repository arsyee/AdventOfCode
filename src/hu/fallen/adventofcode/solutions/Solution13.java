package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import hu.fallen.adventofcode.helper.Pair;

public class Solution13 {

	public static class AccessViolation extends Exception {

		private static final long serialVersionUID = 1L;

	}

	static boolean debug = true;
	
    public static void printSolution() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input13.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(calculate(input));
    }

	public static Pair<Integer, Long> calculate(List<String> input) {
		int[] config = parse(input);
		final List<Integer> unmodifiableConfig = Collections.unmodifiableList(Arrays.stream(config).boxed().collect(Collectors.toList()));
		int severity = calculateSeverity(unmodifiableConfig);
		long delay = 0;
		long cycleTime = lcd(unmodifiableConfig.stream().map(a -> a == 0 ? 0 : 2 * a - 1).collect(Collectors.toList()));
		System.out.println("Cycle time: "+cycleTime);
		for (delay = 0; delay < cycleTime; ++delay) {
			int dSeverity;
			try {
				dSeverity = calculateSeverity(unmodifiableConfig, delay, true);
			} catch (AccessViolation e) {
				continue;
			}
			if (dSeverity == 0) break;
		}
		return new Pair<Integer, Long>(severity, delay);
	}

	private static long lcd(List<Integer> values) {
		long result = 1;
		for (Integer value : values) {
			if (value == 0) continue;
			result = lcd(result, value);
			System.out.println("lcd("+value+"): "+result);
		}
		return result;
	}

	private static long lcd(long a, long b) {
		return a * b / gcd(a, b);
	}

	private static long gcd(long a, long b) {
		System.out.println("gcd("+a+", "+b+")");
		if (a < 0) return 0;
		if (b > a) return gcd(b, a);
		if (b == 0) return a;
		return gcd(b, a % b);
	}

	private static int calculateSeverity(List<Integer> list) {
		try {
			return calculateSeverity(list, 0, false);
		} catch (AccessViolation e) {
			return -1;
		}
	}
	
	private static int calculateSeverity(List<Integer> list, long delay, boolean preventOnViolation) throws AccessViolation {
		// System.out.println("calculateSeverity with delay: "+delay);
		int severity = 0;
		for (int t = 0; t < list.size(); ++t) {
			// at time 't' I will be in the 't-th' section
			// scanner is in position 0 in every (2*(n-1))-th round
			int inc = 0;
			if (list.get(t) == 0) {
				// skip, no layer at this depth
			} else {
				if ((delay+t) % (2*(list.get(t)-1)) == 0) {
					inc = t * list.get(t);
					// System.out.println("Caught; time: "+t+", config: "+list.get(t)+", position: "+((delay+t) % (2*(list.get(t)-1))));
					if (preventOnViolation) throw new AccessViolation();
				}
			}
			severity += inc;
		}
		return severity;
	}

	private static int[] parse(List<String> lines) {
		int[] result = new int[Integer.parseInt(lines.get(lines.size()-1).split(": ")[0])+1];
		for (String line : lines) {
			String[] values = line.split(": ");
			result[Integer.parseInt(values[0])] = Integer.parseInt(values[1]);
		}
		return result;
	}

}
