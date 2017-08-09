package supply;

import java.util.concurrent.ThreadLocalRandom;  // For Java 1.7+

public class NumberLibrary {
	public static int randomNumber(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}
