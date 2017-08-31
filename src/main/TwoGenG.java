package main;

import supply.ResourceLib;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

/**
 * This is a 2Gen distribution: 2Gen GUI
 *
 * @author Nguyen Hoang Duong (NOVAglow)
 * @date 2017-29-08
 */
public class TwoGenG {
    public static HashMap<String, Font> fonts = new HashMap<>();

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

        // TODO: Import fonts
        ResourceLib.importFont("two", "NewsCycle-Regular.ttf", "News Cycle (Regular)");
        ResourceLib.importFont("gen", "NewsCycle-Bold.ttf", "News Cycle (Bold)");

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
