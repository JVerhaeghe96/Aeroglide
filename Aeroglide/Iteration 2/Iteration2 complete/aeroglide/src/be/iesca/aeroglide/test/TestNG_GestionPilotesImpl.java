/**
 * @author Nardella Marine
 * @version 1.0
 */

// test d'intégration
package be.iesca.aeroglide.test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.*;

import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.usecaseimpl.GestionPilotesImpl;

public class TestNG_GestionPilotesImpl {
	private GestionPilotesImpl gestionPilotes;
	private Bundle bundle;
	
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
		Pilote p1=new Pilote("A", "A", "aaertg@gmail.com", "A", "1", "A", 1234, "+32475/12.13.14", 1);
		//	L'ajout devrait bien se passer car le champ email et le champ numéro de GSM ne sont pas obligatoires 
		Pilote p2=new Pilote("B", "B", "", "B", "2", "B", 4567, "", 2);
		//	L'ajout devrait être refusé car le prénom est manquant
		Pilote p3=new Pilote("C", "", "caerty@gmail.com", "C", "3", "C", 7894, "0475/12.13.14",-5);
		
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
	
	
}
