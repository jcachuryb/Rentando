package co.edu.unal.rentando.server.util.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import co.edu.unal.rentando.server.util.INormalUserProvider;
import co.edu.unal.rentando.shared.many2many.INormalUser;

@Singleton
public class INormalUserProviderImpl implements INormalUserProvider {

	Provider<INormalUser> provider;

	@Inject
	public INormalUserProviderImpl(Provider<INormalUser> provider) {
		this.provider = provider;
	}

	@Override
	public INormalUser getuser() {
		// TODO Auto-generated method stub
		return provider.get();
	}

}
