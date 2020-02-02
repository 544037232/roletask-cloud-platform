package com.refordom.app.service.client;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.refordom.app.model.entity.AppEnvironment;
import com.refordom.app.model.exception.ConcurrentException;
import com.refordom.app.model.service.AppEnvironmentService;
import com.refordom.common.action.builder.context.ActionContextHolder;
import com.refordom.common.action.builder.core.AbstractFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/1 15:53
 */
public class EnvironmentDeleteFilter extends AbstractFilter {

    private final AppEnvironmentService appEnvironmentService;

    public EnvironmentDeleteFilter(AppEnvironmentService appEnvironmentService) {
        this.appEnvironmentService = appEnvironmentService;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response) {

        EnvironmentParam.EnvironmentDeleteParam environmentParam = (EnvironmentParam.EnvironmentDeleteParam) ActionContextHolder.getContext().getParamBean();

        QueryWrapper<AppEnvironment> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().eq(AppEnvironment::getClientId,environmentParam.getClientId());

        boolean flag = appEnvironmentService.remove(queryWrapper);

        if (!flag) {
            throw new ConcurrentException("未找到需要删除的客户端");
        }
    }

}
