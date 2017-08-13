package system;

import java.util.Arrays;

public class IO {
    public static String command(String input) {
        return input.split(" ")[0];
    }

    public static String[] argument(String input) {
        return Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);
    }
}
