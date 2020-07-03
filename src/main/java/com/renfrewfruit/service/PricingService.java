package com.renfrewfruit.service;

/*
 * @author James Grant (QWB19204)
 * @date 13/06/2020
 * @version 4.0
 */

import com.renfrewfruit.model.Market;

public interface PricingService {

  void priceStrawberries(Market market);

  void priceRaspberries(Market market);

  void priceBlackberries(Market market);

  void priceGooseberries(Market market);

}
