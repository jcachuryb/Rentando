package co.edu.unal.rentando.shared.many2many.dao;

import java.util.List;

import co.edu.unal.rentando.shared.many2many.IRent;

public interface IRentDAO {
	void saveRent(IRent rent);
	
	IRent loadRent(String id);
	
	List<IRent> fetchRentals();
		
	void remove(String id);
	
}