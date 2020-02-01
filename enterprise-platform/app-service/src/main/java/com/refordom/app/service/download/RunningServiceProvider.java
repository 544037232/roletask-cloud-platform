package com.refordom.app.service.download;

import com.refordom.app.config.AppServiceProvider;
import com.refordom.app.config.AppToken;
import com.refordom.app.core.AppContextHolder;
import com.refordom.app.model.entity.AppDistro;
import com.refordom.app.model.entity.AppRunning;

import java.util.UUID;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/1/31 18:14
 */
public class RunningServiceProvider implements AppServiceProvider {

    @Override
    public AppToken provider(AppToken appToken) {
        DownloadToken downloadToken = (DownloadToken) appToken;

        AppDistro appDistro = (AppDistro) AppContextHolder.getContext().getProcessObj(AppDistro.class);

        downloadToken.setAppId(appDistro.getAppId());
        downloadToken.setAppType(appDistro.getAppType());
        downloadToken.setSign(appDistro.getSign());
        downloadToken.setVersion(appDistro.getVersion());
        downloadToken.setLicenceKey(UUID.randomUUID().toString());

        AppRunning appRunning = new AppRunning();
        appRunning.setAppId(appDistro.getAppId());
        appRunning.setAppKey(appDistro.getAppKey());
        appRunning.setAppType(appDistro.getAppType());
        appRunning.setAppName(appDistro.getAppName());
        appRunning.setKid(appDistro.getKid());
        appRunning.setIssuer(appDistro.getIssuer());
        appRunning.setDescription(appDistro.getDescription());
        appRunning.setDistroDate(appDistro.getDistroDate());
        appRunning.setChangeLog(appDistro.getChangeLog());
        appRunning.setSign(appDistro.getSign());
        appRunning.setVersion(appDistro.getVersion());
        appRunning.setMutexVersion(appDistro.getMutexVersion());

        AppContextHolder.getContext().addResult(appRunning);

        return downloadToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(DownloadToken.class);
    }
}
