/**
 * @author Nardella Marine
 * @version 1.0
 */

// test unitaire

package be.iesca.aeroglide.test;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import be.iesca.aeroglide.dao.PiloteDao;
import be.iesca.aeroglide.daoimpl.DaoFactory;
import be.iesca.aeroglide.daoimpl.PiloteDaoMockImpl;
import be.iesca.aeroglide.domaine.Pilote;

public class TestNG_PiloteDaoMockImpl extends TestNG_PiloteDao{
	
	/*
		obtention de l'objet DAO
	*/
	@BeforeClass
	public void init(){
		this.piloteDao= new PiloteDaoMockImpl();
	}

	
}
