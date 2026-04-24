
package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Starter code for the Online Store workshop.
 * Students will complete the TODO sections to make the program work.
 */
public class Store {

    public static void main(String[] args) {

        // Create lists for inventory and the shopping cart
        List<Product> inventory = new ArrayList<>();
        List<Product> cart = new ArrayList<>();

        // Load inventory from the data file (pipe-delimited: id|name|price)
        loadInventory("products.csv", inventory);

        // Main menu loop
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 3) {
            System.out.println("\nWelcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter 1, 2, or 3.");
                scanner.nextLine();                 // discard bad input
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();                     // clear newline

            switch (choice) {
                case 1 -> displayProducts(inventory, cart, scanner);
                case 2 -> displayCart(cart, scanner);
                case 3 -> System.out.println("Thank you for shopping with us!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }

    /**
     * Reads product data from a file and populates the inventory list.
     * File format (pipe-delimited):
     * id|name|price
     * <p>
     * Example line:
     * A17|Wireless Mouse|19.99
     */
    public static void loadInventory(String fileName, List<Product> inventory) {
        String line;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while ((line = reader.readLine()) != null) {

                String[] divider = line.split("\\|");
                String id = divider[0];
                String name = divider[1];
                double price = Double.parseDouble(divider[2]);

                Product product = new Product(id, name, price);
                inventory.add(product);

            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.err.println("Something went wrong");
        }
        // TODO: read each line, split on "|",
        //       create a Product object, and add it to the inventory list
    }

    /**
     * Displays all products and lets the user add one to the cart.
     * Typing X returns to the main menu.
     */
    public static void displayProducts(List<Product> inventory, List<Product> cart, Scanner scanner) {
        for (Product product : inventory) {
            System.out.println(product);
        }
        while (true) {
            System.out.print("What item # are you interested in? ");
            String id = scanner.nextLine();

            Product matchedProduct = findProductById(id, inventory);

            if (matchedProduct == null) {
                System.out.println("We don't carry that product");
            } else {
                System.out.printf("We carry %s and the price is $%.2f", matchedProduct.getName(), matchedProduct.getPrice());
            }

            cart.add(matchedProduct);

            System.out.println();
            System.out.println("Do you want to Stop?");
            System.out.println("(x to go back to main menu)"/*or (c to continue to cart)*/);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("x")) {
                return;
            } /*else if (input.equalsIgnoreCase("c")) {
                displayCart(cart,scanner);
            }*/
        }

        // TODO: show each product (id, name, price),
        //       prompt for an id, find that product, add to cart
    }

    /**
     * Shows the contents of the cart, calculates the total,
     * and offers the option to check out.
     */
    public static void displayCart(List<Product> cart, Scanner scanner) {
        for (Product product : cart) {
            System.out.println(product);
        }
        double price = 0;
        for (Product priceCart : cart) {
            price += priceCart.getPrice();
        }
        System.out.println("Your total is : $" + price);

        String input;

        do {
            System.out.println();
            System.out.println("Do you want to Stop?");
            System.out.println("(x to go back to main menu)or (c to continue to cart)");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("x")) {
                return;
            } else if (input.equalsIgnoreCase("c")) {
                checkOut(cart,price,scanner);
            }
        } while (!input.equalsIgnoreCase("c"));


        // TODO:
        //   • list each product in the cart
        //   • compute the total cost
        //   • ask the user whether to check out (C) or return (X)
        //   • if C, call checkOut(cart, totalAmount, scanner)
    }

    /**
     * Handles the checkout process:
     * 1. Confirm that the user wants to buy.
     * 2. Accept payment and calculate change.
     * 3. Display a simple receipt.
     * 4. Clear the cart.
     */
    public static void checkOut(List<Product> cart, double totalAmount, Scanner scanner) {


        // TODO: implement steps listed above
    }

    /**
     * Searches a list for a product by its id.
     *
     * @return the matching Product, or null if not found
     */
    public static Product findProductById(String id, List<Product> inventory) {

        for (Product product : inventory) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;

        // TODO: loop over the list and compare ids
    }
}


