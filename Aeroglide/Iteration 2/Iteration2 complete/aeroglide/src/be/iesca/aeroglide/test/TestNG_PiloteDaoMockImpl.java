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
import be.iesca.aeroglide.domaine.Pilote;

public class TestNG_PiloteDaoMockImpl {
	PiloteDao piloteDao;
	
	@BeforeClass
	public void init(){
		DaoFactory daoFactory=DaoFactory.getInstance();
		this.piloteDao=(PiloteDao) daoFactory.getDaoImpl(PiloteDao.class);
	}
	
	@Test
	public void testEnregistrerPilote()
	{
		Pilote p1=new Pilote("A", "A", "a@gmail.com", "A", "1", "A", 1234, "123456789", 1);
		Pilote p2=new Pilote("B", "B", "b@gmail.com", "B", "2", "B", 4567, "987456123", 2);
		Pilote p3=new Pilote("C", "B", "c@gmail.com", "C", "3", "C", 7894, "147852963", 3);
		
		try{
			assertTrue(this.piloteDao.ajouterPilote(p1));
			assertTrue(this.piloteDao.ajouterPilote(p2));
			assertTrue(this.piloteDao.ajouterPilote(p3));
		}catch(Exception e){
			fail("Ajout n'est pas accepter");
		}
	}

}
