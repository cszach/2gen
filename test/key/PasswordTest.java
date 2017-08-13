package key;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import supply.CharacterLib;
import supply.NumberLib;
import java.io.IOException;

public class PasswordTest {
	private Password myPassword;

	@Before
	public void setUp() {
		String[] fileList = {"samples/passwordlist1.txt"};
		myPassword = new Password(8, true, true, true, true, fileList);
	}

	@Test
	public void testPassword() {
		try {
		    // TODO: Compare generated password to a random line in one of the exception files
            /**
             * Repeating 200 times, check if the generated password is the same as any line picked randomly
             * in the exception files.
             * Should not equal all the time
             */
            String comparingKey;
			for (int trial = 0; trial < 200; trial++) {
				comparingKey = CharacterLib.lineAt(
				        NumberLib.randomNumber(0, NumberLib.numberOfLines("samples/passwordlist1.txt") - 2),
                        "samples/passwordlist1.txt");

				assertNotEquals(myPassword.value, comparingKey);
			}
		}
		catch (IOException e) {/*Ignore*/}
	}

}
