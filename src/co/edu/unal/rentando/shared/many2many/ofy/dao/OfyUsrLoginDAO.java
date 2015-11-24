package co.edu.unal.rentando.shared.many2many.ofy.dao;

import static co.edu.unal.rentando.service.ofy.OfyService.ofy;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.shared.many2many.IUsrLogin;
import co.edu.unal.rentando.shared.many2many.dao.IUsrLoginDAO;
import co.edu.unal.rentando.shared.many2many.ofy.OfyUsrLogin;

import com.google.inject.Singleton;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

@Singleton
public class OfyUsrLoginDAO implements IUsrLoginDAO {

	@Override
	public IUsrLogin load(String id) {
		Key<OfyUsrLogin> key = Key.create(OfyUsrLogin.class, id);
		return ofy().load().key(key).now();
	}

	@Override
	public void save(IUsrLogin loginInfo) {
		ofy().save().entity(loginInfo).now();
	}

	@Override
	public List<IUsrLogin> fetchLogin() {
		Query<OfyUsrLogin> q = ofy().load().type(OfyUsrLogin.class);

		return new ArrayList<IUsrLogin>(q.list());
	}

}
