import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Manufacturer;
import entities.Product;
import services.ManufacturerService;
import services.ProductService;

public class App {
    final static int MAX_OPTION = 9;
    static ManufacturerService ms = new ManufacturerService();
    static ProductService ps = new ProductService();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayList<Product> products;
        boolean active = true;
        System.out.println("\t\t-------Welcome to DeviceStore-------");
        
        try {
            while (active) {
                showMenu();
                int opc = promptForOption(scan);
                switch (opc) {
                    case 1:
                        products = ps.getProducts();
                        products.stream()
                            .forEach(prod -> System.out.println(prod.getName()));
                        break;
                    case 2:
                        products = ps.getProducts();
                        printProducts(products);
                        break;
                    case 3:
                        double price1 = promptForPrice(scan);
                        double price2 = promptForPrice(scan);
                        products = ps.getProductsInRange(price1, price2);
                        printProducts(products);
                        break;
                    case 4:
                        String word = promptForWord(scan);
                        products = ps.getProductsContaining(word);
                        printProducts(products);
                        break;
                    case 5:
                        System.out.println("Getting cheapest product....");
                        System.out.println(ps.getCheapestProduct());
                        break;
                    case 6:
                        Product newProd = promptAndCreateProduct(scan);
                        ps.persistProduct(newProd);
                        break;
                    case 7:
                        System.out.println("Enter the id of the product you wish to update.");
                        int id = promptForProductId(scan);
                        System.out.println("Enter the new information for that product.");
                        Product prod = promptAndCreateProduct(scan);
                        
                        ps.updateProduct(id, prod);
                        break;
                    case 8:
                        String manufacturerName = promptForName(scan);
                        Manufacturer newManufacturer = ms.createManufacturer(manufacturerName);
                        ms.persistManufacturer(newManufacturer);
                        break;
                    case 9:
                        active = false;
                        System.out.println("Hope you found what you were looking for! Until next time!");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("SQL Broken.");
        }
    }


    public static void showMenu() {
        System.out.println("\n\n\t\t-------What would you like to do?-------");
        System.out.println("1. Get a list of all available products.");
        System.out.println("2. Get a detailed list of all available products (including prices).");
        System.out.println("3. Show products between a range of prices.");
        System.out.println("4. Show products containing a chosen word.");
        System.out.println("5. Get the cheapest product.");
        System.out.println("6. Insert a new product into the DB.");
        System.out.println("7. Edit a product by id.");
        System.out.println("8. Insert a new manufacturer into the DB.");
        System.out.println("9. Exit.");
    }

    public static int promptForOption(Scanner scan) {
        while (true) {
            System.out.print("Choose a valid option (1-9): ");
            if(scan.hasNextInt()) {
                int opt = scan.nextInt();
                scan.nextLine(); //Throwaway scan.
                if(!isInvalidOption(opt)) {
                    return opt;
                }
            } else {
                scan.next(); //To capture incorrect input from user.
            }
        }
    }

    public static boolean isInvalidOption(int opt) {
        return opt < 0 || opt > MAX_OPTION;
    }

    public static double promptForPrice(Scanner scan) {
        while (true) {
            System.out.print("Insert a valid price: ");
            if(!scan.hasNextDouble()) {
                scan.nextLine(); //To capture incorrect input;
                continue;
            }
            double price = scan.nextDouble();
            scan.nextLine(); //Throwaway scan.
            if(!isInvalidPrice(price))
                return price;
        }
    }

    public static boolean isInvalidPrice(double n) {
        return n < 0;
    }

    public static String promptForWord(Scanner scan) {
        while (true) {
            System.out.print("Insert the word contained by the elements you are looking for: ");
            String word = scan.nextLine();
            if (isInvalidWord(word))
                continue;
            return word;
        }
    }

    public static boolean isInvalidWord(String word) {
        return word == null || word.isEmpty();
    }

    public static void printProducts(ArrayList<Product> products) {
        products.stream()
            .forEach(prod -> System.out.println(prod));
    }

    public static String promptForName(Scanner scan) {
        while (true) {
            System.out.print("Insert a valid name: ");
            String word = scan.nextLine();
            if (isInvalidWord(word))
                continue;
            return word;
        }
    }

    public static int promptForManufacturerId(Scanner scan) throws SQLException {
        while (true) {
            System.out.print("Insert a valid Manufacturer's Id: ");
            if(scan.hasNextInt()) {
                int opt = scan.nextInt();
                scan.nextLine(); //Throwaway scan.
                if(!isInvalidManufacturerId(opt)) {
                    return opt;
                }
            } else {
                scan.next(); //To capture incorrect input from user.
            }
        }
    }

    public static boolean isInvalidManufacturerId(int id) throws SQLException {
        return id < 0 || id > ms.getMaxManufacturerId();
    }

    public static Product promptAndCreateProduct(Scanner scan) throws SQLException {
        String name = promptForName(scan);
        double price = promptForPrice(scan);
        int manufacturerId = promptForManufacturerId(scan);
        return ps.createProduct(name, price, manufacturerId);
    }

    public static int promptForProductId(Scanner scan) throws SQLException {
        while (true) {
            System.out.print("Insert a valid Product's Id: ");
            if(scan.hasNextInt()) {
                int opt = scan.nextInt();
                scan.nextLine(); //Throwaway scan.
                if(opt > 0) {
                    return opt;
                }
            } else {
                scan.next(); //To capture incorrect input from user.
            }
        }
    }

}
