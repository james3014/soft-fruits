package com.renfrewfruit.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Strawberry extends Fruit {

    public Strawberry() {
        setProductName("Strawberries");
        setProductCode("ST");
    }
}
