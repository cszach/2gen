package key;

import supply.NumberLib;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Pin {
	public String value; // The pin itself
	public int length; // Number of digits

	/**
	 * @param length The length attribute of the pin
	 * @param excludeKeyFileName File(s) containing pin that the user doesn't want to generate
	 */
	public Pin(int length, String[] excludeKeyFileName) {
		this.length = length;
		String value;

		// TODO: Generate random pin and check if it matches any exception
		mainLoop: while (true) {

			// TODO: Generate random pin
			System.out.println("Generating a PIN with " + length + " digits");

			value = "";
			for (int i = 0; i < length; i++) {
				int flag = NumberLib.randomNumber(1, 9);
				value += Integer.toString(flag);
			}

			this.value = value; // Assign the generated pin to the pin's value
            System.out.println("Assigned a new PIN: " + value);

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
					System.out.println("Scanning " + fileName);
					while (reader.hasNextLine()) {
						String flagKey = reader.nextLine();
						if (flagKey.equals(this.value)) { // Generated pin matches with an exception
							reader.close();
							System.out.println("Generated PIN matched with an exception. Generating another pin...\n");
							continue mainLoop; // Continue the while loop
						}
					}
					reader.close();
				}
				/**
				 * @exception FileNotFoundException Exception file is listed but not found. If this exception is hit,
				 * output the error as log and continue with the next files
				 */
				catch (FileNotFoundException e) {
					System.err.println("Error: File " + fileName + " not found and is ignored");
					// Continue with the next files
				}
			}

			break mainLoop; // Only executed if all files are checked and no exceptions match
		}

		value = null;
	}
}
