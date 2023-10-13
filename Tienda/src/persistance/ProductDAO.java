package persistance;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Product;

public final class ProductDAO extends DAO {
    
    public void saveProduct(Product prod) throws SQLException {
        try {
            if (prod == null) {
                throw new IllegalArgumentException("Product cannot be null.");
            }
            String query = "INSERT INTO producto(nombre, precio, codigo_fabricante) " + "VALUES( '" + prod.getName() + "', " + prod.getPrice() + ", " + prod.getManufacturerId() + ");";  
            insertUpdateDelete(query);
        } catch (SQLException e) {
            throw e;
        }
    }

    public void updateProduct(Product prod) throws SQLException {
        try {
            if (prod == null) {
                throw new IllegalArgumentException("Product cannot be null.");      
            }
            String query = "UPDATE producto SET " + 
                "nombre = '" + prod.getName() + "', " +
                "precio = " + prod.getPrice() + ", " + 
                "codigo_fabricante = " + prod.getManufacturerId() +
                "WHERE codigo = " + prod.getId() + ";";
            
            insertUpdateDelete(query);
        } catch (SQLException e) {
            throw e;
        }
    }

    public void deleteProduct(int id) throws SQLException {
        try {
            String query = "DELETE FROM producto WHERE codigo = " + id;
            insertUpdateDelete(query);
        } catch (SQLException e) {
            throw e;
        }
    }

    public Product getProductById(int id) throws SQLException {
        try {
            String query = "SELECT * FROM producto WHERE codigo = " + id;
            queryDatabase(query);
            Product prod = null;
            while (result.next()) {
                prod = mapProduct();     
            }
            disconnectDatabase();                
            return prod;
        } catch (SQLException e) {
            disconnectDatabase();
            throw e;
        }
    }

    public ArrayList<Product> getProducts() throws SQLException {
        try {
            String query = "SELECT * FROM producto";
            queryDatabase(query);
            ArrayList<Product> products = new ArrayList<>();
            while (result.next()) {
                Product prod = mapProduct();
                products.add(prod);
            }
            disconnectDatabase();
            return products;
        } catch (SQLException e) {
            disconnectDatabase();
            throw e;
        }
    }

    /**
     * Search into the database for the cheapest product and returns a new instance of Product, 
     * containing all the information retrieved from the DB.
     * @return a new instance of Product containing all the information retrieved from the DB.
     * @throws SQLException
     */
    public Product getCheapestProduct() throws SQLException {
        try {
            String query = "SELECT * FROM producto WHERE precio = (SELECT MIN(precio) FROM producto)";
            queryDatabase(query);
            Product prod = null;
            while (result.next()) {
                prod = mapProduct();
            }
            disconnectDatabase();
            return prod;
        } catch (SQLException e) {
            disconnectDatabase();
            throw e;
        }
    }

    /**
     * Generete a new instance of Product and fills its attributes with the information retrieved from the DB.
     * @return a new Product that contains all the information retrieved from the DB.
     * @throws SQLException
     */
    private Product mapProduct() throws SQLException {
        Product prod = new Product();
        prod.setId(result.getInt("codigo"));
        prod.setName(result.getString("nombre"));
        prod.setPrice(result.getDouble("precio"));
        prod.setManufacturerId(result.getInt("codigo_fabricante")); 
        return prod;
    }

    /**
     * Search for all the products in the database that have their prices between the values recieved as arguments.
     * @param price1
     * @param price2
     * @return an ArrayList with all the products that satisfy the condition (product's price is between the two values recieved as arguments)
     * @throws SQLException
     */
    public ArrayList<Product> getProductsInRange(double price1, double price2) throws SQLException {
        try {
            String query = "SELECT * FROM producto WHERE precio BETWEEN " + Math.min(price1,price2) + " AND " + Math.max(price1, price2);
            queryDatabase(query);
            ArrayList<Product> products = new ArrayList<>();
            while (result.next()) {
                Product prod = mapProduct();
                products.add(prod);
            }
            disconnectDatabase();
            return products;
        } catch (SQLException e) {
            disconnectDatabase();
            throw e;
        }
    }

    /**
     * Search the DB for all the products containing the String recieved as argument in their names.
     * @param string
     * @return A List of Product objects with their corresponding information retrieved from the DB.
     * @throws SQLException
     */
    public ArrayList<Product> getProductsContaining(String string) throws SQLException {
        try {
            String query = "SELECT * FROM producto WHERE nombre LIKE '%" + string + "%';";
            queryDatabase(query);
            ArrayList<Product> products = new ArrayList<>();
            while (result.next()) {
                Product prod = mapProduct();
                products.add(prod);
            }
            disconnectDatabase();
            return products;
        } catch (SQLException e) {
            disconnectDatabase();
            throw e;
        }
    }
}


