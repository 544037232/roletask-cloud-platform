package com.refordom.app.config.core;

import com.refordom.app.config.filter.AppDetailsFilter;
import com.refordom.app.config.filter.ParamsCheckFilter;

import javax.servlet.Filter;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 过滤器排序
 *
 * @author pricess.wang
 * @date 2020/1/2 18:47
 *
 */
final class FilterComparator implements Comparator<Filter>, Serializable {

    private static final int INITIAL_ORDER = 1;

    private final Map<String, Integer> filterToOrder = new HashMap<>();

    private Integer stepSize;

    public FilterComparator() {
        Step order = new Step(INITIAL_ORDER);

        put(ParamsCheckFilter.class, order.next());
        put(AppDetailsFilter.class, order.next());
        put(AbstractAppPrimaryFilter.class, order.next());

        this.stepSize = filterToOrder.size();
    }

    @Override
    public int compare(Filter o1, Filter o2) {
        Integer left = getOrder(o1.getClass());
        Integer right = getOrder(o2.getClass());
        return left - right;
    }

    private void put(Class<? extends Filter> filter, int position) {
        String className = filter.getName();
        filterToOrder.put(className, position);
    }

    private Integer getOrder(Class<? extends Filter> clazz) {
        Class<?> delegator = clazz;

        while (delegator != null) {
            Integer result = filterToOrder.get(delegator.getName());
            if (result != null) {
                return result;
            }
            delegator = delegator.getSuperclass();
        }

        return reOrder(clazz);
    }

    public void registerAfter(Class<? extends Filter> filterClass, Class<? extends Filter> afterFilter) {

    }

    public void registerBefore(Class<? extends Filter> filterClass, Class<? extends Filter> beforeFilter) {

    }

    private Integer reOrder(Class<? extends Filter> filterClass) {

        for (int i = 0; i < stepSize; i++) {
            for (Map.Entry<String, Integer> entry : filterToOrder.entrySet()) {

            }
        }
        return 0;
    }

    private static class Step {

        private int value;

        Step(int initialValue) {
            this.value = initialValue;
        }

        int next() {
            int value = this.value;
            this.value++;
            return value;
        }

    }
}
