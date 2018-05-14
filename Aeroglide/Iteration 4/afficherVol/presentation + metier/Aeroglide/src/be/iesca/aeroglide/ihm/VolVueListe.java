package be.iesca.aeroglide.ihm;

import be.iesca.aeroglide.controleur.GestionnaireUseCases;
import be.iesca.aeroglide.domaine.Bundle;
import be.iesca.aeroglide.domaine.Pilote;
import be.iesca.aeroglide.domaine.Vol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class VolVueListe extends JPanel {
    private PiloteModel model;
    private GestionnaireUseCases gestionnaire;
    private Bundle bundle;
    private JComboBox<Date> jcbDates;
    private JButton jbListerVols;
    private List<Date> listeDate;
    private PanelListeVol plv;

    @SuppressWarnings("unchecked")
    public VolVueListe(PiloteModel model){
        this.model = model;
        this.gestionnaire = GestionnaireUseCases.getINSTANCE();
        this.bundle = new Bundle();

        this.setLayout(new BorderLayout());
        JPanel jpSaisie = new JPanel(new FlowLayout());
        this.add(jpSaisie, BorderLayout.NORTH);

        this.gestionnaire.listerDates(bundle);
        listeDate = (List<Date>) bundle.get(Bundle.LISTE);

        Date[] tabDates = new Date[listeDate.size()];
        for(int i = 0; i < listeDate.size(); i++){
            tabDates[i] = listeDate.get(i);
        }

        this.jcbDates = new JComboBox<>(tabDates);
        jpSaisie.add(jcbDates);

        this.jbListerVols = new JButton("Rechercher les vols");
        jbListerVols.addActionListener(e->listerVols());

        jpSaisie.add(jbListerVols);
    }

    @SuppressWarnings("unchecked")
    private void listerVols(){
        Date date = this.listeDate.get(this.jcbDates.getSelectedIndex());
        this.bundle.put(Bundle.DATE, date);
        this.gestionnaire.listerVols(bundle);

        List<Vol> listeVol = (List<Vol>) bundle.get(Bundle.LISTE);
        if(listeVol != null && !listeVol.isEmpty()){
            this.plv = new PanelListeVol(listeVol);
            this.add(plv);
            SwingUtilities.updateComponentTreeUI(this);
        }

        this.model.setBundle(bundle);
    }

    private class PanelListeVol extends JPanel{
        private final String[] nomsColonnes = {"Date", "Pilote", "Planeur", "Durée", "Coût"};
        private Object[][] donnees;
        private JTable jtable;

        public PanelListeVol(List<Vol> liste){
            this.jtable = new JTable();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyy");
            donnees = new Object[liste.size()][nomsColonnes.length];
            for(int i = 0; i < liste.size(); i++){
                Vol v = liste.get(i);
                donnees[i][0] = sdf.format(v.getDate());
                donnees[i][1] = v.getPilote();
                donnees[i][2] = v.getPlaneur();
                donnees[i][3] = v.getDuree();
                donnees[i][4] = v.getCout();
            }

            this.jtable.setModel(new DefaultTableModel(donnees, nomsColonnes));
        }

    }
}
