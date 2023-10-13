import java.util.ArrayList;

import entities.Product;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }


    public static void printProducts(ArrayList<Product> products) {
        products.stream()
            .forEach(prod -> System.out.println(prod));
    }
}
