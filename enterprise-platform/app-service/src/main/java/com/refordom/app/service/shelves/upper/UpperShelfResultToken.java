package com.refordom.app.service.shelves.upper;

import com.pricess.omc.ResultToken;
import com.refordom.app.model.entity.AppDistro;

public class UpperShelfResultToken implements ResultToken {

    private AppDistro appDistro;

    public UpperShelfResultToken(AppDistro appDistro){
        this.appDistro = appDistro;
    }

    public AppDistro getAppDistro() {
        return appDistro;
    }
}
