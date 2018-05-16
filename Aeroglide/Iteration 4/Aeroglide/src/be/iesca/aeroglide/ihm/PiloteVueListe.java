/**
 * @author Verhaeghe Julien
 * @version 1.0
 */

package be.iesca.aeroglide.ihm;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

import be.iesca.aeroglide.controleur.GestionnaireUseCases;
import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.domaine.Pilote;

import java.awt.*;

@SuppressWarnings("serial")
public class PiloteVueListe extends JPanel implements ChangeListener{
	private static final String[] nomsColonnes = {"Id", "Nom", "Prénom", "Email",
			"Rue", "Numéro", "Localité", "Code Postal", "Numéro de GSM", "Solde"};
	private Object[][] donnees;
	private PiloteModel model;
	private JTable jtable;
	private GestionnaireUseCases gestionnaireUseCases;
	private Bundle bundle;

	public PiloteVueListe(PiloteModel model) {
		this.setLayout(new BorderLayout());
		this.gestionnaireUseCases = GestionnaireUseCases.getINSTANCE();
		this.bundle = new Bundle();

		this.jtable = new JTable();
		this.jtable.setModel(new PiloteTableModel(this.donnees, nomsColonnes));
		JScrollPane jspTable = new JScrollPane(this.jtable);
		this.add(jspTable);
		if (model != null) {
			this.model = model;
			this.model.addChangeListener(this);
			majListe();
		}
		SwingUtilities.updateComponentTreeUI(this);
	}


	private void majListe(){
		if (model == null)
			return;
		Bundle bundle = model.getBundle();

		if ((Boolean) bundle.get(Bundle.OPERATION_REUSSIE)){
			@SuppressWarnings("unchecked")
			List<Pilote> listePilotes = (List<Pilote>) bundle.get(Bundle.LISTE);
			this.donnees = new String[listePilotes.size()][nomsColonnes.length];

			for (int i = 0; i < listePilotes.size(); i++) {

				Pilote p = listePilotes.get(i);
				donnees[i][0] = ""+p.getIdPilote();
				donnees[i][1] = p.getNom();
				donnees[i][2] = p.getPrenom();
				donnees[i][3] = p.getEmail();
				donnees[i][4] = p.getRue();
				donnees[i][5] = p.getNumero();
				donnees[i][6] = p.getLocalite();
				donnees[i][7] = ""+p.getCodePostal();
				donnees[i][8] = p.getNoGsm();
				donnees[i][9] = ""+p.getSolde();
			}
			this.jtable.setModel(new PiloteTableModel(donnees, nomsColonnes));
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		majListe();
	}


	class PiloteTableModel extends DefaultTableModel{
		public PiloteTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return column != 0;
		}

		@Override
		public void fireTableCellUpdated(int row, int column) {
			super.fireTableCellUpdated(row, column);
			Pilote pilote = null;

			try{
				int idPilote = Integer.parseInt((String) this.getValueAt(row, 0));
				String nom = (String) this.getValueAt(row, 1);
				String prenom = (String) this.getValueAt(row, 2);
				String email = (String) this.getValueAt(row, 3);
				String rue = (String) this.getValueAt(row, 4);
				String numero = (String) this.getValueAt(row, 5);
				String localite = (String) this.getValueAt(row, 6);
				int codePostal = Integer.parseInt((String)this.getValueAt(row, 7));
				String noGsm = (String) this.getValueAt(row, 8);
				double solde =  Double.parseDouble((String)this.getValueAt(row, 9));

				pilote = new Pilote(nom, prenom, email, rue, numero, localite, codePostal, noGsm, solde);
				pilote.setIdPilote(idPilote);
				bundle.put(Bundle.PILOTE, pilote);
				PiloteVueListe.this.gestionnaireUseCases.modifierPilote(bundle);
			}catch(ClassCastException e){
				String champs = this.getColumnName(column);
				bundle.put(Bundle.MESSAGE, "Erreur sur le champ "+ champs +" : format de donnée inattendu.");
			}

			//	On récupère la liste pour la mettre à jour avant de remettre cette liste dans le bundle
			@SuppressWarnings("unchecked")
			List<Pilote> listePilote = (List<Pilote>) PiloteVueListe.this.model.getBundle().get(Bundle.LISTE);

			listePilote.remove(row);
			listePilote.add(row, pilote);

			bundle.put(Bundle.LISTE, listePilote);

			PiloteVueListe.this.model.setBundle(bundle);
		}
	}

}
