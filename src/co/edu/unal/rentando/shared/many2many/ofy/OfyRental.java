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
	private Date due;

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
	public void setRentalDateInfo(RentalDate rental) {
		// TODO Auto-generated method stub
		setInitialDate(rental.getInitDate());
	}

	@Override
	public RentalDate getRentalDate() {
		RentalDate r = new RentalDate();
		r.setInitDate(init);
		r.setDueDate(due);
		return r;
	}

	@Override
	public void setDueDate(Date dueDate) {
		// TODO Auto-generated method stub
		this.due = dueDate;
	}

	@Override
	public Date getDueDate() {
		// TODO Auto-generated method stub
		return due;
	}

}
