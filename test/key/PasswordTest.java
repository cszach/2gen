package key;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import supply.NumberLib;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class PasswordTest {
	Password myPassword;

	@Before
	public void setUp() {
		String[] fileList = {"samples/passwordlist1.txt"};
		myPassword = new Password(8, true, true, true, true, fileList);
	}

	@Test
	public void testPassword() {
		try {
			// Pick a random key from the file and check if the generated password matches
			// Repeat 8000 times
			// Shouldn't match with anything
			for (int trial = 0; trial < 8000; trial++) {
				assertTrue(myPassword.value.equals(
					Files.readAllLines(Paths.get("samples/passwordlist1.txt"))
					.get(NumberLib.randomNumber(1, NumberLib.numberOfLines("samples/passwordlist1.txt")))));
			}
		}
		catch (IOException e) {/*Ignore*/};
	}

}
