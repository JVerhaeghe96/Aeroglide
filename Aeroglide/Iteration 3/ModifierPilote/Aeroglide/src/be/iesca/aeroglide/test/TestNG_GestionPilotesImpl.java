/**
 * @author Nardella Marine
 * @version 1.0
 */

// test d'intégration
package be.iesca.aeroglide.test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.*;

import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.usecaseimpl.GestionPilotesImpl;

public class TestNG_GestionPilotesImpl {
	private GestionPilotesImpl gestionPilotes;
	private Bundle bundle;
	private Pilote p1;
	private Pilote p2;
	private Pilote p3;
	
	/*
		création du gestionnaire de pilotes et du bundle
	*/
	@BeforeClass
	public void init(){
		this.gestionPilotes=new GestionPilotesImpl();
		this.bundle=new Bundle();
	}
	
	@Test
	public void testEnregistrerPilote()
	{
		//	L'ajout devrait bien se passer car aucun champ n'est manquant
		this.p1=new Pilote("A", "A", "aaertg@gmail.com", "A", "1", "A", 1234, "+32475/12.13.14", 1);
		//	L'ajout devrait bien se passer car le champ numéro de GSM ne sont pas obligatoires 
		this.p2=new Pilote("B", "B", "baeb@gmail.com", "B", "2", "B", 4567, "", 2);
		//	L'ajout devrait être refusé car le prénom est manquant
		this.p3=new Pilote("C", "", "caerty@gmail.com", "C", "3", "C", 7894, "0475/12.13.14",-5);
		
		//ajouter pilote dans bundle
		bundle.put(Bundle.PILOTE, p1);
				
		//ajouter le bundle dans le gestionnaire de pilote
		this.gestionPilotes.ajouterPilote(bundle);
				
		//tester le bundle
		assertTrue((Boolean)this.bundle.get(Bundle.OPERATION_REUSSIE));
		
		//vider bundle
		this.bundle.vider();
		
	
		bundle.put(Bundle.PILOTE, p2);
		this.gestionPilotes.ajouterPilote(bundle);
		assertTrue((Boolean)this.bundle.get(Bundle.OPERATION_REUSSIE));
		this.bundle.vider();
		
		bundle.put(Bundle.PILOTE, p3);
		this.gestionPilotes.ajouterPilote(bundle);
		assertFalse((Boolean)this.bundle.get(Bundle.OPERATION_REUSSIE));
		this.bundle.vider();
		
	}
	
	@Test(dependsOnMethods="testEnregistrerPilote")
	public void testListerPilotes(){
		this.gestionPilotes.listerPilotes(bundle);
		
		assertTrue((Boolean) bundle.get(Bundle.OPERATION_REUSSIE));
		@SuppressWarnings("unchecked")
		List<Pilote> liste = (List<Pilote>) bundle.get(Bundle.LISTE);
		
		assertTrue(liste.get(0).equals(p1));
		assertTrue(liste.get(1).equals(p2));

		try{
			assertTrue(liste.get(2).equals(p2));
		}catch(IndexOutOfBoundsException e){
			// Ok, il ne doit pas y avoir de 3ème pilote
		}
	}
	
	
}
