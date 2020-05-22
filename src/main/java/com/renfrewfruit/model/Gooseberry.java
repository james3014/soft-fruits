package com.renfrewfruit.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gooseberry extends Fruit {

    public Gooseberry() {
        super();
        setProductName("Gooseberries");
        setFruitCode("GO");
    }
}
