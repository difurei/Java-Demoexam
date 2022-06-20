package demoexam.ui;

import demoexam.Main;
import demoexam.manager.UserManager;
import demoexam.util.BasicForm;
import demoexam.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class AuthForm extends BasicForm {
    private JFormattedTextField loginField;
    private JPasswordField passwordField;
    private JButton authButton;
    private JButton guestButton;
    private JPanel mainPanel;

    public AuthForm() {
        super(400, 400);
        setContentPane(mainPanel);
        initButtons();
        setVisible(true);
    }

    private void initButtons()
    {
        authButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());

            try {
                String role = UserManager.getUserRole(login, password);
                if (role == null) {
                    DialogUtil.showError(this, "Неверный логин или пароль");
                    return;
                }

                if ("Администратор".equalsIgnoreCase(role)){
                    Main.IS_ADMIN = true;
                }
                dispose();
                new ProductTable();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        guestButton.addActionListener(e -> {
            dispose();
            new ProductTable();
        });
    }
}
