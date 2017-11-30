package com.sabel.rateDB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RateDB {
    private List<Rate> list;

    public RateDB() {
        this.list = new ArrayList<>();
    }

    public int size() {
        return list.size();
    }

    public boolean add(Rate rate) {
        return list.add(rate);
    }

    public boolean add(long timestamp, double rateEUR, double rateUSD) {
        return add(new Rate(timestamp, rateEUR, rateUSD));
    }

    public Rate getLastRate(){
        return list.get(list.size() - 1);
    }

    public Rate get(int index) {
        return list.get(index);
    }

    public List<Rate> get(long beginTimestamp, long endTimestamp){
        List<Rate> newList = new ArrayList<>();
        for (Rate rate : list) {
            long rateTimestamp = rate.getTimestamp();
            if (rateTimestamp> beginTimestamp &&  rateTimestamp < endTimestamp){
                newList.add(rate);
            }
        }
        return newList;
    }

    // TODO
    public Rate remove(long timestamp) {
        Iterator<Rate> iterator = list.iterator();
        while (iterator.hasNext()){
            Rate nextRate = iterator.next();
            if (nextRate.getTimestamp() == timestamp){
                iterator.remove();
                return nextRate;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Rate DB:");
        sb.append(String.format("%n"));
        for (Rate rate : list) {
            sb.append(rate.toString());
        }
        return sb.toString();
    }
}