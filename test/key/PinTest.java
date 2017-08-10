package key;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import supply.NumberLibrary;


public class PinTest {
	Pin myPin;

	@Before
	public void setUp() {
		String[] fileList1 = {"samples/pinlist1.txt", "samples/pinlist2.txt"};
		myPin = new Pin(4, fileList1);
	}

	@Test
	public void test() {
		// TODO: Pick a random line from the exception file (which is a pin) and it's ok if the generated pin isn't the same
		try {
			for (int i = 0; i < 1000; i++) {
				assertTrue(myPin.value != 
					Files.readAllLines(Paths.get("samples/pinlist1.txt"))
					.get(NumberLibrary.randomNumber(1, NumberLibrary.numberOfLines("samples/pinlist1.txt"))));				
			}
			for (int i = 0; i < 800; i++) {
				assertTrue(myPin.value != 
					Files.readAllLines(Paths.get("samples/pinlist2.txt"))
					.get(NumberLibrary.randomNumber(1, NumberLibrary.numberOfLines("samples/pinlist2.txt"))));
			}
		} 
		catch (IOException e) {/*Do nothing*/}
	}

}
