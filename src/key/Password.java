package key;

import supply.CharacterLibrary;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

@SuppressWarnings("deprecation")
public class Password {
	private String value; // The password itself

	// Attributes of a password
	// Used for displaying password's details
	private int length;
	private boolean upperCase;
	private boolean lowerCase;
	private boolean number;
	private boolean symbol;

	Password(int length, boolean upperCase, boolean lowerCase, boolean number, boolean symbol,
			String[] excludeKeyFileName) {
		// TODO: Assign attributes
		this.length = length;
		this.upperCase = upperCase;
		this.lowerCase = lowerCase;
		this.number = number;
		this.symbol = symbol;
		
		// TODO: Generate password and check exceptions
		mainLoop: while (true) {
			// TODO: Generate password
			String charInPassword = "";
			if (upperCase) { charInPassword += CharacterLibrary.UPPERCASE; }
			if (lowerCase) { charInPassword += CharacterLibrary.LOWERCASE; }
			if (number) { charInPassword += CharacterLibrary.NUMBER; }
			if (symbol) { charInPassword += CharacterLibrary.SYMBOL; }
			this.value = RandomStringUtils.random(length, charInPassword);
			
			if (excludeKeyFileName == null) {  // No exception is set
				break mainLoop;
			}
			
			// TODO: Scan file(s) to check for exceptions
			for (String fileName : excludeKeyFileName) {
				try {
					File keyFile = new File(fileName);
					Scanner reader = new Scanner(keyFile);
					while (reader.hasNextLine()) {
						String flagKey = reader.nextLine();
						if (flagKey == this.value) { // Generated password matches with an exception
							reader.close();
							continue mainLoop; // Continue the while loop
						}
					}
					reader.close();
				} 
				catch (FileNotFoundException e) {
					System.err.println("File " + fileName + " not found.");
					// Continue with the next files
				}
			}
			charInPassword = null;
			break mainLoop;  // Executed only when all files are checked and no exceptions is hit
		}
	}

	public static void main (String[] args) {
		Password myPassword = new Password(12, true, true, true, true, null);
		System.out.println(myPassword.value);
		System.out.println(myPassword.length);
	}
}
