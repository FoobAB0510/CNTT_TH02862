/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CRUD_DAO {
    static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=TrainEmploy;user=sa;password=123;trustServerCertificate=true";

    public static Connection getConnect() throws Exception {
        return DriverManager.getConnection(connectionUrl);
    }

    public static Object executeQuery(String query, List<Object> params) {
        try {
            Connection conn = getConnect();
            PreparedStatement stm = conn.prepareStatement(query);
            for (int i = 0; i < params.size(); i++) { 
                stm.setObject(i + 1, params.get(i));
            }
            if (query.trim().toLowerCase().startsWith("select")) {
                ResultSet rs = stm.executeQuery();
                return rs;
            } else {
                int row = stm.executeUpdate();
                System.out.println("Bạn đã " + query.split(" ")[0] + " " + row + " bản ghi");
                if(row > 0) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
