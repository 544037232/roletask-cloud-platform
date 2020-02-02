package com.refordom.common.action.builder.event;

import com.refordom.common.action.builder.ResultToken;
import org.springframework.context.ApplicationEvent;

/**
 * @author pricess.wang
 * @date 2019/12/17 18:41
 */
public class FilterSuccessEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param resultToken the object on which the event initially occurred (never {@code null})
     */
    public FilterSuccessEvent(ResultToken resultToken) {
        super(resultToken);
    }
}
