package co.edu.unal.rentando.server.util.impl;

import javax.inject.Provider;

import com.google.inject.Inject;

import co.edu.unal.rentando.server.util.IRentProvider;
import co.edu.unal.rentando.shared.many2many.IRent;

public class IRentProviderImpl implements IRentProvider {
	private Provider<IRent> provider;
	@Inject
	public IRentProviderImpl(Provider<IRent> provider) {
		this.provider = provider;
	}

	@Override
	public IRent getNewRent() {
		// TODO Auto-generated method stub
		return provider.get();
	}

}
