package co.edu.unal.rentando.client.behavior;

import java.util.List;

import co.edu.unal.rentando.shared.many2many.IUsrLogin.UserRole;

public interface RoleChange {
	void onRoleChange(List<UserRole> roles);
}
