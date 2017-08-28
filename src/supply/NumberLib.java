package supply;

import java.awt.datatransfer.SystemFlavorMap;
import java.util.concurrent.ThreadLocalRandom; // For Java 1.7+
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <h1>Number Library</h1>
 * Provide function for generating random numbers (which is used everywhere),
 * and function for getting number of lines present in a file
 */
public class NumberLib {
    /**
	 * Return a random number within a specific range (given as arguments)
     * @param min Minimum possible number that could be generated, inclusive
     * @param max Maximum possible number that could be generated, inclusive
     * @return Generated random number in range from min to max
     */
	public static int randomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

    /**
     * Function that provides number of occurences of directory delimiters in a directory, given as a string
     * Directory delimiter in Windows is "\" (back slash), and in Linux is "/" (forward slash)
     * @return Number of occurences of directory delimiters depends on the OS (either Windows or Linux)
     */
	public static int numDirDelimiter() {
	    int result = 0;
	    if (System.getProperty("os.name").equals("Windows")) {
            for (int i = 0; i < System.getProperty("user.dir").length(); i++) {
                if (System.getProperty("user.dir").charAt(i) == '\\') {
                    result++;
                }
            }
            return result;
        }
        if (System.getProperty("os.name").equals("Linux")) {
	        for (int i = 0; i < System.getProperty("user.dir").length(); i++) {
	            if (System.getProperty("user.dir").charAt(i) == '/') {
	                result++;
                }
            }
            return result;
        }
        return -1;  // Unsupported OS
    }

    /**
     * Return the number of lines in a file
     * @param fileName Name of the file we want to get number of lines in
     * @return Number of lines in the file whose name is thrown as the (only) argument
     * @throws IOException
     */
	public static int numberOfLines(String fileName) throws IOException {
		int flag = 0;
		LineNumberReader result = null;
		try {
			result = new LineNumberReader(new FileReader(new File(fileName)));
			result.skip(Long.MAX_VALUE);
			flag = result.getLineNumber() + 1;
		} 
		catch (FileNotFoundException e) {/*Do nothing. It shouldn't ever run into this exception*/} 
		finally {
			if (result != null) {
				result.close();
			}
		}
		return flag;
	}
}
