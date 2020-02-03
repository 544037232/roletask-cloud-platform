package com.refordom.app.service.client;

import com.refordom.common.action.builder.validator.ActionParamParser;
import com.refordom.common.action.builder.validator.ParamAdapter;

import javax.servlet.http.HttpServletRequest;

/**
 * <p></p>
 *
 * @author pricess.wang
 * @date 2020/2/1 15:47
 */
public class EnvironmentParamParser implements ActionParamParser {

    @Override
    public ParamAdapter build(HttpServletRequest request) {
        return new EnvironmentParamAdapter(request);
    }

    public static class EnvironmentParamUpdateParser extends EnvironmentParamParser {
        @Override
        public ParamAdapter build(HttpServletRequest request) {
            return new EnvironmentParamAdapter.EnvironmentUpdateParamAdapter(request);
        }
    }

    public static class EnvironmentParamDeleteParser extends EnvironmentParamParser {
        @Override
        public ParamAdapter build(HttpServletRequest request) {
            return new EnvironmentParamAdapter.EnvironmentDeleteParamAdapter(request);
        }
    }
}
