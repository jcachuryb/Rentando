package co.edu.unal.rentando.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.unal.rentando.server.RentandoServiceImpl;
import co.edu.unal.rentando.shared.many2many.ICar;

public class CarInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String pictURL;
	private String brand;
	private String reference;
	private String description;
	private String price;
	private List<RentInfo> rentals;
	private boolean isNew;

	public CarInfo(String id, String pictURL, String brand, String reference,
			String description, String price, List<RentInfo> rentals) {
		this.id = id;
		this.pictURL = pictURL;
		this.brand = brand;
		this.reference = reference;
		this.description = description;
		this.price = price;
		this.rentals = rentals;
	}

	public CarInfo() {
		this.id = "";
		this.pictURL = "";
		this.brand = "";
		this.reference = "";
		this.description = "";
		this.price = "";
		this.isNew = false;
		this.rentals = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPictURL() {
		return pictURL;
	}

	public void setPictURL(String pictURL) {
		this.pictURL = pictURL;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<RentInfo> getRentals() {
		return rentals;
	}

	public void setRentals(List<RentInfo> rentals) {
		this.rentals = rentals;
	}

	public void setIsNew(boolean n) {
		this.isNew = n;
	}

	public boolean isNew() {
		return this.isNew;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void fillOutCar(ICar car) {
		this.setId(car.getId());
		this.setBrand(car.getBrand());
		this.setPictURL(car.getPicture());
		this.setDescription(car.getDescription());
		this.setReference(car.getReference());
		this.setPrice(car.getRentalPrice());
//		this.setRentals(RentandoServiceImpl.getRentInfoList(car.getRentals()));
	}

}
