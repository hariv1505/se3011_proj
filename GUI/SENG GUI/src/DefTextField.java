import java.awt.Color;

import javax.swing.JTextField;


public class DefTextField extends JTextField {
    private static final long serialVersionUID = 1L;

    JTextField textField = new JTextField();
    String defaultText;

    public DefTextField(String defaultText) {
        super(defaultText);
        this.defaultText = defaultText;
        this.setForeground(Color.LIGHT_GRAY);
    }

    public String getDefText() {
    	return defaultText;
    }
}
