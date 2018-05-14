/**
 * @author Nardella Marine
 * @version 1.0
 */

// test d'int�gration
package be.iesca.aeroglide.test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.*;

import be.iesca.aeroglide.controleur.GestionnaireUseCases;
import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.domaine.TypePlaneur;
import be.iesca.aeroglide.domaine.Vol;
import be.iesca.aeroglide.usecaseimpl.GestionPilotesImpl;

public class TestNG_GestionPilotesImpl {
	private GestionnaireUseCases gestionnaire;
	private Bundle bundle;
	private Pilote p1;
	private Pilote p2;
	private Pilote p3;
	private Vol v1;
	private Vol v2;
	private Vol v3;
	private Vol v4;
	
	
	/*
		cr�ation du gestionnaire de pilotes et du bundle
	*/
	@BeforeClass
	public void init(){
		this.gestionnaire=GestionnaireUseCases.getINSTANCE();
		this.bundle=new Bundle();
	}
	
	@Test
	public void testEnregistrerPilote()
	{
		this.bundle.vider();
		//	L'ajout devrait bien se passer car aucun champ n'est manquant
		this.p1=new Pilote("A", "A", "aaertg@gmail.com", "A", "1", "A", 1234, "+32475/12.13.14", 1);
		//	L'ajout devrait bien se passer car le champ num�ro de GSM ne sont pas obligatoires 
		this.p2=new Pilote("B", "B", "baeb@gmail.com", "B", "2", "B", 4567, "", -500);
		//	L'ajout devrait �tre refus� car le pr�nom est manquant
		this.p3=new Pilote("C", "", "caerty@gmail.com", "C", "3", "C", 7894, "0475/12.13.14",-5);
		
		//ajouter pilote dans bundle
		bundle.put(Bundle.PILOTE, p1);
				
		//ajouter le bundle dans le gestionnaire de pilote
		this.gestionnaire.ajouterPilote(bundle);
				
		//tester le bundle
		assertTrue((Boolean)this.bundle.get(Bundle.OPERATION_REUSSIE));
		
		//vider bundle
		this.bundle.vider();
		
	
		bundle.put(Bundle.PILOTE, p2);
		this.gestionnaire.ajouterPilote(bundle);
		assertTrue((Boolean)this.bundle.get(Bundle.OPERATION_REUSSIE));
		this.bundle.vider();
		
		bundle.put(Bundle.PILOTE, p3);
		this.gestionnaire.ajouterPilote(bundle);
		assertFalse((Boolean)this.bundle.get(Bundle.OPERATION_REUSSIE));
		this.bundle.vider();
		
	}
	
	@Test(dependsOnMethods="testEnregistrerPilote")
	public void testListerPilotes(){
		this.bundle.vider();
		this.gestionnaire.listerPilotesSoldeNegatif(bundle);
		
		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		@SuppressWarnings("unchecked")
		List<Pilote> liste = (List<Pilote>) bundle.get(Bundle.LISTE);

		assertTrue(liste.get(0).equals(p2));

		try{
			assertTrue(liste.get(1).equals(p2));
		}catch(IndexOutOfBoundsException e){
			// Ok, il ne doit pas y avoir de 3eme pilote
		}
	}

	@Test(dependsOnMethods="testEnregistrerPilote")
	public void testListerPilotesSoldeNegatif(){
		this.bundle.vider();
		this.gestionnaire.listerPilotes(bundle);

		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		@SuppressWarnings("unchecked")
		List<Pilote> liste = (List<Pilote>) bundle.get(Bundle.LISTE);

		assertTrue(liste.get(0).equals(p1));
		assertTrue(liste.get(1).equals(p2));

		try{
			assertTrue(liste.get(2).equals(p2));
		}catch(IndexOutOfBoundsException e){
			// Ok, il ne doit pas y avoir de 3eme pilote
		}
	}

	@Test(dependsOnMethods = "testListerPilotes")
	public void testModifierPilotes(){
		this.bundle.vider();
		this.p1.setNoGsm("0478/52.32.54");
		this.p1.setLocalite("Tuiles");

		this.bundle.put(Bundle.PILOTE, this.p1);
		this.gestionnaire.modifierPilote(bundle);
		assertTrue((boolean) this.bundle.get(Bundle.OPERATION_REUSSIE));

		this.bundle.vider();
		this.p2.setNoGsm("0378/78.12.85");
		this.p2.setLocalite("Toles");

		this.bundle.put(Bundle.PILOTE, this.p2);
		this.gestionnaire.modifierPilote(bundle);
		assertTrue((boolean) this.bundle.get(Bundle.OPERATION_REUSSIE));

		this.bundle.vider();
	}
	
	@Test(dependsOnMethods = "testModifierPilotes")
	public void testEnregistrerVol(){
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
			this.v2=new Vol(35,date,18.3,p2,liste.get(1));
			this.v3=new Vol(-1,date,20.5,p1,liste.get(0));
			this.v4=new Vol(45,date,-2,p2,liste.get(1));
		
			bundle.put(Bundle.VOL,v1);
			this.gestionnaire.enregistrerVol(bundle);
			assertTrue((Boolean)this.bundle.get(Bundle.OPERATION_REUSSIE));
			this.bundle.vider();
			

			bundle.put(Bundle.VOL,v2);
			this.gestionnaire.enregistrerVol(bundle);
			assertTrue((Boolean)this.bundle.get(Bundle.OPERATION_REUSSIE));
			this.bundle.vider();
			

			bundle.put(Bundle.VOL,v3);
			this.gestionnaire.enregistrerVol(bundle);
			assertFalse((Boolean)this.bundle.get(Bundle.OPERATION_REUSSIE));
			this.bundle.vider();
			

			bundle.put(Bundle.VOL,v4);
			this.gestionnaire.enregistrerVol(bundle);
			assertFalse((Boolean)this.bundle.get(Bundle.OPERATION_REUSSIE));
			this.bundle.vider();
			
		}catch(Exception e){
			
		}
		
		
	}
	
}
