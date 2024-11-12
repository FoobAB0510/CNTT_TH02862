/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package db;

import java.sql.*;

/**
 *
 * @author Administrator
 */
public class DB {
    public static Connection getConnect() {
        try {
            return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=master;encrypt=true;trustservercertificate=true", "sa", "123");
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
