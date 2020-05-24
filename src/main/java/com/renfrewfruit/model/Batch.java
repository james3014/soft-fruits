package com.renfrewfruit.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Batch {

    private String batchNumber;
    private Fruit batchFruit;
    private String batchDate;
    private Weight batchWeight;
    private Farm batchOrigin;
    private Price batchValue;

}
