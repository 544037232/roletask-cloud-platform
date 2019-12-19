package com.refordom.app.config.filter;

import com.refordom.app.config.AppDetails;
import com.refordom.app.config.core.AbstractAppPrimaryFilter;
import com.refordom.app.config.core.StandardAppDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pricess.wang
 * @date 2019/12/18 10:50
 */
public class StandardAppPrimaryFilter extends AbstractAppPrimaryFilter {

    @Override
    protected AppDetails onContext(HttpServletRequest request, HttpServletResponse response) {
        return new StandardAppDetails(request);
    }

}
