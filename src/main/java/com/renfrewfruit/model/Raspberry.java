package com.renfrewfruit.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Raspberry extends Fruit {

    public Raspberry() {
        setProductName("Raspberries");
        setProductCode("RA");
    }
}
