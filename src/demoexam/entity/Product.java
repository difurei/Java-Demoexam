package demoexam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
public class Product {
    private int id;
    private String title;
    private String productType;
    private String  description;
    private String imagePath;
    private Double cost;
    private Date registerDate;

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




