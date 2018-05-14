/**
 * @author Nardella Marine
 * @version 1.0
 */

package be.iesca.aeroglide.ihm;
/**
 *  Vue permettant l'affichage d'un message
 */
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import be.iesca.aeroglide.domaine.Bundle;

@SuppressWarnings("serial")
public class VueMessage extends JLabel implements ChangeListener {
	private PiloteModel model;

	public VueMessage(PiloteModel model) {
		this.setBorder(new TitledBorder("Message"));
		this.setPreferredSize(new Dimension(50, 60));
		if (model!=null) {
			this.model = model;
			this.model.addChangeListener(this);
			majMessage();
		}
	}

	private void majMessage() {
		if (model == null) return;
		Bundle bundle = this.model.getBundle();
		String message = (String) bundle.get(Bundle.MESSAGE);
		this.setText(message);
		boolean operationReussie = (Boolean) bundle
				.get(Bundle.OPERATION_REUSSIE);
		Color couleur = operationReussie ? Color.BLUE : Color.RED;
		this.setForeground(couleur);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		majMessage();
	}
}
