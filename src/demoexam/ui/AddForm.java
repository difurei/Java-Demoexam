package demoexam.ui;

import demoexam.Main;
import demoexam.entity.Product;
import demoexam.manager.ProductManager;
import demoexam.util.BasicForm;
import demoexam.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class AddForm extends BasicForm {
    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField typeField;
    private JTextArea descriptionField;
    private JTextField imagePathField;
    private JTextField costField;
    private JTextField regDateField;
    private JButton backButton;
    private JButton saveButton;


    public AddForm() {
        super(400, 400);
        setContentPane(mainPanel);

        initButtons();

        setVisible(true);
    }

    private void initButtons()
    {
        saveButton.addActionListener(e -> {
            String title = titleField.getText();
            if (title.isEmpty()|| title.length() > 100) {
                DialogUtil.showError(this,"Неправильный ввод имени");
                return;
            }
            String type = typeField.getText();
            if (type.isEmpty()|| type.length() > 100) {
                DialogUtil.showError(this,"Неправильный ввод типа");
                return;
            }
            String description = descriptionField.getText();
            String imagePath = imagePathField.getText();
            if (imagePath.isEmpty()|| imagePath.length() > 100) {
                DialogUtil.showError(this,"Неправильный ввод путя до изображения");
                return;
            }
            if(costField.getText().isEmpty() || !(isDouble(costField.getText()))) {
                DialogUtil.showError(this, "Ошибка ввода стоимости");
                return;
            }
            double cost = Double.parseDouble(costField.getText());
            if (cost < 0) {
                DialogUtil.showError(this, "Введите положительное число");
                return;
            }
            Date regDate = null;
            try {
                 regDate = Main.DATE_FRORMAT.parse(regDateField.getText());
            } catch (ParseException ex) {
                ex.printStackTrace();
                DialogUtil.showError(this, "Ошибка ввода даты");
                return;
            }

            Product product = new Product(
                  title, type, description, imagePath, cost, regDate
            );

            try {
                ProductManager.insert(product);
                DialogUtil.showInfo(this, "Продукт успешно добавлен");
                dispose();
                new ProductTable();
            } catch (SQLException ex) {
                ex.printStackTrace();
                DialogUtil.showError(this, "Ошибка сохранения данных" + ex.getMessage());
                return;
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new ProductTable();
        });
    }

    public static boolean isDouble(String s) {
        try
        {
            Double.parseDouble(s);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}
