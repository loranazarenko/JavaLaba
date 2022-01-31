package com.epam.onlinestore.dao.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* Database connection configuration
*/
@Slf4j
public class DBManager {

    private static DBManager dbManager;
    private static String connectionURL = "";

    public static DBManager getInstance() {

        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public static Connection getConnection(String connectionUrl) {

        dbManager = DBManager.getInstance();
        Connection con = null;
        try {

            con = DriverManager.getConnection(connectionUrl);
            con.setAutoCommit(true);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static Connection getConnection() throws SQLException {
       connectionURL = "jdbc:mysql://localhost:3306/onlineStore?user=root&password=root&useUnicode=true&characterEncoding=cp1251";
       return getConnection(connectionURL);
    }

}
