/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gy.salano.ireport.test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author csullivan
 */
public class ConnectionUtils {
    public static Connection getConnection() throws SQLException,
            ClassNotFoundException {
 
        // Using Oracle
        // You may be replaced by other Database.
        return OracleConnUtils.getOracleConnection();
    }
 
    //
    // Test Connection ...
    //
    public static void main(String[] args) throws SQLException,
            ClassNotFoundException {
 
        System.out.println("Get connection ... ");
 
        // Get a Connection object
        Connection conn = ConnectionUtils.getConnection();
 
        System.out.println("Get connection " + conn);
 
        System.out.println("Done!");
    }
}
