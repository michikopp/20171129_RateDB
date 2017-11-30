package com.sabel.rateDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RatesService {

    private Connection connection;
    private PreparedStatement pStatInsert;
    private PreparedStatement pStatSelect;
    private PreparedStatement pStatSelctLastRate;

    public RatesService() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite://D:/Rates.sqlite");
        this.pStatSelect = connection.prepareStatement("SELECT * FROM rate");
        this.pStatInsert = connection.prepareStatement("INSERT INTO rate (timestamp, rateEUR, rateUSD) VALUES(?,?,?)");
//        this.pStatSelctLastRate = connection.prepareStatement("SELECT * FROM rate where ");
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    public void save(Rate rate) throws SQLException {
        pStatInsert.setLong(1, rate.getTimestamp());
        pStatInsert.setDouble(2, rate.getRateEUR());
        pStatInsert.setDouble(3, rate.getRateUSD());
        pStatInsert.executeUpdate();
    }

    public RateDB readAllRates(){
        return null;
    }

    public Rate getLastRate(){

        return null;
    }
}