package system;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import system.IO;

public class IOTest {

	@Test
	public void testRemovedSpaces() {
		assertEquals("exit", IO.removedSpaces("   exit "));
		assertEquals("generate password", IO.removedSpaces("  generate  password"));
		assertEquals("generate pin -d 4", IO.removedSpaces("  generate pin  -d 4  "));
		assertEquals("generate password -l 12 -d 4 -uc -lc -n", IO.removedSpaces(" generate  password -l 12 -d   4 -uc  -lc -n"));
	}

	@Test
	public void testCommand() {
		assertEquals("exit", IO.command("exit"));
		assertEquals("generate", IO.command("generate password"));
		assertEquals("generate", IO.command("generate pin -l 6 -d 2"));
		assertEquals("generate", IO.command("generate password -uc -lc -s"));
		assertEquals("generate", IO.command(IO.removedSpaces("   generate  password  -l    30  ")));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testArgument() {
		String[] expectedOne = {"pin"};
		String[] expectedTwo = {"pin", "-d", "10"};
		String[] expectedThree = {"password", "-l", "15", "-uc", "-lc", "-n", "-s"};
		assertEquals(expectedOne, IO.argument("generate pin"));
		assertEquals(expectedTwo, IO.argument("generate pin -d 10"));
		assertEquals(expectedThree, IO.argument("generate password -l 15 -uc -lc -n -s"));
	}

}
