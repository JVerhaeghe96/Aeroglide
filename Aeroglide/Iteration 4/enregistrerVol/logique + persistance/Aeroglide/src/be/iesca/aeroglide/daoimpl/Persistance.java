/**
 * @author Nardella Marine
 * @version 1.0
 */

package be.iesca.aeroglide.daoimpl;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import be.iesca.aeroglide.dao.Dao;



/*
 * Une instance de cette classe encapsule les donn�es caract�risant un syst�me
 * de persistance. Elle sera soit de type MOCK, soit de type DB.
 * Si, elle est de type MOCK, les attributs driver, user, et password ne seront
 * pas utilis�s.
 * Le parser assignera aux attributs les valeurs extraites du fichier XML. 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "persistance")
public class Persistance {
	public static String MOCK = "MOCK";
	public static String DB = "DB";

	@XmlAttribute
	private String type; // type de persistance

	@XmlElement(name = "dao")
	private Set<String> daos = new HashSet<String>(); // ensemble des noms des
														// classes
														// d'impl�mentation des
														// daos

	private String driver; // nom du driver jdbc

	private String url; // url d'acc�s � la db

	private String user; // login d'un utilisateur enregistr� dans la db

	private String password; // mot de passe de cet utilisateur

	public Persistance() {
		super();
	}

	// Le daoFactory appellera cette m�thode afin de charger
	// le driver jdbc sp�cifi� dans le fichier de configuration
	// Devenu inutile depuis le jdbc4, car il et charg� automatiquement par le
	// driver manager. Obligatoire pour les versions pr�c�dentes.
	public void configurer() {
		if (this.type.equals(DB)) {
			try {
				Class.forName(this.driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	// renvoie l'instance du dao dont on sp�cifie l'interface
	@SuppressWarnings("unchecked")
	public Dao getDaoImpl(Class<? extends Dao> interfaceDao) {
		Dao dao = null;
		Class<Dao> classeDaoImpl;
		Class<?>[] interfaces;
		try {
			for (String nomDaoImpl : daos) {
				classeDaoImpl = (Class<Dao>) Class.forName(nomDaoImpl);
				interfaces = (Class<?>[]) classeDaoImpl.getInterfaces();
				for (Class<?> i : interfaces) {
					if ( i.getName().equals(interfaceDao.getName())) {
						dao = (Dao) classeDaoImpl.newInstance();
						return dao;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getType() {
		return this.type;
	}

	public int getNbDaos() {
		return this.daos.size();
	}

}
