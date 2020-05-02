package com.renfrewfruit.service.impl;

import com.renfrewfruit.model.Batch;
import com.renfrewfruit.service.BatchService;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.utility.BatchNumberCreator;
import com.renfrewfruit.utility.DateResolver;

import java.util.Scanner;

public class BatchServiceImpl implements BatchService {

    private final DateResolver dateResolver = new DateResolver();
    private final Scanner scanner = new Scanner(System.in);
    private final FileService fileService = new FileServiceImpl();
    private final BatchNumberCreator batchNumberCreator = new BatchNumberCreator();

    public void openMenu() {

        boolean startApplication = false;

        do {
            System.out.println("Welcome To Renfrewshire Soft Fruits Cooperative \n");
            System.out.println("Select An Option: \n");
            System.out.println("1. Create New Batch \n2. Quit");
            System.out.print("> ");

            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    startApplication = true;
                    batchProcess();
                    break;
                case 2:
                    startApplication = true;
                    break;
                default:
                    System.out.println("Invalid Selection\n");
            }

        } while (!startApplication);
    }


    public void batchProcess() {

        String date = dateResolver.processDate();
        String fruitType = processFruitType();
        int batchWeight = processBatchWeight();
        int farmNumber = processFarmNumber();
        boolean validBatch = false;

        do {
            System.out.println("-----------------------");
            System.out.println("Today's Date: " + date);
            System.out.println("\nThis batch contains " + batchWeight + "KG " + "of "
                    + fruitType + " from farm number " + farmNumber + " received on "
                    + date + ". " + "Is this correct Y/N?");
            System.out.print("> ");

            String isValid = scanner.next().toUpperCase();

            switch (isValid) {
                case "Y":
                    validBatch = true;
                    printDetails(date, fruitType, farmNumber, batchWeight);
                    break;
                case "N":
                    System.out.println("No Problem. Let's Start Again.");
                    batchProcess();
                    break;
            }
        } while (!validBatch);
    }

    public int processFarmNumber() {

        int farmNumber;

        do {
            System.out.println("\nEnter Farm Number (001 to 999)");
            System.out.print("> ");
            farmNumber = scanner.nextInt();

            if (farmNumber < 1 || farmNumber > 999) System.out.println("Invalid Farm Number. Try Again.");

        } while (farmNumber < 1 || farmNumber > 999);

        return farmNumber;
    }

    public String processFruitType() {

        String fruitType = null;
        System.out.println("\nSelect a Fruit Type: ");
        System.out.println("1. Strawberries\n2. Raspberries\n3. Blackberries\n4. Gooseberries");
        System.out.print("> ");

        switch (scanner.nextInt()) {
            case 1:
                fruitType = "Strawberries";
                break;
            case 2:
                fruitType = "Raspberries";
                break;
            case 3:
                fruitType = "Blackberries";
                break;
            case 4:
                fruitType = "Gooseberries";
                break;
            default:
                System.out.println("Invalid Selection. Try Again.");
                processFruitType();
        }
        return fruitType;
    }

    public int processBatchWeight() {

        int batchWeight;

        do {
            System.out.println("\nEnter Batch Weight in kg's (Max Weight Is 100kg)");
            System.out.print("> ");
            batchWeight = scanner.nextInt();

            if (batchWeight < 1 || batchWeight > 100) System.out.println("Invalid Batch Weight. Try Again.");

        } while (batchWeight < 1 || batchWeight > 100);

        return batchWeight;
    }


    public void printDetails(String date, String fruitType, int farmNumber, int batchWeight) {

        System.out.println("Print Batch Details Y/N? ");
        System.out.println("> ");
        String print = scanner.next().toUpperCase();
        String mainMenuChoice;

        if (print.equals("Y")) {
            Batch batch = new Batch(fruitType, date, batchWeight, farmNumber);
            fileService.createFile(batch);
            System.out.println("Batch Number: " + batchNumberCreator.createBatchNumber(batch));
            System.out.println("Received Date: " + batch.getBatchDate());
            System.out.println("Fruit Type: " + batch.getProductCode());
            System.out.println("Batch Weight: " + batch.getBatchWeight() + "\n");
            System.out.println("Return To Main Menu? Y/N");

            mainMenuChoice = scanner.next();

            if (mainMenuChoice.equals("Y")) {
                openMenu();
            } else System.exit(0);

        } else System.out.println("Batch Not Printed.");
    }
}
