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
	private Integer totalDays;

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

	public Integer getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}

}
