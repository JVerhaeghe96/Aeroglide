package be.iesca.aeroglide.test;

import org.testng.annotations.BeforeClass;

import be.iesca.aeroglide.daoimpl.PiloteDaoImpl;
import be.iesca.aeroglide.daoimpl.VolDaoImpl;

public class TestNG_VolDaoImpl extends TestNG_VolDao {

	@BeforeClass
	@Override
	public void init() {
		this.piloteDao=new PiloteDaoImpl();
		this.volDao=new VolDaoImpl();
	}
	

}
