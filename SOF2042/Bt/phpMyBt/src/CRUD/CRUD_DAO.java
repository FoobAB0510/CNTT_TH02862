/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CRUD_DAO {

    static String connection = "jdbc:sqlserver://localhost:1433;databaseName=test;user=sa;password=Doanvantay@7;trustServerCertificate=true";

    public static Connection getConnect() {
        try {
            return DriverManager.getConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object executeQuery(String query, List<Object> params) {
        try {
            Connection cn = getConnect();
            PreparedStatement st = cn.prepareStatement(query);
            // Truyền các params vào trong statement
            // số lượng dấu ? = số lượng phần tử của params
            for (int i = 0; i < params.size(); i++) { // truyền từng phần tử
                st.setObject(i + 1, params.get(i)); // truyền giá trị
            }
            // Thực thi statement
            if (query.trim().toLowerCase().startsWith("select")) {
                ResultSet rs = st.executeQuery();
                return rs; // trả về resultSet với câu lệnh select
            } else {
                int row = st.executeUpdate(); // chạy và trả về số dòng bị tác động (row affected)
                System.out.println("Bạn đã " + query.split(" ")[0] + " " + row + " bản ghi");
                // query.split(" ") sẽ cắt chuỗi thành 1 mảng theo dấu cách " ", lúc này mỗi phần tử là 1 từ
                // query.split(" ")[0] sẽ chính là loại truy vấn
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
