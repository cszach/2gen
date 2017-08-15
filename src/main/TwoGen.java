package main;

import system.IO;
import key.Password;
import key.Pin;
import supply.NumberLib;
import java.util.ArrayList;
import java.util.Scanner;
import exception.LengthOutOfRangeException;
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

        // Check if password's length is unset, or equal to 0
        // For example:
        // "generate password -d 10" as input would output 10 different passwords with different lengths
        // Without the variable lengthUnset, it would output 10 different passwords, but share one length value
        boolean passwordLengthUnset;

        // Pin and its variants
        Pin myPin;
        int numberOfDigits;
        int duplicatePin;
        ArrayList<String> exceptPinList;
        String[] exceptPin;

        // This variable has the correspond function to passwordLengthUnset. See above
        boolean pinLengthUnset;

        mainProcess: while (!userInput.equals("exit")) {

            // TODO: Get user input
            System.out.print("2Gen $ ");
            userInput = IO.removedSpaces(scanner.nextLine());

            // TODO: Process and Output

            // Exit. Must placed before all processes for any other command
            if (IO.command(userInput).equals("exit")) {
                System.exit(0);
            }

            // TODO: Generate keys
            if (IO.command(userInput).equals("generate")) {
                // Check if there is at least one argument is passed
                // If there's 0, cancel all the sessions and processes
                if (IO.argument(userInput).length == 0) {
                    continue mainProcess;
                }

                // TODO: Start session for Password
                if (IO.argument(userInput)[0].equals("password")) {
                    // TODO: Setup for Password
                    length = 0;
                    duplicatePassword = 1;
                    useUpperCase = false;
                    useLowerCase = false;
                    useNumber = false;
                    useSymbol = false;
                    exceptPasswordList = new ArrayList<>();
                    exceptPassword = null;
                    passwordLengthUnset = false;

                    // TODO: Process input for Password generation
                    if (userInput.split(" ").length == 2) {  // Input: generate password  ... -> default argument
                        myPassword = new Password
                                (NumberLib.randomNumber(8, 12),
                                true,
                                true,
                                true,
                                true,
                                null);
                        System.out.println(myPassword.value);  // Output
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
                                catch (ArrayIndexOutOfBoundsException e) {
                                    System.err.println("No argument found for password's length");
                                    continue mainProcess;
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
                                catch (ArrayIndexOutOfBoundsException e) {
                                    System.err.println("No argument found for number of passwords");
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
                                getFileNamesOne: for (int getFile = argAt + 1; getFile < IO.argument(userInput).length; getFile++) {
                                    if (!IO.argument(userInput)[getFile].substring(0, 1).equals("-")) {
                                        exceptPasswordList.add(IO.argument(userInput)[getFile]);
                                    }
                                    else {
                                        break getFileNamesOne;
                                    }
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

                        if (length == 0) {
                            passwordLengthUnset = true;
                        }

                        // TODO: Generate and display password
                        for (int counter = 0; counter < duplicatePassword; counter++) {
                            if (passwordLengthUnset) {length = NumberLib.randomNumber(8, 12);}
                            myPassword = new Password(length, useUpperCase, useLowerCase, useNumber, useSymbol, exceptPassword);
                            System.out.println(myPassword.value);
                        }
                    }
                }

                // TODO: Start session for Pin
                if (IO.argument(userInput)[0].equals("pin")) {
                    // TODO: Setup for Pin
                    numberOfDigits = 0;
                    duplicatePin = 1;
                    exceptPinList = new ArrayList<>();
                    exceptPin = null;
                    pinLengthUnset = false;

                    // TODO: Process input for Pin generation
                    if (userInput.split(" ").length == 2) {  // generate pin -> use default settings
                        myPin = new Pin(NumberLib.randomNumber(4, 8), null);
                        System.out.println(myPin.value);
                        continue mainProcess;
                    }
                    else {  // generate pin with arguments -> process those arguments
                        for (int argAt = 1; argAt < IO.argument(userInput).length; argAt++) {
                            if (IO.argument(userInput)[argAt].equals("-l")) {
                                try {
                                    numberOfDigits = Integer.parseInt(IO.argument(userInput)[argAt + 1]);
                                    if (numberOfDigits < 2 || numberOfDigits > 20) {
                                        throw new LengthOutOfRangeException();
                                    }
                                }
                                catch (ArrayIndexOutOfBoundsException e) {
                                    System.err.println("No argument found for pin's number of digits");
                                    continue mainProcess;
                                }
                                catch (NumberFormatException e) {
                                    System.err.println("Invalid argument for pin's number of digits");
                                    continue mainProcess;
                                }
                                catch (LengthOutOfRangeException e) {
                                    continue mainProcess;
                                }
                            }
                            if (IO.argument(userInput)[argAt].equals("-d")) {
                                try {
                                    duplicatePin = Integer.parseInt(IO.argument(userInput)[argAt + 1]);
                                    if (duplicatePin < 1) {
                                        throw new InvalidDuplicateValueException();
                                    }
                                }
                                catch (ArrayIndexOutOfBoundsException e) {
                                    System.err.println("No argument found for number of pins");
                                    continue mainProcess;
                                }
                                catch (NumberFormatException e) {
                                    System.err.println("Invalid argument for number of pins");
                                    continue mainProcess;
                                }
                                catch (InvalidDuplicateValueException e) {
                                    continue mainProcess;
                                }
                            }
                            if (IO.argument(userInput)[argAt].equals("-e")) {
                                getFileNamesTwo: for (int getFile = argAt + 1; getFile < IO.argument(userInput).length; getFile++) {
                                    if (!IO.argument(userInput)[getFile].substring(0, 1).equals("-")) {
                                        exceptPinList.add(IO.argument(userInput)[getFile]);
                                    }
                                    else {
                                        break getFileNamesTwo;
                                    }
                                }
                            }
                        }
                        // TODO: Move exceptPinList (ArrayList) to exceptPin (Array)
                        if (exceptPinList.size() > 0) {
                            exceptPin = new String[exceptPinList.size()];
                            exceptPin = exceptPinList.toArray(exceptPin);
                        }

                        if (numberOfDigits == 0) {
                            pinLengthUnset = true;
                        }
                        // TODO: Generate and display pin
                        for (int counter = 0; counter < duplicatePin; counter++) {
                            if (pinLengthUnset) {numberOfDigits = NumberLib.randomNumber(4, 8);}
                            myPin = new Pin(numberOfDigits, exceptPin);
                            System.out.println(myPin.value);
                        }
                    }
                }
            }
        }
    }
}
