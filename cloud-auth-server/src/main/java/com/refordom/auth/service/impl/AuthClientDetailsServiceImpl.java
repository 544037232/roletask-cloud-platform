package com.refordom.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.refordom.auth.dao.AuthClientDetailsDao;
import com.refordom.auth.service.AuthClientDetails;
import com.refordom.auth.service.AuthClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author pricess.wang
 * @date 2019/12/2 15:30
 */
@Service
public class AuthClientDetailsServiceImpl extends ServiceImpl<AuthClientDetailsDao, AuthClientDetails> implements AuthClientDetailsService {
}
