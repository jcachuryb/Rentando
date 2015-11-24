package co.edu.unal.rentando.shared.many2many.ofy;

import co.edu.unal.rentando.shared.many2many.IAdminUser;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Subclass;

@Entity
@Subclass
public class OfyAdminUser extends OfyProfileInfo implements IAdminUser {

	private String id;

	@Override
	public void setAdminId(String id) {
		this.id = id;
	}

	@Override
	public String getAdminId() {
		// TODO Auto-generated method stub
		return id;
	}

}
