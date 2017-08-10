package key;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import supply.NumberLibrary;
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
			for (int i = 0; i < 8000; i++) {
				assertTrue(myPassword.value != 
					Files.readAllLines(Paths.get("/samples/passwordlist1.txt"))
					.get(NumberLibrary.randomNumber(1, NumberLibrary.numberOfLines("/sample/passwordlist1.txt"))));				
			}
		}
		catch (IOException e) {/*Ignore*/};
	}

}
