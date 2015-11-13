package co.edu.unal.rentando.server.guice.sql;

import com.google.inject.AbstractModule;

public class SqlBusinessModule extends AbstractModule {
	public static boolean isActive = false;

	@Override
	protected void configure() {
		isActive = true;
		System.out.println("*** SqlBusinessModule::configure(): "
				+ "binding interfaces to SQL classes");
	}

}
