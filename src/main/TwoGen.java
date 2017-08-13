package main;

import exception.LengthOutOfRangeException;
import system.IO;
import key.Password;
import key.Pin;
import supply.NumberLib;
import java.util.ArrayList;
import java.util.Scanner;
import exception.InvalidDuplicateValueException;

/**
 * The main program for 2Gen
 *
 * @author Nguyen Hoang Duong (NOVAglow)
 * @since 2017-12-08
 */
public class TwoGen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userInput = "";

        // Password and its attributes
        Password myPassword;
        int length = NumberLib.randomNumber(8, 12);
        int duplicatePassword = 1;
        boolean useUpperCase = false;
        boolean useLowerCase = false;
        boolean useNumber = true;
        boolean useSymbol = false;
        ArrayList<String> exceptPasswordList = new ArrayList<>();
        String[] exceptPassword;

        // Pin and its variants
        Pin myPin;
        int numberOfDigits;
        int duplicatePin;
        ArrayList<String> exceptPinList;
        String[] exceptPin;

        mainProcess: while (userInput != "exit") {

            // TODO: Get user input
            System.out.print("$ ");
            userInput = scanner.nextLine().toLowerCase();

            // TODO: Process and Output
            if (IO.command(userInput).equals("exit")) {
                System.exit(0);
            }

            // TODO: Generate keys
            if (IO.command(userInput).equals("generate")) {
                // TODO: Generate password
                if (IO.argument(userInput)[0].equals("password")) {
                    if (userInput.split(" ").length == 2) {  // Input: generate password  ... -> default argument
                        myPassword = new Password
                                (NumberLib.randomNumber(8, 12),
                                true,
                                true,
                                true,
                                true,
                                null);
                        System.out.println(myPassword.value);  // Output
                        myPassword = null;
                        continue mainProcess;
                    }
                    else {  // generate password + arguments -> process
                        for (int argAt = 1; argAt < IO.argument(userInput).length; argAt++) {
                            if (IO.argument(userInput)[argAt] == "-l") {
                                try {
                                    length = Integer.parseInt(IO.argument(userInput)[argAt + 1]);
                                    if (length < 8 || length > 30) {
                                        throw new LengthOutOfRangeException();
                                    }
                                }
                                catch (NumberFormatException e) {
                                    System.err.println("Invalid argument for password's length");
                                    continue mainProcess;
                                }
                                catch (LengthOutOfRangeException e) {
                                    continue mainProcess;
                                }
                            }
                            if (IO.argument(userInput)[argAt] == "-d") {
                                try {
                                    duplicatePassword = Integer.parseInt(IO.argument(userInput)[argAt + 1]);
                                    if (duplicatePassword < 1) {
                                        throw new InvalidDuplicateValueException();
                                    }
                                }
                                catch (NumberFormatException e) {
                                    System.err.println("Invalid argument for number of passwords");
                                    continue mainProcess;
                                }
                                catch (InvalidDuplicateValueException e) {
                                    continue mainProcess;
                                }
                            }
                            if (IO.argument(userInput)[argAt] == "-uc") {useUpperCase = true;};
                            if (IO.argument(userInput)[argAt] == "-lc") {useLowerCase = true;};
                            if (IO.argument(userInput)[argAt] == "-n") {useNumber = true;};
                            if (IO.argument(userInput)[argAt] == "-s") {useSymbol = true;};
                            if (IO.argument(userInput)[argAt] == "-e") {
                                getFileNames: for (int getFile = argAt + 1; getFile < IO.argument(userInput).length; getFile++) {
                                    if (IO.argument(userInput)[getFile].substring(0, 1) != "-") {
                                        exceptPasswordList.add(IO.argument(userInput)[getFile]);
                                    }
                                    else {
                                        break getFileNames;
                                    }
                                }
                            }
                            // TODO: Move exceptPasswordList (ArrayList) to exceptPassword (Array)
                            exceptPassword = new String[exceptPasswordList.size()];
                            exceptPassword = exceptPasswordList.toArray(exceptPassword);

                            // TODO: Generate and display
                            for (int index = 0; index < duplicatePassword; index++) {
                                myPassword = new Password(length, useUpperCase, useLowerCase, useNumber, useSymbol, exceptPassword);
                                System.out.println(myPassword.value);
                            }
                        }
                    }
                }

                // TODO: Generate pin
                if (IO.argument(userInput)[0].equals("pin")) {

                }
            }
        }
    }
}
