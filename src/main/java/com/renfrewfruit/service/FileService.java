package com.renfrewfruit.service;

import com.renfrewfruit.model.Batch;

public interface FileService {

    void createFile(Batch batch);

    String createFileName(String batchNumber);

}
