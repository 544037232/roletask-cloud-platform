package com.refordom.app.config.provisioning;

import com.refordom.app.config.exception.DeprecatedException;
import com.refordom.app.config.manager.AppManager;
import com.refordom.app.config.manager.ModelManagerBuilder;
import com.refordom.common.builder.ObjectConfigurerAdapter;
import org.springframework.util.Assert;

/**
 * @author pricess.wang
 * @date 2019/12/30 15:28
 */
public abstract class AbstractAppConfigurer<M, D, T extends AbstractAppConfigurer<M, D, T, B>, B extends ModelManagerBuilder<B>>
        extends ObjectConfigurerAdapter<AppManager, B> {

    private Class<M> managerServiceClass;

    private M managerService;

    @SuppressWarnings("unchecked")
    public AppModelBuilder inMemory() {
        checkExistsService();
        return new AppModelBuilder((T) this, initInMemoryManager());
    }

    protected abstract M initInMemoryManager();

    protected abstract void createModel(D detail);

    public AbstractAppConfigurer(Class<M> managerServiceClass) {
        Assert.notNull(managerServiceClass, "this managerServiceClass can not be null");
        this.managerServiceClass = managerServiceClass;
    }

    public void setManagerService(M managerService) {
        this.managerService = managerService;
    }

    public M getManagerService() {
        return managerService;
    }

    @SuppressWarnings("unchecked")
    public T jdbc() {
        checkExistsService();
        this.setManagerService(getBuilder().getContext().getBean(managerServiceClass));
        return (T) this;
    }

    protected void checkExistsService() {
        if (managerService != null) {
            throw new DeprecatedException("the configurer already exists");
        }
    }

    public class AppModelBuilder {

        private T configurer;

        public AppModelBuilder(T configurer, M managerService) {
            Assert.notNull(managerService, "this managerService can not be null");
            this.configurer = configurer;
            configurer.setManagerService(managerService);
        }

        public T and() {
            return configurer;
        }

        public AppModelBuilder addModel(D detail) {
            configurer.createModel(detail);
            return this;
        }
    }
}
