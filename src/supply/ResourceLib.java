package supply;

import java.io.File;
import java.awt.Font;
import java.io.IOException;
import java.awt.FontFormatException;
import static main.TwoGenG.fonts;

// Dialog components
import dialog.FontHandlingError;

public class ResourceLib {
    /**
     * Import fonts to be used in 2Gen GUI
     * @param variableName The name of the variable for Font
     * @param fontFileName Name of the file of the font (.ttf), found in the resources folder
     * @param fontNameWithAttribute Name of the font
     */
    public static void importFont(String variableName, String fontFileName, String fontNameWithAttribute) {
        try {
            fonts.put(variableName, Font.createFont(Font.TRUETYPE_FONT, new File("resources/" + fontFileName)));
        }
        catch (IOException e) {
            FontHandlingError.FontFileNotFound(fontFileName);
            System.exit(1);
        }
        catch (FontFormatException e) {
            FontHandlingError.CannotFormatFont(fontNameWithAttribute);
            System.exit(1);
        }
    }
}
