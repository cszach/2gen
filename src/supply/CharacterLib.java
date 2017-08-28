package supply;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * <h1>Character Library</h1>
 * Provide characters for password generation and functions for aid in tests for class Password
 */
public class CharacterLib {
    final public static String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final public static String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
	final public static String NUMBER = "0123456789";
	final public static String SYMBOL = "~`!@#$%^&*()-_+={}[]|\\;:\"<>,./?";

    /**
     * @param lineNumber Index of the line we want to get. This is 0-based (The first line's index is 0)
     * @param fileName Name of the file we want to get the line from
     * @return The line at the index (specified by param linenumber) in the file (whose name specified by param fileName)
     */
	public static String lineAt(int lineNumber, String fileName) {
	    try {
            String result = Files.readAllLines(Paths.get(fileName)).get(lineNumber);
            return result;
        }
        catch (IOException e) {
	        e.printStackTrace();
	        return null;
        }
	}

    /**
     * Function for reversing a string. In the program, it is used in the changing directory stage
     * @param inputString The string that needs to be reversed
     * @return The reversed string
     */
	public static String reversedString(String inputString) {
		String result = new StringBuffer(inputString).reverse().toString();
		return result;
	}
}
