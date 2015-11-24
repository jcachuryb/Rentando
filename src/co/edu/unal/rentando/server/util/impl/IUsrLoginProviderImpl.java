package co.edu.unal.rentando.server.util.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import co.edu.unal.rentando.server.util.IUsrLoginProvider;
import co.edu.unal.rentando.shared.many2many.IUsrLogin;

@Singleton
public class IUsrLoginProviderImpl implements IUsrLoginProvider {

	
	private Provider<IUsrLogin> loginProvider;
	@Inject
	public IUsrLoginProviderImpl(Provider<IUsrLogin> loginProvider) {
		this.loginProvider = loginProvider;
	}

	@Override
	public IUsrLogin getNewLogin() {
		// TODO Auto-generated method stub
		return loginProvider.get();
	}

}
