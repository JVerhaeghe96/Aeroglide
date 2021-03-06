package be.iesca.aeroglide.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import be.iesca.aeroglide.dao.PiloteDao;
import be.iesca.aeroglide.domaine.Pilote;

public class PiloteDaoImpl implements PiloteDao {
	private static final String AJOUT = "INSERT INTO pilote (nom,prenom,email,rue,numero,localite,codePostal,noGsm,solde) VALUES (?,?,?,?,?,?,?,?,?)";

	// obligatoire pour pouvoir construire une instance avec newInstance() 
	public PiloteDaoImpl() {
	}
	
	private void cloturer(ResultSet rs, PreparedStatement ps, Connection con) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception ex) {
		}
		try {
			if (ps != null)
				ps.close();
		} catch (Exception ex) {
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception ex) {
		}
	}
	
	@Override
	public boolean ajouterPilote(Pilote pilote) {
		boolean ajoutReussi = false;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(AJOUT);
			ps.setString(1, pilote.getNom().trim());
			ps.setString(2, pilote.getPrenom().trim());
			ps.setString(3, pilote.getEmail().trim());
			ps.setString(4, pilote.getRue().trim());
			ps.setString(5, pilote.getNumero().trim());
			ps.setString(6, pilote.getLocalite().trim());
			ps.setLong(7, pilote.getCodePostal());
			ps.setString(8, pilote.getNoGsm().trim());
			ps.setDouble(9, pilote.getSolde());
			
			int resultat = ps.executeUpdate();
			if (resultat == 1) {
				ajoutReussi = true;
			}
		} catch (Exception ex) {
			ajoutReussi = false;
		} finally {
			cloturer(null, ps, con);
		}
		return ajoutReussi;
	}
	

}
