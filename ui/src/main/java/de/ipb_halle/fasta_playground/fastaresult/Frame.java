package de.ipb_halle.fasta_playground.fastaresult;

import java.util.HashMap;
import java.util.Map;

public enum Frame {
	FORWARD_ZERO("f", "forward"),
	//FORWARD_ONE("N/A"),
	//FORWARD_TWO("N/A"),
	REVERSE_ZERO("r", "reverse");
	//REVERSE_ONE("N/A"),
	//REVERSE_TWO("N/A");

	private final String pattern;
	private final String name;
	private static Map<String, Frame> lookupMap = new HashMap<>();

	static {
		for (Frame f : values()) {
			lookupMap.put(f.pattern, f);
		}
	}

	private Frame(String pattern, String name) {
		this.pattern = pattern;
		this.name = name;
	}

	public static Frame fromPattern(String pattern) {
		return lookupMap.get(pattern);
	}

	public String getName() {
		return name;
	}
}