package co.edu.unal.rentando.shared;

import java.io.Serializable;

import co.edu.unal.rentando.shared.many2many.IUser.UserRole;

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
	private String pictureUrl;
	private UserRole role = UserRole.outside_user;
	private boolean hasABooking;

	public boolean isLoggedIn = false;
	public static final String ROLE_NORMAL = "normal-user";
	public static final String ROLE_BASE = "base-user";

	public UserInfo(String name, String email, String lastName) {
		this.name = name;
		this.email = email;
		this.lastName = lastName;
	}

	public UserInfo() {
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

	public void setPictureUrl(String text) {
		pictureUrl = text;
	}

	public String getPictureURL() {
		return pictureUrl;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public boolean hasABooking() {
		return hasABooking;
	}

	public void setBookingID(boolean b) {
		this.hasABooking = b;
	}

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", lastName=" + lastName + ", address="
				+ address + ", document=" + document + ", role=" + role
				+ ", hasABooking=" + hasABooking + "]";
	}

}