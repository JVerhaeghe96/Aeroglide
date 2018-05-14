package be.helha.calendar;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PanelJours extends JPanel implements ActionListener, ChangeListener{
    private CalendarModel model;
    private JButton buttonSelectionne;
    private JButton[] tabButtons = new JButton[49];
    private final static Color COULEUR_NORMALE = Color.BLACK;
    private final static Color COULEUR_SELECTIONNE = Color.RED;

    public PanelJours(CalendarModel model){
        this.model = model;
        this.model.addChangeListener(this);

        this.setLayout(new GridLayout(0,7));
        this.setBorder(new LineBorder(Color.BLACK, 1, false));

        for(int i = 0; i < tabButtons.length; i++){
            this.tabButtons[i] = new JButton();
            this.add(this.tabButtons[i]);
        }

        this.setNomsJours();
        this.setJours();
    }

    private void setNomsJours(){
        DateFormatSymbols dfs = new DateFormatSymbols(getLocale());
        String[] nomJours = dfs.getWeekdays();

        for(int i = 0; i < 6; i++){
            this.tabButtons[i].setEnabled(false);
            this.tabButtons[i].setText(nomJours[i+2]);
            this.tabButtons[i].setHorizontalAlignment(JLabel.CENTER);
        }
        this.tabButtons[6].setEnabled(false);
        this.tabButtons[6].setText(nomJours[1]);
        this.tabButtons[6].setHorizontalAlignment(JLabel.CENTER);
    }

    private void setJours(){

            //  obtenir le jour de début de mois
        LocalDate debutMois = LocalDate.of(this.model.getYear(), this.model.getMonth(), 1);
        DayOfWeek jourSemaine = debutMois.getDayOfWeek();
        int jourDebutMois = jourSemaine.getValue();

            //  obtenir le jour de fin du mois précédent
        LocalDate finMoisPrec = debutMois.minus(1, ChronoUnit.DAYS);
        int jourFinMoisPrec = finMoisPrec.getDayOfMonth();

        for(int i = 0; i < jourDebutMois-1; i++){
            this.tabButtons[i+7].setText(""+(jourFinMoisPrec-jourDebutMois+i+1));
            this.tabButtons[i+7].setHorizontalAlignment(JLabel.CENTER);
            this.tabButtons[i+7].setEnabled(false);
        }

            //  l'année courante est-elle bissextile ?
        boolean anneeBissextile = debutMois.isLeapYear();
        int nbJoursMois = debutMois.getMonth().length(anneeBissextile);

        for(int i = jourDebutMois; i < nbJoursMois+jourDebutMois; i++){
            this.tabButtons[i+6].setText(""+(i-jourDebutMois+1));
            this.tabButtons[i+6].setHorizontalAlignment(JLabel.CENTER);
            this.tabButtons[i+6].setForeground(COULEUR_NORMALE);
            this.tabButtons[i+6].setEnabled(true);
            this.tabButtons[i+6].addActionListener(this);
        }

        int jourMoisSuiv = 1;

        for(int i = jourDebutMois+nbJoursMois+6; i < tabButtons.length; i++){
            this.tabButtons[i].setText(""+(jourMoisSuiv++));
            this.tabButtons[i].setHorizontalAlignment(JLabel.CENTER);
            this.tabButtons[i].setEnabled(false);
        }

        this.tabButtons[this.model.getDay()+6].setForeground(COULEUR_SELECTIONNE);
        this.buttonSelectionne = this.tabButtons[this.model.getDay()+6];
    }

    @Override
    public void actionPerformed(ActionEvent e){
        this.model.setDay(Integer.parseInt(((JButton)e.getSource()).getActionCommand()));
        this.buttonSelectionne.setForeground(COULEUR_NORMALE);
        this.buttonSelectionne = (JButton) e.getSource();
        this.buttonSelectionne.setForeground(COULEUR_SELECTIONNE);
    }

    @Override
    public void stateChanged(ChangeEvent e){
        this.setJours();
    }
}