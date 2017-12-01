package com.sabel.rateDB;

import java.sql.*;


public class RatesService {

    private Connection connection;
    private PreparedStatement pStatInsert, pStatSelect, pStatSelectLast;
    private static final String URL = "jdbc:sqlite:d:\\rateDB.sqlite";

    public RatesService() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.pStatInsert = connection.prepareStatement("INSERT INTO rate (tstamp, rateEUR, rateUSD)VALUES (?,?,?)");
        this.pStatSelect = connection.prepareStatement("Select tstamp, rateEUR, rateUSD from rate ORDER BY tstamp");
        this.pStatSelectLast = connection.prepareStatement("Select tstamp, rateEUR, rateUSD from rate ORDER BY tstamp DESC LIMIT 1");
    }

    public void save(Rate rate) throws SQLException {
        pStatInsert.setLong(1, rate.getTimestamp());
        pStatInsert.setDouble(2, rate.getRateEUR());
        pStatInsert.setDouble(3, rate.getRateUSD());
        pStatInsert.executeUpdate();
    }


    public void close() throws SQLException {
//        if (pStatInsert != null) {
//            pStatInsert.close();
//        }
//        if (pStatSelect != null) {
//            pStatSelect.close();
//        }
        if (connection != null) {
            connection.close();
        }
        connection = null;
    }


    public RateDB readAllRates() throws SQLException {
        RateDB rateDB = new RateDB();
        ResultSet resultSet = pStatSelect.executeQuery();

        while (resultSet.next()){
            Rate rate = new Rate();
            rate.setTimestamp(resultSet.getLong(1));
            rate.setRateEUR(resultSet.getDouble(2));
            rate.setRateUSD(resultSet.getDouble(3));
            rateDB.add(rate);
        }
        return rateDB;
    }


    public Rate readLastRate() throws SQLException {
        ResultSet resultSet = pStatSelectLast.executeQuery();
        Rate rate = null;
        if (resultSet.next()){
            rate = new Rate();
            rate.setTimestamp(resultSet.getLong(1));
            rate.setRateEUR(resultSet.getDouble(2));
            rate.setRateUSD(resultSet.getDouble(3));
        }
        return  rate;
    }

}