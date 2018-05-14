package be.helha.calendar;

import javax.swing.event.*;
import java.time.LocalDate;
import java.util.*;

public class CalendarModel {
    private int day;
    private int month;
    private int year;
    private Collection<ChangeListener> listVues;

    public CalendarModel(){
        LocalDate date = LocalDate.now();
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();

        this.listVues = new ArrayList<>();
    }

    public void setDay(int day){
        this.day = day;
        this.traiterEvent(new ChangeEvent(this));
    }

    public int getDay(){
        return this.day;
    }

    public void setMonth(int month){
        this.month = month;
        this.traiterEvent(new ChangeEvent(this));
    }

    public int getMonth(){
        return this.month;
    }

    public void setYear(int year){
        this.year = year;
        this.traiterEvent(new ChangeEvent(this));
    }

    public int getYear(){
        return this.year;
    }

    public String getDate(){
        return this.getDay() + "/" + this.getMonth() + "/" + this.getYear();
    }

        //  Ajoute un listener
    public synchronized void addChangeListener(ChangeListener cl){
        if(!this.listVues.contains(cl))
            this.listVues.add(cl);
    }

        //  Retire un listener
    public synchronized void removeChangeListener(ChangeListener cl){
        if(this.listVues.contains(cl))
            this.listVues.remove(cl);
    }

        //  Notifie les listener
    public synchronized void traiterEvent(ChangeEvent e){
        for(ChangeListener listener : listVues){
            listener.stateChanged(e);
        }
    }
}
