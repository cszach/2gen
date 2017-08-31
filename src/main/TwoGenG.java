package main;

import javax.swing.*;
import java.awt.Color;

/**
 * This is a 2Gen distribution: 2Gen GUI
 *
 * @author Nguyen Hoang Duong (NOVAglow)
 * @date 2017-29-08
 */
public class TwoGenG {
    JFrame frame;
    TwoGenG() {
        // Setup for frame
        frame = new JFrame("2Gen");
        frame.setSize(800, 400);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: Set frame's background color
        Color backgroundColor = Color.decode("#C1DFD4");
        frame.getContentPane().setBackground(backgroundColor);

        // Output field for generated passwords / PINs
        JTextArea keyOutput = new JTextArea();
        frame.add(keyOutput);
        keyOutput.setBounds(450, 10, 335, 380);  // Set position: on the left
        keyOutput.setEditable(false);  // Make output field uneditable
    }

    public static void main (String[] args) {
        new TwoGenG();
    }
}
