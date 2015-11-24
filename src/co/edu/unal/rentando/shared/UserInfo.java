package co.edu.unal.rentando.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.shared.many2many.IUsrLogin.UserRole;

public class UserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	private String phoneNumber = "";
	private String lastName;
	private String address;
	private String document;
	private List<UserRole> roles = new ArrayList<>();
	public boolean isLoggedIn = false;

	public UserInfo(String name, String email, String lastName) {
		this.name = name;
		this.email = email;
		this.lastName = lastName;
	}

	public UserInfo() {
		roles.add(UserRole.outside_user);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public List<UserRole> getRole() {
		return roles;
	}

	public void setRoles(List<UserRole> role) {
		this.roles = role;
	}

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", lastName=" + lastName + ", address="
				+ address + ", document=" + document + ", role=" + roles + "]";
	}

	public static UserInfo getTestUser() {
		UserInfo u = new UserInfo();
		u.setName("Test Camilo");
		u.setLastName("Achury Test");
		u.setEmail("truancamilo@gmail.com");
		ArrayList<UserRole> uRoles = new ArrayList<>();
		uRoles.add(UserRole.admin_user);
		u.setRoles(uRoles);
		return u;
	}
}