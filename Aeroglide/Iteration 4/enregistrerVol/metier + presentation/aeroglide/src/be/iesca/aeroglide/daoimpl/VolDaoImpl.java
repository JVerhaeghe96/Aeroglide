package be.iesca.aeroglide.daoimpl;

import be.iesca.aeroglide.dao.VolDao;
import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.domaine.TypePlaneur;
import be.iesca.aeroglide.domaine.Vol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VolDaoImpl implements VolDao{
    private static final String AJOUT = "INSERT INTO vol (duree, date, cout, idPilote, planeur) VALUES (?, ?, ?, ?, ?)";
    private static final String MODIFIER_SOLDE_PILOTE = "UPDATE pilote SET solde=? WHERE idpilote=?";
    private static final String LISTER_PLANEURS = "SELECT * FROM typeplaneur";

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

        con = DaoFactory.getInstance().getConnexion();
        try{
            con.setAutoCommit(false);
            ps = con.prepareStatement(AJOUT);

            ps.setInt(1, vol.getDuree());
            ps.setDate(2, vol.getDate());
            ps.setDouble(3, vol.getCout());
            ps.setInt(4, vol.getPilote().getIdPilote());
            ps.setString(5, vol.getPlaneur().getPlaneur());

            int result = ps.executeUpdate();

            if(result == 1){
                if(modifierSoldePilote(vol.getPilote())){
                    ajoutReussi = true;
                    con.commit();
                }
            }else{
                con.rollback();
            }
        }catch(Exception e){
            try {
                con.rollback();
            } catch (SQLException e1) {
            }
            ajoutReussi = false;
        }finally{
            cloturer(null, ps, con);
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
}
