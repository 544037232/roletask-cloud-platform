package com.refordom.app.config.provisioning.configurer;

import com.refordom.app.config.manager.AppProviderManagerBuilder;
import com.refordom.app.model.AppDetailsManager;
import com.refordom.common.builder.ObjectPostProcessor;

abstract class AbstractDaoAppConfigurer<B extends AppProviderManagerBuilder<B>, C extends AbstractDaoAppConfigurer<B, C, A>, A extends AppDetailsManager>
		extends AppDetailsAwareConfigurer<B, A> {

	private final A appDetailsManager;

	protected AbstractDaoAppConfigurer(A appDetailsManager) {
		this.appDetailsManager = appDetailsManager;
	}

	@SuppressWarnings("unchecked")
	public C withObjectPostProcessor(ObjectPostProcessor<?> objectPostProcessor) {
		addObjectPostProcessor(objectPostProcessor);
		return (C) this;
	}

	@Override
	public void configure(B builder) throws Exception {

	}

	@Override
	public A getAppDetailManager() {
		return appDetailsManager;
	}
}
