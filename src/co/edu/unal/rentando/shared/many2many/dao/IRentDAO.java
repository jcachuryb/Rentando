package co.edu.unal.rentando.shared.many2many.dao;

import co.edu.unal.rentando.shared.many2many.IRent;

public interface IRentDAO {
	void saveRent(IRent rent);
	
	IRent loadRent(String id);
		
}