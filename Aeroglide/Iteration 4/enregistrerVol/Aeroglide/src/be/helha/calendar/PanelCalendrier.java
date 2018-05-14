package be.helha.calendar;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;

public class PanelCalendrier extends JPanel{
    private CalendarModel model;

    public PanelCalendrier(CalendarModel model){
        this.model = model;

        PanelMoisAnnee jpMoisAnnee = new PanelMoisAnnee(model);
        PanelJours jpjours = new PanelJours(model);

        this.setLayout(new BorderLayout());
        this.add(jpMoisAnnee, BorderLayout.NORTH);
        this.add(jpjours, BorderLayout.CENTER);
        this.setBorder(new LineBorder(Color.BLACK, 1, false));
    }

    public void setModel(CalendarModel model){
        if(model == null)
            throw new NullPointerException("No model found.");
        this.model = model;
    }
}
