package com.cmpe202;

import java.io.File;
import java.util.Scanner;

public class Billing {

    /**
     *  /home/shubhada/202-individual-project/Sample_Inventory_Input_Output.csv
     */


    public static void main (String[] args) throws Exception
    {

        Inventory inventory = new Inventory();

        System.out.println("----------------OUTPUT-----------------");
        String filename = args[0];
        System.out.println("Filename: " + filename);

        try {
            Scanner scanner = new Scanner(new File(filename));

            //Skip first two lines
            scanner.nextLine();
            scanner.nextLine();

            //Read the file line by line
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


        inventory.printInventory();

//        Essentials cloths = new Essentials(100,20);
//        Essentials soap = new Essentials(200,5);
//        Essentials shampoo = new Essentials(200,10);
//        Essentials milk = new Essentials(100,5);
//        Luxury perfume = new Luxury(50,50);
//        Luxury chocolates = new Luxury(300,3);
//        Luxury handbag = new Luxury(75,150);
//        Luxury wallet = new Luxury(100,100);
//        Misc bedSheet = new Misc(150,75);
//        Misc footware = new Misc(200,25);
//        Misc homeDecorePiece = new Misc(100,40);
//        Misc pen = new Misc(400,3);
//        Misc pencile = new Misc(400,3);

        //parsing a CSV file into Inventory class

       // Inventory inventory = new Inventory(new File ("/home/shubhada/Desktop/csv-inventory.csv"));

    }
}

