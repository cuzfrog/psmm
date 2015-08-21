package cuz.psmm.utility;

import cuz.psmm.exceptions.PsmmInvalidValuePathException;

public class ValuePathParser {

	private ValuePathParser(){}
	
	public static void validate(String path){
		if(!path.matches("[\\w/]+")||path.matches(".*//.*"))
			throw new PsmmInvalidValuePathException();
	}
	static void isDirectory(String path){
		//path.matches(".+/.*")
	}
}
