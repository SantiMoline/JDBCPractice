package services;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Product;
import persistance.ProductDAO;

public class ProductService {
    
    private ProductDAO dao;


    public ProductService() {
        dao = new ProductDAO();
    }

    public Product createProduct(String name, double price, int manufacturerId) {
        if (name == null || name.isBlank())
        throw new IllegalArgumentException("Name cannot be null or blank.");
        if (price <= 0) 
            throw new IllegalArgumentException("Price cannot be less than zero.");
        if (manufacturerId < 0) 
            throw new IllegalArgumentException("Invalid Manufacturer ID. ManufacturerId cannot be less than zero.");
        
        Product prod = new Product();
        prod.setName(name);
        prod.setPrice(price);
        prod.setManufacturerId(manufacturerId);
        return prod;
    }

    public void persistProduct(Product prod) throws SQLException {
        dao.saveProduct(prod);
    }

    public void updateProduct(int id, Product prod) throws SQLException {
        Product storedProd = dao.getProductById(id);
        if (storedProd == null) {
            throw new IllegalArgumentException("The product did not exist in our database.");
        }
        dao.updateProduct(prod);
    }

    public ArrayList<Product> getProducts() throws SQLException {
        return dao.getProducts();
    }

    public ArrayList<Product> showProductsInRange(double price1, double price2) throws SQLException {
        return dao.getProductsInRange(price1, price2);
    }

    public void showCheapestProduct() throws SQLException {
        System.out.println(dao.getCheapestProduct());
    }

    public ArrayList<Product> getProductsContaining(String word) throws SQLException {
        return dao.getProductsContaining(word);
    }

}
