package demoexam;

import demoexam.ui.AddForm;
import demoexam.ui.AuthForm;
import demoexam.ui.ProductTable;
import demoexam.util.CustomTableModel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Main {
    public static SimpleDateFormat DATE_FRORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean IS_ADMIN = false;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        new AuthForm();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "1234");
    }
}
