package co.edu.unal.rentando.shared.many2many.ofy;

import com.googlecode.objectify.annotation.Subclass;

import co.edu.unal.rentando.shared.many2many.IAdminUser;
@Subclass(index=true)
public class OfyAdminUser extends OfyUser implements IAdminUser {

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
