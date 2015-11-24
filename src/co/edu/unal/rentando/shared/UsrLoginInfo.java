package co.edu.unal.rentando.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.shared.many2many.IUsrLogin.UserRole;

public class UsrLoginInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private List<UserRole> roles;
	private Boolean isActive = true;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public Boolean userExists() {
		return isActive;
	}

	public void setUserExistance(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "UsrLoginInfo [id=" + id + ", roles=" + roles + ", isActive="
				+ isActive + "]";
	}

	public static UsrLoginInfo getTestLogin() {
		UsrLoginInfo uli = new UsrLoginInfo();
		uli.setId("truancamilo@gmail.com");
		ArrayList<UserRole> roles = new ArrayList<>();
		roles.add(UserRole.admin_user);
		uli.setRoles(roles);
		return uli;
	}

}
