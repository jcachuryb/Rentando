package co.edu.unal.rentando.shared.many2many.dao;

import java.util.List;

import co.edu.unal.rentando.shared.many2many.ICar;

public interface ICarDAO {
	
	Boolean carExists(String id);
	
	void saveCar(ICar car);

	ICar loadCar(String id);

	List<ICar> fetchAllCars();

	void removeCar(String id);
}
