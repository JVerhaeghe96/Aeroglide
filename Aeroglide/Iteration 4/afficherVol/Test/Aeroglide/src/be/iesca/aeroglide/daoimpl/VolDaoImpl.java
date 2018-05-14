package be.iesca.aeroglide.daoimpl;

import be.iesca.aeroglide.dao.VolDao;
import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.domaine.TypePlaneur;
import be.iesca.aeroglide.domaine.Vol;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VolDaoImpl implements VolDao{
    private static final String AJOUT = "INSERT INTO vol (duree, date, cout, idPilote, planeur) VALUES (?, ?, ?, ?, ?)";
    private static final String MODIFIER_SOLDE_PILOTE = "UPDATE pilote SET solde=? WHERE idpilote=?";
    private static final String LISTER_PLANEURS = "SELECT * FROM typeplaneur";
    private static final String LISTER_VOLS = "SELECT * FROM vol INNER JOIN pilote ON vol.idpilote = pilote.idpilote INNER JOIN typeplaneur ON typeplaneur.planeur = vol.planeur WHERE vol.date = ?";
    private static final String LISTER_DATES = "SELECT DISTINCT date FROM vol";
    
    public VolDaoImpl(){

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
    public boolean enregistrerVol(Vol vol) {
        boolean ajoutReussi = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        con = DaoFactory.getInstance().getConnexion();
        try{
            con.setAutoCommit(false);
            ps = con.prepareStatement(AJOUT, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, vol.getDuree());
            ps.setDate(2, vol.getDate());
            ps.setDouble(3, vol.getCout());
            ps.setInt(4, vol.getPilote().getIdPilote());
            ps.setString(5, vol.getPlaneur().getPlaneur());

            int result = ps.executeUpdate();

            if(result == 1){
                rs = ps.getGeneratedKeys();
                if(modifierSoldePilote(vol.getPilote()) && rs.next()){
                    ajoutReussi = true;
                    con.commit();
                    vol.setId((int) rs.getLong(1));
                }else{
                    con.rollback();
                    ajoutReussi = false;
                }
            }else{
                con.rollback();
            }
        }catch(Exception e){
            try {
                con.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
            }
            ajoutReussi = false;
        }finally{
            cloturer(rs, ps, con);
        }

        return ajoutReussi;
    }

    @Override
    public boolean modifierSoldePilote(Pilote pilote){
        boolean modificationReussie = false;
        Connection con = null;
        PreparedStatement ps = null;

        con = DaoFactory.getInstance().getConnexion();

        try{
            ps = con.prepareStatement(MODIFIER_SOLDE_PILOTE);

            ps.setDouble(1, pilote.getSolde());
            ps.setInt(2, pilote.getIdPilote());

            int result = ps.executeUpdate();

            if(result == 1){
                modificationReussie = true;
            }

        }catch(Exception e){
            modificationReussie = false;
        }finally{
            cloturer(null, ps, con);
        }

        return modificationReussie;
    }

    @Override
    public List<TypePlaneur> listerTypePlaneur(){
        List<TypePlaneur> liste = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            con = DaoFactory.getInstance().getConnexion();

            ps = con.prepareStatement(LISTER_PLANEURS);

            rs = ps.executeQuery();
            while(rs.next()){
                TypePlaneur tp = new TypePlaneur(rs.getString("planeur"),
                                                 rs.getDouble("tarifhoraire"),
                                                 rs.getDouble("coutremorquage"));
                liste.add(tp);
            }
        }catch(Exception e){
            liste = null;
        }finally{
            cloturer(rs, ps, con);
        }

        return liste;
    }

	@Override
	public List<Vol> listerVols(Date date) {
		List<Vol> liste=new ArrayList<Vol>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			con=DaoFactory.getInstance().getConnexion();
			ps=con.prepareStatement(LISTER_VOLS);

			ps.setDate(1, date);
			
			rs=ps.executeQuery();
			while(rs.next()){
				Vol vol=new Vol(rs.getInt("duree"),
						        rs.getDate("date"),
						        rs.getDouble("cout"),
						        new Pilote(
										rs.getString("nom"), 
										rs.getString("prenom"),
										rs.getString("email"),
										rs.getString("rue"),
										rs.getString("numero"),
										rs.getString("localite"),
										rs.getInt("codePostal"),
										rs.getString("nogsm"),
										rs.getDouble("solde")),
						        new TypePlaneur(rs.getString("planeur"),
                                        rs.getDouble("tarifhoraire"),
                                        rs.getDouble("coutremorquage"))
						);
				vol.setId(rs.getInt(1));
				liste.add(vol);
			}
		}catch(Exception e){
			liste=null;
		}finally{
			cloturer(rs,ps,con);
		}
		return liste;
	}

	@Override
	public List<Date> listerDates() {
		List<Date> liste=new ArrayList<Date>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			con=DaoFactory.getInstance().getConnexion();
			ps=con.prepareStatement(LISTER_DATES);
			rs=ps.executeQuery();
			while(rs.next()){
				Date date=rs.getDate("date");
				liste.add(date);
			}
		}catch(Exception e){
			liste=null;
		}finally{
			cloturer(rs,ps,con);
		}
		
		
	
		return liste;
	}
}
