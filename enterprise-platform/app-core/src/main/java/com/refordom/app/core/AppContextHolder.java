package com.refordom.app.core;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;

/**
 * 应用上下文执行器，默认使用threadLocal
 * @author pricess.wang
 * @date 2019/12/31
 */
public class AppContextHolder {

	public static final String MODE_THREADLOCAL = "MODE_THREADLOCAL";
	public static final String SYSTEM_PROPERTY = "roletask.app.strategy";
	private static String strategyName = System.getProperty(SYSTEM_PROPERTY);
	private static AppContextHolderStrategy strategy;
	private static int initializeCount = 0;

	static {
		initialize();
	}

	public static void clearContext() {
		strategy.clearContext();
	}

	public static AppContext getContext() {
		return strategy.getContext();
	}

	public static int getInitializeCount() {
		return initializeCount;
	}

	private static void initialize() {
		if (!StringUtils.hasText(strategyName)) {
			// Set default
			strategyName = MODE_THREADLOCAL;
		}

		if (strategyName.equals(MODE_THREADLOCAL)) {
			strategy = new ThreadLocalAppContextHolderStrategy();
		}
		else {
			// Try to load a custom strategy
			try {
				Class<?> clazz = Class.forName(strategyName);
				Constructor<?> customStrategy = clazz.getConstructor();
				strategy = (AppContextHolderStrategy) customStrategy.newInstance();
			}
			catch (Exception ex) {
				ReflectionUtils.handleReflectionException(ex);
			}
		}

		initializeCount++;
	}

	public static void setContext(AppContext context) {
		strategy.setContext(context);
	}

	public static void setStrategyName(String strategyName) {
		AppContextHolder.strategyName = strategyName;
		initialize();
	}

	public static AppContextHolderStrategy getContextHolderStrategy() {
		return strategy;
	}

	public static AppContext createEmptyContext() {
		return strategy.createEmptyContext();
	}

	@Override
	public String toString() {
		return "AppContextHolder[strategy='" + strategyName + "'; initializeCount="
				+ initializeCount + "]";
	}
}
