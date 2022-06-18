package demoexam.util;

import javax.swing.*;
import java.awt.*;

public class DialogUtil extends JFrame {
    public static void showError(Component parentComponent, String text)
    {
        JOptionPane.showMessageDialog(parentComponent, text, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfo(Component parentComponent, String text)
    {
        JOptionPane.showMessageDialog(parentComponent, text, "Информация", JOptionPane.INFORMATION_MESSAGE);
    }
}
