package key;

import supply.NumberLibrary;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Pin {
	String value; // The pin itself
	int length; // Number of digits

	/**
	 * @param length The length attribute of the pin
	 * @param excludeKeyFileName File(s) containing pin that the user doesn't want to generate
	 */
	Pin(int length, String[] excludeKeyFileName) {
		this.length = length;
		String value = "";

		// TODO: Generate random pin and check if it matches any exception
		mainLoop: while (true) {

			// TODO: Generate random pin
			for (int i = 0; i < length; i++) {
				int flag = NumberLibrary.randomNumber(1, 9);
				value += Integer.toString(flag);
			}

			this.value = value; // Assign the generated pin to the pin's value

			if (excludeKeyFileName == null) { // No exception is set
				break mainLoop;
			}

			// TODO: Scan files to check matches
			// There's custom file(s) for scanning -> scan to see if there's a match
			Scanner reader;
			for (String fileName : excludeKeyFileName) {
				try {
					File keyFile = new File(fileName);
					reader = new Scanner(keyFile);
					while (reader.hasNextLine()) {
						String flagKey = reader.nextLine();
						if (flagKey == this.value) { // Generated pin matches with an exception
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

			break mainLoop; // Only executed if all files are checked and no exceptions match
		}

		value = null;
	}
}
