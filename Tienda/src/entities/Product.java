package entities;

public class Product {
    private int id;
    private String name;
    private double price;
    private int manufacturerId;


    public Product() {
    }

    public Product(String name, double price, int manufacturerId) {
        setName(name);
        setPrice(price);
        setManufacturerId(manufacturerId);
    }

    public Product(int id, String name, double price, int manufacturerId) {
        setId(id);
        setName(name);
        setPrice(price);
        setManufacturerId(manufacturerId);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        if (id < 0)
            throw new IllegalArgumentException("Id cannot be less than zero.");
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank.");
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price <= 0) 
            throw new IllegalArgumentException("Price cannot be less than zero.");
        this.price = price;
    }

    public int getManufacturerId() {
        return this.manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        if (manufacturerId < 0) 
            throw new IllegalArgumentException("Invalid Manufacturer ID. ManufacturerId cannot be less than zero.");
        this.manufacturerId = manufacturerId;
    }

    @Override
    public String toString() {
        return 
            "\nid: " + getId() + 
            "\tName: " + getName() + 
            "\tPrice: " + getPrice() + 
            "\tManufacturerid: " + getManufacturerId();
    }

}
