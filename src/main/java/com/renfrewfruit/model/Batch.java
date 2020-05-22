package com.renfrewfruit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Batch {

    private String batchNumber;
    private Fruit batchFruit;
    private String batchDate;
    private int batchWeight;
    private Farm batchOrigin;
    private Price batchValue;

    public Batch(Fruit fruit, String batchDate, int batchWeight, Farm origin, Price batchValue) {
        this.batchFruit = fruit;
        this.batchDate = batchDate;
        this.batchWeight = batchWeight;
        this.batchOrigin = origin;
        this.batchValue = batchValue;
    }
}
