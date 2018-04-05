/**
 * @author Nardella Marine
 * @version 1.0
 */

package be.iesca.aeroglide.daoimpl;
import be.iesca.aeroglide.dao.Dao;

public class DaoFactory {
	private static final DaoFactory INSTANCE = new DaoFactory();
	private Dao dao;

	public static DaoFactory getInstance() {
		return INSTANCE;
	}

	private DaoFactory(){
		this.dao=new PiloteDaoMockImpl();
	}

	public Dao getDao() {
		return this.dao;
	}
	
	

}
