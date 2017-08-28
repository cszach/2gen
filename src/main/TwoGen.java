package main;

import system.IO;
import key.Password;
import key.Pin;
import supply.CharacterLib;
import supply.NumberLib;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import exception.DirectoryDoesNotExistException;
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

            // Clear console screen
            if (IO.command(userInput).equals("clear") || IO.command(userInput).equals("cls")
                    || IO.command(userInput).equals("clrscr")) {
                System.out.print("\u001b[2J" + "\u001b[H");
                System.out.flush();
            }

            // Directory handling
            // This is somewhat crucial in importing exception files

            // Get working directory
            if (IO.command(userInput).equals("pwd")) {
                System.out.println(System.getProperty("user.dir"));
            }

            // Change directory
            if (IO.command(userInput).equals("cd") || IO.command(userInput).equals("chdir")) {
                String orgDir = System.getProperty("user.dir");
                String destinateDir = null;

                // TODO: Preprocess the destination directory
                try {
                    // Destination "." -> Same directory
                    if (IO.argument(userInput)[0].equals(".")) {
                        continue mainProcess;
                    }

                    //Handler if the input is ".." (folder up)
                    // /home/user/code + cd .. -> /home/user
                    if (IO.argument(userInput)[0].equals("..")) {
                        if (NumberLib.numDirDelimiter() == 1) {
                            if (System.getProperty("os.name").equals("Windows")) {
                                System.setProperty("user.dir", System.getProperty("user.dir").substring(0, 3));
                                continue mainProcess;
                            }
                            if (System.getProperty("os.name").equals("Linux")) {
                                System.setProperty("user.dir", "/");
                                continue mainProcess;
                            }
                        }
                        if (System.getProperty("os.name").equals("Windows")) {
                            destinateDir = System.getProperty("user.dir").substring(0,
                                    System.getProperty("user.dir").length() -
                                            CharacterLib.reversedString(
                                                    System.getProperty("user.dir")).indexOf("\\") -1);
                        }
                        else if (System.getProperty("os.name").equals("Linux")) {
                            destinateDir = System.getProperty("user.dir").substring(0,
                                    System.getProperty("user.dir").length() -
                                            CharacterLib.reversedString(
                                                    System.getProperty("user.dir")).indexOf("/") - 1);
                        }
                        else {
                            System.err.println("Error: Unsupported operating system");
                            continue mainProcess;
                        }
                        System.setProperty("user.dir", destinateDir);
                        continue mainProcess;
                    }

                    if (System.getProperty("os.name").equals("Windows")) {
                        if (IO.argument(userInput)[0].substring(1, 2).equals(":")) {  // User provides full path
                            destinateDir = IO.argument(userInput)[0];
                        }
                        else {
                            if (System.getProperty("user.dir").length() == 3) {
                                destinateDir = orgDir + IO.argument(userInput)[0];
                            }
                            else {
                                destinateDir = orgDir + "\\" + IO.argument(userInput)[0];
                            }
                        }
                    }
                    else if (System.getProperty("os.name").equals("Linux")) {
                        if (IO.argument(userInput)[0].substring(0, 1).equals("/")) {  // User provides full path
                            destinateDir = IO.argument(userInput)[0];
                        }
                        else {
                            if (System.getProperty("user.dir").equals("/")) {
                                destinateDir = orgDir + IO.argument(userInput)[0];
                            }
                            else {
                                destinateDir = orgDir + "/" + IO.argument(userInput)[0];
                            }
                        }
                    }
                    else {
                        System.err.println("Error: Unsupported operating system");  // Temporary just leave this error here
                        continue mainProcess;
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error: No argument found for new directory");
                    continue mainProcess;
                }

                try {
                    if (!Files.isDirectory(Paths.get(destinateDir))) {
                        throw new DirectoryDoesNotExistException();
                    }
                    // Change directory
                    System.setProperty("user.dir", destinateDir);
                }
                catch (DirectoryDoesNotExistException e) {
                    System.err.println("Error: No such directory");
                    System.setProperty("user.dir", orgDir);
                }
            }

            // List files in the working directory
            if (IO.command(userInput).equals("ls") || IO.command(userInput).equals("dir")) {
                File dir = new File(System.getProperty("user.dir"));
                String[] filesList = dir.list();
                if (filesList.length > 0) {
                    for (String fileName : filesList) {
                        System.out.println(fileName);
                    }
                }
                dir = null;
                filesList = null;
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
                        System.out.println("Output: " + myPassword.value);  // Output
                        System.out.println();
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
                                    System.err.println("Error: No argument found for password's length");
                                    continue mainProcess;
                                }
                                catch (NumberFormatException e) {
                                    System.err.println("Error: Invalid argument for password's length");
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
                                    System.err.println("Error: No argument found for number of passwords");
                                }
                                catch (NumberFormatException e) {
                                    System.err.println("Error: Invalid argument for number of passwords");
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
                                    if (IO.argument(userInput)[getFile].substring(0, 1).equals("-")) {
                                        break getFileNamesOne;  // A new attribute is specified
                                    }
                                    // Again, check if the user provides full path
                                    if (System.getProperty("os.name").equals("Windows")) {
                                        if (IO.argument(userInput)[getFile].substring(1, 2).equals(":")) {
                                            exceptPasswordList.add(IO.argument(userInput)[getFile]);
                                        }
                                        else {
                                            exceptPasswordList.add(System.getProperty("user.dir") + "\\" +IO.argument(userInput)[getFile]);
                                        }
                                    }
                                    else if (System.getProperty("os.name").equals("Linux")) {
                                        if (IO.argument(userInput)[getFile].substring(0, 1).equals("/")) {
                                            exceptPasswordList.add(IO.argument(userInput)[getFile]);
                                        }
                                        else {
                                            exceptPasswordList.add(System.getProperty("user.dir") + "/" + IO.argument(userInput)[getFile]);
                                        }
                                    }
                                    else {
                                        System.err.println("Error: Unsupported operating system");
                                        continue mainProcess;
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
                            System.out.println("Output [" + (counter + 1) + "]: " + myPassword.value);
                            System.out.println();
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
                        System.out.println("Output: " + myPin.value);
                        System.out.println();
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
                                    System.err.println("Error: No argument found for pin's number of digits");
                                    continue mainProcess;
                                }
                                catch (NumberFormatException e) {
                                    System.err.println("Error: Invalid argument for pin's number of digits");
                                    continue mainProcess;
                                }
                                catch (LengthOutOfRangeException e) {
                                    System.err.println("Error: Invalid number of digits for pin. Must be in between 2 and 20");
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
                                    System.err.println("Error: No argument found for number of pins");
                                    continue mainProcess;
                                }
                                catch (NumberFormatException e) {
                                    System.err.println("Error: Invalid argument for number of pins");
                                    continue mainProcess;
                                }
                                catch (InvalidDuplicateValueException e) {
                                    continue mainProcess;
                                }
                            }
                            if (IO.argument(userInput)[argAt].equals("-e")) {
                                getFileNamesTwo: for (int getFile = argAt + 1; getFile < IO.argument(userInput).length; getFile++) {
                                    if (IO.argument(userInput)[getFile].substring(0, 1).equals("-")) {
                                        break getFileNamesTwo;
                                    }
                                    // Again, check if the user provides full path
                                    if (System.getProperty("os.name").equals("Windows")) {
                                        if (IO.argument(userInput)[getFile].substring(1, 2).equals(":")) {
                                            exceptPinList.add(IO.argument(userInput)[getFile]);
                                        }
                                        else {
                                            exceptPinList.add(System.getProperty("user.dir") + "\\" +IO.argument(userInput)[getFile]);
                                        }
                                    }
                                    else if (System.getProperty("os.name").equals("Linux")) {
                                        if (IO.argument(userInput)[getFile].substring(0, 1).equals("/")) {
                                            exceptPinList.add(IO.argument(userInput)[getFile]);
                                        }
                                        else {
                                            exceptPinList.add(System.getProperty("user.dir") + "/" + IO.argument(userInput)[getFile]);
                                        }
                                    }
                                    else {
                                        System.err.println("Error: Unsupported operating system");
                                        continue mainProcess;
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
                            System.out.println("Output [" + (counter + 1) + "]: " + myPin.value);
                            System.out.println();
                        }
                    }
                }
            }
        }
    }
}
