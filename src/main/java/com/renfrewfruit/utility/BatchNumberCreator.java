package com.renfrewfruit.utility;

import com.renfrewfruit.model.Batch;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BatchNumberCreator {

    public String createBatchNumber(Batch batch) {

        String fruitCode;
        String batchNumber;

        switch (batch.getProductCode()) {
            case "Strawberries":
                fruitCode = "ST";
                break;
            case "Raspberries":
                fruitCode = "RA";
                break;
            case "Blackberries":
                fruitCode = "BL";
                break;
            case "Gooseberries":
                fruitCode = "GO";
                break;
            default:
                fruitCode = "Invalid Fruit Type";
        }
        batchNumber = batch.getBatchDate() + "-" + fruitCode + "-" + batch.getOriginCode();
        return batchNumber;
    }
}
