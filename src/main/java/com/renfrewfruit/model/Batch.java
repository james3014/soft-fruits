package com.renfrewfruit.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Batch {

    private Fruit productCode;
    private String batchDate;
    private int batchWeight;
    private Farm originCode;
}
