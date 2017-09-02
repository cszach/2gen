package main;

import supply.ResourceLib;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * This is a 2Gen distribution: 2Gen GUI
 *
 * @author Nguyen Hoang Duong (NOVAglow)
 * @date 2017-29-08
 */
public class TwoGenG {
    private static JFrame mainFrame;  // Main frame
    private static JFrame outputFrame;  // Output frame, pop up after hitting the "Generate" button

    private static JTextArea outputs;  // Output text field for writing generated keys to. Accessible everywhere in this class
    public static HashMap<String, Font> fonts = new HashMap<>();  // For storing Font's variants with variable names

    public static void showOutput() {  //
        outputFrame = new JFrame("2Gen: Outputs");
        outputFrame.setSize(350, 400);
        outputFrame.getContentPane().setBackground(Color.decode("#C1DFD4"));
        outputFrame.setLayout(null);
        outputFrame.setResizable(false);  // Make the frame's size fixed
        outputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        outputFrame.setVisible(true);

        outputs = new JTextArea();
        outputs.setEditable(false);  // Make output field uneditable
        outputs.setBounds(5, 10, 340, 380);  // This puts JTextArea to the center of the frame
        outputFrame.add(outputs);
    }

    TwoGenG() {
        // TODO: Prepare
        mainFrame = new JFrame("2Gen");
        mainFrame.setSize(800, 400);
        mainFrame.getContentPane().setBackground(Color.decode("#C1DFD4"));  // This color is used for all frames
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setResizable(false);  // Make the frame's size fixed
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        // TODO: Import fonts
        ResourceLib.importFont("nCR", "NewsCycle-Regular.ttf", "News Cycle (Regular)");
        ResourceLib.importFont("nCB", "NewsCycle-Bold.ttf", "News Cycle (Bold)");
    }

    public static void main (String[] args) {
        new TwoGenG();
    }
}
