package be.iesca.aeroglide.ihm;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.iesca.aeroglide.controleur.GestionnaireUseCases;
import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.domaine.TypePlaneur;
import be.iesca.aeroglide.domaine.Vol;

public class VolVueController extends JPanel{
	private PiloteModel piloteModel;
	private Bundle bundle;
	private GestionnaireUseCases gestionnaire;
	private JTextField jtfDuree;
	private JTextField jtfDate;
	private JTextField jtfCout;
	private JComboBox<Pilote> jcbPilote;
	private JComboBox<TypePlaneur> jcbPlaneur;
	private JButton jbEnregistrerVol;
	private List<Pilote> listePilotes;
	private List<TypePlaneur> listePlaneurs;

	
	@SuppressWarnings("unchecked")
	public VolVueController(PiloteModel piloteModel){
		this.piloteModel=piloteModel;
		this.gestionnaire = GestionnaireUseCases.getINSTANCE();
		this.bundle=new Bundle();
		
		this.gestionnaire.listerPilotes(bundle);

		if(!(boolean)bundle.get(Bundle.OPERATION_REUSSIE)){
			bundle.put(Bundle.MESSAGE, "Aucun pilote n'a été enregistré.");
			this.piloteModel.setBundle(this.bundle);
		}else{
			this.listePilotes=(List<Pilote>) bundle.get(Bundle.LISTE);
			Pilote[] pilotes=new Pilote[listePilotes.size()];
			for(int i=0;i<listePilotes.size();i++){
				pilotes[i]=listePilotes.get(i);
			}

			this.gestionnaire.listerTypePlaneur(bundle);
			this.listePlaneurs=(List<TypePlaneur>) bundle.get(Bundle.LISTE);
			TypePlaneur[] planeurs=new TypePlaneur[listePlaneurs.size()];
			for(int i=0;i<listePlaneurs.size();i++){
				planeurs[i]=listePlaneurs.get(i);
			}


			this.jtfDuree = new JTextField(20);
			this.jtfDate = new JTextField(20);
			this.jtfCout = new JTextField(20);
			this.jcbPilote = new JComboBox<Pilote>(pilotes);
			this.jcbPlaneur = new JComboBox<TypePlaneur>(planeurs);
			this.jbEnregistrerVol = new JButton("Enregistrer vol");

			this.jcbPilote.addActionListener(e-> System.out.println(listePilotes.get(jcbPilote.getSelectedIndex()).getIdPilote()));

			this.add(creerPanelSaisies(), BorderLayout.NORTH);
		}
	}
	
	private JPanel creerPanelSaisies() {
		JPanel jpSaisies = new JPanel(new GridLayout(6, 1));
		jpSaisies.setPreferredSize(new Dimension(900, 180));
		
		// saisie duree
		JPanel jpDuree = new JPanel(new BorderLayout());
		jpDuree.add(new JLabel("   Duree   "),BorderLayout.WEST);
		jpDuree.add(this.jtfDuree);
		
		// saisie date
		JPanel jpDate = new JPanel(new BorderLayout());
		jpDate.add(new JLabel("   date   "),BorderLayout.WEST);
		jpDate.add(this.jtfDate);
		
		// saisie cout
		JPanel jpCout = new JPanel(new BorderLayout());
		jpCout.add(new JLabel("   cout   "),BorderLayout.WEST);
		jpCout.add(this.jtfCout);
		
		// liste pilote
		JPanel jpPilote = new JPanel(new BorderLayout());
		jpPilote.add(new JLabel("   Pilote   "),BorderLayout.WEST);
		jpPilote.add(this.jcbPilote);
		
		// saisie planeur
		JPanel jpPlaneur = new JPanel(new BorderLayout());
		jpPlaneur.add(new JLabel("   Planeur   "),BorderLayout.WEST);
		jpPlaneur.add(this.jcbPlaneur);
		
		jpSaisies.add(jpDuree);
		jpSaisies.add(jpDate);
		jpSaisies.add(jpCout);
		jpSaisies.add(jpPilote);
		jpSaisies.add(jpPlaneur);
		jpSaisies.add(jbEnregistrerVol);
		
		//bouton 
		jbEnregistrerVol.addActionListener(e -> {
			enregistrerVol();
		});
		return jpSaisies;
	}
	
	private void enregistrerVol() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		try{
			java.util.Date dateUtil=sdf.parse(jtfDate.getText().trim());
			int duree = Integer.parseInt(jtfDuree.getText().trim());
			Date date = new Date(dateUtil.getTime());
			double cout=Double.parseDouble(jtfCout.getText().trim());
			Pilote pilote=listePilotes.get(jcbPilote.getSelectedIndex());
			TypePlaneur planeur=listePlaneurs.get(jcbPlaneur.getSelectedIndex());
			
			Vol vol=new Vol(duree, date, cout, pilote, planeur);
			this.bundle.put(Bundle.VOL, vol);
			this.gestionnaire.enregistrerVol(bundle);
			
		}catch(NumberFormatException nfe){
			bundle.put(Bundle.MESSAGE, "Veuillez rentrer un nombre dans le champ \"duree\" / \"cout\"");
			bundle.put(Bundle.OPERATION_REUSSIE, false);
		}catch (ParseException e){
			bundle.put(Bundle.MESSAGE, "Le format de la date n'est pas correct, format attendu: dd/MM/yyyy");
			bundle.put(Bundle.OPERATION_REUSSIE, false);
		}
		
		if((Boolean) bundle.get(Bundle.OPERATION_REUSSIE))
			this.viderFormulaire();
		
		this.piloteModel.setBundle(bundle);
	}
	
	private void viderFormulaire(){
		this.jtfDuree.setText("");
		this.jtfDate.setText("");
		this.jtfCout.setText("");
	}
	

}
