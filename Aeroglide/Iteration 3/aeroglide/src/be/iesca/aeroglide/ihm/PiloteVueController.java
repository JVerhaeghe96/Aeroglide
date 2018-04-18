/**
 * @author Nardella Marine
 * @version 1.0
 */

package be.iesca.aeroglide.ihm;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import be.iesca.aeroglide.controleur.GestionnaireUseCases;
import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.domaine.Pilote;

public class PiloteVueController extends JPanel{
	private PiloteModel piloteModel;
	private Bundle bundle = new Bundle();
	private GestionnaireUseCases gestionnaire;
	private JTextField jtfNom = new JTextField(20);
	private JTextField jtfPrenom = new JTextField(20);
	private JTextField jtfEmail = new JTextField(20);
	private JTextField jtfRue = new JTextField(20);
	private JTextField jtfNumero = new JTextField(20);
	private JTextField jtfLocalite = new JTextField(20);
	private JTextField jtfCodePostal = new JTextField(20);
	private JTextField jtfNoGsm = new JTextField(20);
	private JTextField jtfSolde = new JTextField(20);
	private JButton jbEnregistrerPilote = new JButton("Enregistrer pilote");
	
	public PiloteVueController(PiloteModel piloteModel){
		this.piloteModel=piloteModel;
		this.gestionnaire = GestionnaireUseCases.getINSTANCE();
		this.add(creerPanelSaisies(), BorderLayout.NORTH);
		
	}
	
	private JPanel creerPanelSaisies() {
		JPanel jpSaisies = new JPanel(new GridLayout(6, 1));
		jpSaisies.setPreferredSize(new Dimension(900, 180));

		// saisie nom et prénom
		JPanel jpNomPrenom = new JPanel(new GridLayout(1,2));
		JPanel jpNom=new JPanel(new BorderLayout());
		jpNom.add(new JLabel("   Nom   "),BorderLayout.WEST);
		jpNom.add(this.jtfNom);
		JPanel jpPrenom=new JPanel(new BorderLayout());
		jpPrenom.add(new JLabel("   Prenom  "),BorderLayout.WEST);
		jpPrenom.add(this.jtfPrenom);
		jpNomPrenom.add(jpNom);
		jpNomPrenom.add(jpPrenom);
		
		// saisie email
		JPanel jpEmail = new JPanel(new BorderLayout());
		jpEmail.add(new JLabel("   Email   "),BorderLayout.WEST);
		jpEmail.add(this.jtfEmail);
		
		// saisie rue et numero
		JPanel jpRueNumero= new JPanel(new GridLayout(1,2));
		JPanel jpRue=new JPanel(new BorderLayout());
		jpRue.add(new JLabel("   Rue   "),BorderLayout.WEST);
		jpRue.add(this.jtfRue);
		JPanel jpNumero=new JPanel(new BorderLayout());
		jpNumero.add(new JLabel("   Numero   "),BorderLayout.WEST);
		jpNumero.add(this.jtfNumero);
		jpRueNumero.add(jpRue);
		jpRueNumero.add(jpNumero);
		
		// saisie localite et code postal
		JPanel jpLocaliteCodePostal= new JPanel(new GridLayout(1,2));
		JPanel jpLocalite=new JPanel(new BorderLayout());
		jpLocalite.add(new JLabel("   Localite   "),BorderLayout.WEST);
		jpLocalite.add(this.jtfLocalite);
		JPanel jpCodePostal=new JPanel(new BorderLayout());
		jpCodePostal.add(new JLabel("   Code postal   "),BorderLayout.WEST);
		jpCodePostal.add(this.jtfCodePostal);
		jpRueNumero.add(jpLocalite);
		jpRueNumero.add(jpCodePostal);
		
		// saisie numero de gsm et solde
		JPanel jpNumeroSolde= new JPanel(new GridLayout(1,2));
		JPanel jpnumero=new JPanel(new BorderLayout());
		jpnumero.add(new JLabel("   Numero de GSM   "),BorderLayout.WEST);
		jpnumero.add(this.jtfNoGsm);
		JPanel jpSolde=new JPanel(new BorderLayout());
		jpSolde.add(new JLabel("   Solde   "),BorderLayout.WEST);
		jpSolde.add(this.jtfSolde);
		jpNumeroSolde.add(jpnumero);
		jpNumeroSolde.add(jpSolde);
		
		//bouton 
		jbEnregistrerPilote.addActionListener(e -> {
			enregistrerPilote();
		});
		
		jpSaisies.add(jpNomPrenom);
		jpSaisies.add(jpEmail);
		jpSaisies.add(jpRueNumero);
		jpSaisies.add(jpLocaliteCodePostal);
		jpSaisies.add(jpNumeroSolde);
		jpSaisies.add(jbEnregistrerPilote);
		return jpSaisies;
	}
	
	private void enregistrerPilote() {
		
		try{
			String nom = jtfNom.getText().trim();
			String prenom = jtfPrenom.getText().trim();
			String email=jtfEmail.getText().trim();
			String rue=jtfRue.getText().trim();
			String numero=jtfNumero.getText().trim();
			String localite=jtfLocalite.getText().trim();
			int codePostal=Integer.parseInt(jtfCodePostal.getText().trim());
			String noGsm=jtfNoGsm.getText().trim();
			double solde=Double.parseDouble(jtfSolde.getText().trim());
			
			Pilote pilote = new Pilote(nom,prenom,email,rue,numero,localite,codePostal,noGsm,solde);
			bundle.put(Bundle.PILOTE, pilote);
			this.gestionnaire.ajouterPilote(bundle);
		}catch(NumberFormatException nfe){
			bundle.put(Bundle.MESSAGE, "Erreur : nombre attendu sur le champ code postal ou solde");
		}
		
		this.piloteModel.setBundle(bundle);
	}
	
	
}
