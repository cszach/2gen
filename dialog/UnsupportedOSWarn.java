package dialog;

import javax.swing.*;

/**
 * Display warning dialog to warn the user that their operating system is not supported (Not Windows or Linux)
 */
public class UnsupportedOSWarn {
    JOptionPane dialog;
    public UnsupportedOSWarn() {
        JOptionPane.showMessageDialog(null, "Warning: Unsupported operating system", "2Gen", JOptionPane.WARNING_MESSAGE);
    }
}
