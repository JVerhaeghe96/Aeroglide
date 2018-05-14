/**
 * @author Verhaeghe Julien
 * @version 1.0
 */

package be.iesca.aeroglide.domaine;

/*
	Cette classe permet de communiquer les informations d'une couche à l'autre.
 */
import java.util.HashMap;
import java.util.Map;


public class Bundle {	
	public static final String MESSAGE = "message";
	public static final String LISTE = "liste";
	public static final String PILOTE = "pilote";
	public static final String OPERATION_REUSSIE = "operationReussie";
	public static final String VOL = "vol";
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	
	public Bundle() {
		this.map.put(Bundle.OPERATION_REUSSIE, false);
		this.map.put(Bundle.MESSAGE, "");
	}
	public void put(String clef, Object valeur) {
		this.map.put(clef, valeur);
	}
	
	public Object get(String clef) {
		return this.map.get(clef);
	}
	
	public void vider() {
		this.map.clear();		
	}
}
