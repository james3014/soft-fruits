package com.renfrewfruit.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Batch {

    private String productCode;
    private String batchDate;
    private int batchWeight;
    private int originCode;
}
