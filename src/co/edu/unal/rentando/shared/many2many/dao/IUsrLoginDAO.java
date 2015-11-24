package co.edu.unal.rentando.shared.many2many.dao;

import java.util.List;

import co.edu.unal.rentando.shared.many2many.IUsrLogin;
import co.edu.unal.rentando.shared.many2many.ofy.OfyUsrLogin;

public interface IUsrLoginDAO {

	IUsrLogin load(String id);
	
	void save(IUsrLogin loginInfo);
	
	List<IUsrLogin> fetchLogin();
	
}
