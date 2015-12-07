package co.edu.unal.rentando.shared;

import java.io.Serializable;

import co.edu.unal.rentando.shared.many2many.ofy.ExtraInfo;

public class NormalUserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email;

	private ExtraInfo extra;

	private UserInfo user;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ExtraInfo getExtra() {
		return extra;
	}

	public void setExtra(ExtraInfo extra) {
		this.extra = extra;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

}
