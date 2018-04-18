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

import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.domaine.Pilote;

import java.awt.*;

@SuppressWarnings("serial")
public class PiloteVueListe extends JPanel implements ChangeListener{
	private static final String[] nomsColonnes = { "Nom", "Prénom", "Email",
			"Rue", "Numéro", "Localité", "Code Postal", "Numéro de GSM", "Solde"};
	private PiloteModel model;
	private JTable jtable;

	public PiloteVueListe(PiloteModel model) {
		this.setLayout(new BorderLayout());

		this.jtable = new JTable();
		this.jtable.setEnabled(false);
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
			List<Pilote> listeBieres = (List<Pilote>) bundle.get(Bundle.LISTE);
			String[][] donnees = new String[listeBieres.size()][nomsColonnes.length];

			for (int i = 0; i < listeBieres.size(); i++) {
				Pilote p = listeBieres.get(i);
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
			this.jtable.setModel(new DefaultTableModel(donnees, nomsColonnes));
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		majListe();
	}

}
