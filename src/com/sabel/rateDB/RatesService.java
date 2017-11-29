package com.sabel.rateDB;

import java.sql.*;

public class RatesService {

    private Connection connection;
    private PreparedStatement pStatInsert, pStatSelect;
    private static final String URL = "jdbc:sqlite:d:\\Kopp\\_Privat\\Programmieren\\IdeaProjects\\20171129_RateDB\\rateDB.sqlite";

    public RatesService() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.pStatInsert = connection.prepareStatement("");
        this.pStatSelect = connection.prepareStatement("");

    }

    public void save(Rate rate) throws SQLException {
        String sql = "INSERET INTO rateDB VALUES (" + rate.getTimestamp() + ", "
                + rate.getRateEUR() + ", " + rate.getRateUSD() + ")";
        this.pStatInsert.executeUpdate(sql);
    }


    public void close() throws SQLException {
        if (pStatInsert != null) {
            pStatInsert.close();
        }
        if (pStatSelect != null) {
            pStatSelect.close();
        }
        if (connection != null) {
            connection.close();
        }
    }


    public RateDB readAllRates() throws SQLException {
        RateDB rateDB = new RateDB();
        String sql = "SELECT timtestamp, rateEUR, rateUSD FROM rateDB";
        ResultSet resultSet = this.pStatSelect.executeQuery(sql);
        if (resultSet.next()) {
            rateDB.add(resultSet.getLong(1), resultSet.getDouble(2), resultSet.getDouble(3));
        }



        return rateDB;
    }


    public Rate readLastRate() throws SQLException {
        String sql = "SELECT timtestamp, rateEUR, rateUSD FROM rateDB ORDER BY timestamp DESC LIMIT 1";
        ResultSet resultSet = this.pStatSelect.executeQuery(sql);
        Rate rate = new Rate(resultSet.getLong(1), resultSet.getDouble(2), resultSet.getDouble(3));
        return  rate;
    }
    
}
