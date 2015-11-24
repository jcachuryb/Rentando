package co.edu.unal.rentando.server.util.impl;

import javax.inject.Provider;

import co.edu.unal.rentando.server.util.ICarProvider;
import co.edu.unal.rentando.shared.many2many.ICar;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ICarProviderImpl implements ICarProvider {
	private Provider<ICar> carProvider;

	@Inject
	public ICarProviderImpl(Provider<ICar> carProvider) {
		this.carProvider = carProvider;
	}

	@Override
	public ICar getNewCar() {
		// TODO Auto-generated method stub
		return carProvider.get();
	}

}
