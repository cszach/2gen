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
        int length;
        int duplicatePassword;
        boolean useUpperCase;
        boolean useLowerCase;
        boolean useNumber;
        boolean useSymbol;
        ArrayList<String> exceptPasswordList;
        String[] exceptPassword;

        // Pin and its variants
        Pin myPin;
        int numberOfDigits;
        int duplicatePin;
        ArrayList<String> exceptPinList;
        String[] exceptPin;

        mainProcess: while (!userInput.equals("exit")) {

            // TODO: Get user input
            System.out.print("$ ");
            userInput = IO.removedSpaces(scanner.nextLine().toLowerCase());

            // TODO: Process and Output
            if (IO.command(userInput).equals("exit")) {
                System.exit(0);
            }

            // TODO: Generate keys
            if (IO.command(userInput).equals("generate")) {
                // TODO: Generate password
                if (IO.argument(userInput)[0].equals("password")) {
                    // TODO: Setup for Password
                    length = NumberLib.randomNumber(8, 12);
                    duplicatePassword = 1;
                    useUpperCase = false;
                    useLowerCase = false;
                    useNumber = false;
                    useSymbol = false;
                    exceptPasswordList = new ArrayList<>();
                    exceptPassword = null;

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
                            if (IO.argument(userInput)[argAt].equals("-l")) {
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
                            if (IO.argument(userInput)[argAt].equals("-d")) {
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
                            if (IO.argument(userInput)[argAt].equals("-uc")) {useUpperCase = true;}
                            if (IO.argument(userInput)[argAt].equals("-lc")) {useLowerCase = true;}
                            if (IO.argument(userInput)[argAt].equals("-n")) {useNumber = true;}
                            if (IO.argument(userInput)[argAt].equals("-s")) {useSymbol = true;}
                            if (IO.argument(userInput)[argAt].equals("-e")) {
                                getFileNames: for (int getFile = argAt + 1; getFile < IO.argument(userInput).length; getFile++) {
                                    if (!IO.argument(userInput)[getFile].substring(0, 1).equals("-")) {
                                        exceptPasswordList.add(IO.argument(userInput)[getFile]);
                                    }
                                    else {
                                        break getFileNames;
                                    }
                                }
                            }
                            // TODO: Move exceptPasswordList (ArrayList) to exceptPassword (Array)
                            if (exceptPasswordList.size() > 0) {
                                exceptPassword = new String[exceptPasswordList.size()];
                                exceptPassword = exceptPasswordList.toArray(exceptPassword);
                            }

                            // If useUpperCase, useLowerCase, useNumber and useSymbol are all false (by default)
                            // ...change them all to true
                            if (!useUpperCase && !useLowerCase && !useNumber && !useSymbol) {
                                useUpperCase = true;
                                useLowerCase = true;
                                useNumber = true;
                                useSymbol = true;
                            }
                        }
                        // TODO: Generate and display
                        for (int counter = 0; counter < duplicatePassword; counter++) {
                            myPassword = new Password(length, useUpperCase, useLowerCase, useNumber, useSymbol, exceptPassword);
                            System.out.println(myPassword.value);
                        }
                    }
                }

                // TODO: Generate pin
                if (IO.argument(userInput)[0].equals("pin")) {
                    // TODO: Setup for Pin
                    numberOfDigits = NumberLib.randomNumber(4, 8);
                    duplicatePin = 1;
                    exceptPinList = new ArrayList<>();
                }
            }
        }
    }
}
