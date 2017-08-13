package system;

import java.util.Arrays;

/**
 * This class is used to help in user's input processing
 */
public class IO {
    public static String command(String input) {
        return input.split(" ")[0];
    }

    public static String[] argument(String input) {
        return Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);
    }
}
