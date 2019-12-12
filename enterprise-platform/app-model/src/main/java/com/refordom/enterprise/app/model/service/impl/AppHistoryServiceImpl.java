package com.refordom.enterprise.app.model.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.enterprise.app.model.dao.AppHistoryDao;
import com.refordom.enterprise.app.model.service.AppHistoryService;
import com.refordom.enterprise.app.model.entity.AppHistory;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/9 16:16
 */
@Service
public class AppHistoryServiceImpl extends ServiceImpl<AppHistoryDao, AppHistory> implements AppHistoryService {
}
