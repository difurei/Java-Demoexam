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

public class UpdateForm extends BasicForm {
    private JPanel mainPanel;
    private JTextField titleField;
    private JTextField typeField;
    private JTextArea descriptionField;
    private JTextField imagePathField;
    private JTextField costField;
    private JTextField regDateField;
    private JButton backButton;
    private JButton saveButton;
    private JTextField idField;
    private JButton deleteButton;

    private Product product;

    public UpdateForm(Product product) {
        super(400, 400);
        this.product = product;

        setContentPane(mainPanel);

        initButtons();
        initFields();

        setVisible(true);
    }

    private void initFields()
    {
        idField.setText(String.valueOf(product.getId()));
        titleField.setText(product.getTitle());
        typeField.setText(product.getProductType());
        descriptionField.setText(product.getDescription());
        imagePathField.setText(product.getImagePath());
        costField.setText(String.valueOf(product.getCost()));
        regDateField.setText(Main.DATE_FRORMAT.format(product.getRegisterDate()));
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
                return;
            }

            product.setTitle(title);
            product.setProductType(type);
            product.setDescription(description);
            product.setImagePath(imagePath);
            product.setCost(cost);
            product.setRegisterDate(regDate);

            try {
                ProductManager.update(product);
                DialogUtil.showInfo(this, "Продукт успешно отредактирован");
                dispose();
                new ProductTable();
            } catch (SQLException ex) {
                ex.printStackTrace();
                DialogUtil.showError(this, "Ошибка редактирования данных" + ex.getMessage());
                return;
            }
        });

        deleteButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Вы точно хотите удалить этот продукт?", "Подтверждение", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            {
                try {
                    ProductManager.delete(product);
                    DialogUtil.showInfo(this, "Продукт успешно удалён");
                    dispose();
                    new ProductTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    DialogUtil.showError(this, "Ошибка удаления продукта" + ex.getMessage());
                    return;
                }
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
