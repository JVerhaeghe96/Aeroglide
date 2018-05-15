package be.iesca.aeroglide.test;

import org.testng.annotations.BeforeClass;

import be.iesca.aeroglide.dao.PiloteDao;
import be.iesca.aeroglide.daoimpl.DaoMockImpl;

public class TestNG_VolDaoMockImpl extends TestNG_VolDao {

	@BeforeClass
	@Override
	public void init() {
		this.volDao=new DaoMockImpl();
		this.piloteDao=(PiloteDao) this.volDao;
	}
}
