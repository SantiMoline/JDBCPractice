package entities;

public class Product {
    private int id;
    private String name;
    private double price;
    private int manufacturerId;


    public Product() {
    }

    public Product(String name, double price, int manufacturerId) {
        this.name = name;
        this.price = price;
        this.manufacturerId = manufacturerId;
    }

    public Product(int id, String name, double price, int manufacturerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.manufacturerId = manufacturerId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getManufacturerId() {
        return this.manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    @Override
    public String toString() {
        return 
            "\nid: " + getId() + 
            "\nName: " + getName() + 
            "\nPrice: " + getPrice() + 
            "\nManufacturerid: " + getManufacturerId();
    }

}
