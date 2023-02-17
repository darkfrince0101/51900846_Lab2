package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import org.example.*;

public class Program {
    public static void main(String[] args) throws SQLException {
        menu();
    }

    public static void menu() throws SQLException {
        int choice = 1;
        do {
            System.out.println("---------------MENU------------------");
            System.out.println("1. Read all products");
            System.out.println("2. Read detail of a product by id");
            System.out.println("3. Add a new product");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product by id");
            System.out.println("6. Exit");
            Scanner sc = new Scanner(System.in);
            System.out.print("Your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    ProductDAO productDAO = new ProductDAO();
                    List<Product> products = productDAO.readAll();
                    for (Product product : products) {
                        System.out.println(product.toString());
                    }
                }
                case 2 -> {
                    System.out.print("Enter product ID: ");
                    ProductDAO productDAO2 = new ProductDAO();
                    int id_Read = sc.nextInt();
                    Product product = productDAO2.read(id_Read);
                    if (product == null) {
                        System.out.println("Product not found.");
                    } else {
                        System.out.println(product.toString());
                    }

                }
                case 3 -> {
                    sc.nextLine();
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter product price: ");
                    float price = sc.nextFloat();
                    ProductDAO productDAO3 = new ProductDAO();
                    Product product3 = new Product(0, name, price);
                    Integer id_Add = productDAO3.add(product3);
                    if (id_Add != null) {
                        System.out.println("New product added successful with id: " + id_Add);
                    } else {
                        System.out.println("Failed to add new product, product with the same id already exists.");
                    }
                }
                case 4 -> {
                    System.out.print("Enter the product id to update: ");
                    int id = sc.nextInt();
                    ProductDAO productDAO_update = new ProductDAO();
                    Product product_update = productDAO_update.read(id);
                    if (product_update == null) {
                        System.out.println("Product not found");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter new name: ");
                    String name_update = sc.nextLine();
                    System.out.print("Enter new price: ");
                    double price_update = sc.nextDouble();
                    product_update.setName(name_update);
                    product_update.setPrice(price_update);
                    if (productDAO_update.update(product_update)) {
                        System.out.println("Update product successful");
                    } else {
                        System.out.println("Update product failed");
                    }
                }
                case 5 -> {
                    System.out.print("Enter the product id to delete: ");
                    int id_del = sc.nextInt();
                    ProductDAO productDAO_del = new ProductDAO();
                    boolean isDeleted = productDAO_del.delete(id_del);
                    if (isDeleted) {
                        System.out.println("Delete product successful.");
                    } else {
                        System.out.println("Delete product failed.");
                    }
                }
                case 6 -> System.exit(0);
            }
        } while (choice != 6);
    }
}