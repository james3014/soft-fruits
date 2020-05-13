package com.renfrewfruit.utility;

import com.renfrewfruit.model.Batch;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BatchNumberCreator {

    public String createBatchNumber(Batch batch) {

        return batch.getBatchDate() + "-" + batch.getProductCode().getProductCode() + "-"
                + batch.getOriginCode().getFarmCode();
    }
}
