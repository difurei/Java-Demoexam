package demoexam.ui;

import demoexam.entity.Product;
import demoexam.manager.ProductManager;
import demoexam.util.BasicForm;
import demoexam.util.CustomTableModel;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductTable extends BasicForm {
    private JPanel mainPanel;
    private JTable table;
    private JButton addButton;
    private JComboBox nameFilterBox;
    private JButton removeFilters;
    private JLabel rowCountLabel;

    private CustomTableModel<Product> model;

    public ProductTable() {
        super(1200, 800);

        setContentPane(mainPanel);

        initTable();
        initBoxes();
        initButtons();

        setVisible(true);
    }

    private void initTable()
    {
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(50);

        //вывод полей из базы данных
        try {
            model = new CustomTableModel<>(
               Product.class,
                    new String[] {"ID", "Название", "Тип", "Описание", "Путь До Изображения", "Стоимость", "Дата Регистрации", "Картинка"},
                    ProductManager.selectAll()
            );
            table.setModel(model);

            TableColumn column = table.getColumn("Путь До Изображения");
            column.setMinWidth(0);
            column.setPreferredWidth(0);
            column.setMaxWidth(0);

            updateRowCountLabel(model.getRows().size(), model.getRows().size());

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

    private void initBoxes()
    {
        nameFilterBox.addItem("Все");
        try {
            List<Product> list = ProductManager.selectAll();
            Set<String> names = new HashSet<>();

            for(Product p : list){
                names.add(p.getProductType());
            }
            for (String s : names){
                nameFilterBox.addItem(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        nameFilterBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED)
                {
                    applyFilters();
                }
            }
        });
    }

    private void applyFilters()
    {
        try {
            List<Product> list = ProductManager.selectAll();
            int max = list.size();

            if (nameFilterBox.getSelectedIndex() != 0) {
                list.removeIf(p -> !p.getProductType().equals(nameFilterBox.getSelectedItem()));
            }

            model.setRows(list);
            model.fireTableDataChanged();

            updateRowCountLabel(list.size(), max);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateRowCountLabel(int actual, int max) {
        rowCountLabel.setText("Отображено записей: " + actual + " / " + max);
    }

    private void initButtons()
    {
        addButton.addActionListener(e -> {
            dispose();
            new AddForm();
        });

        removeFilters.addActionListener(e -> {
            nameFilterBox.setSelectedIndex(0);
        });
    }
}
