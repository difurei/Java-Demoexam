package demoexam.ui;

import demoexam.entity.Product;
import demoexam.manager.ProductManager;
import demoexam.util.BasicForm;
import demoexam.util.CustomTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ProductTable extends BasicForm {
    private JPanel mainPanel;
    private JTable table;
    private JButton addButton;

    private CustomTableModel<Product> model;

    public ProductTable() {
        super(1200, 800);

        setContentPane(mainPanel);

        initTable();
        initButtons();

        setVisible(true);
    }

    private void initTable()
    {
        table.getTableHeader().setReorderingAllowed(false);

        //вывод полей из базы данных
        try {
            model = new CustomTableModel<>(
               Product.class,
                    new String[] {"ID", "Название", "Тип", "Описание", "Путь До Изображения", "Стоимость", "Дата Регистрации"},
                    ProductManager.selectAll()
            );
            table.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //редактирование по двойному клику мыши
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row != 1) {
                        dispose();
                        new UpdateForm(model.getRows().get(row));
                    }
                }
            }
        });


    }

    private void initButtons()
    {
        addButton.addActionListener(e -> {
            dispose();
            new AddForm();
        });
    }
}
