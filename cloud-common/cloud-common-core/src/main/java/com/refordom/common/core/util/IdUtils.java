package com.refordom.common.core.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * <p> </p>
 *
 * @author pricess.wang
 * @date 2019/10/28 16:48
 */
public class IdUtils {

    private final static Long WORKER_ID = 0L;

    private final static Long DATA_CENTER_ID = 0L;

    private final static Snowflake snowflake = IdUtil.createSnowflake(WORKER_ID, DATA_CENTER_ID);

    public static long getId() {
        return snowflake.nextId();
    }

}
