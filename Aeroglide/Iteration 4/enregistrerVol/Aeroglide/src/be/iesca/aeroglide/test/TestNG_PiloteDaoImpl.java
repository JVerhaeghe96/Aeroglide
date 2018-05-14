package be.iesca.aeroglide.test;

import org.testng.annotations.BeforeClass;

import be.iesca.aeroglide.dao.PiloteDao;
import be.iesca.aeroglide.daoimpl.PiloteDaoImpl;

public class TestNG_PiloteDaoImpl extends TestNG_PiloteDao{

	/*
	obtention de l'objet DAO
	 */
	@BeforeClass
	public void init(){
		this.piloteDao= new PiloteDaoImpl();
	}
}
