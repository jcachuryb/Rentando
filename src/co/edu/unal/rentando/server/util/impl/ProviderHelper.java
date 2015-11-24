package co.edu.unal.rentando.server.util.impl;

import com.google.inject.Injector;

import co.edu.unal.rentando.server.util.ICarProvider;
import co.edu.unal.rentando.server.util.INormalUserProvider;
import co.edu.unal.rentando.server.util.IProfileInfoProvider;
import co.edu.unal.rentando.server.util.IUsrLoginProvider;

public class ProviderHelper {

	private ICarProvider carProvider;
	private IUsrLoginProvider loginProvider;
	private INormalUserProvider normalUserProvider;
	private IProfileInfoProvider profileProvider;
	private static ProviderHelper instance;

	private ProviderHelper(Injector injector) {
		carProvider = injector.getInstance(ICarProvider.class);
		loginProvider = injector.getInstance(IUsrLoginProvider.class);
		normalUserProvider = injector.getInstance(INormalUserProvider.class);
		profileProvider = injector.getInstance(IProfileInfoProvider.class);
	}
	
	public static void initialize(Injector injector){
		if (instance == null) {
			instance = new ProviderHelper(injector);
		}
	}
	
	public static ProviderHelper getInstance(){
		return instance;
	}

	public ICarProvider getCarProvider() {
		return carProvider;
	}

	public IUsrLoginProvider getLoginProvider() {
		return loginProvider;
	}

	public INormalUserProvider getNormalUserProvider() {
		return normalUserProvider;
	}

	public IProfileInfoProvider getProfileProvider() {
		return profileProvider;
	}
	
	

}
