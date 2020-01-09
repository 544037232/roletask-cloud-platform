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
 */
final class FilterComparator implements Comparator<Filter>, Serializable {

    private static final int INITIAL_ORDER = 100;
    private static final int ORDER_STEP = 100;
    private final Map<String, Integer> filterToOrder = new HashMap<>();

    public FilterComparator() {
        Step order = new Step(INITIAL_ORDER, ORDER_STEP);

        put(ParamsCheckFilter.class, order.next());
        put(AppDetailsFilter.class, order.next());
        put(AbstractAppPrimaryFilter.class, order.next());
    }

    @Override
    public int compare(Filter lhs, Filter rhs) {
        Integer left = getOrder(lhs.getClass());
        Integer right = getOrder(rhs.getClass());
        return left - right;
    }

    /**
     * filter在afterFilter之后
     *
     * @param filter      当前需要增加的过滤器
     * @param afterFilter 比较的过滤器
     */
    public void registerAfter(Class<? extends Filter> filter, Class<? extends Filter> afterFilter) {
        Integer position = getOrder(afterFilter);

        if (position == null) {
            throw new IllegalArgumentException(
                    "Cannot register after unregistered Filter " + afterFilter);
        }

        put(filter, position + 1);
    }

    /**
     * filter在beforeFilter之前
     *
     * @param filter       当前需要增加的过滤器
     * @param beforeFilter 比较的过滤器
     */
    public void registerBefore(Class<? extends Filter> filter, Class<? extends Filter> beforeFilter) {
        Integer position = getOrder(beforeFilter);

        if (position == null) {
            throw new IllegalArgumentException(
                    "Cannot register after unregistered Filter " + beforeFilter);
        }

        put(filter, position - 1);
    }

    /**
     * filter与atFilter同一个顺序执行
     *
     * @param filter   当前需要增加的过滤器
     * @param atFilter 比较的过滤器
     */
    public void registerAt(Class<? extends Filter> filter, Class<? extends Filter> atFilter) {

        Integer position = getOrder(atFilter);
        if (position == null) {
            throw new IllegalArgumentException(
                    "Cannot register after unregistered Filter " + atFilter);
        }

        put(filter, position);
    }

    private void put(Class<? extends Filter> filter, int position) {
        String className = filter.getName();
        filterToOrder.put(className, position);
    }

    private Integer getOrder(Class<?> clazz) {
        while (clazz != null) {
            Integer result = filterToOrder.get(clazz.getName());
            if (result != null) {
                return result;
            }
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    /**
     * 是否已注册到filter链中
     *
     * @param filter 当前需要增加的过滤器
     * @return boolean
     */
    public boolean isRegistered(Class<? extends Filter> filter) {
        return getOrder(filter) != null;
    }


    private static class Step {

        private int value;
        private final int stepSize;

        Step(int initialValue, int stepSize) {
            this.value = initialValue;
            this.stepSize = stepSize;
        }

        int next() {
            int value = this.value;
            this.value += this.stepSize;
            return value;
        }

    }

}
