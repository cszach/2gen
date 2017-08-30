package main;

import javax.swing.*;
// import dialog.UnsupportedOSWarn;

/**
 * This is a 2Gen distribution: 2Gen GUI
 *
 * @author Nguyen Hoang Duong (NOVAglow)
 * @date 2017-29-08
 */
public class TwoGenG {
    JFrame frame;
    TwoGenG() {
        frame = new JFrame("2Gen");
    }

    public static void main (String[] args) {
        // Check if OS is supported
        // If not, shows warning
        /*
        if (System.getProperty("os.name").equals("Windows") || System.getProperty("os.name").equals("Linux")) {
            new UnsupportedOSWarn();
        }
        */

        new TwoGenG();
    }
}
