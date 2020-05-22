package com.renfrewfruit.service.impl;

import com.renfrewfruit.model.Batch;
import com.renfrewfruit.service.FileService;
import com.renfrewfruit.service.SortingService;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class SortingServiceImpl implements SortingService {

    private final FileService fileService = new FileServiceImpl();

    public void gradeBatch(Batch batch, String fileName) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Batch contains " + batch.getBatchWeight() + "kg"
                + " of " + batch.getBatchFruit().getProductName() + " from farm number "
                + batch.getBatchOrigin().getFarmCode() + " received on " + batch.getBatchDate());

        System.out.print("\nEnter percentage of GRADE A fruit in batch: ");
        batch.getBatchFruit().setGradeA(scanner.nextInt());
        System.out.print("\nEnter percentage of GRADE B fruit in batch: ");
        batch.getBatchFruit().setGradeB(scanner.nextInt());
        System.out.print("\nEnter percentage of GRADE C fruit in batch: ");
        batch.getBatchFruit().setGradeC(scanner.nextInt());
        System.out.print("\nEnter percentage of rejected fruit in batch: ");
        batch.getBatchFruit().setRejected(scanner.nextInt());
        System.out.print("\nConfirm grading details are correct Y/N: ");

        if (scanner.next().equalsIgnoreCase("Y")) {
            try {
                fileService.updateFile(batch);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Grading Details Added");
        } else System.out.println("Press Any Key To Return To Main Menu");
    }

    public void calculatePercentages(Batch batch) {

        DecimalFormat df = new DecimalFormat("#.##");

        double percentageA = ((double)batch.getBatchWeight() / 100) * batch.getBatchFruit().getGradeA();
        double percentageB = ((double)batch.getBatchWeight() / 100) * batch.getBatchFruit().getGradeB();
        double percentageC = ((double)batch.getBatchWeight() / 100) * batch.getBatchFruit().getGradeC();
        double percentageRejected = ((double)batch.getBatchWeight() / 100) * batch.getBatchFruit().getRejected();

        System.out.println("GRADE A\t\t " + batch.getBatchFruit().getGradeA() + "% = " + df.format(percentageA) + "kg");
        System.out.println("GRADE B\t\t " + batch.getBatchFruit().getGradeB() + "% = " + df.format(percentageB) + "kg");
        System.out.println("GRADE C\t\t " + batch.getBatchFruit().getGradeB() + "% = " + df.format(percentageC) + "kg");
        System.out.println("REJECTED \t " + batch.getBatchFruit().getRejected() + "% = " + df.format(percentageRejected) + "kg");
    }
}
