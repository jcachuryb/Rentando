package co.edu.unal.rentando.server.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class OfyServlet extends HttpServlet {
public static final String PARAM = "param";
	
	@Override
	public void init( final ServletConfig sc ) {
		try {
			super.init( sc );
			
			String param = sc.getInitParameter(PARAM);
			System.out.println( "OfyServlet::init(): " + PARAM + "=" + param );
			
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
