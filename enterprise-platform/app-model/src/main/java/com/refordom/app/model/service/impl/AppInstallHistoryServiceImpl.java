package com.refordom.app.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.app.model.dao.AppInstallHistoryDao;
import com.refordom.app.model.entity.AppInstallHistory;
import com.refordom.app.model.service.AppInstallHistoryService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppInstallHistoryServiceImpl extends ServiceImpl<AppInstallHistoryDao, AppInstallHistory> implements AppInstallHistoryService {
}
