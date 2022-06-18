package demoexam.util;

import javax.swing.*;
import java.awt.*;


public class BasicForm extends JFrame {
    public static String APP_TITLE = "My App";
    public BasicForm(int width, int height)
    {
        setTitle(APP_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(width, height));
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2
        );
    }
}
