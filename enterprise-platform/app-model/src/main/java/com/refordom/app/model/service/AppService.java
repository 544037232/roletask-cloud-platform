package com.refordom.app.model.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.refordom.app.model.AppDetailsManager;
import com.refordom.app.model.entity.App;

/**
 * @author pricess.wang
 * @date 2019/12/26 14:51
 */
public interface AppService extends IService<App>, AppDetailsManager {
}
