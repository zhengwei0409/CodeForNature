
package PointsSection;

import Triviasection.ConnectDatabase;
import java.io.*;
import java.util.*;

public class PointsShop {
    
    public void orderMerchandise() {
        
        // connect to database
        ConnectDatabase database = new ConnectDatabase();
        
        // get user's username from database
        String username = database.getUsername();
        
        // get points from database
        int points = database.getCurrentPoint();

        try {
            PrintWriter merchandiseWriter = new PrintWriter(new FileWriter("MerchandiseOrder.txt", true));
            Scanner scanner = new Scanner(System.in);

            // Greeting the user
            System.out.println("Welcome to the Points Shop!");

            // Asking if the user wants to use points for merchandise
            System.out.println("There are 3 merchandises for you to choose :");
            System.out.println("merch-1 cost : 5 points");
            System.out.println("merch-2 cost : 10 points");
            System.out.println("merch-3 cost : 20 points");
            System.out.print("Do you want to use your points to purchase merchandise? (Y/N): ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("Y")) {
                // Getting user input for merchandise
                System.out.print("Enter merchandise ID: ");
                String merchandiseID = scanner.nextLine();

                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                System.out.print("Enter delivery address: ");
                
                String address = scanner.nextLine();
                
                // calculate the total cost
                int totalCost = 5 * quantity;
                System.out.println("Total cost : " + totalCost + " points");
                
                if(merchandiseID.equalsIgnoreCase("merch-1")) {
                    points -= totalCost;
                } else if (merchandiseID.equalsIgnoreCase("merch-2")) {
                    points -= 10 * quantity;
                } else {
                    points -= 15 * quantity;
                }
                
                // if the user dont have enough point
                if(points < 0) {
                    System.out.println("You don't have enough point!");
                    return; // exit the method
                }

                // Writing merchandise order to the text file
                merchandiseWriter.println(username + " orders " + quantity + " " + merchandiseID + " to " + address);
                merchandiseWriter.close();

                System.out.println("Order placed successfully in MerchandiseOrder.txt.");
                
                // update the point in database
                database.updateCurrentPoint(points);
            } else {
                System.out.println("No merchandise order placed.");
            }

            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void plantTree() {
        
        // connect to database
        ConnectDatabase database = new ConnectDatabase();
        
        // get user's username from database
        String username = database.getUsername();
        
        // get points from database
        int points = database.getCurrentPoint();
        
        try {
            PrintWriter treeWriter = new PrintWriter(new FileWriter("TreePlantOrder.txt",true));
            Scanner scanner = new Scanner(System.in);
            
            // Asking if the user wants to plant a tree
            System.out.println("Plant a tree cost : 3 points");
            System.out.print("Do you want to plant a tree? (Y/N): ");
            String treeResponse = scanner.nextLine();

            if (treeResponse.equalsIgnoreCase("Y")) {

                System.out.print("Enter the name for the tree: ");
                String treeName = scanner.nextLine();
                
                points -= 3;
                
                // if the user dont have enough point
                if(points < 0) {
                    System.out.println("You don't have enough point!");
                    return; // exit the method
                }
                
                // Writing tree planting order to the text file
                treeWriter.println(username + " plants a tree with the name \"" + treeName + "\"");
                treeWriter.close();

                System.out.println("Tree planting order placed successfully in TreePlantOrder.txt.");
                
                // update the point in database
                database.updateCurrentPoint(points);
                
                System.out.println("Thanks for shopping!");
            } else {
                System.out.println("No tree planting order placed.");
                System.out.println("Thanks for shopping!");
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
}
