package com.renfrewfruit.service;

import com.renfrewfruit.model.Market;

public interface PricingService {

    void priceStrawberries(Market market);

    void priceRaspberries(Market market);

    void priceBlackberries(Market market);

    void priceGooseberries(Market market);
}
