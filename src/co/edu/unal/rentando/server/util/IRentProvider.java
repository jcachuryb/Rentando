package co.edu.unal.rentando.server.util;

import co.edu.unal.rentando.shared.many2many.IRent;

public interface IRentProvider {
	IRent getNewRent();
}
