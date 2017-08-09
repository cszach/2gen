package key;

import supply.CharacterLibrary;
import supply.NumberLibrary;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Password {
	private String value; // The password itself
	
	// Attributes of a password
	// Used for displaying password's details
	private int length;
	private boolean upperCase;
	private boolean lowerCase;
	private boolean number;
	private boolean symbol;
	
	Password(int length, boolean upperCase, boolean lowerCase, boolean number, boolean symbol, String[] excludeKeyFileName) {
		this.length = length;
		this.upperCase = upperCase;
		this.lowerCase = lowerCase;
		this.number = number;
		this.symbol = symbol;
	}
}
