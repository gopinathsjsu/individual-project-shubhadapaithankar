package com.cmpe202;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Billing {

    /**
     * /home/shubhada/202-individual-project/Sample_Inventory_Input_Output.csv
     */

    public static void main(String[] args) throws Exception {

        Inventory inventory = new Inventory();
        CardDatabase cardDatabase = new CardDatabase();
        Order order = new Order();

        if (args.length != 2) {
            throw new IllegalArgumentException("inventory.csv and input.csv should be provided.");
        }

        System.out.println("\n----------------OUTPUT-----------------");
        String inventoryFilename = args[0];
        System.out.println("\nInventory Filename: " + inventoryFilename);


        //Read the file line by line to prepare inventory
        parseInventory(inventory, inventoryFilename);
        parseCreditCard(cardDatabase, inventoryFilename);

        inventory.printInventory();
        cardDatabase.printCardDatabase();


        String inputFilename = args[1];
        System.out.println("\nInput Filename: " + inputFilename);


        parseInputFile(order, inputFilename);
        
        order.printOrder();


        Handler handler = initChainOfResponsibility(cardDatabase);

        handler.handleRequest(order, inventory);
    }

    private static Handler initChainOfResponsibility(CardDatabase cardDatabase) {
        Handler validationHandler = new ValidationHandler();
        Handler cardReaderHandler = new CardReaderHandler(cardDatabase);
        Handler orderSuccessfulHandler = new OrderSuccessfulHandler();
        Handler orderFailureHandler = new OrderFailureHandler();

        validationHandler.setNext(cardReaderHandler);
        cardReaderHandler.setNext(orderSuccessfulHandler);
        orderSuccessfulHandler.setNext(orderFailureHandler);

        return validationHandler;
    }

    private static void parseInputFile(Order order, String inputFilename) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(new File(inputFilename));


            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] split = currentLine.split(",");

                String itemName = split[0];
                String quantity = split[1];

                Order.OrderItem orderItem = new Order.OrderItem(itemName, quantity);

                order.putOrderItem(orderItem);

                if (split.length == 3) {
                    String cardNumber = split[2];
                    order.addCardNumber(cardNumber);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

    }

    private static void parseCreditCard(CardDatabase cardDatabase, String filename) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filename));

        try {

            //inventory lines are skilled from the csv
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();

                if (currentLine.equals("Cards")) {
                    break;
                }
            }

            // ColumnName is read which is CardNumber
            String columnName = scanner.nextLine();
            System.out.println("columnName should be CardNumber. columnName : " + columnName);

            //Actual cards are read.
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();

                cardDatabase.addIfAbsent(currentLine); //Card is added in the database;
            }



            scanner.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static void parseInventory(Inventory inventory, String filename) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filename));

        try {

            scanner.nextLine(); // Table name 'Items' is skipped
            scanner.nextLine(); // Column Names are skipped.

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();


                String[] str = currentLine.split(",");

                if (currentLine.isBlank()) {
                    System.out.println("Empty line is encountered. We stop scanning the inventory");
                    break;
                }

                System.out.println(currentLine);
//                System.out.println(str[1]);

                String itemName = str[0];
                String category = str[1];
                String quantity = str[2];
                String costPerUnit = str[3];


                Component component = Factory.create(itemName, category, quantity, costPerUnit);
                inventory.add(component);


            }
            scanner.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        }


    }
}

