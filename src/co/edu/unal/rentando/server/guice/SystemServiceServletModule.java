package co.edu.unal.rentando.server.guice;

import java.util.HashMap;
import java.util.Map;

import co.edu.unal.rentando.server.servlet.OfyServlet;
import co.edu.unal.rentando.server.servlet.SqlServlet;

import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;

public class SystemServiceServletModule extends GuiceSystemServiceServletModule{
	@Override
	protected void configureServlets() {
		super.configureServlets();
		 Map<String, String> sqlParams = new HashMap<>();
		 sqlParams.put(SqlServlet.PARAM, "sql-param");
		
		 serve("/sql").with(SqlServlet.class, sqlParams);

		Map<String, String> ofyParams = new HashMap<>();
		ofyParams.put(OfyServlet.PARAM, "ofy-param");

		serve("/ofy").with(OfyServlet.class, ofyParams);
	}
}
