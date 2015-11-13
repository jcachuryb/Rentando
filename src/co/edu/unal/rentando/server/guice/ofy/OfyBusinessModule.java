package co.edu.unal.rentando.server.guice.ofy;

import com.google.inject.AbstractModule;

public class OfyBusinessModule extends AbstractModule {
	public static boolean isActive = false;

	@Override
	protected void configure() {
		isActive = true;
		System.out.println("*** OfyBusinessModule::configure(): "
				+ "binding interfaces to OFY classes");

	}

}
