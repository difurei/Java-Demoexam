package demoexam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class Product {
    private int id;
    private String title;
    private String productType;
    private String  description;
    private String imagePath;
    private Double cost;
    private Date registerDate;

    private ImageIcon image;

    public Product(int id, String title, String productType, String description, String imagePath, Double cost, Date registerDate) {
        this.id = id;
        this.title = title;
        this.productType = productType;
        this.description = description;
        this.imagePath = imagePath;
        this.cost = cost;
        this.registerDate = registerDate;

        try {
            this.image = new ImageIcon(
                    ImageIO.read(Product.class.getClassLoader().getResource(imagePath))
                            .getScaledInstance(50, 50, Image.SCALE_DEFAULT)
            );
        } catch (Exception e) {
        }
    }

    public Product(String title, String productType, String description, String imagePath, Double cost, Date registerDate) {
        this.id = -1;
        this.title = title;
        this.productType = productType;
        this.description = description;
        this.imagePath = imagePath;
        this.cost = cost;
        this.registerDate = registerDate;
    }
}




