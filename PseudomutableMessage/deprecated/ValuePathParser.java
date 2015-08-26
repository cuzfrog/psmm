package cuz.psmm.accessoaries;

import cuz.psmm.exceptions.PsmmInvalidValuePathException;


public final class ValuePathParser {

	private ValuePathParser() {
	}

	public static void validate(String path) throws PsmmInvalidValuePathException {
		if (!path.matches("[\\w/]+") || path.matches(".*//.*"))
			throw new PsmmInvalidValuePathException();
	}

	public static boolean isDirectory(String path) {
		return path.matches(".+/.*");
	}
}
