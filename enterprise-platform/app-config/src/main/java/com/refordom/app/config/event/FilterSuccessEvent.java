package com.refordom.app.config.event;

import com.refordom.app.config.AppDetails;
import org.springframework.context.ApplicationEvent;

/**
 * @author pricess.wang
 * @date 2019/12/17 18:41
 */
public class FilterSuccessEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param appDetails the object on which the event initially occurred (never {@code null})
     */
    public FilterSuccessEvent(AppDetails appDetails) {
        super(appDetails);
    }
}
