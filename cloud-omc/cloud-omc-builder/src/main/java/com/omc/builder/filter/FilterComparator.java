package com.omc.builder.filter;

import javax.servlet.Filter;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public final class FilterComparator implements Comparator<Filter>, Serializable {

    private final Map<String, Integer> filterToOrder = new HashMap<>();

    private final Map<String, Boolean> registered = new HashMap<>();

    public int compare(Filter lhs, Filter rhs) {
        Integer left = getOrder(lhs.getClass());
        Integer right = getOrder(rhs.getClass());
        return left.compareTo(right);
    }

    private Integer getOrder(Class<?> clazz) {

        Class<?> delegate = clazz;

        while (clazz != null) {
            Integer result = filterToOrder.get(clazz.getName());
            if (result != null) {
                return result;
            }
            clazz = clazz.getSuperclass();
        }

        throw new IllegalArgumentException(
                "this Filter " + delegate.getName() + " already exist");
    }

    public void register(Filter filter, Integer sort) {
        String filterName = filter.getClass().getName();

        if (filterToOrder.get(filterName) != null && isRegister(filterName)) {
            throw new IllegalArgumentException(
                    "this Filter " + filterName + " already exist");
        }
        filterToOrder.put(filterName, sort);

        registered.put(filterName, true);
    }

    private Boolean isRegister(String filterName) {
        Boolean isRegister = registered.get(filterName);

        return isRegister == null ? false : isRegister;
    }

    public void reset(Class<? extends Filter> filter, Integer sort) {
        String filterName = filter.getName();

        filterToOrder.put(filterName, sort);
        registered.put(filterName, false);
    }
}
