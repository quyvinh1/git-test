/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.DBContext;
import java.sql.PreparedStatement;

/**
 *
 * @author FPTSHOP
 */
public class Product extends DBContext{
    private int id;
    private String description,image,name;
    private double price;
    private int quantity;
    private String category;
    private int sale;

    public Product() {
    }

    public Product(int id, String description, String image, String name, double price, int quantity, String category) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Product(int id, String description, String image, String name, double price, int quantity, String category, int sale) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.sale = sale;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }
    
    public boolean addProduct() {
        try {
            Product p = new Product();
            String strSQL = "INSERT INTO [dbo].[Product]\n"
                    + "           ([Description]\n"
                    + "           ,[Image]\n"
                    + "           ,[Name]\n"
                    + "           ,[Price]\n"
                    + "           ,[Quantity]\n"
                    + "           ,[Product_Category_ID])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(strSQL);
            st.setString(1, description);
            st.setString(2, image);
            st.setString(3, name);
            st.setDouble(4, price);
            st.setInt(5, quantity);
            st.setInt(6, Integer.parseInt(category));
            st.execute();
            return true;

        } catch (Exception e) {
            System.out.println("getaddEm:" + e.getMessage());
            return false;
        }
    }
    
    
    
}
