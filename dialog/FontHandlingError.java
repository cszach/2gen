package dialog;

import javax.swing.*;

public class FontHandlingError {
    public static void FontFileNotFound(String fontFileName) {
        JOptionPane.showMessageDialog(null,
                "Error: Failed to load resources/" + fontFileName, "2Gen", JOptionPane.ERROR_MESSAGE);
    }
    public static void CannotFormatFont(String font) {
        JOptionPane.showMessageDialog(null,
                "Error: Failed to format this font: " + font, "2Gen", JOptionPane.ERROR_MESSAGE);
    }
}
