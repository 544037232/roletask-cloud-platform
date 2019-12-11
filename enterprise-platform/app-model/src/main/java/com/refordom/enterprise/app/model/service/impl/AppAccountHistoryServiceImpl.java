package com.refordom.enterprise.app.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.enterprise.app.model.dao.AppAccountHistoryDao;
import com.refordom.enterprise.app.model.entity.AppAccountHistory;
import com.refordom.enterprise.app.model.service.AppAccountHistoryService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppAccountHistoryServiceImpl extends ServiceImpl<AppAccountHistoryDao, AppAccountHistory> implements AppAccountHistoryService {
}
