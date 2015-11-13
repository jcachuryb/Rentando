package co.edu.unal.rentando.server.guice.ofy;

import co.edu.unal.rentando.server.guice.SystemServiceServletModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class OfyServletContextListener extends GuiceServletContextListener{

	
	private final Injector injector = Guice.createInjector(
			new OfyBusinessModule(),
			new SystemServiceServletModule() );
	@Override
	protected Injector getInjector() {
		System.out.println("Injector is on? = " + injector.toString());
		return injector;
	}

}
