package be.iesca.aeroglide.test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import be.iesca.aeroglide.dao.PiloteDao;
import be.iesca.aeroglide.dao.VolDao;
import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.domaine.TypePlaneur;
import be.iesca.aeroglide.domaine.Vol;

public abstract class TestNG_VolDao {
	protected PiloteDao piloteDao;
	protected VolDao volDao;
	private Pilote p1;
	private Pilote p2;
	private Pilote p3;
	private Vol v1;
	private Vol v2;
	
	@BeforeClass
	public abstract void init();
	
	@Test
	public void testEnregistrerVol()
	{
		//	Pilote
		this.p1=new Pilote("A", "A", "aa@gmail.com", "A", "1", "A", 1234, "123/12.34.56", 1);
		this.p2=new Pilote("B", "B", "bb@gmail.com", "B", "2", "B", 4567, "987/65.43.21", 2);
		
		//date
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date dateUtil = sdf.parse("18/05/2018");
			Date date=new Date(dateUtil.getTime());
			
			//type de planneur
			List<TypePlaneur> liste = new ArrayList<>();
			liste.add(new TypePlaneur("bois & toile", 17, 25));
			liste.add(new TypePlaneur("biplace", 30, 25));
			
			// vol
			this.v1=new Vol(30,date,15.3,p1,liste.get(0));
			this.v2=new Vol(30,date,15.3,p2,liste.get(1));
			
			this.piloteDao.ajouterPilote(this.p1);
			System.out.println(this.p1.getIdPilote());
			this.piloteDao.ajouterPilote(this.p2);
			
			assertTrue(this.volDao.enregistrerVol(this.v1));
			assertTrue(this.volDao.enregistrerVol(this.v2));
		} catch (ParseException e) {
			e.printStackTrace();
			fail("il aurait dû accepter de parser la date");
		}
	}

}
