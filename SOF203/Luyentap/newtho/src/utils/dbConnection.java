/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.*;

/**
 *
 * @author Administrator
 */
public class dbConnection {    
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=master;encrypt=true;trustservercertificate=true", "sa", "123"
            );
        }
        catch (Exception e) {
            System.out.print(e);
        }
        return null;
    }
}