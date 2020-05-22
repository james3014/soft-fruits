package com.renfrewfruit.service;

import com.renfrewfruit.model.Batch;
import com.renfrewfruit.model.Market;

public interface FileService {

    void createFile(Batch batch);

    void updateBatchFile(Batch batch);

    void updateMarketFile(Market market);

    String createFileName(String batchNumber);

    String findFile(String batchName);

    Market retrieveMarket();

}
