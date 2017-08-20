package key;

import supply.CharacterLib;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

@SuppressWarnings("deprecation")
public class Password {
	public String value; // The password itself

	// Attributes of a password
	// Used for displaying password's details
	public int length;
	public boolean upperCase;
	public boolean lowerCase;
	public boolean number;
	public boolean symbol;

	/**
	 * @param length Length attribute of the password
	 * @param upperCase Attribute indicates if the password contains upper case characters
	 * @param lowerCase Attribute indicates if the password contains lower case characters
	 * @param number Attribute indicates if the password contains numbers
	 * @param symbol Attribute indicates if the password contains symbols, or special characters
	 * @param excludeKeyFileName An array containing names of files that contain passwords the user doesn't want to generate
	 */
	public Password(int length, boolean upperCase, boolean lowerCase, boolean number, boolean symbol,
			String[] excludeKeyFileName) {
		// TODO: Assign attributes
		this.length = length;
		this.upperCase = upperCase;
		this.lowerCase = lowerCase;
		this.number = number;
		this.symbol = symbol;
		
		// TODO: Generate password and check exceptions
		String charInPassword;
		mainLoop: while (true) {
			// TODO: Generate password
            System.out.println("Generating a password with a length of " + length + ":");

			charInPassword = "";  // This string contains allowed characters in the would-be-generated password
			System.out.print("Use upper case characters: ");
			if (upperCase) {
			    charInPassword += CharacterLib.UPPERCASE;
			    System.out.println("Yes");
			} else {System.out.println("No");};
			System.out.print("Use lower case characters: ");
			if (lowerCase) {
			    charInPassword += CharacterLib.LOWERCASE;
			    System.out.println("Yes");
			} else {System.out.println("No");}
			System.out.print("Use numbers: ");
			if (number) {
			    charInPassword += CharacterLib.NUMBER;
			    System.out.println("Yes");
			} else {System.out.println("No");}
			System.out.print("Use special characters / symbols: ");
			if (symbol) {
			    charInPassword += CharacterLib.SYMBOL;
			    System.out.println("Yes");
			} else {System.out.println("No");}

			// Assign a new password
			this.value = RandomStringUtils.random(length, charInPassword);
			System.out.println("Assigned a new password: " + this.value);
			
			if (excludeKeyFileName == null) {  // No exception is set
				break mainLoop;
			}
			
			// TODO: Scan file(s) to check for exceptions
			for (String fileName : excludeKeyFileName) {
				try {
                    File keyFile = new File(fileName);
                    Scanner reader = new Scanner(keyFile);
                    System.out.println("Scanning " + fileName);
                    while (reader.hasNextLine()) {
                        String flagKey = reader.nextLine();
                        if (flagKey.equals(this.value)) { // Generated password matches with an exception
                            reader.close();
                            System.out.println("Generated password matched with an exception. Generating another password\n");
                            continue mainLoop; // Continue the while loop
                        }
                    }
                    reader.close();
                }
				catch (FileNotFoundException e) {
					System.err.println("Error: File " + fileName + " not found");
					// Continue with the next files
				}
			}
			charInPassword = null;
			break mainLoop;  // Executed only when all files are checked and no exceptions is hit
		}
	}
}
