package demoexam.manager;

import demoexam.Main;
import demoexam.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
ID int AI PK
        Title varchar(100)
        ProductType varchar(100)
        Description longtext
        Image varchar(100)
        Cost decimal(10,2)
        RegisterDate date
 */

public class ProductManager {
    public static void insert(Product product) throws SQLException {
        try (Connection c = Main.getConnection())
        {
            String sql = "INSERT INTO Product(Title, ProductType, Description, Image, Cost, RegisterDate) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getProductType());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getImagePath());
            ps.setDouble(5, product.getCost());
            ps.setTimestamp(6, new Timestamp(product.getRegisterDate().getTime()));

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                product.setId(keys.getInt(1));
                return;
            }
            throw new SQLException("entity not added");

        }
    }

    public static void update(Product product) throws SQLException {
        try (Connection c = Main.getConnection())
        {
            String sql = "UPDATE Product SET Title=?, ProductType=?, Description=?, Image=?, Cost=?, RegisterDate=? WHERE ID=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getProductType());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getImagePath());
            ps.setDouble(5, product.getCost());
            ps.setTimestamp(6, new Timestamp(product.getRegisterDate().getTime()));
            ps.setInt(7, product.getId());

            ps.executeUpdate();

        }
    }

    public static Product selectById(int id) throws SQLException {
        try (Connection c = Main.getConnection())
        {
            String sql = "SELECT FROM Product WHERE ID = ?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return new Product(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("ProductType"),
                        resultSet.getString("Description"),
                        resultSet.getString("Image"),
                        resultSet.getDouble("Cost"),
                        resultSet.getTimestamp("RegisterDate")
                );
            }
            return null;
        }
    }

    public static List<Product> selectAll() throws SQLException {
        try (Connection c = Main.getConnection())
        {
            String sql = "SELECT * FROM Product";

            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<Product> list = new ArrayList<>();
            while (resultSet.next()){
                list.add(new Product(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("ProductType"),
                        resultSet.getString("Description"),
                        resultSet.getString("Image"),
                        resultSet.getDouble("Cost"),
                        resultSet.getTimestamp("RegisterDate")
                ));
            }
            return list;
        }
    }

    public static void delete(Product product) throws SQLException {
        try (Connection c = Main.getConnection())
        {
            String sql = "DELETE FROM Product WHERE ID = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, product.getId());
            ps.executeUpdate();
        }
    }
}
