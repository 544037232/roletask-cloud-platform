package com.omc.builder.global.feature;

import com.omc.builder.api.TransactionManager;
import com.omc.object.ObjectBuilder;

public interface FutureBuilder<H extends FutureBuilder<H>> extends
        ObjectBuilder<FutureManager> {

    H transactionManager(TransactionManager transactionManager);

}
