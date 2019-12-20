package com.refordom.app.config.provisioning.builders;

import cn.hutool.core.util.IdUtil;
import com.refordom.app.model.AppModel;
import com.refordom.app.config.provisioning.AppModelManagerService;
import com.refordom.app.model.entity.AppAccount;
import com.refordom.app.model.enums.PairEnum;
import com.refordom.common.builder.ObjectBuilder;
import com.refordom.common.builder.ObjectConfigurerAdapter;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class AppModelBuilder<B extends AppModelBuilder<B>> extends
        ObjectConfigurerAdapter<AppModelManagerService, B> implements ObjectBuilder<AppModelManagerService> {

    private List<AccountBuilder> clientBuilders = new ArrayList<>();

    public InMemoryAppModelBuilder inMemory() throws Exception {
        return new InMemoryAppModelBuilder();
    }

    public JdbcAppModelBuilder jdbc(ApplicationContext context) throws Exception {
        return new JdbcAppModelBuilder(context);
    }

    @SuppressWarnings("rawtypes")
    public AppModelBuilder<?> clients(final AppModelManagerService appModelManagerService) throws Exception {
        return new AppModelBuilder() {
            @Override
            public AppModelManagerService build() throws Exception {
                return appModelManagerService;
            }
        };
    }

    public AccountBuilder withAccount(String accountCode) {
        AccountBuilder accountBuilder = new AccountBuilder(accountCode);
        this.clientBuilders.add(accountBuilder);
        return accountBuilder;
    }

    @Override
    public AppModelManagerService build() throws Exception {
        for (AccountBuilder accountBuilder : clientBuilders) {
            addClient(accountBuilder.accountCode, accountBuilder.build());
        }
        return performBuild();
    }

    protected void addClient(String clientId, AppModel build) {
    }

    protected AppModelManagerService performBuild() {
        throw new UnsupportedOperationException("Cannot build appModel services (maybe use inMemory() or jdbc()).");
    }

    public final class AccountBuilder {
        private final String accountCode;

        private String appId;

        private String appSecret;

        private String accountName;

        private String groupName;

        private Boolean balanceCheck;

        private PairEnum pairingModel;

        private AppModel build() {
            AppAccount result = new AppAccount();
            result.setId((long) accountCode.hashCode());
            result.setAppId(appId);
            result.setAppSecret(appSecret);
            result.setAccountName(accountName);
            result.setGroupName(groupName);
            result.setBalanceCheck(balanceCheck);
            result.setPairingModel(pairingModel);
            return result;
        }

        public AccountBuilder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public AccountBuilder groupName(String groupName){
        	this.groupName = groupName;
        	return this;
		}

		public AccountBuilder balanceCheck(Boolean balanceCheck){
			this.balanceCheck = balanceCheck;
			return this;
		}

		public AccountBuilder pairingModel(PairEnum pairingModel){
			this.pairingModel = pairingModel;
			return this;
		}

        public AppModelBuilder<B> and() {
            return AppModelBuilder.this;
        }

        private AccountBuilder(String accountCode) {
            this.accountCode = accountCode;
            this.appId = Md5Crypt.apr1Crypt(accountCode);
            this.appSecret = IdUtil.fastUUID();
        }

    }

}
