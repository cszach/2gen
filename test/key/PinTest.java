package key;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import supply.NumberLib;


public class PinTest {
	Pin myPin;

	@Before
	public void setUp() {
		String[] fileList1 = {"samples/pinlist1.txt", "samples/pinlist2.txt", "samples/pinlist3.txt"};
		myPin = new Pin(4, fileList1);
	}

	@Test
	public void test() {
		// TODO: Pick a random line from the exception file (which is a pin) and it's ok if the generated pin isn't the same
		try {
			for (int trial = 0; trial < 1000; trial++) {
				assertTrue(myPin.value != 
					Files.readAllLines(Paths.get("samples/pinlist1.txt"))
					.get(NumberLib.randomNumber(1, NumberLib.numberOfLines("samples/pinlist1.txt"))));
			}
			for (int trial = 0; trial < 800; trial++) {
				assertTrue(myPin.value != 
					Files.readAllLines(Paths.get("samples/pinlist2.txt"))
					.get(NumberLib.randomNumber(1, NumberLib.numberOfLines("samples/pinlist2.txt"))));
			}
			for (int trial = 0; trial < 500; trial++) {
				assertTrue(myPin.value != 
					Files.readAllLines(Paths.get("samples/pinlist3.txt"))
					.get(NumberLib.randomNumber(1, NumberLib.numberOfLines("samples/pinlist3.txt"))));
			}
		} 
		catch (IOException e) {/*Do nothing*/}
	}

}
