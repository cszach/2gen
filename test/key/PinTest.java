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
		try {
			for (int i = 0; i < 1000; i++) {
				assertTrue(myPin.value != 
						Files.readAllLines(Paths.get("/samples/pinlist1.txt"))
						.get(NumberLibrary.randomNumber(1, NumberLibrary.numberOfLines("/sample/pinlist1.txt"))));				
			}
		} catch (IOException e) {
			// Do nothing
		}
	}

}
