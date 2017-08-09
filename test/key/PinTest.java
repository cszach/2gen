package key;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PinTest {
	Pin myPin;

	@Before
	public void setUp() {
		String[] fileList1 = {"samples/pinlist1.txt"};
		myPin = new Pin(4, fileList1);
	}

	@Test
	public void test() {
	}

}
