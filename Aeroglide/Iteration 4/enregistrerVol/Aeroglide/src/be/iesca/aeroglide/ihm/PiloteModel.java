/**
 * @author Nardella Marine
 * @version 1.0
 */

package be.iesca.aeroglide.ihm;

import be.iesca.aeroglide.domaine.Bundle;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PiloteModel {
	private Bundle bundle;
	private ArrayList<ChangeListener> listeVues;

	public PiloteModel() {
		this.bundle = new Bundle();
		this.bundle.put(Bundle.OPERATION_REUSSIE, true);
		this.listeVues = new ArrayList<ChangeListener>(1);
	}

	public PiloteModel(String message) {
		this();
		this.bundle.put(Bundle.MESSAGE, message);
	}

	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
		traiterEvent(new ChangeEvent(this));
	}

	/** Enregistre un listener */
	public synchronized void addChangeListener(ChangeListener chl) {
		if (!listeVues.contains(chl)) {
			listeVues.add(chl);
		}
	}

	/** supprime un listener */
	public synchronized void removeChangeListener(ChangeListener chl) {
		if (listeVues.contains(chl)) {
			listeVues.remove(chl);
		}
	}

	/** notifie le(s) listener(s) */
	protected synchronized void traiterEvent(ChangeEvent e) {
		for (ChangeListener listener : listeVues) {
			listener.stateChanged(e);
		}
	}

	public Bundle getBundle() {
		return this.bundle;
	}


}
