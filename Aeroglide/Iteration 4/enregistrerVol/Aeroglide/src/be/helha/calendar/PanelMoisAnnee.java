package be.helha.calendar;

import be.iesca.aeroglide.domaine.Pilote;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PanelMoisAnnee extends JPanel{
    private CalendarModel model;

    public PanelMoisAnnee(CalendarModel model){
        this.model = model;
        this.setBackground(new Color(255, 209, 67));
//        Collection<Pilote> mois = Arrays.asList(
//                );

        ArrayList<Pilote> mois = new ArrayList<>();
        mois.add(new Pilote("Togo", "Lopo", "Hug@gt.com", null, null, null, 0, null, 0));
        mois.add(new Pilote("Lop", "Hugo", "Hug@gt.com", null, null, null, 0, null, 0));

//        Pilote[] pilotes = (Pilote[]) Arrays.asList(mois).toArray();
        Pilote[] pilotes = new Pilote[mois.size()];
        for(int i = 0; i < mois.size(); i++){
            pilotes[i] = mois.get(i);
        }
        JComboBox<Pilote> jcbMonth = new JComboBox<>(pilotes);
        jcbMonth.setEditable(false);
        jcbMonth.setSelectedIndex(0);
        jcbMonth.addActionListener(e->this.model.setMonth((jcbMonth.getSelectedIndex()+1)));
        this.add(jcbMonth);

        JSpinner jsYear = new JSpinner(new SpinnerNumberModel());
        jsYear.setEditor(new JSpinner.NumberEditor(jsYear, "0000"));
        jsYear.setValue(this.model.getYear());
        jsYear.addChangeListener(e->this.model.setYear((Integer)jsYear.getValue()));
        this.add(jsYear);
    }
}
