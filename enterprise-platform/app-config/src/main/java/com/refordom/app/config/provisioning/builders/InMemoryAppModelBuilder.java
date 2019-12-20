package com.refordom.app.config.provisioning.builders;

import com.refordom.app.config.provisioning.manager.InMemoryAppModelManagerService;
import com.refordom.app.model.AppModel;
import com.refordom.app.config.provisioning.AppModelManagerService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dave Syer
 */
public class InMemoryAppModelBuilder extends
        AppModelBuilder<InMemoryAppModelBuilder> {

    private Map<String, AppModel> clientDetails = new HashMap<>();

    @Override
    protected void addClient(String clientId, AppModel value) {
        clientDetails.put(clientId, value);
    }

    @Override
    protected AppModelManagerService performBuild() {
        InMemoryAppModelManagerService clientDetailsService = new InMemoryAppModelManagerService();
        clientDetailsService.setAppModelStore(clientDetails);
        return clientDetailsService;
    }

}
