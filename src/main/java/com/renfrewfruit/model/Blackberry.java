package com.renfrewfruit.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Blackberry extends Fruit {

    public Blackberry() {
        setProductName("Blackberries");
        setProductCode("BL");
    }
}