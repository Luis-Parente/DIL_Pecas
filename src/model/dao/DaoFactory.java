package model.dao;

import db.DB;
import model.dao.impl.PecaDaoJDBC;
import model.dao.impl.VendaDaoJDBC;

public class DaoFactory {

	public static VendaDao createVendaDao() {
		return new VendaDaoJDBC(DB.getConnection());
	}
	
	public static PecaDao createPecaDao() {
		return new PecaDaoJDBC(DB.getConnection());
	}
}
