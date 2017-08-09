package supply;

import java.util.concurrent.ThreadLocalRandom;  // For Java 1.7+
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NumberLibrary {
	public static int randomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	public static int numberOfLines(String fileName) throws IOException {
		int flag = 0;
		LineNumberReader result = null;
		try {
			result = new LineNumberReader(new FileReader(new File(fileName)));
			result.skip(Long.MAX_VALUE);
			flag = result.getLineNumber() + 1;
		} 
		catch (FileNotFoundException e) {
		}
		finally {
			if (result != null) {
				result.close();
			}
		}
		return flag;
	}
}
