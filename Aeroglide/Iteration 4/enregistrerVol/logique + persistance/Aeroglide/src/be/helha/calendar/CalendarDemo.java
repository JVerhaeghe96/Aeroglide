package be.helha.calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalendarDemo extends JFrame implements ActionListener{
    private JButton jbAfficheDialog;
    private JLabel jlDate;
    private CalendarModel model;

    public CalendarDemo(String s){
        super(s);
        this.setLayout(new FlowLayout());

        this.model = new CalendarModel();

        this.jbAfficheDialog = new JButton("Afficher le calendrier");
        this.jbAfficheDialog.addActionListener(this);
        this.add(this.jbAfficheDialog);

        this.jlDate = new JLabel(model.getDay() + "/" + model.getMonth() + "/" + model.getYear());
        this.add(this.jlDate);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        PanelCalendrier panelCalendrier = new PanelCalendrier(this.model);
        int reponse = JOptionPane.showConfirmDialog(
                this, panelCalendrier,
                "Calendrier ",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null);
        if ( reponse == JOptionPane.CANCEL_OPTION ) return;
        String date = this.model.getDate();
        this.jlDate.setText(date);
    }

    public static void main(String[] args){
        new CalendarDemo("CalendarDemo");
    }
}
