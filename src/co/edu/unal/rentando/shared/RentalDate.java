package co.edu.unal.rentando.shared;

import java.io.Serializable;
import java.util.Date;

public class RentalDate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Id;
	private Date initDate;
	private Date dueDate;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

}
