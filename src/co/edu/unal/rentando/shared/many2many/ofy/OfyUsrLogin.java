package co.edu.unal.rentando.shared.many2many.ofy;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import co.edu.unal.rentando.shared.many2many.IUsrLogin;

@Entity
public class OfyUsrLogin implements IUsrLogin {

	@Id
	private String id;
	private List<UserRole> roles;
	private Boolean userExists;

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setUserRoles(List<UserRole> role) {
		// TODO Auto-generated method stub
		roles = role;
	}

	@Override
	public List<UserRole> getUserRoles() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public void setUserExists(boolean b) {
		// TODO Auto-generated method stub
		this.userExists = b;
	}

	@Override
	public boolean userExists() {
		// TODO Auto-generated method stub
		return this.userExists;
	}

	@Override
	public void removeUserRole(UserRole role) {
		roles.remove(role);
	}

}
