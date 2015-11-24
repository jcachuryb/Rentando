package co.edu.unal.rentando.server.util;

import co.edu.unal.rentando.shared.many2many.IUsrLogin;

public interface IUsrLoginProvider {
	IUsrLogin getNewLogin();
}
