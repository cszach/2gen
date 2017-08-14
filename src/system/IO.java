package system;

import java.util.Arrays;

/**
 * This class is used to help in user's input processing
 */
public class IO {
    /**
     * This method is used to remove additional space characters
     * i.e. With "  generate     password " as an input, it will return "generate password"
     * @param input A string as a parameter, in this program, the user's input / command
     * @return A string with all the unnecessary space characters removed
     */
    public static String removedSpaces(String input) {
        return input.trim().replaceAll(" +", " ");
    }

    /**
     * Get the main command (or command for short) of the user's input
     * The main command basically tells the program what it will do next
     * With input "generate password", "generate" is the command, therefore it should return "generate"
     * There are only two command in 2Gen: "generate" and "exit"
     * @param input A string as a parameter, in this program, the user's input / command
     * @return The command
     */
    public static String command(String input) {
        return input.split(" ")[0];
    }

    /**
     * This method is used to get a list of parameters, crucial in input processing
     * Parameters are keywords that follow the command (at least in 2Gen, we are assuming like that)
     * i.e. "generate pin -l 8" has these parameters: "pin", "-l", "8"
     * @param input A string as a parameter, in this program, the user's input / command
     * @return An array of parameters
     */
    public static String[] argument(String input) {
        return Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length);
    }
}
