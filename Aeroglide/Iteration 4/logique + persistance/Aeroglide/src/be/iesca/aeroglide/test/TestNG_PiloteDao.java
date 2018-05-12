package be.iesca.aeroglide.test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.util.List;

import be.iesca.aeroglide.domaine.TypePlaneur;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import be.iesca.aeroglide.dao.PiloteDao;
import be.iesca.aeroglide.domaine.Pilote;

public abstract class TestNG_PiloteDao {
	protected PiloteDao piloteDao;
	private Pilote p1;
	private Pilote p2;
	private Pilote p3;
	private Pilote p4;

	private TypePlaneur tp1;
	private TypePlaneur tp2;
	private TypePlaneur tp3;

	@BeforeClass
	public abstract void init();
	
	@Test
	public void testEnregistrerPilote()
	{
		//	L'ajout des pilotes sera réussi
		this.p1=new Pilote("A", "A", "aa@gmail.com", "A", "1", "A", 1234, "123/12.34.56", 1);
		this.p2=new Pilote("B", "B", "bb@gmail.com", "B", "2", "B", 4567, "987/65.43.21", 2);
		this.p3=new Pilote("C", "B", "cc@gmail.com", "C", "3", "C", 7894, "025/78.21.65", -250);
		this.p4=new Pilote("D", "D", "dd@gmail.com", "D", "4", "D", 5874, "087/85.23.54", -500);
		
		try{
			assertTrue(this.piloteDao.ajouterPilote(p1));
			assertTrue(this.piloteDao.ajouterPilote(p2));
			assertTrue(this.piloteDao.ajouterPilote(p3));
			assertTrue(this.piloteDao.ajouterPilote(p4));
		}catch(Exception e){
			fail("L'ajout aurait dû réussir");
		}
	}
	
	@Test(dependsOnMethods="testEnregistrerPilote")
	public void testListerPilotes(){
		List<Pilote> liste = this.piloteDao.listerPilotes();
		
		assertTrue(liste.get(0).equals(p1));
		assertTrue(liste.get(1).equals(p2));
		assertTrue(liste.get(2).equals(p3));
		assertTrue(liste.get(3).equals(p4));
	}

	@Test(dependsOnMethods = "testEnregistrerPilote")
	public void testListerPilotesSoldeNegatif(){
		List<Pilote> liste = this.piloteDao.listerPilotesSoldeNegatif();

		assertTrue(liste.get(0).equals(p4));
		assertTrue(liste.get(1).equals(p3));
	}

	@Test(dependsOnMethods = "testListerPilotes")
	public void testModifierPilote(){
		Pilote p1Modifier = p1;
		p1Modifier.setNoGsm("587/54.32.52");

		assertTrue(this.piloteDao.modifierPilote(p1Modifier));

		Pilote p2Modifier = p2;
		p2Modifier.setLocalite("Bruxelles");

		assertTrue(this.piloteDao.modifierPilote(p2Modifier));
	}

}
