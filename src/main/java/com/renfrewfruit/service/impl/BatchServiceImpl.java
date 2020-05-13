package com.renfrewfruit.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.renfrewfruit.model.*;
import com.renfrewfruit.service.BatchService;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.utility.BatchNumberCreator;
import com.renfrewfruit.utility.DateResolver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
            System.out.print("Select An Option: \n");
            System.out.print("1. Create a New Batch \n2. List All Batches \n3. View Details of a Batch" +
                    "\n4. Sort & Grade a Batch \n5. Quit \n>");

            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    startApplication = true;
                    batchProcess();
                    break;
                case 2:
                    startApplication = true;
                    listAllBatches();
                    break;
//                case 3: startApplication = true;
//                        batchDetails();
//                        break;
//                case 4: startApplication = true;
//                        sortAndGradeBatch();
//                        break;
                case 5:
                    System.out.println("Exiting Application\n");
                default:
                    System.out.println("Invalid Selection\n");
            }

        } while (!startApplication);
    }


    public void batchProcess() {

        String date = dateResolver.processDate();
        Fruit fruitType = processFruitType();
        int batchWeight = processBatchWeight();
        Farm farmNumber = processFarmNumber();
        boolean validBatch = false;

        do {
            System.out.println("-----------------------");
            System.out.println("Today's Date: " + date);
            System.out.println("\nThis batch contains " + batchWeight + "KG " + "of "
                    + fruitType.getProductName() + " from farm number " + farmNumber.getFarmCode() + " received on "
                    + date + ". " + "Is this correct Y/N? \n>");

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

    public Farm processFarmNumber() {

        Farm farm = new Farm();
        int farmNumber;

        do {
            System.out.println("\nEnter Farm Number (001 to 999) \n>");
            farmNumber = scanner.nextInt();

            if (farmNumber < 1 || farmNumber > 999) System.out.println("Invalid Farm Number. Try Again.");
            else farm.setFarmCode(farmNumber);

        } while (farmNumber < 1 || farmNumber > 999);

        return farm;
    }

    public Fruit processFruitType() {

        Fruit fruitType = null;
        System.out.println("\nSelect a Fruit Type: ");
        System.out.println("1. Strawberries\n2. Raspberries\n3. Blackberries\n4. Gooseberries");
        System.out.print("> ");

        switch (scanner.nextInt()) {
            case 1:
                fruitType = new Strawberry();
                break;
            case 2:
                fruitType = new Raspberry();
                break;
            case 3:
                fruitType = new Blackberry();
                break;
            case 4:
                fruitType = new Gooseberry();
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


    public void printDetails(String date, Fruit fruitType, Farm farm, int batchWeight) {

        System.out.println("Print Batch Details Y/N? ");
        System.out.println("> ");
        String print = scanner.next().toUpperCase();
        String mainMenuChoice;

        if (print.equals("Y")) {
            Batch batch = new Batch(fruitType, date, batchWeight, farm);
            fileService.createFile(batch);
            System.out.println("Batch Number: " + batchNumberCreator.createBatchNumber(batch));
            System.out.println("Received Date: " + batch.getBatchDate());
            System.out.println("Fruit Type: " + batch.getProductCode().getProductName());
            System.out.println("Batch Weight: " + batch.getBatchWeight() + "\n");
            System.out.println("Return To Main Menu? Y/N");

            mainMenuChoice = scanner.next();

            if (mainMenuChoice.equals("Y")) {
                openMenu();
            } else System.exit(0);

        } else System.out.println("Batch Not Printed.");
    }


    public void listAllBatches() {

        File[] files = new File("src/main/resources/json/").listFiles();
        ObjectMapper mapper = new ObjectMapper();
        List<String> fileNames = new ArrayList<>();

        System.out.println("Current Batches");

        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
                fileNames.forEach(b -> {
                    try {
                        Batch batch = mapper.readValue(Paths.get("src/main/resources/json/" + b).toFile(), Batch.class);
                        System.out.print(b.substring(0, b.lastIndexOf(".")) + "\t");
                        System.out.print(batch.getProductCode().getProductName() + "\t");
                        System.out.print(batch.getOriginCode().getFarmCode() + "\t");
                        System.out.print(batch.getBatchWeight() + "kg"  + "\t");
                        System.out.print(batch.getBatchDate() + "\n");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        } else System.out.println("No Batches Available");
    }
}
