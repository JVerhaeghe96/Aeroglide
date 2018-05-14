package be.iesca.aeroglide.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.*;

import be.iesca.aeroglide.dao.PiloteDao;
import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.domaine.Vol;


public class PiloteDaoImpl implements PiloteDao {
	private static final String AJOUT = "INSERT INTO pilote (nom,prenom,email,rue,numero,localite,codePostal,noGsm,solde) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String LISTER = "SELECT * FROM pilote ORDER BY nom, prenom";
	private static final String LISTER_SOLDE_NEGATIF = "SELECT * FROM pilote WHERE solde < 0 ORDER BY solde";
	private static final String MAJ = "UPDATE pilote SET nom= ?, prenom= ?, email=?, rue=?,numero=?,localite=?,codePostal=?,noGsm=?,solde=? where idPilote=?";

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
		ResultSet rs = null;
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(AJOUT, Statement.RETURN_GENERATED_KEYS);
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

				rs = ps.getGeneratedKeys();
				if(rs.next()){
					pilote.setIdPilote((int) rs.getLong(1));
				}else{
					ajoutReussi = false;
				}
			}
		} catch (Exception ex) {
			ajoutReussi = false;
		} finally {
			cloturer(rs, ps, con);
		}
		return ajoutReussi;
	}
	
	@Override
	public List<Pilote> listerPilotes(){
		List<Pilote> liste = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(LISTER);
			
			rs = ps.executeQuery();
			while(rs.next()){
				Pilote pilote = new Pilote(
						rs.getString("nom"), 
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("rue"),
						rs.getString("numero"),
						rs.getString("localite"),
						rs.getInt("codePostal"),
						rs.getString("nogsm"),
						rs.getDouble("solde")
					);
				pilote.setIdPilote(rs.getInt("idpilote"));
				liste.add(pilote);
			}
		}catch(Exception e){
			liste = null;
		}finally{
			cloturer(rs, ps, con);
		}
		
		return liste;
	}

	@Override
	public List<Pilote> listerPilotesSoldeNegatif() {
		List<Pilote> liste = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try{
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(LISTER_SOLDE_NEGATIF);

			rs = ps.executeQuery();
			while(rs.next()){
				Pilote pilote = new Pilote(
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("email"),
						rs.getString("rue"),
						rs.getString("numero"),
						rs.getString("localite"),
						rs.getInt("codePostal"),
						rs.getString("nogsm"),
						rs.getDouble("solde")
				);
				pilote.setIdPilote(rs.getInt("idpilote"));
				liste.add(pilote);
			}
		}catch(Exception e){
			liste = null;
		}finally{
			cloturer(rs, ps, con);
		}

		return liste;
	}
	
	@Override
	public boolean modifierPilote(Pilote pilote) {
		boolean modificationReussie = false;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(MAJ);
			String email = pilote.getEmail().trim();
			ps.setString(1, pilote.getNom().trim());
			ps.setString(2, pilote.getPrenom().trim());
			ps.setString(3, email);
			ps.setString(4, pilote.getRue().trim());
			ps.setString(5, pilote.getNumero().trim());
			ps.setString(6, pilote.getLocalite().trim());
			ps.setLong(7, pilote.getCodePostal());
			ps.setString(8, pilote.getNoGsm().trim());
			ps.setDouble(9, pilote.getSolde());
			ps.setLong(10, pilote.getIdPilote());
	
			int resultat = ps.executeUpdate();
			if (resultat == 1) {
				modificationReussie = true;
			}
		} catch (Exception ex) {
			modificationReussie = false;
		} finally {
			cloturer(null, ps, con);
		}
		return modificationReussie;
	}
}
