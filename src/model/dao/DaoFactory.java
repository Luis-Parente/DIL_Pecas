package model.dao;

import model.dao.impl.PecaDaoJDBC;
import model.dao.impl.VendaDaoJDBC;

public class DaoFactory {

	public static VendaDao createVendaDao() {
		return new VendaDaoJDBC();
	}
	
	public static PecaDao createPecaDao() {
		return new PecaDaoJDBC();
	}
}
