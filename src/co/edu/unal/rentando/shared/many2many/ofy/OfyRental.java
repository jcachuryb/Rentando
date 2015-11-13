package co.edu.unal.rentando.shared.many2many.ofy;

import java.util.Date;

import co.edu.unal.rentando.shared.RentalDate;
import co.edu.unal.rentando.shared.many2many.ICar;
import co.edu.unal.rentando.shared.many2many.IRent;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;

@Entity
public class OfyRental implements IRent {

	@Id
	private String id;
	private Ref<ICar> car;
	private Date init;
	private Integer days;

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setCar(ICar car) {
		// TODO Auto-generated method stub
		this.car = Ref.create(car);
	}

	@Override
	public ICar getCar() {
		// TODO Auto-generated method stub
		return this.car.get();
	}

	@Override
	public void setInitialDate(Date initDate) {
		// TODO Auto-generated method stub
		this.init = initDate;
	}

	@Override
	public Date getInitialDate() {
		// TODO Auto-generated method stub
		return this.init;
	}

	@Override
	public void setTotalDays(Integer days) {
		// TODO Auto-generated method stub
		this.days = days;
	}

	@Override
	public Integer getTotalDays() {
		// TODO Auto-generated method stub
		return this.days;
	}

	@Override
	public void setRentalDateInfo(RentalDate rental) {
		// TODO Auto-generated method stub
		setInitialDate(rental.getInitDate());
		setTotalDays(rental.getTotalDays());
	}

	@Override
	public RentalDate getRentalDate() {
		RentalDate r = new RentalDate();
		r.setInitDate(this.init);
		r.setTotalDays(this.days);
		return r;
	}

}
