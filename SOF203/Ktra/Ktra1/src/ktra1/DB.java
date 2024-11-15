/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ktra1;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Administrator
 */
public class DB {
    public static Connection getConnect() {
        try {
            return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ktra1;encrypt=true;trustservercertificate=true", "sa", "123");
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
