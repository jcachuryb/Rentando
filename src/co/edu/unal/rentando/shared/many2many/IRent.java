package co.edu.unal.rentando.shared.many2many;

import java.util.Date;

import co.edu.unal.rentando.shared.RentalDate;

public interface IRent {
	void setId(String id);

	String getId();

	void setCar(ICar car);

	ICar getCar();

	void setInitialDate(Date initDate);

	Date getInitialDate();

	void setTotalDays(Integer days);

	Integer getTotalDays();

	void setRentalDateInfo(RentalDate rental);
	
	RentalDate getRentalDate();
	
}
