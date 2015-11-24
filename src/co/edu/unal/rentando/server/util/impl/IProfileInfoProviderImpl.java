package co.edu.unal.rentando.server.util.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import co.edu.unal.rentando.server.util.IProfileInfoProvider;
import co.edu.unal.rentando.shared.many2many.IProfileInfo;

@Singleton
public class IProfileInfoProviderImpl implements IProfileInfoProvider {
	private Provider<IProfileInfo> provider;

	@Inject
	public IProfileInfoProviderImpl(Provider<IProfileInfo> provider) {
		this.provider = provider;
	}

	@Override
	public IProfileInfo getNewProfileInfo() {
		// TODO Auto-generated method stub
		return this.provider.get();
	}

}
