package com.sabel.rateDB;

import java.util.ArrayList;
import java.util.List;

public class RateDB {

    private List<Rate> liste;

    public RateDB() {
        this.liste = new ArrayList<>();
    }

    public int size() {
        return liste.size();
    }

    public boolean add(Rate rate) {
        return liste.add(rate);
    }

    public boolean add(long timestamp, double rateEUR, double rateUSD) {
        return liste.add(new Rate(timestamp, rateEUR, rateUSD));
    }

    public Rate getLastRate() {
        return liste.get(liste.size());
    }


    public Rate get(int index) {
        return liste.get(index);
    }

    public List get(long beginTimestamp, long endTimestamp) {
        List<Rate> returnList = new ArrayList<>();
        for (Rate rate: liste) {
            if (beginTimestamp == rate.getTimestamp()){
                do{
                    returnList.add(rate);
                }while(endTimestamp == rate.getTimestamp());
            }
        }
        
        return returnList;
    }

    public Rate remove(long timestamp) {
        Rate rateReturn = null;
        for (Rate rate:liste) {
            if (timestamp == rate.getTimestamp()) {
                rateReturn = rate;
            }
        }
        return rateReturn;
    }


    @Override
    public String toString() {
        String rueckgabe = "";

        rueckgabe += "Zeitstempel" + "RateInEuro\n";

        for (Rate rate: liste) {
            rueckgabe += rate.getTimestamp() + rate.getRateEUR() + "\n";

        }
        return rueckgabe;
    }
}
