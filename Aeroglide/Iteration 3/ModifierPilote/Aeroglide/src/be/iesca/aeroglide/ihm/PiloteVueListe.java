/**
 * @author Verhaeghe Julien
 * @version 1.0
 */

package be.iesca.aeroglide.ihm;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import java.util.List;

import be.iesca.aeroglide.controleur.GestionnaireUseCases;
import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.domaine.Pilote;

import java.awt.*;

@SuppressWarnings("serial")
public class PiloteVueListe extends JPanel implements ChangeListener{
	private static final String[] nomsColonnes = { "Nom", "Pr�nom", "Email",
			"Rue", "Num�ro", "Localit�", "Code Postal", "Num�ro de GSM", "Solde"};
	private PiloteModel model;
	private JTable jtable;
	private GestionnaireUseCases gestionnaireUseCases;
	private Bundle bundle;

	public PiloteVueListe(PiloteModel model) {
		this.setLayout(new BorderLayout());
		this.gestionnaireUseCases = GestionnaireUseCases.getINSTANCE();
		this.bundle = new Bundle();

		this.jtable = new JTable();
		JScrollPane jspTable = new JScrollPane(this.jtable);
		this.add(jspTable);
		if (model != null) {
			this.model = model;
			this.model.addChangeListener(this);
			majListe();
		}

	}


	private void majListe() {
		if (model == null)
			return;
		Bundle bundle = model.getBundle();

		if ((Boolean) bundle.get(Bundle.OPERATION_REUSSIE)) {
			@SuppressWarnings("unchecked")
			List<Pilote> listePilotes = (List<Pilote>) bundle.get(Bundle.LISTE);
			Object[][] donnees = new String[listePilotes.size()][nomsColonnes.length];

			for (int i = 0; i < listePilotes.size(); i++) {

				Pilote p = listePilotes.get(i);
				donnees[i][0] = p.getNom();
				donnees[i][1] = p.getPrenom();
				donnees[i][2] = p.getEmail();
				donnees[i][3] = p.getRue();
				donnees[i][4] = p.getNumero();
				donnees[i][5] = p.getLocalite();
				donnees[i][6] = ""+p.getCodePostal();
				donnees[i][7] = p.getNoGsm();
				donnees[i][8] = ""+p.getSolde();
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
		public void fireTableCellUpdated(int row, int column) {
			super.fireTableCellUpdated(row, column);

			try{String nom = (String) this.getValueAt(row, 0);
				String prenom = (String) this.getValueAt(row, 1);
				String email = (String) this.getValueAt(row, 2);
				String rue = (String) this.getValueAt(row, 3);
				String numero = (String) this.getValueAt(row, 4);
				String localite = (String) this.getValueAt(row, 5);
				int codePostal = Integer.parseInt((String)this.getValueAt(row, 6));
				String noGsm = (String) this.getValueAt(row, 7);
				double solde =  Double.parseDouble((String)this.getValueAt(row, 8));

				Pilote pilote = new Pilote(nom, prenom, email, rue, numero, localite, codePostal, noGsm, solde);
				bundle.put(Bundle.PILOTE, pilote);
				PiloteVueListe.this.gestionnaireUseCases.modifierPilote(bundle);
			}catch(ClassCastException e){
				String champs = this.getColumnName(column);
				bundle.put(Bundle.MESSAGE, "Erreur sur le champ "+ champs +" : format de donnée inattendu.");
			}

			PiloteVueListe.this.model.setBundle(bundle);
		}
	}

}
