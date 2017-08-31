package supply;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

// Dialog components
import dialog.FontHandlingError;

public class ResourceLib {
    public static Font font(String fontFileName, String fontNameWithAttribute) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(fontFileName));
        }
        catch (IOException e) {
            FontHandlingError.FontFileNotFound(fontFileName);
            return null;
        }
        catch (FontFormatException e) {
            FontHandlingError.CannotFormatFont(fontNameWithAttribute);
            return null;
        }
    }
}
