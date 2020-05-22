package com.renfrewfruit.service;

import com.renfrewfruit.model.Batch;

import java.io.IOException;

public interface FileService {

    void createFile(Batch batch);

    void updateFile(Batch batch) throws IOException;

    String createFileName(String batchNumber);

    String findFile(String batchName);

}
